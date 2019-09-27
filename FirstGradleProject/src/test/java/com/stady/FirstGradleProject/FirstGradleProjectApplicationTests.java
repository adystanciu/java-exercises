package com.stady.FirstGradleProject;

import com.stady.FirstGradleProject.controllers.UserController;
import com.stady.FirstGradleProject.model.User;
import com.stady.FirstGradleProject.repository.UserRepository;
import com.stady.FirstGradleProject.services.UserServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FirstGradleProjectApplicationTests {

	@Autowired
	private UserController userController;
	@Autowired
	private UserServiceImpl userServiceImpl;
	@MockBean
	private UserRepository userRepository;



	@Test
	public void contextLoads() {
		Assert.assertNotNull(userController);
		Assert.assertNotNull(userServiceImpl);
		Assert.assertNotNull(userRepository);
	}


	@Test
	public void addTest() throws Exception {
		User user = new User();
		userController.addUser(user);
		Mockito.verify(userRepository, Mockito.times(1)).save(user);
	}

}
