package com.api.exception;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

public class ErrorResponse {
	
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	private LocalDateTime timestamp;
	
	@JsonProperty("error")
	private List<String> errors;

	
	public ErrorResponse(List<String> errors) {
		this.timestamp = LocalDateTime.now();
		this.errors = errors;
	}
}
