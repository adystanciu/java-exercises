package com.stady.config;

import com.stady.security.CustomUserDetails;
import com.stady.security.CustomUserDetailsManager;
import com.stady.security.PlainTextPasswordEncoder;

import org.springframework.security.core.userdetails.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // any request is authorized
//        http.authorizeRequests().anyRequest().permitAll();

        // any requested ask for auth
        http.httpBasic(); // configure to basic auth
        http.authorizeRequests().anyRequest().authenticated();

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new PlainTextPasswordEncoder();
//        return NoOpPasswordEncoder.getInstance();
    }
/* Note that the 3 classes from security package are customs. There is a posibility to make the same thing with the default classes.
 * Please check the code which is commented */

    @Bean
    public UserDetailsManager userDetailsManager(){
        CustomUserDetailsManager userDetailsManager = new CustomUserDetailsManager();
        userDetailsManager.createUser(new CustomUserDetails("Alex","12345"));

//        InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
//        UserDetails user = User.withUsername("Alin")
//                                .password("12345")
//                                .authorities("ADMIN")
//                                .build();
//        userDetailsManager.createUser(user);

        return userDetailsManager;
    }
}
