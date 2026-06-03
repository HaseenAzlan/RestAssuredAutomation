package Automation.RestAssured;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import files.AddplacePayload;


public class AddPlaceApi {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RestAssured.baseURI="https://rahulshettyacademy.com";
	String resposne=	given().log().all().headers("Content-Type", "application/json").queryParam("key", "qaclick123").
        body(AddplacePayload.payload()).
		when().post("maps/api/place/add/json").
		then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP")).extract().response().asPrettyString();
	System.out.println("*******************************");
	System.out.println(resposne);
	JsonPath js= new JsonPath(resposne);
String placeId=	js.getString("place_id");
System.out.println(placeId);
	}

}
