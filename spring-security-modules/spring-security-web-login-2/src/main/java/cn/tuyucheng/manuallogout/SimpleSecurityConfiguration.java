package cn.tuyucheng.manuallogout;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.logout.HeaderWriterLogoutHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.header.writers.ClearSiteDataHeaderWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;

import static org.springframework.security.web.header.writers.ClearSiteDataHeaderWriter.Directive.*;

@Configuration
@EnableWebSecurity
public class SimpleSecurityConfiguration {

	private static final Logger logger = LoggerFactory.getLogger(SimpleSecurityConfiguration.class);

	@Order(4)
	@Configuration
	public static class LogoutOnRequestConfiguration extends WebSecurityConfigurerAdapter {
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.antMatcher("/request/**")
					.authorizeRequests(auth -> auth.anyRequest().permitAll())
					.logout(logout -> logout.logoutUrl("/request/logout")
							.addLogoutHandler((request, response, auth) -> {
								try {
									request.logout();
								} catch (ServletException e) {
									logger.error(e.getMessage());
								}
							}));
		}
	}

	@Order(3)
	@Configuration
	public static class DefaultLogoutConfiguration extends WebSecurityConfigurerAdapter {
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.antMatcher("/basic/**")
					.authorizeRequests(auth -> auth.anyRequest()
							.permitAll())
					.logout(logout -> logout.logoutUrl("/basic/basiclogout"));
		}
	}

	@Order(2)
	@Configuration
	public static class AllCookieClearingLogoutConfiguration extends WebSecurityConfigurerAdapter {
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.antMatcher("/cookies/**")
					.authorizeRequests(auth -> auth.anyRequest().permitAll())
					.logout(logout -> logout.logoutUrl("/cookies/cookielogout")
							.addLogoutHandler(new SecurityContextLogoutHandler())
							.addLogoutHandler((request, response, auth) -> {
								for (Cookie cookie : request.getCookies()) {
									String cookieName = cookie.getName();
									Cookie cookieToDelete = new Cookie(cookieName, null);
									cookieToDelete.setMaxAge(0);
									response.addCookie(cookieToDelete);
								}
							}));
		}
	}

	@Order(1)
	@Configuration
	public static class ClearSiteDataHeaderLogoutConfiguration extends WebSecurityConfigurerAdapter {

		private static final ClearSiteDataHeaderWriter.Directive[] SOURCE = {CACHE, COOKIES, STORAGE, EXECUTION_CONTEXTS};

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.antMatcher("/csd/**")
					.authorizeRequests(auth -> auth.anyRequest()
							.permitAll())
					.logout(logout -> logout.logoutUrl("/csd/csdlogout")
							.addLogoutHandler(new HeaderWriterLogoutHandler(new ClearSiteDataHeaderWriter(SOURCE))));
		}
	}
}