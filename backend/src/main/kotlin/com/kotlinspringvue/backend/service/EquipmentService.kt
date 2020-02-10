package com.kotlinspringvue.backend.service

import com.kotlinspringvue.backend.jpa.Equipment
import com.kotlinspringvue.backend.repository.EquipmentRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException


@Service // Позволяем IoC контейнеру внедрять класс
class EquipmentService(private val equipmentRepository: EquipmentRepository) {
    fun all(): Iterable<Equipment> = equipmentRepository.findAll()

    fun get(id: Long): Equipment = equipmentRepository.findById(id).orElse(null)

    fun add(equipment: Equipment): Equipment = equipmentRepository.save(equipment)

    fun edit(id: Long, equipment: Equipment): Equipment = equipmentRepository.save(equipment.copy(id = id))

    fun remove(id: Long) = equipmentRepository.deleteById(id)
}