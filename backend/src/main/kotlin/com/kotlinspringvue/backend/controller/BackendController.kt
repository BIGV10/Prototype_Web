package com.kotlinspringvue.backend.controller

import org.springframework.web.bind.annotation.*
import com.kotlinspringvue.backend.jpa.Equipment
import com.kotlinspringvue.backend.jpa.Request
import com.kotlinspringvue.backend.model.Greeting
import java.util.concurrent.atomic.AtomicLong
import com.kotlinspringvue.backend.repository.*
import com.kotlinspringvue.backend.service.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus

@RestController
@RequestMapping("api")
class EquipmentController(private val equipmentService: EquipmentService){

    @Autowired
    lateinit var equipmentRepository :EquipmentRepository

    @GetMapping("/equipment")
    fun getEquipmentAll() = equipmentService.all()

    @PostMapping("/equipment")
    @ResponseStatus(HttpStatus.CREATED)
    fun postEquipment(@RequestBody equipment: Equipment) = equipmentService.add(equipment)

    @GetMapping("/equipment/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    fun getEquipmentId(@PathVariable id: Long) = equipmentService.get(id)

    @GetMapping("/equipment/")
    fun getEquipmentBarcode(@RequestParam(name = "barcode") value: String) = equipmentRepository.findByBarcode(value)

    @PutMapping("/equipment/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun putEquipmentId(@PathVariable id: Long, @RequestBody equipment: Equipment) = equipmentService.edit(id, equipment)

    @DeleteMapping("/equipment/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun deleteEquipmentId(@PathVariable id: Long) = equipmentService.remove(id)
}

@RestController
@RequestMapping("api")
class RequestController(private val requestService: RequestService){

    @Autowired
    lateinit var requestRepository: RequestRepository

    @GetMapping("/request")
    fun getEquipmentAll() = requestService.all()

    @PostMapping("/request")
    @ResponseStatus(HttpStatus.CREATED)
    fun postEquipment(@RequestBody request: Request) = requestService.add(request)

    @GetMapping("/request/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    fun getEquipmentId(@PathVariable id: Long) = requestService.get(id)

    @PutMapping("/request/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun putEquipmentId(@PathVariable id: Long, @RequestBody request: Request) = requestService.edit(id, request)

    @DeleteMapping("/request/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun deleteEquipmentId(@PathVariable id: Long) = requestService.remove(id)
}

class BackendController() {
    val counter = AtomicLong()

    @GetMapping("/greeting")
    fun greeting(@RequestParam(value = "name", defaultValue = "World") name: String) =
            Greeting(counter.incrementAndGet(), "Hello, $name")
}