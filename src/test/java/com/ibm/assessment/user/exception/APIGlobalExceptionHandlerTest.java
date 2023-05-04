package com.ibm.assessment.user.exception;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.ibm.assessment.user.model.RegistrationRequest;
import com.ibm.assessment.user.service.MessageProvider;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc

class APIGlobalExceptionHandlerTest {
	@Autowired
	MockMvc mockMvc;
	@Mock
	MessageProvider messageProvider;

	ObjectMapper objectMapper = JsonMapper.builder().addModule(new JavaTimeModule()).build();

	@Test
	void test_handleMethodArgumentNotValidException() throws Exception {
		RegistrationRequest request = new RegistrationRequest("username", "password", "");
		mockMvc.perform(post("/api/v1/user/register").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(request)))
				.andExpect(status().is(400));
	}
	
	@Test
	void test_handleHttpMessageNotReadableException() throws Exception {
		mockMvc.perform(post("/api/v1/user/register").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).content(""))
				.andExpect(status().is(400));
	}

	

	@Test
	void test_handleHttpMethodTypeException() throws Exception {
		mockMvc.perform(get("/api/v1/user/register")).andExpect(status().is(405));
	}


}
