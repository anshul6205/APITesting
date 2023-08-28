package RestAPI.APITesting;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matcher.*;

import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;



public class queryParams {

	@Test
	void testqueryandPathparams() {
		given().pathParam("mypath","users").queryParam("page", 2).queryParam("id", 5)
				.when().get("https://reqres.in/api/{mypath}")
				.then().statusCode(200).log().all();
	}
	
	void testCookies() {
		given()
		.when().get("https://www.google.com")
		.then().log().all();
	}
	
	@Test
	void getCookiesdetails() {
		Response res=when().get("https://www.google.com");
		String cookie_value=res.getCookie("AEC");
		System.out.println(cookie_value);
		Map<String, String> values=res.getCookies();
		System.out.println(values.keySet());
		for(String k:values.keySet())
		{
			String cvalues=res.getCookie(k);
			System.out.println(k+" "+cvalues);
		}
		Headers myheaders=res.getHeaders();
		
		for(Header hd:myheaders)
		{
			System.out.println(hd.getName()+""+hd.getValue());
		}
	}
}
