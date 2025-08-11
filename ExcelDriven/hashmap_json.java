package LibraryAPI;

import org.testng.annotations.Test;

import ExcelDriven.ExcelDriven;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class hashmap_json {
	
	@Test
	public void addbook() throws IOException {
		
		ExcelDriven ex=new ExcelDriven();
		ArrayList l=ex.getData("Rudra tara","BookDetails");
		
		HashMap<String,Object> hm= new HashMap<String, Object>();
		hm.put("name", l.get(0));
		hm.put("isbn", l.get(1));
		hm.put("aisle", l.get(2));
		hm.put("author",l.get(3));
		
		//when you have nested json, create another hashmap to hold all the variable
//		HashMap<String, Object> hm1=new HashMap<String,Object>();
//		hm1.put("lat", 1234);
//		hm1.put("lng", 2345);
//		hm.put("location", hm1);here under location it has 2 attributes which is lat and lng
		
//      when u have nested json array
		/*List<String> list=new ArrayList<String>();
		list.add("gaming");
		list.add("playing");
		hm.put("hobbies", list);*/
		
		RestAssured.baseURI="http://216.10.245.166";
		given().log().all().header("Content-Type","application/json")
		.body(hm)
		.when().post("/Library/Addbook.php")
		.then().log().all().assertThat().statusCode(200);
	}

}
