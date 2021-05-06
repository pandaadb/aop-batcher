package com.pdb.aopbatcher.types;

import com.pdb.aopbatcher.ListResultAggregator;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class ListTypeProvider implements TypeProvider<List, ListResultAggregator> {

  @Override
  public Class<List> type() {
    return List.class;
  }

  @Override
  public ListResultAggregator provide() {
    return new ListResultAggregator(); // we control construction now 
  }
}
