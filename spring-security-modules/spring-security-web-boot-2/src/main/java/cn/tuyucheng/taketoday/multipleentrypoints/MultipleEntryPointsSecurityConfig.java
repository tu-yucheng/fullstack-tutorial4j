package cn.tuyucheng.taketoday.multipleentrypoints;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class MultipleEntryPointsSecurityConfig {

	@Bean
	public UserDetailsService userDetailsService() throws Exception {
		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
		manager.createUser(User.withUsername("user").password(encoder().encode("userPass")).roles("USER").build());
		manager.createUser(User.withUsername("admin").password(encoder().encode("adminPass")).roles("ADMIN").build());
		return manager;
	}

	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	@Configuration
	@Order(1)
	public static class App1ConfigurationAdapter extends WebSecurityConfigurerAdapter {

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.antMatcher("/admin/**")
					.authorizeRequests().anyRequest().hasRole("ADMIN")
					.and().httpBasic().authenticationEntryPoint(authenticationEntryPoint())
					.and().exceptionHandling().accessDeniedPage("/403");
		}

		@Bean
		public AuthenticationEntryPoint authenticationEntryPoint() {
			BasicAuthenticationEntryPoint entryPoint = new BasicAuthenticationEntryPoint();
			entryPoint.setRealmName("admin realm");
			return entryPoint;
		}
	}

	@Configuration
	@Order(2)
	public static class App2ConfigurationAdapter extends WebSecurityConfigurerAdapter {

		protected void configure(HttpSecurity http) throws Exception {
			http.antMatcher("/user/**")
					.authorizeRequests().anyRequest().hasRole("USER")
					.and().formLogin().loginProcessingUrl("/user/login")
					.failureUrl("/userLogin?error=loginError").defaultSuccessUrl("/user/myUserPage")
					.and().logout().logoutUrl("/user/logout").logoutSuccessUrl("/multipleHttpLinks")
					.deleteCookies("JSESSIONID")
					.and().exceptionHandling()
					.defaultAuthenticationEntryPointFor(loginUrlAuthenticationEntryPointWithWarning(), new AntPathRequestMatcher("/user/private/**"))
					.defaultAuthenticationEntryPointFor(loginUrlAuthenticationEntryPoint(), new AntPathRequestMatcher("/user/general/**"))
					.accessDeniedPage("/403")
					.and().csrf().disable();
		}

		@Bean
		public AuthenticationEntryPoint loginUrlAuthenticationEntryPoint() {
			return new LoginUrlAuthenticationEntryPoint("/userLogin");
		}

		@Bean
		public AuthenticationEntryPoint loginUrlAuthenticationEntryPointWithWarning() {
			return new LoginUrlAuthenticationEntryPoint("/userLoginWithWarning");
		}
	}

	@Configuration
	@Order(3)
	public static class App3ConfigurationAdapter extends WebSecurityConfigurerAdapter {

		protected void configure(HttpSecurity http) throws Exception {
			http.antMatcher("/guest/**").authorizeRequests().anyRequest().permitAll();
		}
	}
}