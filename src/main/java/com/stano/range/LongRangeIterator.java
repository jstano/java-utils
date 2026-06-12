package com.stano.range;

import java.util.Iterator;

public final class LongRangeIterator implements Iterator<Long> {
  private final Long lastValue;

  private Long nextValue;

  public LongRangeIterator(LongRange range) {
    this.lastValue = range.getEnd();
    this.nextValue = range.getStart();
  }

  @Override
  public boolean hasNext() {
    return nextValue != null;
  }

  @Override
  public Long next() {
    Long result = nextValue;

    nextValue++;

    if (nextValue > lastValue) {
      nextValue = null;
    }

    return result;
  }

  @Override
  public void remove() {
    throw new UnsupportedOperationException("The remove method is not supported by LongRangeIterator.");
  }
}
