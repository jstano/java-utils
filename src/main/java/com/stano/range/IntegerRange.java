package com.stano.range;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Objects;

public final class IntegerRange implements Serializable, Cloneable, Comparable<IntegerRange>, Iterable<Integer> {
  private final int start;
  private final int end;

  public static IntegerRange of(int start, int end) {
    return new IntegerRange(start, end);
  }

  public int getStart() {
    return start;
  }

  public int getEnd() {
    return end;
  }

  public int getSize() {
    return end - start + 1;
  }

  public boolean containsValue(int value) {
    return value >= start && value <= end;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    IntegerRange other = (IntegerRange)o;
    return start == other.start && end == other.end;
  }

  @Override
  public int hashCode() {
    return Objects.hash(start, end);
  }

  @Override
  public String toString() {
    return String.format("%d - %d", start, end);
  }

  @Override
  public int compareTo(IntegerRange other) {
    return Integer.compare(start, other.start);
  }

  @Override
  public Iterator<Integer> iterator() {
    return new IntegerRangeIterator(this);
  }

  public boolean overlapsWith(IntegerRange other) {
    return this.end >= other.start && this.start <= other.end;
  }

  @Override
  protected IntegerRange clone() throws CloneNotSupportedException {
    return this;
  }

  private IntegerRange(int start, int end) {
    this.start = start;
    this.end = end;
  }
}
