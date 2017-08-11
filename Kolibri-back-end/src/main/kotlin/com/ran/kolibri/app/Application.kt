package com.ran.kolibri.app

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@ComponentScan(basePackages = arrayOf("com.ran.kolibri"))
@EnableJpaRepositories(basePackages = arrayOf("com.ran.kolibri.repository"))
@EntityScan(basePackages = arrayOf("com.ran.kolibri.entity"))
class Application

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}