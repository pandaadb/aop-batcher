package com.pdb.aopbatcher;

public interface ResultAggregator {
	public void add(final Object toAggregate);
	public Object getResult();
}
