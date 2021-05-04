package com.pdb.aopbatcher;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;

@Repository
public class ExampleDaoImpl implements ExampleDao {

  private Map<Integer, String> data = new HashMap<Integer, String>();

  public ExampleDaoImpl() {
    for (int i = 0; i < 1000; i++) {
      data.put(i, UUID.randomUUID().toString());
    }
  }

  @Batch
  @Override
  public List<String> grabString(List<Integer> ids) {

    System.err.println("Grab String with list size " + ids.size());

    return data.entrySet()
        .stream()
        .filter(e -> ids.contains(e.getKey()))
        .map(e -> e.getValue())
        .collect(Collectors.toList());
  }

  @Batch
  @Override
  public Map<Integer, String> grabStringMap(String name, @BatchInput List<String> ids) {
    System.err.println("Grab String map with list size " + ids.size());

    return data.entrySet()
        .stream()
        .filter(e -> ids.contains(String.valueOf(e.getKey())))
        .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()));
  }
}
