package com.pdb.aopbatcher.types;

import com.pdb.aopbatcher.MapResultAggregator;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class MapTypeProvider implements TypeProvider<Map, MapResultAggregator>{

  @Override
  public Class<Map> type() {
	  return Map.class;
  }

  @Override
  public MapResultAggregator provide() {
    return new MapResultAggregator();
  }}
