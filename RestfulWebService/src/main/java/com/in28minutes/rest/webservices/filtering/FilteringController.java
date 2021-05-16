package com.in28minutes.rest.webservices.filtering;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FilteringController {
	@GetMapping("/filtering")//will not send value3 in json response due to filtering
	public SomeBean retrieveSomeBean() {
		return new SomeBean("value1","value2","value3");
	}

	@GetMapping("/filtering-list")//will not send value3 and value32 in json response of list of beans
	public List<SomeBean> retrieveListOfSomeBean() {
		return Arrays.asList(new SomeBean("value1","value2","value3"),new SomeBean("value12","value22","value32"));
	}
}
