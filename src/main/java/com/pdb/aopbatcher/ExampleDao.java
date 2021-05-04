package com.pdb.aopbatcher;

import java.util.List;
import java.util.Map;

public interface ExampleDao {
	
	List<String> grabString(List<Integer> ids);
	
	Map<Integer,String> grabStringMap(final String name, @BatchInput List<String> ids);
	
}
