package com.kotlinspringvue.backend.controller

import com.kotlinspringvue.backend.jpa.Equipment
import com.kotlinspringvue.backend.repository.EquipmentRepository
import com.kotlinspringvue.backend.service.EquipmentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api")
class EquipmentController(private val equipmentService: EquipmentService) {

    @Autowired
    lateinit var equipmentRepository: EquipmentRepository

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