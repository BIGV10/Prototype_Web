package com.kotlinspringvue.backend.controller

import com.kotlinspringvue.backend.jpa.Equipment
import com.kotlinspringvue.backend.jpa.Request
import com.kotlinspringvue.backend.repository.EquipmentRepository
import com.kotlinspringvue.backend.repository.RequestRepository
import com.kotlinspringvue.backend.service.RequestService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api")
class RequestController(private val requestService: RequestService) {

    @Autowired
    lateinit var requestRepository: RequestRepository

    @Autowired
    lateinit var equipmentRepository: EquipmentRepository

    @GetMapping("/request")
    fun getRequestAll() = requestService.all()

    @PostMapping("/request")
    @ResponseStatus(HttpStatus.CREATED)
    fun postRequest(@RequestBody request: Request) = requestService.add(request)

    @PostMapping("/request/{requestId}/equipment/{equipmentId}")
    fun postEquipmentToRequest(@PathVariable requestId: Long, @PathVariable equipmentId: Long): Request {
        val newEquipment: Equipment = equipmentRepository.findById(equipmentId).orElse(null)
        val currentRequest: Request = requestRepository.findById(requestId).orElse(null)
        currentRequest.equipment.add(newEquipment)
        requestRepository.save(currentRequest)
        return currentRequest
    }

    @GetMapping("/request/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    fun getRequestId(@PathVariable id: Long) = requestService.get(id)

    @GetMapping("/request/")
    fun getRequestStatus(@RequestParam(name = "status") value: Long) = requestRepository.findByStatus(value)

    @PutMapping("/request/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun putRequestId(@PathVariable id: Long, @RequestBody request: Request) = requestService.edit(id, request)

    @DeleteMapping("/request/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun deleteRequestId(@PathVariable id: Long) = requestService.remove(id)
}