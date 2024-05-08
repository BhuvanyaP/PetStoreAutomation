package api.test;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payloads.User;
import io.restassured.response.Response;

public class UserTests {
	
	Faker faker;
	User Payload;
	public Logger logger;
	
	@BeforeClass
	public void setup()
	{
	   faker= new Faker();	
	   Payload = new User();
	   
	   Payload.setId(faker.idNumber().hashCode());
	   Payload.setUsername(faker.name().username());
	   Payload.setFirstName(faker.name().firstName());
	   Payload.setLastName(faker.name().lastName());
	   Payload.setEmail(faker.internet().safeEmailAddress());
	   Payload.setPassword(faker.internet().password(5, 10));
	   Payload.setPhone(faker.phoneNumber().cellPhone());
	   
	   
	   //logs
	   logger=LogManager.getLogger(this.getClass());
	   
	}
	
	@Test(priority=1)
	public void testPostUser()
	{
		logger.info("******Creating user **********");
		Response response=UserEndPoints.CreateUser(Payload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("*********User is created***********");
		
	}
	
	@Test(priority=2)
	public void testGetUserByName()
	{
		logger.info("******Reading user info **********");
		Response response=UserEndPoints.ReadUser(this.Payload.getUsername());
		//System.out.println(this.Payload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("****** user info is displayed **********");
	}
	
	
	@Test(priority=3)
	public void testUpdateUserByName()
	{
		logger.info("******Updating user **********");	
		//update data for the same username using payload
		Payload.setFirstName(faker.name().firstName());
		   Payload.setLastName(faker.name().lastName());
		   Payload.setEmail(faker.internet().safeEmailAddress());
		   
		Response response=UserEndPoints.UpdateUser(Payload,this.Payload.getUsername());
		response.then().log().body();
		Assert.assertEquals(response.getStatusCode(), 200);
		
		//Checking data after update
		Response responseAfterUpdate=UserEndPoints.ReadUser(this.Payload.getUsername());
		responseAfterUpdate.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("****** user is updated **********");
	}
	
	
	@Test(priority=4)
	public void testDeleteUserByName()
	{
		logger.info("******Deleting user **********");
		Response response=UserEndPoints.DeleteUser(this.Payload.getUsername());
		//response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("****** user  deleted **********");
	}
	
	
	
	

}
