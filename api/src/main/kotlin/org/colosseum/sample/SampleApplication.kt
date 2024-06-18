package org.colosseum.sample

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SampleApplication

fun main(args: Array<String>) {
    System.setProperty("spring.config.name", "application,application-infra")
    runApplication<SampleApplication>(*args)
}
