package StepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	@Before("@deletePlace")
	public void beforeScenario() throws IOException {
		
		StepDefinition stepDef = new StepDefinition();
		
		if(StepDefinition.place_id==null) {
			stepDef.add_place_payload_with("J Palace", "English", "India");
			stepDef.user_calls_with_http_request("addPlaceApi", "post");
			stepDef.verify_place_id_created_maps_to_using("J Palace", "getPlaceApi");
		}
	}
}
