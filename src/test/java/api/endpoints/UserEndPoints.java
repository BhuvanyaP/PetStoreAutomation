package api.endpoints;


//User EndPoints.java
//Created to perform CRUD operations(Create,Retrieve,Update and Delete)

import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

import api.payloads.User;
public class UserEndPoints {

	@Test(priority=1)
	public static Response CreateUser(User payload)
	{
		Response response=given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(payload)
		  
		.when()
		.post(Roots.post_url);
		 
		return response;
		}
	
	@Test(priority=2)
	public static Response ReadUser(String userName)
	{
		Response response=given()
		 .pathParam("username", userName)
		
		.when()
		 .get(Roots.get_url);
		
		return response;
		
	}
	@Test(priority=3)
	public static Response UpdateUser( User payload,String userName)
	{
		Response response=given()
		 .pathParam("username", userName)
		 .contentType(ContentType.JSON)
		 .accept(ContentType.JSON)
		 .body(payload)
		 
		.when()
		 .put(Roots.update_url);
		
		return response;
	}
	
	@Test(priority=4)
	public static Response DeleteUser(String userName)
	{
		Response response=given()
		.pathParam("username", userName)
		
		.when()
		.delete(Roots.delete_url);
		
		return response;
	}
	
	
}

