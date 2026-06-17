package Automation.RestAssured;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LibraryApi {

	/*
	 * @Test(dataProvider = "addBookData") public void addBook(String isbn, String
	 * aisle) { RestAssured.baseURI="http://216.10.245.166";
	 * given().header("content-type","application/json").
	 * body(PayloadClass.addbookPayload(isbn,aisle)).
	 * when().post("Library/Addbook.php").
	 * then().log().all().assertThat().statusCode(200). body("Msg",
	 * equalTo("successfully added")); }
	 * 
	 * @DataProvider(name="addBookData") public Object[][] getdata(){ return new
	 * Object[][] {{"Azlanm", "1812"},{"Haseena","1012"}, {"Altaf","1112"}}; }
	 */
@Test
public void addbookStatic() throws IOException {
	RestAssured.baseURI="http://216.10.245.166";
	given().header("content-type","application/json").
	body(new String(Files.readAllBytes(Paths.get("C:\\Users\\aleen\\git\\repository\\RestAssured\\src\\test\\resources\\payload.json")))).
	when().post("Library/Addbook.php").
	then().log().all().assertThat().statusCode(200).
	body("Msg", equalTo("successfully added"));
	
	}
}


