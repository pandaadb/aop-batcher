package com.pdb.aopbatcher.types;

import com.pdb.aopbatcher.ResultAggregator;

public interface AggregatorFactory {
	public ResultAggregator provide(Class<?> clazz);
}
