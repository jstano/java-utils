package com.stano.clone;

import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URI;
import java.net.URL;
import java.util.Collections;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

/**
 * Cloner: deep clone objects.
 * <p/>
 * This class is thread safe. One instance can be used by multiple threads on the same time.
 *
 * @author kostantinos.kougios
 * <p/>
 * 18 Sep 2008
 */
public class Cloner {
  private final Objenesis objenesis;
  private final Set<Class<?>> ignored = new HashSet<Class<?>>();
  private final Map<Object, Boolean> ignoredInstances = new IdentityHashMap<Object, Boolean>();
  private final ConcurrentHashMap<Class<?>, List<Field>> fieldsCache = new ConcurrentHashMap<Class<?>, List<Field>>();
  private boolean dumpClonedClasses = false;

  public Cloner() {
    objenesis = new ObjenesisStd();
    init();
  }

  private void init() {
    registerKnownJdkImmutableClasses();
    registeredKnownConstants();
  }

  public Cloner(final Objenesis objenesis) {
    this.objenesis = objenesis;
    init();
  }

  public void registerConstant(final Object o) {
    ignoredInstances.put(o, true);
  }

  public void registerConstant(final Class<?> c, final String privateFieldName) {
    try {
      final Field field = c.getDeclaredField(privateFieldName);
      field.setAccessible(true);
      final Object v = field.get(null);
      ignoredInstances.put(v, true);
    }
    catch (final SecurityException | NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * registers some known JDK immutable classes. Override this to register your
   * own list of jdk's immutable classes
   */
  protected void registerKnownJdkImmutableClasses() {
    registerImmutable(String.class);
    registerImmutable(Integer.class);
    registerImmutable(Long.class);
    registerImmutable(Boolean.class);
    registerImmutable(Class.class);
    registerImmutable(Float.class);
    registerImmutable(Double.class);
    registerImmutable(Character.class);
    registerImmutable(Byte.class);
    registerImmutable(Short.class);
    registerImmutable(Void.class);

    registerImmutable(BigDecimal.class);
    registerImmutable(BigInteger.class);
    registerImmutable(URI.class);
    registerImmutable(URL.class);
    registerImmutable(UUID.class);
    registerImmutable(Pattern.class);
  }

  protected void registeredKnownConstants() {
    registerConstant(TreeSet.class, "PRESENT");
    registerConstant(HashSet.class, "PRESENT");
  }

  /**
   * instances of classes that shouldn't be cloned can be registered using this method.
   *
   * @param c The class that shouldn't be cloned. That is, whenever a deep clone for
   *          an object is created and c is encountered, the object instance of c will
   *          be added to the clone.
   */
  public void dontClone(final Class<?>... c) {
    Collections.addAll(ignored, c);
  }

  /**
   * registers an immutable class. Immutable classes are not cloned.
   *
   * @param c the immutable class
   */
  public void registerImmutable(final Class<?>... c) {
    Collections.addAll(ignored, c);
  }

  /**
   * creates a new instance of c. Override to provide your own implementation
   *
   * @param <T> the type of c
   * @param c   the class
   * @return a new instance of c
   */
  @SuppressWarnings("unchecked")
  protected <T> T newInstance(final Class<T> c) {
    return (T)objenesis.newInstance(c);
  }

  /**
   * deep clones "o".
   *
   * @param <T> the type of "o"
   * @param o   the object to be deep-cloned
   * @return a deep-clone of "o".
   */
  public <T> T deepClone(final T o) {
    if (o == null) {
      return null;
    }
    if (dumpClonedClasses) {
      System.out.println("start>" + o.getClass());
    }
    final Map<Object, Object> clones = new IdentityHashMap<Object, Object>();
    try {
      return clone(o, clones);
    }
    catch (final IllegalAccessException e) {
      throw new CloningException("error during cloning of " + o, e);
    }
  }

  /**
   * shallow clones "o". This means that if c=shallowClone(o) then
   * c!=o. Any change to c won't affect o.
   *
   * @param <T> the type of o
   * @param o   the object to be shallow-cloned
   * @return a shallow clone of "o"
   */
  public <T> T shallowClone(final T o) {
    if (o == null) {
      return null;
    }
    try {
      return clone(o, null);
    }
    catch (final IllegalAccessException e) {
      throw new CloningException("error during cloning of " + o, e);
    }
  }

  @SuppressWarnings("unchecked")
  private <T> T clone(final T o, final Map<Object, Object> clones) throws IllegalAccessException {
    if (o == null) {
      return null;
    }
    if (ignoredInstances.containsKey(o)) {
      return o;
    }

    final Class<T> clz = (Class<T>)o.getClass();
    if (clz.isEnum()) {
      return o;
    }
    // skip cloning ignored classes
    if (ignored.contains(clz)) {
      return o;
    }
    final Object clonedPreviously = clones != null ? clones.get(o) : null;
    if (clonedPreviously != null) {
      return (T)clonedPreviously;
    }

    if (clz.isArray()) {
      final int length = Array.getLength(o);
      final T newInstance = (T)Array.newInstance(clz.getComponentType(), length);
      clones.put(o, newInstance);
      for (int i = 0; i < length; i++) {
        final Object v = Array.get(o, i);
        final Object clone = clones != null ? clone(v, clones) : v;
        Array.set(newInstance, i, clone);
      }
      return newInstance;
    }
    if (dumpClonedClasses) {
      System.out.println("clone>" + o.getClass());
    }

    final T newInstance = newInstance(clz);
    if (clones != null) {
      clones.put(o, newInstance);
    }
    final List<Field> fields = allFields(clz);
    for (final Field field : fields) {
      if (!Modifier.isStatic(field.getModifiers())) {
        if (dumpClonedClasses) {
          System.out.println("cloning field>" + field);
        }
        field.setAccessible(true);
        final Object fieldObject = field.get(o);
        final Object fieldObjectClone = clones != null ? clone(fieldObject, clones) : fieldObject;
        field.set(newInstance, fieldObjectClone);
      }
    }
    return newInstance;
  }

  /**
   * reflection utils
   */
  private void addAll(final List<Field> l, final Field[] fields) {
    Collections.addAll(l, fields);
  }

  /**
   * reflection utils
   */
  private List<Field> allFields(final Class<?> c) {
    List<Field> l = fieldsCache.get(c);

    if (l == null) {
      l = new LinkedList<Field>();
      final Field[] fields = c.getDeclaredFields();
      addAll(l, fields);
      Class<?> sc = c;
      while ((sc = sc.getSuperclass()) != Object.class && sc != null) {
        addAll(l, sc.getDeclaredFields());
      }
      fieldsCache.putIfAbsent(c, l);
    }

    return l;
  }

  public boolean isDumpClonedClasses() {
    return dumpClonedClasses;
  }

  /**
   * will println() all cloned classes. Useful for debugging only.
   *
   * @param dumpClonedClasses true to enable printing all cloned classes
   */
  public void setDumpClonedClasses(final boolean dumpClonedClasses) {
    this.dumpClonedClasses = dumpClonedClasses;
  }
}
