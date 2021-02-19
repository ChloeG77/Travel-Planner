package com.laioffer.travel_planner_backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import javax.sql.DataSource;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //csrf() resolve vulnerability, prevent fishing website
        http
                .csrf().disable() //current no this functionality
                .formLogin()
                .loginPage("/index")
                .loginProcessingUrl("/login");

//

        http
                .authorizeRequests()
                .antMatchers("/trip/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                .anyRequest().permitAll(); // the rest requst do not need any authentication

        // get*/: * means regular expression
//            /**: match any url after it.  only one *, cannot match multiple /a/b (only match /a)

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication().withUser("xma@gmail.com").password("123").authorities("ROLE_ADMIN");
        //use for developer test
        auth
                .jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("SELECT emailId, password, enabled FROM users WHERE email_id=?")
                .authoritiesByUsernameQuery("SELECT emailId, authorities FROM authorities WHERE email_id=?");
        // actual usage
    }

    @SuppressWarnings("deprecation")
    @Bean
    public static NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }
    // fronted encode is better
}
