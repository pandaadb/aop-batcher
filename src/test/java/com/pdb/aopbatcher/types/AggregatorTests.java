package com.pdb.aopbatcher.types;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AggregatorTests {
  @Autowired AggregatorFactory factory;
  
  
  @Test
  public void testProvide() {
	  assertNotNull(factory.provide(List.class));
	  assertNotNull(factory.provide(Map.class));
  }
}
