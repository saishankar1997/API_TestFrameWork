package StepDefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.API_resources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefinition extends Utils{
	RequestSpecification requestSpec;
	ResponseSpecification resSpec;
	Response response;
	TestDataBuild data = new TestDataBuild();
	static String place_id;
	
	@Given("Add Place Payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address) throws IOException{
		//set common initiation
		requestSpec = given().spec(requestSpecification()).body(data.addPlacePayload(name, language, address));
	}
	
	@When("User calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String method) {
		API_resources apiResources = API_resources.valueOf(resource);
		
		//set a common response
		resSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		if(method.equalsIgnoreCase("post")) {
			response = requestSpec.when().post(apiResources.getResource());
		} else if(method.equalsIgnoreCase("get")) {
			response = requestSpec.when().get(apiResources.getResource());
		} else if(method.equalsIgnoreCase("delete")) {
			response = requestSpec.when().delete(apiResources.getResource());
		}
				
	}
	
	@Then("The API call got success with status code {int}")
	public void the_api_call_got_success_with_status_code(Integer int1) {
	    assertEquals(response.getStatusCode(),200);
	}
	
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String expectedValue) {
		assertEquals(getJsonPath(response, keyValue),expectedValue);
	}
	
	@Then("verify place_id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException {
	   //Set req spec
		place_id = getJsonPath(response, "place_id");
		requestSpec = given().spec(requestSpecification()).queryParam("place_id", place_id);
		user_calls_with_http_request(resource, "GET");
		String actualName = getJsonPath(response, "name");
		assertEquals(actualName, expectedName);
	}
	
	@Given("Delete place payload")
	public void delete_place_payload() throws IOException {
	   requestSpec = given().spec(requestSpecification()).body(data.deletePlacePayload(place_id));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
