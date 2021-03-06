package gr.hua.dit.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;


@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(securedEnabled = true)
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private DataSource dataSource;
	
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    
             auth.jdbcAuthentication().dataSource(dataSource)
            .passwordEncoder(passwordEncoder())
               .usersByUsernameQuery(
                "select username, password, enabled from user where username=?")
              .authoritiesByUsernameQuery(
                "select username, authority from user where username=?");
             
            // .authoritiesByUsernameQuery(
            //         "select username, authority from authorities where username=?");
System.out.println("eimai sth authedication");
    }
	
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**");

}

	/*public void addViewControllers(ViewControllerRegistry registry) {
	    registry.addViewController("/login").setViewName("login");
	  }*/
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		System.out.println("http security");
		http.authorizeRequests().anyRequest().authenticated().and().formLogin().loginPage("/login")
				.loginProcessingUrl("/authUser").permitAll().and().logout().logoutSuccessUrl("/login").permitAll()
			    .and()
			    .csrf().disable();
		
		//.logoutSuccessUrl("/login")
				//.and().exceptionHandling()
				//.accessDeniedPage("/403");
		
	//	http.authorizeRequests()
	//	.antMatchers("/teacher/*").hasRole("TEACHER")
	//	.antMatchers("/publicher/*").hasRole("PUBLISHER")
	//	.antMatchers("/student/*").hasRole("STUDENT")
	//	.antMatchers("/secretary/*").hasRole("SECRETARY")
	//	.antMatchers("/systems/**").hasRole("ADMIN")
	//	.and()
	//	.formLogin();  
			
}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
}

}
