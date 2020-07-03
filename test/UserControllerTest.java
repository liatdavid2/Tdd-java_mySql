package com.group.db.springbootmysql.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
		ResponseEntity<Object> res = postSignUp(user,Object.class);
		assertThat(res.getStatusCode()).isEqualTo(HttpStatus.OK);
	}
	
	@Test
	public void postUser_whenUserIsValid_passwordIsHashedInDB() {
		User user = createValidUser();
		ResponseEntity<Object> res = postSignUp(user,Object.class);		
		assertThat(res.getStatusCode()).isEqualTo(HttpStatus.OK);
	}
	
	@Test
	public void postUser_whenUserIsValid_receiveSuccessMessage() {
		User user = createValidUser();
		ResponseEntity<GenericResponse> res = postSignUp(user,GenericResponse.class);
		assertThat(res.getBody().getMessage()).isNotNull();
	}
	

	private User createValidUser() {
		User user = new User();
		user.setID(2);
		user.setName("test-user");
		user.setPassword("P4ssword");
		return user;
	}
	
	@Test
	public void GetAllUsers_receiveOk() {
		ResponseEntity<Object> res = testRestTemplate
				.getForEntity(REST_1_0_USERS +"all", Object.class);		
		assertThat(res.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	public <T> ResponseEntity<T> postSignUp(Object requset,Class<T>response){
		return testRestTemplate.postForEntity(REST_1_0_USERS+"add", requset, response);
	}
	
	@Test
	public void postUser_whenUserHasNullUserName_receiveBadRequest() {
		User user = createValidUser();
		user.setName(null);		
		ResponseEntity<Object> res = postSignUp(user,Object.class);
		System.out.println(res.getStatusCode());
		assertThat(res.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
	}
	
	@Test
	public void postUser_whenUserHasNullPassword_receiveBadRequest() {
		User user = createValidUser();
		user.setPassword(null);		
		ResponseEntity<Object> res = postSignUp(user,Object.class);
		System.out.println(res.getStatusCode());
		assertThat(res.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
	}
	
	@Test
	public void postUser_whenUserHasUserNameWithLessThanRequired_receiveBadRequest() {
		User user = createValidUser();
		user.setName("abc");		
		ResponseEntity<Object> res = postSignUp(user,Object.class);
		System.out.println(res.getStatusCode());
		assertThat(res.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
	}
	
	@Test
	public void postUser_whenUserHasUserNameWithMoreThanRequired_receiveBadRequest() {
		User user = createValidUser();
		String valueOf256Chars = IntStream.rangeClosed(1, 256).mapToObj(x -> "a").collect(Collectors.joining());
		user.setName(valueOf256Chars);		
		ResponseEntity<Object> res = postSignUp(user,Object.class);
		System.out.println(res.getStatusCode());
		assertThat(res.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
	}
	
	
	@Test
	public void postUser_whenUserHasPasswordWithLessThanRequired_receiveBadRequest() {
		User user = createValidUser();
		user.setPassword("P4s");		
		ResponseEntity<Object> res = postSignUp(user,Object.class);
		System.out.println(res.getStatusCode());
		assertThat(res.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
	}
	@Test
	public void postUser_whenUserHasPasswordWithMoreThanRequired_receiveBadRequest() {
		User user = createValidUser();
		String valueOf256Chars = IntStream.rangeClosed(1, 256).mapToObj(x -> "a").collect(Collectors.joining());
		user.setPassword(valueOf256Chars + "A1");		
		ResponseEntity<Object> res = postSignUp(user,Object.class);
		System.out.println(res.getStatusCode());
		assertThat(res.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
	}
	
	@Test
	public void postUser_whenUserHasPasswordWithAllUppercase_receiveBadRequest() {
		User user = createValidUser();
		user.setPassword("ABCDEFGHI");		
		ResponseEntity<Object> res = postSignUp(user,Object.class);
		System.out.println(res.getStatusCode());
		assertThat(res.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
	}
	@Test
	public void postUser_whenUserHasPasswordWithAllLowercase_receiveBadRequest() {
		User user = createValidUser();
		user.setPassword("abcdefghi");		
		ResponseEntity<Object> res = postSignUp(user,Object.class);
		System.out.println(res.getStatusCode());
		assertThat(res.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
	}
	@Test
	public void postUser_whenUserHasPasswordWithAllDigits_receiveBadRequest() {
		User user = createValidUser();
		user.setPassword("123456789");		
		ResponseEntity<Object> res = postSignUp(user,Object.class);
		System.out.println(res.getStatusCode());
		assertThat(res.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
	}
}
