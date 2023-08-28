package RestAPI.APITesting;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import junit.framework.Assert;

public class ComplexJsonParse {

	public static void main(String[] args) {
		
		/*JsonPath js=new JsonPath(basics.ParseJson());
		
		System.out.println(js.getInt("courses.size()"));
		int count=js.getInt("courses.size()");
		System.out.println(js.getInt("dashboard.purchaseAmount"));
		System.out.println(js.get("courses[0].title"));
		
		for(int i=0;i<count;i++) 
		{
			System.out.println(js.get("courses["+i+"].title"));
		}
		
		for(int i=0;i<count;i++) {
			String title1=js.get("courses["+i+"].title");
			if(title1.equalsIgnoreCase("RPA")) {
				System.out.println(js.get("courses["+i+"].copies"));
				break;
			}
		}*/

	}

	@Test
	public static void sumofNumbers() {
		JsonPath js=new JsonPath(basics.ParseJson());
		int count=js.getInt("courses.size()");
		int Sum=0;
		int totalamount=js.getInt("dashboard.purchaseAmount");
		for(int i=0;i<count;i++) 
		{
			int price=js.get("courses["+i+"].price");
			int copies=js.get("courses["+i+"].copies");
			int total=price*copies;
			Sum=Sum+total;
		}
		System.out.println(Sum);
		Assert.assertEquals(totalamount, Sum);
		System.out.println("Test Case has passed");
		
	}
}
