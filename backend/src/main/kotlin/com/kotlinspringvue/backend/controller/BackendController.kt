package com.kotlinspringvue.backend.controller

import com.kotlinspringvue.backend.model.Greeting
import org.springframework.web.bind.annotation.*
import java.util.concurrent.atomic.AtomicLong


class BackendController() {
    val counter = AtomicLong()

    @GetMapping("/greeting")
    fun greeting(@RequestParam(value = "name", defaultValue = "World") name: String) =
            Greeting(counter.incrementAndGet(), "Hello, $name")
}