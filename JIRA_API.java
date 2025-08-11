package JIRA_API;

import org.testng.annotations.Test;


import files.Basics1_Payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

import java.io.File;


public class JIRA_API {
	public static String Id;
	
	@Test(priority=1)
	public void createbug() {
		RestAssured.baseURI="https://karpukl19.atlassian.net/";
		String response=given().log().all().header("Content-Type","application/json").header("Authorization","Basic a2FycHVrbDE5QGdtYWlsLmNvbTpBVEFUVDN4RmZHRjBnS3pJcjh1LTQ0RnBoclVkSm1EdllKUy1iOEo0aHlhYXpYSERYUVhDWldjMjRFLUUxV3dzVDNMc2Q2aEliUkppNnh1TS1JeVdkLTZ6MVQ1LU5STGRYUkZMc1UtR3R5OTBvTzhVMDUyVHNVYkR6NzBsaklJd1ktMkxmUU5ub29UWVBqTnpFUWdfZWtVNkY4cjQ1QlNyVllhSUxIaXk5eGpLemxQUURiREY0Umc9MUU3ODc2MDk=")
		.body(Basics1_Payload.createbug()).when().post("/rest/api/3/issue")
		.then().log().all().assertThat().statusCode(201)
		.extract().response().asString();
		
		JsonPath js=new JsonPath(response);
		Id=js.getString("id");
		System.out.println(Id);
	    
		
	}
	
	@Test(priority=2)
	public void addAttachement() {
		given().pathParam("key", Id)
		.header("Authorization","Basic a2FycHVrbDE5QGdtYWlsLmNvbTpBVEFUVDN4RmZHRjBnS3pJcjh1LTQ0RnBoclVkSm1EdllKUy1iOEo0aHlhYXpYSERYUVhDWldjMjRFLUUxV3dzVDNMc2Q2aEliUkppNnh1TS1JeVdkLTZ6MVQ1LU5STGRYUkZMc1UtR3R5OTBvTzhVMDUyVHNVYkR6NzBsaklJd1ktMkxmUU5ub29UWVBqTnpFUWdfZWtVNkY4cjQ1QlNyVllhSUxIaXk5eGpLemxQUURiREY0Umc9MUU3ODc2MDk=")
		.header("X-Atlassian-Token","no-check")
		.multiPart("file",new File("C://Users//2273486//Downloads//sample.html"))
		.when().post("/rest/api/3/issue/{key}/attachments")
		.then().log().all().assertThat().statusCode(200);
	
		
	}

}
