package RestAPI.APITesting;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import junit.framework.Assert;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONArray;
import org.json.JSONObject;

import static io.restassured.matcher.RestAssuredMatchers.*;

public class ParsingJSONResponseData {

	public static void main(String[] args) {

	}

	@Test(enabled = false, priority = 1)
	void testResponseJSON() {
		given().contentType("ContentType.JSON").when().get("https://reqres.in/api/users?page=2").then().statusCode(200)
				.header("Content-Type", "application/json; charset=utf-8")
				.body("data[0].email", equalTo("michael.lawson@reqres.in")).log().all();
	}

	@Test(enabled = true, priority = 2)
	void testResponseJSONsecondMethod() {
		Response res = given().contentType(ContentType.JSON).when().get("https://reqres.in/api/users?page=2");
		
		String responseBody = res.getBody().asString();
        JSONObject jsonObject = new JSONObject(responseBody);

        JSONArray dataArray = jsonObject.getJSONArray("data");

        for (int i = 0; i < dataArray.length(); i++) {
            JSONObject userData = dataArray.getJSONObject(i);
            String email = userData.getString("email");
            System.out.println(email);
        }
}
}
