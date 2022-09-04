package utils;

import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import groovy.json.JsonOutput;
import groovy.json.JsonSlurper;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;

public class TestUtils {

	WebDriver driver;
	ApiUtils apiUtils;
	HashMap<String, List<HashMap<String, Object>>> testData = new HashMap<String, List<HashMap<String, Object>>>();

	public WebDriver getWebDriver() {
		return this.driver;
	}

	public void setWebDriver(WebDriver driver) {
		this.driver = driver;
	}

	public void setAPIUtils(ApiUtils apiUtils) {
		this.apiUtils = apiUtils;
	}
	
	public ApiUtils getAPIUtils() {
		return apiUtils;
	}
	
	public WebDriverWait getWebDriverWait() {
		return new WebDriverWait(driver,30);
	}
	
	@SuppressWarnings("unchecked")
	public void parseTestDataFile(String fileName) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			String data = JsonOutput.toJson(new JsonSlurper().parse(new FileReader(fileName)));
			testData = (HashMap<String, List<HashMap<String, Object>>>) mapper.readValue(data, Map.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public HashMap<String, Object> getTestData(String key) {
		return testData.get(key).get(0);
	}
}