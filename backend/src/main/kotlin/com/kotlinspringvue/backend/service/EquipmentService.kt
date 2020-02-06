package com.kotlinspringvue.backend.service

import com.kotlinspringvue.*
import com.kotlinspringvue.backend.jpa.Equipment
import com.kotlinspringvue.backend.repository.EquipmentRepository
import org.springframework.stereotype.Service


@Service // Позволяем IoC контейнеру внедрять класс
class EquipmentService(private val equipmentRepository: EquipmentRepository) {
    fun all(): Iterable<Equipment> = equipmentRepository.findAll()

    fun get(id: Long): Equipment = equipmentRepository.findById(id).orElse(null)

    fun add(equipment: Equipment): Equipment = equipmentRepository.save(equipment)

    fun edit(id: Long, equipment: Equipment): Equipment = equipmentRepository.save(equipment.copy(id = id))
    // Сохраняем копию объекта с указанным id в БД. Идиоматика Kotlin говорит что НЕ изменяемый - всегда лучше чем изменяемый (никто не поправит значение в другом потоке) и предлагает метод copy для копирования объектов (специальных классов для хранения данных) с возможностью замены значений

    fun remove(id: Long) = equipmentRepository.deleteById(id)
}