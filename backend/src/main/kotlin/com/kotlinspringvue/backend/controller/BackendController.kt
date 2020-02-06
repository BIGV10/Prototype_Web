package com.kotlinspringvue.backend.controller

import org.springframework.web.bind.annotation.*
import com.kotlinspringvue.backend.model.Greeting
import java.util.concurrent.atomic.AtomicLong
import com.kotlinspringvue.backend.jpa.Equipment
import com.kotlinspringvue.backend.repository.*
import com.kotlinspringvue.backend.service.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus

@RestController
@RequestMapping("api")
class EquipmentController(private val equipmentService: EquipmentService){
    @GetMapping("/equipment")
    fun getEquipmentAll() = equipmentService.all()

    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
    fun postEquipment(@RequestBody equipment: Equipment) = equipmentService.add(equipment)

    @GetMapping("/equipment/{id}")
//    @ResponseStatus(HttpStatus.FOUND)
    fun getEquipmentId(@PathVariable id: Long) = equipmentService.get(id)

    @PutMapping("/equipment/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun putEquipmentId(@PathVariable id: Long, @RequestBody equipment: Equipment) = equipmentService.edit(id, equipment)

    @DeleteMapping("/equipment/{id}")
//    @ResponseStatus(HttpStatus.OK)
    fun deleteEquipmentId(@PathVariable id: Long) = equipmentService.remove(id)
}

class BackendController() {
    val counter = AtomicLong()

    @GetMapping("/greeting")
    fun greeting(@RequestParam(value = "name", defaultValue = "World") name: String) =
            Greeting(counter.incrementAndGet(), "Hello, $name")
}