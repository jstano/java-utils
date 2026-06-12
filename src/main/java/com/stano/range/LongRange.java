package com.stano.range;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Objects;

public final class LongRange implements Serializable, Cloneable, Comparable<LongRange>, Iterable<Long> {
  private final long start;
  private final long end;

  public static LongRange of(long start, long end) {
    return new LongRange(start, end);
  }

  public long getStart() {
    return start;
  }

  public long getEnd() {
    return end;
  }

  public long getSize() {
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
    LongRange other = (LongRange)o;
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
  public int compareTo(LongRange other) {
    return Long.compare(start, other.start);
  }

  @Override
  public Iterator<Long> iterator() {
    return new LongRangeIterator(this);
  }

  public boolean overlapsWith(LongRange other) {
    return this.end >= other.start && this.start <= other.end;
  }

  @Override
  protected LongRange clone() throws CloneNotSupportedException {
    return this;
  }

  private LongRange(long start, long end) {
    this.start = start;
    this.end = end;
  }
}
