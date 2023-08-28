package RestAPI.APITesting;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Payload.PayloadBody;

import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class DynamicJson {

	@Test(dataProvider="BooksData")
	public void addbook(String aisle, String isbn ){
		RestAssured.baseURI = "http://216.10.245.166";
		String response = given().log().all().header("Content-Type", "application/json")
				.body(PayloadBody.Addbook(aisle,isbn))
				.when().post("/Library/Addbook.php").then().log().all().statusCode(200).extract().response().asString();

		JsonPath js = new JsonPath(response);
		String id = js.get("ID");
		System.out.println(id);

	}
	
	@DataProvider(name="BooksData")
	public Object[][] getdata(){
		return new Object[][] {{"3627","dgfdhg"},{"3637","terg"},{"35188","gsjsk"}};
	}
}
