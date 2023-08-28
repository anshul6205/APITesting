package RestAPI.APITesting;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import junit.framework.Assert;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import Payload.PayloadBody;

public class basics {

	public static void main(String[] args) throws IOException {
		RestAssured.baseURI="https://rahulshettyacademy.com";
		//Add Place
		String response=given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
		.body(PayloadBody.AddPlacePayload()).when().post("/maps/api/place/add/json").then().log().all().assertThat().statusCode(200)
		.body("scope",equalTo("APP")).header("server", "Apache/2.4.41 (Ubuntu)").extract().response().asString();
		
		JsonPath js=new JsonPath(response);
		String placeID=js.getString("place_id");
		
		System.out.println(placeID);
		
		//Update Place
		String add="New Area";
		given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
		.body(new String(Files.readAllBytes(Paths.get("C:\\Users\\anshu\\OneDrive\\Documents\\Rest API\\Complex.json"))))
		.when().put("/maps/api/place/update/json").then().log().all().assertThat().statusCode(200)
		.body("msg", equalTo("Address successfully updated"));
		
		//Get Place
		String getplace=given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeID)
		.when().get("/maps/api/place/get/json")
		.then().assertThat().log().all().statusCode(200).extract().response().asString();
		
		JsonPath js1=new JsonPath(getplace);
		String actualaddress=js1.getString("address");
		System.out.println(actualaddress);
		Assert.assertEquals(actualaddress, add);
		
	}
	public static String ParseJson() {
		return "{\r\n"
				+ "\"dashboard\": {\r\n"
				+ "\"purchaseAmount\": 910,\r\n"
				+ "\"website\": \"rahulshettyacademy.com\"\r\n"
				+ "},\r\n"
				+ "\"courses\": [\r\n"
				+ "{\r\n"
				+ "\"title\": \"Selenium Python\",\r\n"
				+ "\"price\": 50,\r\n"
				+ "\"copies\": 6\r\n"
				+ "},\r\n"
				+ "{\r\n"
				+ "\"title\": \"Cypress\",\r\n"
				+ "\"price\": 40,\r\n"
				+ "\"copies\": 4\r\n"
				+ "},\r\n"
				+ "{\r\n"
				+ "\"title\": \"RPA\",\r\n"
				+ "\"price\": 45,\r\n"
				+ "\"copies\": 10\r\n"
				+ "}\r\n"
				+ "]\r\n"
				+ "}";
	}

}
