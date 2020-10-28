package TestSuites;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Pojo1.EmployeeWithOutJsonIgnore;
import net.javacrumbs.jsonunit.JsonAssert;
import net.javacrumbs.jsonunit.core.Configuration;
import net.javacrumbs.jsonunit.core.Option;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

public class TestSuite1 {
	
	Configuration config;

@BeforeMethod
  public void beforeMethod() {
	  config = Configuration.empty().withIgnorePlaceholder("*").when(Option.IGNORING_ARRAY_ORDER);
  }
  
@Test
  public void Test1() {
	  EmployeeWithOutJsonIgnore employeeObject = new EmployeeWithOutJsonIgnore();
	  employeeObject.setAge(28);
	  employeeObject.setDesignation("Senior Software Test Automation Engineer");
	  employeeObject.setFirstName("Gowtham");
	  employeeObject.setLastName("Epparla");
	  String payloadAsString=null;
	  ObjectMapper objectMapper = new ObjectMapper();
	  try {
		 payloadAsString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(employeeObject);
	} catch (JsonProcessingException e) {
		e.printStackTrace();
	}
	  System.out.println(payloadAsString);
	  EmployeeWithOutJsonIgnore outputObject=null;
	  
	  try {
		  outputObject = objectMapper.readValue(payloadAsString, EmployeeWithOutJsonIgnore.class);
	} catch (JsonMappingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (JsonProcessingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  System.out.println(outputObject.getAge());
	  
	  JsonAssert.assertJsonEquals(payloadAsString, payloadAsString, config);
  }

  @AfterMethod
  public void afterMethod() {
  }

}
