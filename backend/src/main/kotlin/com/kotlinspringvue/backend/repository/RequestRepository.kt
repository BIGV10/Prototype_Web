package com.kotlinspringvue.backend.repository

import com.kotlinspringvue.backend.jpa.Request
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface RequestRepository: CrudRepository<Request, Long> {

}