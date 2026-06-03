package Automation.RestAssured;

import java.util.List;

import io.restassured.path.json.JsonPath;

public class UtilityClass {
	
	public  static JsonPath jsonUtility(String Response) {
		JsonPath js= new JsonPath(Response);
		return js;
		
		
	}

}
