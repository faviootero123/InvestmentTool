package com.crumbs.crumby.Configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("com.crumbs.crumby.Repository")
public class ApplicationConfiguration {

}
