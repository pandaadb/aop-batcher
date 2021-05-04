package com.pdb.aopbatcher;

import java.util.HashMap;
import java.util.Map;

public class MapResultAggregator implements ResultAggregator {
  private Map<Object, Object> entries = new HashMap<>();

  public void add(Object toAggregate) {
    Map<Object, Object> map = (Map<Object, Object>) toAggregate;
    entries.putAll(map);
  }

  public Object getResult() {
    return entries;
  }
}
