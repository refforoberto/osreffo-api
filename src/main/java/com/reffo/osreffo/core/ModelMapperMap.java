package com.reffo.osreffo.core;

import java.util.List;

public abstract interface ModelMapperMap {
	
	
  abstract <I> I toEntity(Object input);
  
  abstract <I> List<I> toCollectionModel(List<Object> osList);
	

	
	
	

}
