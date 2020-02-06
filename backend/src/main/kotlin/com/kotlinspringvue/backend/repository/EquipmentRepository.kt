package com.kotlinspringvue.backend.repository

import com.kotlinspringvue.backend.jpa.Equipment
import org.springframework.data.repository.*
import org.springframework.stereotype.Repository
import org.springframework.data.rest.core.annotation.RepositoryRestResource

//@RepositoryRestResource(collectionResourceRel = "api", path = "equipment")
@Repository
interface EquipmentRepository: CrudRepository<Equipment, Long> {

}