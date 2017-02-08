package com.vijay.web.core.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseShards {
	@JsonProperty(value = "total")
	private int total;

	@JsonProperty(value = "successful")
	private int successful;

	@JsonProperty(value = "failed")
	private int failed;

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getSuccessful() {
		return successful;
	}

	public void setSuccessful(int successful) {
		this.successful = successful;
	}

	public int getFailed() {
		return failed;
	}

	public void setFailed(int failed) {
		this.failed = failed;
	}

}
