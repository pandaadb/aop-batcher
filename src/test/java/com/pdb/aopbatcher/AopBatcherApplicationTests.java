package com.pdb.aopbatcher;

import java.util.Arrays;
import java.util.Map.Entry;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@SpringBootTest
class AopBatcherApplicationTests {

  @Test
  void contextLoads() {}

  @Autowired ExampleDao dao;

  @Test
  public void testBatch() {
    var res = dao.grabString(Arrays.asList(1, 2, 3, 4, 5, 6, 7,8,9));

    for (String s : res) {
      System.err.println(s);
    }
  }

  @Test
  public void testBatch2() {
    var res = dao.grabStringMap("test", Arrays.asList("1", "2", "3", "4", "5", "6", "7"));

    for (Entry<Integer, String> e : res.entrySet()) {
      System.err.println(e);
    }
  }
}
