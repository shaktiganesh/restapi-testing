package com.test;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.hasItems;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.apache.http.HttpStatus;

import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;
import cucumber.table.DataTable;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class StepDefinitions {

	Response response;
	RequestSpecification rspec;

	@Given("^the user makes a call to the all country service$")
	public void the_user_makes_a_call_to_the_all_country_service() throws URISyntaxException {
		baseURI = "http://services.groupkt.com";
		basePath = "/country/get/";
		response = when().get(new URI("all"));
	}

	@When("^a user searches the countries by code$")
	public void a_user_searches_the_countries_by_code(DataTable table) {
		List<List<String>> data = table.raw();
		response.then().body("RestResponse.result.alpha2_code", hasItems(data.get(1).get(1), data.get(2).get(1), data.get(3).get(1)));
	}

	@Then("^the status code is Http Ok$")
	public void the_status_code_is_Http_Ok() {
		response.then().assertThat().statusCode(HttpStatus.SC_OK);
	}

}
