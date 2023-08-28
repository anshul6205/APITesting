package RestAPI.APITesting;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matcher.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;

public class differentWaysToCreatePostRequestbody {
	
	String name;
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	String location;
	String course;
	
	@Test
	public void postusingHashmap() throws FileNotFoundException 
	{
		HashMap hm=new HashMap();
		hm.put("name", "Roney");
		hm.put("location","Street");
		hm.put("phone","123456");
		
		JSONObject data=new JSONObject();
		data.put("course", "Java");
		
		String courArr[]= {"c","c++"};
		hm.put("courses", courArr).toString();
		
		given().contentType("application/json").body(hm)
		.when().post("https://reqres.in/api/users")
		.then().statusCode(201).log().all();
		
		File f=new File(".//");
		FileReader fr=new FileReader(f);
		JSONTokener js=new JSONTokener(fr);
		JSONObject data1=new JSONObject(js);
		System.out.println(data1);
		
		
	}
	

}
