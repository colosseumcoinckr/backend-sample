package org.colosseum.sample

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ExternalSampleApplication

fun main(args: Array<String>) {
    runApplication<ExternalSampleApplication>(*args)
}
