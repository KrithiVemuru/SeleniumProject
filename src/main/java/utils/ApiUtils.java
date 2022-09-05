package utils;

import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import groovy.json.JsonOutput;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ApiUtils {

	private HashMap<String, String> headers = new HashMap<String, String>();
	
	public void setHeaders(HashMap<String,String> payloadHeaders) {
		payloadHeaders.keySet().forEach(expectedHeaderKey ->{
			headers.put(expectedHeaderKey, payloadHeaders.get(expectedHeaderKey)); 
		});
	}
	
	public void login(String userName,String password) {
		Response response = RestAssured.given()
				.contentType("application/json")
				.get(RestAssured.baseURI+"/v2/user/login?username="+userName+"+&password="+password);
		System.out.println("Login Response Code:"+response.getStatusCode());
	}

	public JSONObject post(HashMap<String,Object> payload,String uri) {
		Response response = RestAssured.given()
				.contentType("application/json")
				.headers(headers)
				.body(JsonOutput.toJson(payload))
				.post(RestAssured.baseURI+uri);
		if(response.getStatusCode() != 200) {
			return null;
		}
		System.out.println("Post Response Code:"+response.getStatusCode());
		return new JSONObject(response.jsonPath().prettify());
	}

	public JSONObject put(HashMap<String, Object> payload,String uri) {
		Response response = RestAssured.given()
				.contentType("application/json")
				.headers(headers)
				.body(JsonOutput.toJson(payload))
				.put(uri);
		if(response.getStatusCode() != 201) {
			return null;
		}
		System.out.println("Put Response Code:"+response.getStatusCode());
		return new JSONObject(response.jsonPath().prettify());
	}
	
	public JSONObject get(String uri) {
		Response response = RestAssured.given()
				.contentType("application/json")
				.headers(headers)
				.get(RestAssured.baseURI+uri);
		if(response.getStatusCode() != 200) {
			return null;
		}
		System.out.println("Get Response Code:"+response.getStatusCode());
		return new JSONObject(response.jsonPath().prettify());
	}

	public JSONArray getWithQueryParams(String uri) {
		Response response = RestAssured.given()
				.contentType("application/json")
				.headers(headers)
				.get(RestAssured.baseURI+uri);
		if(response.getStatusCode() != 200) {
			return null;
		}
		System.out.println("Get Response Code:"+response.getStatusCode());
		return new JSONArray(response.jsonPath().prettify());
	}

	public String delete(HashMap<String, Object> payload,String uri) {
		Response response = RestAssured.given()
				.contentType("application/json")
				.headers(headers)
				.delete(RestAssured.baseURI+uri);
		System.out.println("Delete Response Code:"+response.getStatusCode());
		if(response.getStatusCode() != 204) {
			return null;
		}
		return String.valueOf(response.getStatusCode());
	}
}