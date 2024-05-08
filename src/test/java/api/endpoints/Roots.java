package api.endpoints;


/*
 * Swagger URL:https://petstore.swagger.io/
 * Create User(Post):https://petstore.swagger.io/v2/user
   Get User(Get):https://petstore.swagger.io/v2/user/{username}
   Update User(Put):https://petstore.swagger.io/v2/user/{username}
   Delete User(delete):https://petstore.swagger.io/v2/user/{username}
 */

//Contains only the URL's

public class Roots {
 
	public static String base_url="https://petstore.swagger.io/";
	
	//User Module
	
	public static String post_url= base_url +"v2/user";
	public static String get_url= base_url +"v2/user/{username}"; 
	public static String update_url= base_url +"v2/user/{username}";
	public static String delete_url= base_url +"v2/user/{username}"; 
	
	//Store Module
	//Here we can create Store Module URL's
	
	//Pet Module
	//Here we can create Pet Module URL's
			
	
}
