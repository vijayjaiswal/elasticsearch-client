package com.vijay.web.core.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseHits {
	@JsonProperty(value = "hits")
	private Hits hits;
	
	@JsonProperty(value = "took")
	private int took;

	@JsonProperty(value = "timed_out")
	private boolean timedOut;

	@JsonProperty(value = "_shards")
	private ResponseShards shards;

	public int getTook() {
		return took;
	}

	public void setTook(int took) {
		this.took = took;
	}

	public boolean isTimedOut() {
		return timedOut;
	}

	public void setTimedOut(boolean timedOut) {
		this.timedOut = timedOut;
	}

	public ResponseShards getShards() {
		return shards;
	}

	public void setShards(ResponseShards shards) {
		this.shards = shards;
	}

	public Hits getHits() {
		return hits;
	}

	public void setHits(Hits hits) {
		this.hits = hits;
	}

}
