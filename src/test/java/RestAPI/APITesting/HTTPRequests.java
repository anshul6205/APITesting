package RestAPI.APITesting;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

import org.testng.annotations.Test;

public class HTTPRequests {
	int id;

	@Test(enabled = false)
	void getUser() {
		when().get("https://reqres.in/api/users?page=2").then().statusCode(200).body("page", equalTo(2)).log().all();
	}

	@Test(priority = 1, enabled = true)
	void createUser() {
		HashMap hm = new HashMap();
		hm.put("name", "Anshul");
		hm.put("job", "Engineer");
		id = given().contentType("application/json").body(hm)

				.when().post("https://reqres.in/api/users").jsonPath().getInt("id");
		System.out.println(id);
//		.then().statusCode(201).log().all();
	}

	@Test(priority = 2, enabled = true, dependsOnMethods = { "createUser" })
	void updateUser() {
		HashMap hm = new HashMap();
		hm.put("name", "frust");
		given().contentType("application/json").body(hm).when().put("https://reqres.in/api/users/" + id).then()
				.statusCode(200).log().all();

	}

	@Test(dependsOnMethods = { "updateUser" })
	void deleteUser() {
		when().delete("https://reqres.in/api/users/" + id).then().statusCode(204).log().all();
	}
}
