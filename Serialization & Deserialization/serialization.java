package Serialization;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class serialization {
	
	
	
        @Test
        public void serial() {
        
        RequestSpecification req=new RequestSpecBuilder()
        		.setBaseUri("https://rahulshettyacademy.com")
        		.setContentType(ContentType.JSON)
        		.addQueryParam("key", "qaclick123").build();
        
        ResponseSpecification re=new ResponseSpecBuilder()
        		.expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
	
		Details d=new Details();
		d.setAccuracy(50);
		d.setAddress("29, side layout, cohen 09");
		d.setPhone_number("(+91) 983 893 3937");
		d.setName("Frontline house");
		d.setWebsite("http://google.com");
		d.setLanguage("French-IN");
		
		Location l=new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		
		d.setLocation(l);
		
		ArrayList<String> t=new ArrayList<String>();
		t.add("shoe park");
		t.add("shop");
		
		d.setTypes(t);
		
		

				
		RequestSpecification res=given().log().all().spec(req)
		.body(d);
		Response response=res.when().post("/maps/api/place/add/json")
		.then().spec(re)
		.extract().response();
		
		String r=response.asString();
	    System.out.println(r);
		
	}
}

