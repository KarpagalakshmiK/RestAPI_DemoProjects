//package Ecommerce;
//
//import static io.restassured.RestAssured.given;
//
//import java.io.File;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.testng.Assert;
//import org.testng.annotations.Test;
//
//import io.restassured.builder.RequestSpecBuilder;
//import io.restassured.http.ContentType;
//import io.restassured.path.json.JsonPath;
//import io.restassured.specification.RequestSpecification;
//
//public class Ecommerce {
//	
//	
//	@Test
//	public void ecom() {
//		
//		//login
//		LoginRequest l=new LoginRequest();
//		l.setUserEmail("karpagalakshmik70@gmail.com");
//		l.setUserPassword("Tangled2001#");
//		
//		
//		
//		
//		RequestSpecification req=new RequestSpecBuilder()
//				.setContentType(ContentType.JSON).setBaseUri("https://rahulshettyacademy.com/api/ecom")
//				
//				.build();
//		RequestSpecification res=given().log().all().spec(req).body(l);
//		
//		LoginResponse login=res.when().post("/auth/login")
//				
//		.then().log().all().assertThat().statusCode(200).extract().response().as(LoginResponse.class);
//		
//		String token=login.getToken();
//		String userId=login.getUserId();
//		
//		
//		//Add Product
//		RequestSpecification request=new RequestSpecBuilder()
//				.setBaseUri("https://rahulshettyacademy.com/api/ecom")
//				.addHeader("Authorization", token)
//				.build();
//		RequestSpecification requ=given().log().all().spec(request)
//		.param("productName", "flowers")
//		.param("productAddedBy", userId)
//		.param("productCategory", "fashion")
//		.param("productSubCategory", "shirts")
//		.param("productPrice", "11500")
//		.param("productDescription", "flower")
//		.param("productFor", "women")
//		.multiPart("productImage", new File("C://Users//2273486//Downloads//sign.png"));
//		
//		String productresponse=requ.when().post("/product/add-product")
//		.then().log().all().assertThat().statusCode(201).extract().response().asString();
//		
//		JsonPath js=new JsonPath(productresponse);
//		String productId=js.get("productId");
//		
//		OrderDetails o=new OrderDetails();
//		o.setCountry("India");
//		o.setProductOrderedId(productId);
//		
//		List<OrderDetails> list=new ArrayList<OrderDetails>();
//		list.add(o);
//		
//		Orders order=new Orders();
//		order.setOrders(list);
//		
//		//create order
//		RequestSpecification rep=new RequestSpecBuilder()
//				.setBaseUri("https://rahulshettyacademy.com/api/ecom")
//				.addHeader("Authorization", token)
//				.setContentType(ContentType.JSON).build();
//		
//		RequestSpecification reqspe=given().log().all().spec(rep).body(order);
//		
//		OrderResponse rem=reqspe.when().post("/order/create-order")
//		.then().log().all().assertThat().statusCode(201).extract().response().as(OrderResponse.class);
//		
//		List<String> orderId=rem.getOrders();
//		
//		
//		
//		System.out.println(rem);
//		
//		//view order details
//		RequestSpecification rel=new RequestSpecBuilder()
//				.setBaseUri("https://rahulshettyacademy.com/api/ecom")
//				.addHeader("Authorization", token)
//				.addQueryParam("id", orderId).build();
//		
//		RequestSpecification rl=given().log().all().spec(rel);
//		String rle=rl.when().get("/order/get-orders-details")
//		.then().log().all().extract().response().asString();
//	
//		System.out.println(rle);
//		
//		//delete order
//		RequestSpecification rd=new RequestSpecBuilder()
//				.setBaseUri("https://rahulshettyacademy.com/api/ecom")
//				.addHeader("Authorization", token).build();
//		
//		
//		RequestSpecification dlt=given().log().all().spec(rd)
//				.pathParam("productId", productId);
//		String del=dlt.when().delete("/product/delete-product/{productId}")
//		.then().log().all().extract().response().asString();
//		
//		System.out.println(del);
//		
//		JsonPath je=new JsonPath(del);
//		Assert.assertEquals("Product Deleted Successfully",je.get("message"));
//		
//		
//		//delete order details
//		RequestSpecification ro=new RequestSpecBuilder()
//				.setBaseUri("https://rahulshettyacademy.com/api/ecom")
//				.addHeader("Authorization", token).build();
//		
////		Map<String,Object> pathParams=new HashMap<>();
////		pathParams.put("orderId", orderId);
//		
//		RequestSpecification delor=given().log().all().spec(ro)
//				.pathParams(pathParams);
//		
//		String d=delor.when().delete("/order/delete-order/{orderId}")
//		.then().log().all().extract().response().asString();
//		
//		System.out.println(d);
//		
//				
//		
//		
//		
//	}
//	
//	
//			
//
//}
