package com.kotlinspringvue.backend.controller

import com.kotlinspringvue.backend.exception.ResourceNotFoundException
import com.kotlinspringvue.backend.jpa.Equipment
import com.kotlinspringvue.backend.repository.EquipmentRepository
import com.kotlinspringvue.backend.service.EquipmentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.persistence.NonUniqueResultException


@RestController
@RequestMapping("api")
class EquipmentController(private val equipmentService: EquipmentService) {

    @Autowired
    lateinit var equipmentRepository: EquipmentRepository

    @GetMapping("/equipments")
    @ResponseStatus(HttpStatus.OK)
    fun getEquipmentAll() = equipmentService.all()

    @PostMapping("/equipments")
    @ResponseStatus(HttpStatus.CREATED)
    fun postEquipment(@RequestBody equipment: Equipment) = equipmentService.add(equipment)

    @GetMapping("/equipments/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getEquipmentId(@PathVariable id: Long) = equipmentService.get(id)

    @GetMapping("/equipments", params = ["barcode"])
    @ResponseStatus(HttpStatus.OK)
    fun getEquipmentBarcode(@RequestParam(name = "barcode", required = false) value: String): Equipment {
        try {
            return equipmentRepository.findByBarcode(value)
        }
        catch (e: EmptyResultDataAccessException) {
            throw  ResourceNotFoundException("Equipment", value)
        }
        catch (e: NonUniqueResultException) {
            throw  NonUniqueResultException(e.message)
        }
    }

    @PutMapping("/equipments/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun putEquipmentId(@PathVariable id: Long, @RequestBody equipment: Equipment) = equipmentService.edit(id, equipment)

    @DeleteMapping("/equipments/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun deleteEquipmentId(@PathVariable id: Long) = equipmentService.remove(id)
}