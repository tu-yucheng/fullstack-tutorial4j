package cn.tuyucheng.taketoday.jdbcauthentication.postgre;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:application-postgres.properties")
public class PostgresJdbcAuthenticationApplication {

	public static void main(String[] args) {
		SpringApplication.run(PostgresJdbcAuthenticationApplication.class, args);
	}
}