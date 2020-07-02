package com.group.db.springbootmysql.test;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import com.group.db.springbootmysql.model.User;
import com.group.db.springbootmysql.shared.GenericResponse;
import com.group.db.springbootmysql.SpringBootMysqlApplication;
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT
,classes = SpringBootMysqlApplication.class)
@ActiveProfiles("test")
public class UserControllerTest {
	
	private static final String REST_1_0_USERS = "http://localhost:7000/rest/1.0/users/";
	@Autowired
	TestRestTemplate testRestTemplate;
	
	@Test
	public void postUser_whenUserIsValid_receiveOk() {
		User user = createValidUser();
		ResponseEntity<Object> res = testRestTemplate.postForEntity(REST_1_0_USERS+"add",user,Object.class);		
		assertThat(res.getStatusCode()).isEqualTo(HttpStatus.OK);
	}
	
	@Test
	public void postUser_whenUserIsValid_passwordIsHashedInDB() {
		User user = createValidUser();
		ResponseEntity<Object> res = testRestTemplate.postForEntity(REST_1_0_USERS+"add",user,Object.class);		
		assertThat(res.getStatusCode()).isEqualTo(HttpStatus.OK);
	}
	
	@Test
	public void postUser_whenUserIsValid_receiveSuccessMessage() {
		User user = createValidUser();
		ResponseEntity<GenericResponse> res = testRestTemplate.postForEntity(REST_1_0_USERS+"add",user,GenericResponse.class);		
		assertThat(res.getBody().getMessage()).isNotNull();
	}
	

	private User createValidUser() {
		User user = new User();
		user.setID(1);
		user.setName("gogo");
		user.setPassword("gogo777");
		return user;
	}
	
	@Test
	public void GetAllUsers_receiveOk() {
		ResponseEntity<Object> res = testRestTemplate
				.getForEntity(REST_1_0_USERS +"all", Object.class);		
		assertThat(res.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

}
