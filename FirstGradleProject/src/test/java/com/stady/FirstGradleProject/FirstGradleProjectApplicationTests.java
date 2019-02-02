package com.stady.FirstGradleProject;

import com.stady.FirstGradleProject.controllers.MainController;
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
	private MainController mainController;
	@Autowired
	private UserServiceImpl userServiceImpl;
	@MockBean
	private UserRepository userRepository;



	@Test
	public void contextLoads() {
		Assert.assertNotNull(mainController);
		Assert.assertNotNull(userServiceImpl);
		Assert.assertNotNull(userRepository);
	}


	@Test
	public void addTest(){
		User user = new User();
		mainController.addUser(user);
		Mockito.verify(userRepository, Mockito.times(1)).save(user);
	}

}
