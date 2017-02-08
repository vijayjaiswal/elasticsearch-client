package com.bazaarify.web.insight;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.sniff.ElasticsearchHostsSniffer;
import org.elasticsearch.client.sniff.HostsSniffer;
import org.elasticsearch.client.sniff.Sniffer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bazaarify.web.core.listener.LoggingFailureListener;

@Service("elasticSearchClientService")
public class ElasticSearchClientServiceImpl implements ElasticSearchClientService {
	/*
	 * private RestClient client = RestClient .builder(new HttpHost("localhost",
	 * 9200, "http"), new HttpHost("localhost", 9201, "http")).build();
	 */
	private RestClient restClient;
	private Sniffer sniffer;

	//LoggingFailureListener loggingFailureListener=new LoggingFailureListener();
	@Autowired
	LoggingFailureListener loggingFailureListener;

	@PostConstruct
	public void afterCreation() {
		this.restClient = RestClient.builder(new HttpHost("localhost", 9200)).setFailureListener(loggingFailureListener).build();
		//this.restClient = RestClient.builder(new HttpHost("localhost", 9200)).build();

		HostsSniffer hostsSniffer = new ElasticsearchHostsSniffer(restClient,
				ElasticsearchHostsSniffer.DEFAULT_SNIFF_REQUEST_TIMEOUT, ElasticsearchHostsSniffer.Scheme.HTTP);
		this.sniffer = Sniffer.builder(restClient).setHostsSniffer(hostsSniffer).build();
	}

	@PreDestroy
	public void preDestroy() throws IOException {
		if (this.sniffer != null) {
			this.sniffer.close();
		}
		if (this.restClient != null) {
			this.restClient.close();
		}

	}

	public RestClient getRestClient() {
		return restClient;
	}

	public void setRestClient(RestClient restClient) {
		this.restClient = restClient;
	}

}
