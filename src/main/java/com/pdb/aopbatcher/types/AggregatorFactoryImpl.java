package com.pdb.aopbatcher.types;

import com.pdb.aopbatcher.ResultAggregator;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AggregatorFactoryImpl implements AggregatorFactory {

  private Map<Class<?>, TypeProvider<?, ? extends ResultAggregator>> collect;

  @Autowired
  public AggregatorFactoryImpl(Set<TypeProvider<?, ? extends ResultAggregator>> providers) {
    collect = providers.stream().collect(Collectors.toMap(x -> x.type(), x -> x));
  }

  @Override
  public ResultAggregator provide(Class<?> clazz) {
    return collect.get(clazz).provide();
  }
}
