package com.kotlinspringvue.backend.controller

import com.kotlinspringvue.backend.jpa.Equipment
import com.kotlinspringvue.backend.jpa.Request
import com.kotlinspringvue.backend.repository.EquipmentRepository
import com.kotlinspringvue.backend.repository.RequestRepository
import com.kotlinspringvue.backend.service.RequestService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api")
class RequestController(private val requestService: RequestService) {

    @Autowired
    lateinit var requestRepository: RequestRepository

    @Autowired
    lateinit var equipmentRepository: EquipmentRepository

    @GetMapping("/requests")
    @ResponseStatus(HttpStatus.OK)
    fun getRequestAll() = requestService.all()

    @PostMapping("/requests")
    @ResponseStatus(HttpStatus.CREATED)
    fun postRequest(@RequestBody request: Request) = requestService.add(request)

    @PostMapping("/requests/{requestId}/equipment/{equipmentId}")
    @ResponseStatus(HttpStatus.CREATED)
    fun postEquipmentToRequest(@PathVariable requestId: Long, @PathVariable equipmentId: Long): Request {
        val newEquipment: Equipment = equipmentRepository.findById(equipmentId).orElse(null)
        val currentRequest: Request = requestRepository.findById(requestId).orElse(null)
        currentRequest.equipment.add(newEquipment)
        requestRepository.save(currentRequest)
        return currentRequest
    }

    @GetMapping("/requests/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getRequestId(@PathVariable id: Long) = requestService.get(id)

    @GetMapping("/requests/")
    @ResponseStatus(HttpStatus.OK)
    fun getRequestStatus(@RequestParam(name = "status") value: Long) = requestRepository.findByStatus(value)

    @GetMapping("/requests/last")
    @ResponseStatus(HttpStatus.OK)
    fun getLastRequests(): List<Request> {
        var pageable: Pageable = PageRequest.of(0, 10, Sort.Direction.DESC, "id")
        var bottomPage: Page<Request> = requestRepository.findAll(pageable)
        return bottomPage.content
    }

    @PutMapping("/request/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun putRequestId(@PathVariable id: Long, @RequestBody request: Request) = requestService.edit(id, request)

    @DeleteMapping("/request/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun deleteRequestId(@PathVariable id: Long) = requestService.remove(id)
}