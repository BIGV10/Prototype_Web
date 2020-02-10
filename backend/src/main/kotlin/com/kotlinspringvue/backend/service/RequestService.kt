package com.kotlinspringvue.backend.service

import com.kotlinspringvue.backend.jpa.Request
import com.kotlinspringvue.backend.repository.EquipmentRepository
import com.kotlinspringvue.backend.repository.RequestRepository
import org.springframework.stereotype.Service

@Service // Позволяем IoC контейнеру внедрять класс
class RequestService(private val requestRepository: RequestRepository) {
    fun all(): Iterable<Request> = requestRepository.findAll()

    fun get(id: Long): Request = requestRepository.findById(id).orElse(null)

    fun add(request: Request): Request = requestRepository.save(request)

    fun edit(id: Long, request: Request): Request = requestRepository.save(request.copy(id = id))

    fun remove(id: Long) = requestRepository.deleteById(id)
}