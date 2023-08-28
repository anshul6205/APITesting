package RestAPI.APITesting;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

public class AsanaAPI {
	
	
	@Test
	public static void APITesting() {
	RestAssured.baseURI="https://app.asana.com/api/1.0";
	
	String response = given().log().all()
	        .header("Authorization", "Bearer 1/1204513175523868:d65f7216988d8626814629c7405b3c9c", "accept",
	                "Content-Type", "application/json")
	        .body("{\r\n" + "  \"data\": {\r\n" + "    \"name\": \"API Testing Hello\",\r\n"
	                + "    \"notes\": \"Testing the API Service through Eclipse\",\r\n" + "    \"due_on\": \"2023-05-31\",\r\n"
	                + "    \"assignee\": \"anshulprabhakar6205@gmail.com\",\r\n" + "    \"projects\": [\r\n"
	                + "      \"1204517152833951\"\r\n" + "    ]\r\n" + "  }\r\n" + "}")
	        .when().post("/tasks").then().log().all().assertThat().statusCode(201).extract().response().asString();

	System.out.println(response);
	
	}

}
