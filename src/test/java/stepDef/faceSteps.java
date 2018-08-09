package stepDef;

import static com.jayway.restassured.RestAssured.given;

import org.junit.Assert;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class faceSteps {
	
	private  String authToken; 
	Response reqResp;
	String useURL;
	String theMessage;
	String idPost;
	
	@Given("^The user who has the facebook authentication token$")
	public void the_user_who_has_the_facebook_authentication_token() throws Throwable {
	    
		this.authToken = "EAADFsoUp0ckBAMPEA92wZAicyMWbeZCKyEJ1g5gNdp2X6GquL6e4taJtCo9sjv3xvyvhhZAV2BZC7D1a5x1AKjdZA71fU5b5gbiqpvHYrleFl2tObwoZBEvxvcPAD3QraCyVhTGJjrQdfxe3ZAedHbzecICar76NuZCo0iLbCnbuhQ6mcPJpNbe7fsE36ZC6xHN314ZCF7NFR5fgZDZD";
	}

	@When("^The user will made a request to \"(.*)\"$")
	public void the_user_will_made_a_request_to(String URL) throws Throwable {
		this.useURL = URL + "/?access_token=" + authToken;
	}

	@And("^The user sent a get request with his token$")
	public void the_user_sent_a_get_request_with_his_token() throws Throwable {
		reqResp = RestAssured.get(this.useURL);
	}

	@Then("^The response code of system should be (\\d{3})$")
	public void the_response_code_of_system_should_be(int arg1) throws Throwable {
		if(reqResp.getStatusCode() == 200) 
		{
			System.out.println(reqResp.getStatusCode() + " - sucessful to connect");	
		}else 
		{
			System.out.println(reqResp.getStatusCode() + " - fail - check your credentials");
		}
		Assert.assertEquals(200, reqResp.getStatusCode());
	}
//-------------------------------------------------------------------------------------------------------------------------------------
	
	@And("^User will pass the body message \"(.*)\"$")
	public void user_will_pass_the_body_message(String messageTHE) throws Throwable {
		
		theMessage = messageTHE;  
		
	}

	@And("^The user sent a post request$")
	public void the_user_sent_a_post_request() throws Throwable {
		
				 reqResp = given()
				.contentType(ContentType.JSON)
				.body(theMessage)
				.when()
				.post(useURL);
				 
		if(reqResp.getStatusCode() == 200) {
						
			System.out.println(reqResp.getStatusCode() + " - post request sucessful");
						
		}else
		{
			System.out.println(reqResp.getStatusCode() + " - verify your request and try again");
		}
		Assert.assertEquals(200, reqResp.getStatusCode());
					
	}

	@Then("^The ID of his post will be save$")
	public void the_ID_of_his_post_will_be_save() throws Throwable {
		this.idPost = reqResp.
                then().
                contentType(ContentType.JSON).extract().path("id");
                System.out.println(this.idPost + "- id.post saved");
                
	}
	
//---------------------------------------------------------------------------------------------------------------------------------------	


	@When("^Passing the body message \"([^\"]*)\"$")
	public void passing_the_body_message(String arg1) throws Throwable {
		reqResp = given()
				.contentType(ContentType.JSON)
				.body("{\"message\":\"123APITEST\"}")
				.when()
				.put(useURL + idPost + "/?access_token=" + authToken );
				
		if(reqResp.getStatusCode() == 200) {
			System.out.println(reqResp.getStatusCode() + " - request for post change sucessful ");
		}else 
		{
			System.out.println(reqResp.getStatusCode() + " - fail in change your post, verify your credentials");
		}
		Assert.assertEquals(200, reqResp.getStatusCode());
	}


}
