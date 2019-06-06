package com.ljshuoda;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ljshuoda.config.CustomMyProps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootApplication(scanBasePackages = "com.ljshuoda.config")
class BootConfig {

}

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BootConfig.class)
public class MySpringBootApplicationTests {

	@Autowired
	private CustomMyProps customMyProps;

	private ObjectMapper objectMapper = new ObjectMapper();

	@Test
	public void common() throws JsonProcessingException{
		//JSONObject o = new JSONObject();
		//objectMapper.readValue(src, valueType)
		Map<String, Object> m = new HashMap<>();
		m.put("k", "<我是中文>");
		String s = objectMapper.writeValueAsString(m);
		System.out.println(s);
	}
	
	@Test
	public void propsTest() throws JsonProcessingException {
		System.out.println("simpleProp: " + customMyProps.getSimpleProp());
		System.out.println("arrayProps: " + objectMapper.writeValueAsString(customMyProps.getArrayProps()));
		System.out.println("listProp1: " + objectMapper.writeValueAsString(customMyProps.getListProp1()));
		System.out.println("listProp2: " + objectMapper.writeValueAsString(customMyProps.getListProp2()));
		System.out.println("mapProps: " + objectMapper.writeValueAsString(customMyProps.getMapProps()));
	}

}
