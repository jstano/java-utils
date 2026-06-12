package com.stano.clone;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class CloneServices {
  private static Cloner cloner;

  public <T> T deepClone(final T object) {
    return performDeepClone(object);
  }

  public <T> T shallowClone(final T object) {
    return performShallowClone(object);
  }

  private static <T> T performDeepClone(final T o) {
    return getCloner().deepClone(o);
  }

  private static <T> T performShallowClone(final T o) {
    return getCloner().shallowClone(o);
  }

  private static Cloner getCloner() {
    if (cloner == null) {
      cloner = new Cloner();
      cloner.registerImmutable(LocalDate.class);
      cloner.registerImmutable(LocalTime.class);
      cloner.registerImmutable(LocalDateTime.class);
    }

    return cloner;
  }
}
