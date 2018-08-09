package withoutBDD;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.*;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

public class completeTEST {

	private  String authToken = "EAADFsoUp0ckBAMPEA92wZAicyMWbeZCKyEJ1g5gNdp2X6GquL6e4taJtCo9sjv3xvyvhhZAV2BZC7D1a5x1AKjdZA71fU5b5gbiqpvHYrleFl2tObwoZBEvxvcPAD3QraCyVhTGJjrQdfxe3ZAedHbzecICar76NuZCo0iLbCnbuhQ6mcPJpNbe7fsE36ZC6xHN314ZCF7NFR5fgZDZD";
	private  String postID;

	@Before
	public void verifyAuthentication() throws Exception {
		
		
		Response resp = RestAssured.get("https://graph.facebook.com/me/?access_token=" + authToken);
		
		if(resp.getStatusCode() == 200) {
			
			System.out.println(resp.getStatusCode() + " - sucessful to connect");	
		}else {
			System.out.println(resp.getStatusCode() + " - fail - check your credentials");}
		Assert.assertEquals(200, resp.getStatusCode());
	}

	

	@Test
	public void postTimeLine() {
		
		String PostURL = "https://graph.facebook.com/me/feed/?access_token=";
		
		Response postReq = given()
							.contentType(ContentType.JSON)
							.body("{\"message\":\"TESTEAPI123\"}")
							.when()
							.post(PostURL + authToken);
		
		if(postReq.getStatusCode() == 200) {
			
			System.out.println(postReq.getStatusCode() + " - post request sucessful");
			this.postID = postReq.
	                then().
	                contentType(ContentType.JSON).extract().path("id");
	                System.out.println(this.postID + "- id.post saved");
		}else
		{
			System.out.println(postReq.getStatusCode() + " - verify your request and try again");
		}				
	}
	
	@Test
	public void changePostTimeLine() {
		
		String changeURL = "https://graph.facebook.com/";
		
		Response changeReq = given()
						.contentType(ContentType.JSON)
						.body("{\"message\":\"123APITEST\"}")
						.when()
						.put(changeURL + postID + "/?access_token=" + authToken );
						
		if(changeReq.getStatusCode() == 200) {
			System.out.println(changeReq.getStatusCode() + " - request for post change sucessful ");
		}else 
		{
			System.out.println(changeReq.getStatusCode() + " - fail in change your post, verify your credentials");
		}
		
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
}
