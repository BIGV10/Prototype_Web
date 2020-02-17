package com.kotlinspringvue.backend.repository

import com.kotlinspringvue.backend.jpa.Equipment
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface EquipmentRepository : JpaRepository<Equipment, Long> {
    fun findByBarcode(barcode: String): Equipment
}
