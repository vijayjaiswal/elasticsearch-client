package com.vijay.web.core.listener;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient.FailureListener;
import org.springframework.stereotype.Component;

@Component
public class LoggingFailureListener extends FailureListener {

	@Override
	public void onFailure(HttpHost host) {
		System.out.println("Host " + host + " failed to sniff:");
		super.onFailure(host);
	}
}
