package com.pdb.aopbatcher.types;

import com.pdb.aopbatcher.ResultAggregator;

public interface TypeProvider<T, X extends ResultAggregator> {

  Class<T> type();

  X provide();
}
