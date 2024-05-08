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

import java.util.ResourceBundle;

import org.testng.annotations.Test;

import api.payloads.User;
public class UserEndPoints2 {
	 
	
	//Method created for getting url's from properties file
	static ResourceBundle getURL()
	{
		ResourceBundle routes=ResourceBundle.getBundle("routes"); // Load the properties file
		return routes;
	}

	
	public static Response CreateUser(User payload)
	{
		
		String post_url =getURL().getString("post_url");
		Response response=given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(payload)
		  
		.when()
		.post(post_url);
		 
		return response;
		}
	
	public static Response ReadUser(String userName)
	{
		String get_url =getURL().getString("get_url");
		Response response=given()
		 .pathParam("username", userName)
		
		.when()
		 .get(get_url);
		
		return response;
		
	}
	
	public static Response UpdateUser( User payload,String userName)
	{
		String update_url =getURL().getString("update_url");
		Response response=given()
		 .pathParam("username", userName)
		 .contentType(ContentType.JSON)
		 .accept(ContentType.JSON)
		 .body(payload)
		 
		.when()
		 .put(update_url);
		
		return response;
	}
	
	public static Response DeleteUser(String userName)
	{
		String delete_url =getURL().getString("delete_url");
		Response response=given()
		.pathParam("username", userName)
		
		.when()
		.delete(delete_url);
		
		return response;
	}
	
	
}

