Feature: Validating Place API's

@addPlace @regression
Scenario Outline: Verify if Place is being successfuly added using AddPlaceAPI
	Given Add Place Payload with "<name>" "<language>" "<address>"
	When User calls "addPlaceApi" with "post" http request
	Then The API call got success with status code 200
	And "status" in response body is "OK"
	And "scope" in response body is "APP"
	And verify place_id created maps to "<name>" using "getPlaceApi"

Examples:
	|name		|language	|address						|
	|AAhouse|English	|World cross center	|
#	|BBhouse|Spanish	|Sea corss center		|

@deletePlace @regression
Scenario: Verify if place is being successfully deleted using deletePlaceApi
	Given Delete place payload
	When User calls "deletePlaceApi" with "delete" http request
	Then The API call got success with status code 200
	And "status" in response body is "OK"
	