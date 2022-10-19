package cn.tuyucheng.acl.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "cn.tuyucheng.acl.persistence.dao")
@PropertySource("classpath:cn.tuyucheng.acl.datasource.properties")
@EntityScan(basePackages = {"cn.tuyucheng.acl.persistence.entity"})
public class JPAPersistenceConfig {

}