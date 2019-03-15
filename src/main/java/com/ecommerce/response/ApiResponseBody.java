package com.ecommerce.response;

import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;

@Data
public class ApiResponseBody {

	private HttpStatus status;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private final LocalDateTime timestamp;

	@JsonInclude(value = Include.NON_NULL)
	private Object data;
	private String message;
	private boolean success;

	public ApiResponseBody() {
		timestamp = LocalDateTime.now();
	}

	public ApiResponseBody(final HttpStatus status) {
		this();
		this.status = status;
	}

	ApiResponseBody(final HttpStatus status, final Throwable ex) {
		this();
		this.status = status;
		this.message = "Unexpected error";

	}

	ApiResponseBody(final HttpStatus status, final String message, final Throwable ex) {
		this();
		this.status = status;
		this.message = message;

	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(final HttpStatus status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(final String message) {
		this.message = message;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
