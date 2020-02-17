package com.kotlinspringvue.backend.service

import com.kotlinspringvue.backend.exception.ResourceNotFoundException
import com.kotlinspringvue.backend.jpa.Equipment
import com.kotlinspringvue.backend.repository.EquipmentRepository
import org.springframework.stereotype.Service

@Service
class EquipmentService(private val equipmentRepository: EquipmentRepository) {
    fun all(): Iterable<Equipment> = equipmentRepository.findAll()

    fun get(id: Long): Equipment = equipmentRepository.findById(id).orElseThrow { ResourceNotFoundException("Equipment", id) }

    fun add(equipment: Equipment): Equipment = equipmentRepository.save(equipment)

    fun edit(id: Long, equipment: Equipment): Equipment = equipmentRepository.save(equipment.copy(id = id))

    fun remove(id: Long) = equipmentRepository.deleteById(id)
}