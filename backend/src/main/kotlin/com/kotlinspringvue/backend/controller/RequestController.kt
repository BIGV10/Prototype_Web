package com.kotlinspringvue.backend.controller

import com.kotlinspringvue.backend.exception.ResourceNotFoundException
import com.kotlinspringvue.backend.jpa.Equipment
import com.kotlinspringvue.backend.jpa.Request
import com.kotlinspringvue.backend.repository.EquipmentRepository
import com.kotlinspringvue.backend.repository.RequestRepository
import com.kotlinspringvue.backend.repository.UserRepository
import com.kotlinspringvue.backend.service.RequestService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.http.HttpStatus
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime


@CrossOrigin(origins = ["*"], maxAge = 3600)
@RestController
@RequestMapping("api")
@PreAuthorize("hasAnyRole('ROLE_TECHNICIAN', 'ROLE_MODERATOR', 'ROLE_ADMIN')")
class RequestController(private val requestService: RequestService) {

    @Autowired
    lateinit var requestRepository: RequestRepository

    @Autowired
    lateinit var equipmentRepository: EquipmentRepository

    @Autowired
    lateinit var userRepository: UserRepository

    private val logger: Logger = LoggerFactory.getLogger(RequestController::class.java)

    @GetMapping("/requests")
    @ResponseStatus(HttpStatus.OK)
    fun getRequestAll() = requestService.all()

    //Не работает
//    @GetMapping("/requests")
//    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_TECHNICIAN', 'ROLE_MODERATOR', 'ROLE_ADMIN')")
//    @ResponseStatus(HttpStatus.OK)
//    fun getRequests(
//            @RequestParam(name = "status", required = false) status: Long,
//            @RequestParam(name = "username", required = false) username: String): List<Request> {
//        val authentication = SecurityContextHolder.getContext().authentication
//        val currentPrincipal = authentication.principal as User
//        var roles = currentPrincipal.roles!!.map { it.name }?.any { it == "ROLE_TECHNICIAN" || it == "ROLE_MODERATOR" || it == "ROLE_ADMIN" }
//        try {
//            if (username != null && status != null && roles) {
//                var usernameList = requestRepository.findByIssuedBy(username)
//                var statusList = requestRepository.findByStatus(status)
//                return usernameList.intersect(statusList).toList()
//            } else
//                if (status != null && roles) {
//                    return requestRepository.findByStatus(status)
//                } else
//                    if (username != null) {
//                        return requestRepository.findByIssuedBy(username)
//                    } else
//                        if (roles)
//                            return requestService.all().toList()
//        }
//            catch (e: AuthenticationException) {
//                throw ResourceNotFoundException(e.message!!)
//            }
//        }


    @PostMapping("/requests")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_TECHNICIAN', 'ROLE_MODERATOR', 'ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    fun postRequest(@RequestBody request: Request): Request {
        val authentication: Authentication = SecurityContextHolder.getContext().authentication
        val currentPrincipalName: String = authentication.name
        val currentPrincipal = userRepository.findByUsername(currentPrincipalName)
        try {
            val currentDateTime = LocalDateTime.now()
            request.dateIssued = currentDateTime
            request.issuedBy = currentPrincipal.get().username
            return requestService.add(request)
        } catch (e: AuthenticationException) {
            throw ResourceNotFoundException(e.message!!)
        }
    }

    @PostMapping("/requests/{requestId}/equipment/{equipmentId}")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_TECHNICIAN', 'ROLE_MODERATOR', 'ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    fun postEquipmentToRequest(@PathVariable requestId: Long, @PathVariable equipmentId: Long): Request {

        val newEquipment: Equipment = equipmentRepository.findById(equipmentId).orElse(null)
        val currentRequest: Request = requestRepository.findById(requestId).orElse(null)
        currentRequest.equipment.add(newEquipment)
        requestRepository.save(currentRequest)
        logger.info(currentRequest.id.toString(), newEquipment.id.toString())
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
        val pageable: Pageable = PageRequest.of(0, 10, Sort.Direction.DESC, "id")
        val bottomPage: Page<Request> = requestRepository.findAll(pageable)
        return bottomPage.content
    }

    @GetMapping("/requests/user")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_TECHNICIAN', 'ROLE_MODERATOR', 'ROLE_ADMIN')")
    fun getUserRequests(): List<Request> {
        val authentication: Authentication = SecurityContextHolder.getContext().authentication
        val currentPrincipalName: String = authentication.name
        return requestRepository.findByIssuedBy(currentPrincipalName)
    }

    //    TODO При таком запросе из заявки удаляется все оборудование
    @PutMapping("/requests/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun putRequestId(@PathVariable id: Long, @RequestBody request: Request) = requestService.edit(id, request)

    @DeleteMapping("/requests/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun deleteRequestId(@PathVariable id: Long) = requestService.remove(id)
}