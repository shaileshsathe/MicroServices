package com.in28minutes.rest.webservices.filtering;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value= {"field1","field3"})//to define multiple props to be filtered, but its hardcoding pros name, JsonIgnore is better
public class SomeBean {

	private String field1;
	
	//This is static filtering using @JsonIgnore and @JsonIgnoreProperties (if you want one field in one scenario and another field in another scenario to be filtered then you can't do it with this approach)
	//@JsonIgnore //you can ignore/filter as many attributes as you can, another way is to use @JsonIgnoreProperties
	private String field2;
	
	//@JsonIgnore//to ignore property in the bean to be sent in the jspn response
	private String field3;
	public SomeBean(String field1, String field2, String field3) {
		super();
		this.field1 = field1;
		this.field2 = field2;
		this.field3 = field3;
	}
	public String getField1() {
		return field1;
	}
	public void setField1(String field1) {
		this.field1 = field1;
	}
	public String getField2() {
		return field2;
	}
	public void setField2(String field2) {
		this.field2 = field2;
	}
	public String getField3() {
		return field3;
	}
	public void setField3(String field3) {
		this.field3 = field3;
	}
	
}
