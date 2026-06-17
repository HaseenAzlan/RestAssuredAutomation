package Automation.RestAssured;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import static org.hamcrest.Matchers.equalTo;

public class LibraryApi {

@Test(dataProvider = "addBookData")
public void addBook(String isbn, String aisle) {
	RestAssured.baseURI="http://216.10.245.166";
	given().log().all().header("content-type","application/json").
	body(PayloadClass.addbookPayload(isbn,aisle)).
	when().post("Library/Addbook.php").
	then().log().all().assertThat().statusCode(200).
	body("Msg", equalTo("successfully added"));
	System.out.println("******************");
	}
@DataProvider(name="addBookData")
public Object[][] getdata(){
	return new Object[][] {{"Azlanm", "1812"},{"Haseena","1012"}, {"Altaf","1112"}};
}


}
