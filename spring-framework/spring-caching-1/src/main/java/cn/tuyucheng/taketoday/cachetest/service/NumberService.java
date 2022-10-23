package cn.tuyucheng.taketoday.cachetest.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class NumberService {
    public static final Logger LOGGER = LoggerFactory.getLogger(NumberService.class);

    @Cacheable(value = "squareCache", key = "#number", condition = "#number>10")
    public BigDecimal square(Long number) {
        BigDecimal square = BigDecimal.valueOf(number).multiply(BigDecimal.valueOf(number));
        LOGGER.info("square of {} is {}", number, square);
        return square;
    }
}