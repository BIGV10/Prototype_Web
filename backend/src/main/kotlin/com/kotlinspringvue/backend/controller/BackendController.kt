package com.kotlinspringvue.backend.controller

import org.springframework.web.bind.annotation.*
import com.kotlinspringvue.backend.model.Greeting
import java.util.concurrent.atomic.AtomicLong
import com.kotlinspringvue.backend.jpa.Equipment
import com.kotlinspringvue.backend.repository.EquipmentRepository
import org.springframework.beans.factory.annotation.Autowired



@RestController
@RequestMapping("/api")
class BackendController() {

    @Autowired
    lateinit var equipmentRepository: EquipmentRepository

    val counter = AtomicLong()

    @GetMapping("/greeting")
    fun greeting(@RequestParam(value = "name", defaultValue = "World") name: String) =
            Greeting(counter.incrementAndGet(), "Hello, $name")

    @GetMapping("/equipment")
    fun getEquipment() = equipmentRepository.findAll()
}