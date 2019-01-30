/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stady.FirstGradleProject;


import com.stady.FirstGradleProject.model.User;
import com.stady.FirstGradleProject.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
//@ActiveProfiles("test")
public class UserIntegrationTest {
    
    @Value("${server.port}")
    private int port;
    
    @Autowired
    private RestTemplate restTemplate;
    
    @Autowired
    private UserRepository userRepository;
    
    @Test
    public void addUserTest(){
     String url = "http://localhost:"+port+"/rest/users";
     User user = new User();
     user.setEmail("ana_ilie@com");
     user.setPassword("12344");
     user.setUsername("ana_maria_test");
    
     restTemplate.postForObject(url, user, Void.class);
     User userReturned = userRepository.findByUsername(user.getUsername());
     Assert.assertNotNull(userReturned);
     Assert.assertEquals(userReturned.getUsername(), user.getUsername());
    }
    
     @Test
    public void getUsersTest(){
     String url = "http://localhost:"+port+"/rest/users";
     ResponseEntity<User[]> response =  restTemplate.getForEntity(url, User[].class);
     List<User> users = Arrays.asList(response.getBody());
     Assert.assertNotNull(users);
     for(User u: users){
         System.out.println(u);
        }
     
     Assert.assertTrue(!users.isEmpty());
    }
}
