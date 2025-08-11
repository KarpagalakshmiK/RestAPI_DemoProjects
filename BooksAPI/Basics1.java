package Basics1;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.Basics1_Payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class Basics1 {
	public static String Id;
	
	//post
	@Test(dataProvider="Booksdata")
	public void createbook(String name, String isbn, String aisle) {
		RestAssured.baseURI="http://216.10.245.166";
		
		String response=given().log().all().header("Content-Type","application/json")
		.body(Basics1_Payload.createBook(name, isbn, aisle))
		.when().post("Library/Addbook.php")
		.then().log().all().assertThat().statusCode(200).body("Msg", equalTo("successfully added"))
		.extract().response().asString();
		
		JsonPath js=new JsonPath(response);
		Id=js.getString("ID");
		System.out.println("Id is " +Id);
		
		
	
	
	
	}
	
	//delete
	@Test(dataProvider="Booksdata")
	public void deletebook(String name, String isbn, String aisle) {
		given().header("Content-Type","application/json")
		.body(Basics1_Payload.delete(name, isbn, aisle))
		.when().post("/Library/DeleteBook.php")
		.then().log().all().assertThat().statusCode(200).body("msg",equalTo("book is successfully deleted"));
	}
	
	@DataProvider(name="Booksdata")
	public Object[][] booksdata() {
		return new Object[][] {{"stella","adn","123"},{"vampire","dsv","962"},{"akshat","ivpt","987"}};
	}
	
	//get with authorname
//	@Test
//	public void getbook() {
//		given().log().all().queryParam("AuthorName", "Ruvi")
//		.when().get("/Library/GetBook.php")
//		.then().log().all().assertThat().statusCode(200);
//	}
//	
//	//get with ID
//	@Test
//	public void getbookID() {
//	   given().log().all().queryParam("ID", Id)
//	   .when().get("Library/GetBook.php")
//	   .then().log().all().assertThat().statusCode(200);

}

