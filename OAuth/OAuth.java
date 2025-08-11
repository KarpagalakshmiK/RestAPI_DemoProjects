package OAuth;

import org.testng.Assert;
import org.testng.annotations.Test;

import POJO.Getcourse;
import POJO.api;
import POJO.mobile;
import POJO.webAutomation;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OAuth {
	
	public static String accessToken;
	String[] c= {"Selenium Webdriver Java","Cypress","Protractor"};
	
	
	@Test
	public void authorizationEndpoint() {
		RestAssured.baseURI="https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token";
		String response=given().formParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.formParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W").formParam("grant_type", "client_credentials")
		.formParam("scope", "trust")
		.when().post().then().log().all().assertThat().statusCode(200)
		.extract().response().asString();
		
		JsonPath js=new JsonPath(response);
		accessToken=js.getString("access_token");
		System.out.println(accessToken);
	}
	
	@Test
	public void getcourse() {
		Getcourse a=given().queryParam("access_token", accessToken)
		.when().log().all().get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").as(Getcourse.class);
		
		System.out.println("Instructor is: "+a.getInstructor());
//		String courseTitle=a.getCourses().getApi().get(1).getCourseTitle();
//		if(courseTitle.equals("SoapUI Webservices testing")) {
//			System.out.println("Price is: "+a.getCourses().getApi().get(1).getPrice());
//		}
		
		//another way to get price
		List<api> courses=a.getCourses().getApi();
		for(int i=0; i<courses.size();i++) {
			if(courses.get(i).getCourseTitle().equals("Rest Assured Automation using Java")) {
				System.out.println("Price is: "+courses.get(i).getPrice());
			}
		}
		
		
		//webautomation
		ArrayList<String> list=new ArrayList<String>();
		List<webAutomation> web=a.getCourses().getWebAutomation();
		for(int i=0; i<web.size();i++) {
			list.add(web.get(i).getCourseTitle());
		}
		System.out.println("Courses are: "+list);
		
		List<String> expected=Arrays.asList(c);
		Assert.assertTrue(list.equals(expected),"Titles are mismatched");
		
		
		//mobile
		ArrayList<String> b=new ArrayList<String>();
		List<mobile> mobile=a.getCourses().getMobile();
		for(int i=0;i<mobile.size();i++) {
			b.add(mobile.get(i).getCourseTitle()+b.add(mobile.get(i).getPrice()));
		}
		System.out.println(b);
		
		
		
		
		
		
	}

}
