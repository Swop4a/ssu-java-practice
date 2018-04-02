package com.swop4a.gd.api.rest.model;

import lombok.Data;

@Data
public class ErrorResponse {

	private String message;
	private StackTraceElement[] stackTrace;
}
