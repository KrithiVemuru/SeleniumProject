package stepDefinitions;

import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;

import core.BaseSpec;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.AssignmentPage;
import utils.TestUtils;

public class AssignmentSteps {

	Object response;
	String petId,orderId;
	TestUtils testUtils = BaseSpec.testUtils;
	AssignmentPage assignmentPage = new AssignmentPage(testUtils);
	HashMap<String, HashMap<String, String>> tableData = new HashMap<String, HashMap<String, String>>();
	HashMap<String, Object> testData = new HashMap<String, Object>();

	@When("User clicks on {string} link")
	public void userClicksOnLink(String linkName) {
		assignmentPage.clickThisLink(linkName);
	}

	@And("User reads the Dynamic Table Data")
	public void userReadsTheDynamicTableData() {
		tableData = assignmentPage.getDynamicTableData();
	}

	@Then("Verify Dynamic Table Data")
	public void verifyDynamicTableData() {
		String expectedResult = "Chrome CPU: " + tableData.get("Chrome").get("CPU");
		String actualResult = assignmentPage.getDynamicTableActualResult();
		Assert.assertTrue("Expected Result:" + expectedResult + " is not equal to Actual Result:" + actualResult,expectedResult.equals(actualResult));
	}

	@Then("Link should be clicked {string} times")
	public void linkShouldBeClickedByThisManyTimes(String count) {
		Assert.assertTrue("Link is not clicked by:" + count + "times.",assignmentPage.getLinkClickedCount(count).isDisplayed());
	}

	@And("User enters {string} data")
	public void enterData(String dataObject) {
		testData = testUtils.getTestData("Customer Details");
		assignmentPage.enterData(testData.get("id").toString(), testData.get("name").toString());
	}

	@Then("Details should be entered successfully")
	public void detailsShoulBeEnteredSuccessfully() {
		String expectedId = testData.get("id").toString();
		String expectedName = testData.get("name").toString();
		String actualId = assignmentPage.getInputField("id").getAttribute("value");
		String actualName = assignmentPage.getInputField("name").getAttribute("value");
		Assert.assertTrue("Expected Id:" + expectedId + " is not equal to Actual Id:" + actualId,expectedId.equals(actualId));
		Assert.assertTrue("Expected Name:" + expectedName + " is not equal to Actual Name:" + actualName,expectedName.equals(actualName));
	}

	@When("User Adds a new Pet {string} to the Store via API")
	public void addNewPetToStore(String petData) {
		testData = testUtils.getTestData(petData);
		response = (JSONObject) testUtils.getAPIUtils().post(testData, "/v2/pet");
		petId = ((JSONObject) response).get("id").toString();
	}

	@Then("Pet should be added successfully")
	public void petShouldBeCreatedSuccessfully() {
		Assert.assertTrue("Expected Status:"+testData.get("status").toString()+" is not equal to Actual Status:"+((JSONObject) response).get("status"),testData.get("status").toString().equals(((JSONObject) response).get("status")));
	}

	@When("User get the Pet by Status {string}")
	public void getPet(String petStatus) {
		response = (JSONArray) testUtils.getAPIUtils().getWithQueryParams("/v2/pet/findByStatus?status=" + petStatus);
	}

	@Then("Pet data should be displayed successfully with status {string}")
	public void validatePetResultsByStatus(String petStatus) {
		((JSONArray) response).forEach(petDetail ->{
			Assert.assertTrue("Expected Pet Status:"+petStatus+" is not equal to Actual Pet Status:"+((JSONObject) petDetail).get("status"),((JSONObject) petDetail).get("status").equals(petStatus));
		});
	}
	
	@When("User places an Order {string} for the Pet")
	public void placeAnOrderForAPet(String petOrder) {
		testData = testUtils.getTestData(petOrder);
		testData.put("petId",petId);
		response = (JSONObject) testUtils.getAPIUtils().post(testData,"/v2/store/order");
		orderId = ((JSONObject) response).get("id").toString();
	}

	@Then("Pet Order should be placed successfully")
	public void validateOrderCreation() {
		String quantity = testData.get("quantity").toString();
		String status = testData.get("status").toString();
		Assert.assertTrue("Id Value in response is NULL",orderId != null && orderId != "");
		Assert.assertTrue("Expected Status:"+status+" is not equal to Actual Status:"+((JSONObject) response).get("status").toString(),status.equals(((JSONObject) response).get("status")));
		Assert.assertTrue("Expected Quantity:"+quantity+" is not equal to Actual Quantity:"+((JSONObject) response).get("quantity"),quantity.equals(((JSONObject) response).get("quantity").toString()));
		Assert.assertTrue(((JSONObject) response).get("complete").equals(true));
	}
}