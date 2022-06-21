package org.generation.FinalProject.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class WebSecurityConfig {

    /*
    1. To provide a datasource: Create an object for connection to the database Sp that we can query our users table. Datasourse refers to the URL database, login credential into the database.
    Will have to do DI - autowired annotation to an object with the datasource, Spring boot will auto inject the infromation from the application properties on the related configuration of the database that it requires.
     */
    @Autowired
    private DataSource dataSource;

    /*2) Datasource object establish, can connect to our database, make our query to our users table to get the username, password and role for checking*/
     /*
    username=? : represents a parameter to be supplied by the client (from the browser)
    usersByUsernameQuery : sets the query to be used for finding a user by their username
    authoritiesByUsernameQuery : Sets the query to be used for finding a user's
    authorities by their username
    */
    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .dataSource(dataSource)
                .usersByUsernameQuery("select username, password, enabled from users where username=?")
                .authoritiesByUsernameQuery("select username, role from users where username=?")
        ;
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf().disable();

        //Not using the Spring Security HttpSecurity default login page
        http.formLogin().loginPage("/login");

        //if user successfully login, user will be directed to the productform.html
        http.formLogin()
                .defaultSuccessUrl("/productform");

        //if user successfully logout, user will be directed to the index.html
        http.logout()
                .logoutSuccessUrl("/index");

        /*.antMatchers(......).permitAll() - tells Spring Security that these webpages
         do not need to have login services

        .antMatchers(.....).hasRole("ADMIN") - tells Spring Security that only user
        with ADMIN role will be able to access the productform.html

        logout method : configure logout functionality provided by Spring Security -
        ensure that the login session to be invalidated. kill the session

         */
        http.authorizeRequests()
                .antMatchers("/", "Homepage", "/products", "/aboutus", "/login").permitAll()
                .antMatchers("/addproduct/**").hasRole("ADMIN")
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .and()
                .logout().permitAll();

        return http.build();
    }
}






