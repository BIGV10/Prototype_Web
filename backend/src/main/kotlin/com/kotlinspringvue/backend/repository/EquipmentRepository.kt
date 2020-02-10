package com.kotlinspringvue.backend.repository

import com.kotlinspringvue.backend.jpa.Equipment
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

//@RepositoryRestResource(collectionResourceRel = "api", path = "equipment")
@Repository
interface EquipmentRepository : CrudRepository<Equipment, Long> {
    fun findByBarcode(barcode: String): Equipment
}
