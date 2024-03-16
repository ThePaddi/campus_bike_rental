package com.cj;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 疾风亦有归途
 * @version v1.0
 * @project campus_bike_rental
 * @package PACKAGE_NAME
 * @company 千锋教育
 * @date 2024/3/16 17:17
 */
@Slf4j
@SpringBootApplication
public class CampusBikeRentalApplication {
    public static void main(String[] args) {
        SpringApplication.run(CampusBikeRentalApplication.class);
        log.info("项目启动成功！");
    }
}
