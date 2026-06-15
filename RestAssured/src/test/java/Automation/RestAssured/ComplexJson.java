package Automation.RestAssured;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;


public class ComplexJson {
//1. Print No of courses returned by API
	@Test
	public void noOfCourses() {
		JsonPath js= UtilityClass.jsonUtility(PayloadClass.complexJsonPayload());
	//JsonPath js= new JsonPath(PayloadClass.complexJsonPayload());
	int count=	js.getInt("courses.size()");
	System.out.println("Total course count="+count);
	//Print All course titles and their respective Prices
			for(int i=0; i<count; i++) {
		String title=	js.getString("courses["+i+"].title");
		int price= js.get("courses["+i+"].price");
		System.out.println(title + " and " +price);
		//Print no of copies sold by RPA Course
		if(title.equalsIgnoreCase("RPA")) {
			int noOfCopies= js.get("courses["+i+"].copies");
			System.out.println("# of copies sold by RPA="+noOfCopies);
			
		}
		
				
			}
		
	}
	//Print Purchase Amount
	@Test
	public void purchaseAmount() {
		JsonPath js= UtilityClass.jsonUtility(PayloadClass.complexJsonPayload());
		String purchaseAmount =js.getString("dashboard.purchaseAmount");
		System.out.println("PurchaseAmount="+purchaseAmount);
	}
	//Print Title of the first course
	@Test
	public void firstCourse() {
		JsonPath js= UtilityClass.jsonUtility(PayloadClass.complexJsonPayload());
		String title= js.getString("courses[0].title");
		System.out.println(title);
	}
	// Verify if Sum of all Course prices matches with Purchase Amount
	
	
	int amount=0;
	@Test
	public void sumOfAllCourses() {
		JsonPath js= UtilityClass.jsonUtility(PayloadClass.complexJsonPayload());
		int purchaseAmount =js.get("dashboard.purchaseAmount");
		int count=	js.getInt("courses.size()");
		for(int i=0;i<count; i++) {
		int price=	js.get("courses["+i+"].price");
		int copies=	js.get("courses["+i+"].copies");
				amount= amount +price * copies;		
				System.out.println(amount);
			
		}
		
		if(purchaseAmount ==amount) {
			System.out.println("Both the prices are same" +purchaseAmount +"="+amount);
		}
		else {
			System.out.println("prices are not same");
			System.out.println(purchaseAmount);
			System.out.println(amount);
		}
	}
	
}
