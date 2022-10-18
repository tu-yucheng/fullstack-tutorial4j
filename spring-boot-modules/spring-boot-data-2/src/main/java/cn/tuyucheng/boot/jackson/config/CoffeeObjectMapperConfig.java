package cn.tuyucheng.boot.jackson.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class CoffeeObjectMapperConfig {

    @Bean
    @Primary
    public ObjectMapper objectMapper() {
        JavaTimeModule module = new JavaTimeModule();
        module.addSerializer(CoffeeConstants.LOCAL_DATETIME_SERIALIZER);
        return new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL).registerModule(module);
    }
}