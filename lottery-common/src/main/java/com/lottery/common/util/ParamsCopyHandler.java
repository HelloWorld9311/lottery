package com.lottery.common.util;

public interface ParamsCopyHandler<O,N>{
	
	N handle(O obj) throws Exception;
}