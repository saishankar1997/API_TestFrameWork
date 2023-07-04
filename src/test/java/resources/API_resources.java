package resources;

public enum API_resources {
	addPlaceApi("maps/api/place/add/json"),
	deletePlaceApi("maps/api/place/delete/json"),
	updatePlaceApi("maps/api/place/update/json"),
	getPlaceApi("maps/api/place/get/json");
	
	private String resource;
	private API_resources(String resource) {
		this.resource = resource;
	}
	
	public String getResource() {
		return resource;
	}
}
