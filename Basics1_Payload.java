package files;


import Basics1.Basics1;

public class Basics1_Payload extends Basics1 {
	
	public static String createBook(String name, String isbn, String aisle) {
		return "{\r\n"
				+ "\r\n"
				+ "\"name\":\""+name+"\",\r\n"
				+ "\"isbn\":\""+isbn+"\",\r\n"
				+ "\"aisle\":\""+aisle+"\",\r\n"
				+ "\"author\":\"Riya\"\r\n"
				+ "}\r\n"
				+ "";
	}

	
	public static String delete(String name, String isbn, String aisle) {
		return "{\r\n"
				+ " \r\n"
				+ "\"ID\" : \""+Id+"\"\r\n"
				+ " \r\n"
				+ "} \r\n"
				+ "";
	}
	
	public static String createbug() {
		return "{\r\n"
				+ "    \"fields\": {\r\n"
				+ "       \"project\":\r\n"
				+ "       {\r\n"
				+ "          \"key\": \"MA\"\r\n"
				+ "       },\r\n"
				+ "       \"summary\": \"Text box is not displayed\",\r\n"
				+ "       \"issuetype\": {\r\n"
				+ "          \"name\": \"Bug\"\r\n"
				+ "       }\r\n"
				+ "   }\r\n"
				+ "}";
	}

}
