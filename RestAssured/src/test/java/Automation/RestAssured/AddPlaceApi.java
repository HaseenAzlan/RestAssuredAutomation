package Automation.RestAssured;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class AddPlaceApi {

	public static void main(String[] args) {
		// Addplace- Post
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String expectedAddress = "70 Summer walk, Hyderabad";
		String response = given().log().all().headers("Content-Type", "application/json")
				.queryParam("key", "qaclick123").body(PayloadClass.payloadForPost()).when()
				.get("maps/api/place/add/json").then().log().all().assertThat().statusCode(200)
				.body("scope", equalTo("APP")).extract().response().asPrettyString();
		System.out.println("*******************************");
		System.out.println(response);
		JsonPath js = UtilityClass.jsonUtility(response);
		String placeId = js.getString("place_id");
		System.out.println(placeId);
		System.out.println("**********************Logging started for PUT*******************");

//Update place- Put
		given().log().all().headers("Content-Type", "application/json").queryParam("key", "qaclick123")
				.body(PayloadClass.payloadForPut(placeId, expectedAddress)).put("maps/api/place/update/json").then()
				.assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));

		System.out.println("**********************Logging started for GET*******************");
//Check the updated address using Get
		String responseFromGet = given().log().all().headers("Content-Type", "application/json")
				.queryParam("key", "qaclick123").queryParam("place_id", placeId).when().get("maps/api/place/get/json")
				.then().log().all().assertThat().statusCode(200).extract().response().asString();
		JsonPath js1 = UtilityClass.jsonUtility(responseFromGet);
		String actualAddress = js1.getString("address");
		Assert.assertEquals(actualAddress, expectedAddress);

	}

}
