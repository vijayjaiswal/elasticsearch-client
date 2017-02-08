package com.bazaarify.web.insight;

import org.elasticsearch.client.RestClient;

public interface ElasticSearchClientService {
	public RestClient getRestClient();
}
