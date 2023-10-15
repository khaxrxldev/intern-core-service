package com.intern.core.service.dto;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Response {

	@JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime time_stamp;
	
	private int status_code;
	
	private HttpStatus status_desc;
	
	private String error_desc;
	
	private Boolean message_status;
	
	private String message_desc;
	
	private String message_code;
	
	private String message_dev;
	
	private Map<?, ?> data;

	public LocalDateTime getTime_stamp() {
		return time_stamp;
	}

	public void setTime_stamp(LocalDateTime time_stamp) {
		this.time_stamp = time_stamp;
	}

	public int getStatus_code() {
		return status_code;
	}

	public void setStatus_code(int status_code) {
		this.status_code = status_code;
	}

	public HttpStatus getStatus_desc() {
		return status_desc;
	}

	public void setStatus_desc(HttpStatus status_desc) {
		this.status_desc = status_desc;
	}

	public String getError_desc() {
		return error_desc;
	}

	public void setError_desc(String error_desc) {
		this.error_desc = error_desc;
	}

	public Boolean getMessage_status() {
		return message_status;
	}

	public void setMessage_status(Boolean message_status) {
		this.message_status = message_status;
	}

	public String getMessage_desc() {
		return message_desc;
	}

	public void setMessage_desc(String message_desc) {
		this.message_desc = message_desc;
	}

	public String getMessage_code() {
		return message_code;
	}

	public void setMessage_code(String message_code) {
		this.message_code = message_code;
	}

	public String getMessage_dev() {
		return message_dev;
	}

	public void setMessage_dev(String message_dev) {
		this.message_dev = message_dev;
	}

	public Map<?, ?> getData() {
		return data;
	}

	public void setData(Map<?, ?> data) {
		this.data = data;
	}
}
