package com.harvey.Util;

public class NoEntityException extends Throwable{

	public NoEntityException(){}
	
	public NoEntityException(String errMessage){
		super(errMessage);
	}
}
