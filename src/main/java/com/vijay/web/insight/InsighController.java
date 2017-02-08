package com.vijay.web.insight;

import java.io.IOException;
import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import org.apache.http.HttpEntity;
import org.apache.http.entity.StringEntity;
import org.elasticsearch.client.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vijay.web.core.entities.Hit;
import com.vijay.web.core.entities.ResponseHits;
import com.vijay.web.entities.Employee;
import com.vijay.web.entities.Greeting;

@RestController
public class InsighController {
	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	@Autowired
	ElasticSearchClientServiceImpl elasticSearchClientService;

	@RequestMapping("/greeting")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}

	@RequestMapping("/employees")
	public List<Employee> getEmployees() throws IOException {

		/*
		 * RestClient restClient = RestClient .builder(new HttpHost("localhost",
		 * 9200, "http"), new HttpHost("localhost", 9201, "http")).build();
		 */

		String query = "{\"query\":{\"match_all\":{}}}";

		Response response = elasticSearchClientService.getRestClient().performRequest("GET", "/customer/_search",
				new Hashtable<>(), new StringEntity(query));

		HttpEntity entity = response.getEntity();
		ObjectMapper mapper = new ObjectMapper();

		ResponseHits responseHits = mapper.readValue(entity.getContent(), ResponseHits.class);

		String str = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(responseHits);
		System.out.println("***********Response***********\n" + str);
		// EntityUtils.toString(responseHits.getEntity());
		return responseHits.getHits().getHits().stream().map(Hit::getSource).collect(Collectors.toList());
	}
}
