package com.abc.customerservice.exception;

@SuppressWarnings("serial")
public class ResourceNotFoundException extends RuntimeException  {

	public ResourceNotFoundException(String msg) {
		super(msg);
	} 

}
