package com.pdb.aopbatcher;

import java.util.ArrayList;
import java.util.List;

public class ListResultAggregator implements ResultAggregator {
  private List<Object> entries = new ArrayList<>();

  public void add(Object toAggregate) {
	  entries.addAll( (List<Object>) toAggregate);
  }

  public Object getResult() {
	  return entries;
  }
}
