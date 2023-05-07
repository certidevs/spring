package com.example

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringWebfluxKotlinApplication

fun main(args: Array<String>) {
    runApplication<SpringWebfluxKotlinApplication>(*args)
}
