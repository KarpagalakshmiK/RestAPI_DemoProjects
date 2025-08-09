package Basics;
import static io.restassured.RestAssured.given;


import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.Assert;

import JsonPath.JsonPaths;
import files.Payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class Basics {
	

	public static String placeId;
	public static String newaddress;

	public static void main(String[] args) throws IOException {



	
	/*in api, we use 3 methods:given,when and then
	given: all input details
	when:  submit the api -(resource and http method)
	then: validate the response*/
	//content type to string- byte to string
	
	RestAssured.baseURI="https://rahulshettyacademy.com";
	String response=given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
	.body(new String(Files.readAllBytes(Paths.get("C:\\Users\\2273486\\Downloads\\Maps_API-POST.txt")))).when().post("maps/api/place/add/json")
	.then().assertThat().log().all().statusCode(200).body("scope", equalTo("APP"))
	.header("server","Apache/2.4.52 (Ubuntu)")
	.extract().response().asString();
	
	System.out.println(response);
	
	JsonPath js=new JsonPath(response);//for parsing into json
	placeId=js.getString("place_id");
	
	System.out.println("Place id is"+placeId);
	
	
	//Update place
	newaddress="RSA street,USA";
	
	given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
	.body(Payload.updatePlace())
	.when().put("maps/api/place/update/json")
	.then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));
	
	
	//get place
	String getresponse=given().log().all().queryParam("key", "qaclick123")
	.queryParam("place_id", placeId)
	.when().get("/maps/api/place/get/json")
	.then().assertThat().log().all().statusCode(200)
	.extract().response().asString();
	
	JsonPath j=JsonPaths.json(getresponse);
	String address=j.getString("address");
	System.out.println(address);
	Assert.assertEquals(address, newaddress);
}



	}


