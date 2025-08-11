package graphql;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;


public class graphql {
	
	//if int => "+locid+"
	// if string => \""+name+"\"
	
	@Test
	public void query() {
		int locid=21900;
		RestAssured.baseURI="https://rahulshettyacademy.com/gq/graphql";
		
		String response=given().log().all().header("Content-Type","application/json")
		.body("{\"query\":\"query($locationId:Int!,$characterId:Int!,$episodeId:Int!){\\n  location(locationId:$locationId){\\n    name\\n    type\\n    dimension\\n    \\n    }\\n  \\n  character(characterId:$characterId){\\n    name\\n    gender\\n    origin{\\n      type\\n      id\\n    }\\n    \\n  }\\n  episode(episodeId:$episodeId){\\n    name\\n    episode\\n    air_date\\n    id\\n    \\n  }\\n  \\n  \\n}\",\"variables\":{\"locationId\":"+locid+",\"characterId\":15451,\"episodeId\":15065}}")
		
		.when().post().then().extract().response().asString();
		
		System.out.println(response);
		
		
		JsonPath js=new JsonPath(response);
		System.out.println(js.get("data.character.origin.type"));
		
	}
	
	@Test
	public void mutation() {
		String episode="vampire diaries";
         RestAssured.baseURI="https://rahulshettyacademy.com/gq/graphql";
		
		String response=given().log().all().header("Content-Type","application/json")
		.body("{\"query\":\"mutation($LocationName:String!,$CharacterType:String!,$EpisodeDate:String!,$locationId:Int!,$newLocationData:String){\\n  createLocation(location:{name:$LocationName,type:\\\"female\\\",dimension:\\\"210\\\"})\\n  {\\n    id\\n  }\\n  \\n  editLocation(locationId:$locationId,newLocationData:{name:$newLocationData,type:\\\"green flag\\\"}){\\n    status\\n  }\\n  \\n  createCharacter(character:{name:\\\"Lakshmi\\\",type:$CharacterType,status:\\\"rich\\\",species:\\\"human\\\",gender:\\\"female\\\",image:\\\"jpg\\\",originId:21898,locationId:21898})\\n  {\\n    id\\n  }\\n  \\n  createEpisode(episode:{name:\""+episode+"\",air_date:$EpisodeDate,episode:\\\"213\\\"})\\n  {\\n    id\\n  }\\n  \\n  deleteLocations(locationIds:[21893]){\\n    locationsDeleted\\n  }\\n\\n  \\n}\",\"variables\":{\"LocationName\":\"Tara\",\"CharacterType\":\"Female\",\"EpisodeDate\":\"10-10-2001\",\"locationId\":21902,\"newLocationData\":\"Amrit\"}}")
		
		.when().post().then().extract().response().asString();
		
		System.out.println(response);
		
		
		
	}

}
