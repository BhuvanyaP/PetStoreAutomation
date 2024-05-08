package api.test;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payloads.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DDTests {

	@Test(priority=1,dataProvider="Data",dataProviderClass=DataProviders.class)
	public void testPostUser(String ID,String Name,String FName,String LName,String email,String password,String phone)
	{
		User userPayload= new User();
		
		Faker data= new Faker();
		
		userPayload.setId(data.idNumber().hashCode());
		  userPayload.setUsername(Name);
		   userPayload.setFirstName(FName);
		   userPayload.setLastName(LName);
		   userPayload.setEmail(email);
		   userPayload.setPassword(password);
		   userPayload.setPhone(phone);
		   
		   if (ID.isEmpty() || Name.isEmpty() || FName.isEmpty() || LName.isEmpty() || email.isEmpty() || password.isEmpty() || phone.isEmpty()) {
		        // Log a message indicating that the test is skipped due to empty data
		        System.out.println("Skipping test because some data is empty: " + ID + ", " + Name + ", " + FName + ", " + LName + ", " + email + ", " + password + ", " + phone);
		        // Skip the test
		        throw new SkipException("Skipping test because some data is empty");
		    }

		   
		  // System.out.println(userPayload);

			Response response=UserEndPoints.CreateUser(userPayload);
			response.then().log().all();
			Assert.assertEquals(response.getStatusCode(), 200);
		
	}
	
	@Test(priority=2,dataProvider="UserNames",dataProviderClass= DataProviders.class)
	public void testDeleteUserByName(String userName)
	{
		 
		   if ( userName.isEmpty() ) {
		        // Log a message indicating that the test is skipped due to empty data
		        System.out.println("Skipping test because some data is empty: " + userName );
		        // Skip the test
		        throw new SkipException("Skipping test because some data is empty");
		    }

		Response res=UserEndPoints.DeleteUser(userName);
		res.then().log().all();
		Assert.assertEquals(res.getStatusCode(),200);
}
}