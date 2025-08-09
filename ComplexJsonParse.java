package Basics;

import files.Payload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		JsonPath js=new JsonPath(Payload.course());
		
		//to get no.of courses-size can be used only for array
		System.out.println("No of courses is: "+js.getInt("courses.size()"));
		
		//to print purchase amt
		System.out.println("Purchase amt is: "+js.getInt("dashboard.purchaseAmount"));
		
		//title of first course(get method is by default string. u can use getString as well)
		System.out.println("Title of 1st book is: "+js.get("courses[0].title"));
		
		//print all courses title and its respective prices
		int course_size=js.getInt("courses.size()");
		for(int i=0;i<course_size;i++) {
			String title=js.get("courses["+i+"].title").toString();
			String price=js.get("courses["+i+"].price").toString();
			System.out.println("Title and price of "+i+" book :"+ title + " - "+ price);
		}
		
		
		
       //print no.of copies sold by RPA course
		for(int i=0; i<course_size;i++) {
			if(js.get("courses["+i+"].title").equals("Cypress")){
				System.out.println("No of copies sold by RPA: "+js.get("courses["+i+"].copies").toString());
				break;
				
			}
		}
		
		//Verify if Sum of all Course prices matches with Purchase Amount
		int sum=0;
		for(int i=0;i<course_size;i++) {
			int price=js.get("courses["+i+"].price");
			int copies=js.get("courses["+i+"].copies");
			sum+=price*copies;
			if(sum==js.getInt("dashboard.purchaseAmount")) {
				System.out.println("Purchase amt and course prices matched");
			}
		}
		
		//all courses
		for(int i=0; i<course_size;i++) {
			System.out.println(js.get("courses["+i+"]"));
			if(i==1) {
			
			break;
			}
		}
		
	}

}
