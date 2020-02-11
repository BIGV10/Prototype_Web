package com.kotlinspringvue.backend.controller

import com.kotlinspringvue.backend.jpa.Equipment
import com.kotlinspringvue.backend.jpa.Request
import com.kotlinspringvue.backend.model.Greeting
import com.kotlinspringvue.backend.repository.EquipmentRepository
import com.kotlinspringvue.backend.repository.RequestRepository
import com.kotlinspringvue.backend.service.EquipmentService
import com.kotlinspringvue.backend.service.RequestService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.concurrent.atomic.AtomicLong
import java.util.function.Function


@RestController
@RequestMapping("api")
class EquipmentController(private val equipmentService: EquipmentService) {

    @Autowired
    lateinit var equipmentRepository: EquipmentRepository

    @GetMapping("/equipment")
    fun getEquipmentAll() = equipmentService.all()

    @PostMapping("/equipment")
    @ResponseStatus(HttpStatus.CREATED)
    fun postEquipment(@RequestBody equipment: Equipment) = equipmentService.add(equipment)

    @GetMapping("/equipment/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    fun getEquipmentId(@PathVariable id: Long) = equipmentService.get(id)

    @GetMapping("/equipment/")
    fun getEquipmentBarcode(@RequestParam(name = "barcode") value: String) = equipmentRepository.findByBarcode(value)

    @PutMapping("/equipment/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun putEquipmentId(@PathVariable id: Long, @RequestBody equipment: Equipment) = equipmentService.edit(id, equipment)

    @DeleteMapping("/equipment/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun deleteEquipmentId(@PathVariable id: Long) = equipmentService.remove(id)
}

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

//    @PostMapping("/request/")
//    @ResponseStatus(HttpStatus.CREATED)
//    fun postRequestWithEquipment(@RequestBody request: Request, @RequestBody equipment: List<Equipment>) {
//        requestService.add(request)
//        request.equipment.addAll(equipment)
//    }

//    @PostMapping("/request/{requestId}/equipment/{equipmentId}")
//    @ResponseStatus(HttpStatus.CREATED)
//    fun postEquipmentToRequest(@PathVariable requestId: Long, @PathVariable equipmentId: Long): Request {
//        val request = requestRepository.findById(requestId).orElse(null)
//        val equipment = equipmentRepository.findById(equipmentId).orElse(null)
////        request.equipment.add(equipment)
//        requestService.addEquipment(request, equipment)
//        return request
//    }

//    @PostMapping("/request/{requestId}/equipment/{equipmentId}")
//    @ResponseStatus(HttpStatus.CREATED)
//    fun postEquipmentToRequest(@PathVariable requestId: Long, @PathVariable equipmentId: Long) =
//            requestService.addEquipment(requestRepository.findById(requestId).orElse(null), equipmentRepository.findById(equipmentId).orElse(null))


//    @PostMapping("/request/{requestId}/equipment/{equipmentId}")
//    fun postEquipmentToRequest(@PathVariable requestId: Long, @PathVariable equipmentId: Long): Set<Equipment> {
//        var equipment: Equipment? = this.equipmentRepository.findById(equipmentId).orElse(null)
//        val request = requestRepository.findById(requestId).orElse(null)
//        return this.requestRepository.findById(requestId).map((request) -> {
//            request.getEquipments().add(equipment)
//            return requestRepository.save(request).getEquipments()}).orElse(null)
//        })
//
//        request.getEquipments().add(equipment)
//
//    }

    @PostMapping("/request/{requestId}/equipment/{equipmentId}")
    fun postEquipmentToRequest(@PathVariable requestId: Long, @PathVariable equipmentId: Long): Request {
        val newEquipment: Equipment = equipmentRepository.findById(equipmentId).orElse(null)
        val currentRequest: Request = requestRepository.findById(requestId).orElse(null)
        currentRequest.equipment.add(newEquipment)
        requestRepository.save(currentRequest)
        return currentRequest
//        return requestRepository.findById(requestId).map(Function<Request, MutableSet<Equipment>> { request: Request ->
//            request.equipment.add(newEquipment)
//            return@Function requestRepository.save(request).equipment
//        }).orElse(null)
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

class BackendController() {
    val counter = AtomicLong()

    @GetMapping("/greeting")
    fun greeting(@RequestParam(value = "name", defaultValue = "World") name: String) =
            Greeting(counter.incrementAndGet(), "Hello, $name")
}