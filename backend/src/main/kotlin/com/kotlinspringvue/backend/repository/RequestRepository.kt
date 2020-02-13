package com.kotlinspringvue.backend.repository

import com.kotlinspringvue.backend.jpa.Request
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RequestRepository : JpaRepository<Request, Long> {
    fun findByStatus(status: Long): List<Request>
}