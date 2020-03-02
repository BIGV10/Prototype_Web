package com.kotlinspringvue.backend.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.NOT_FOUND)
class ResourceNotFoundException : RuntimeException {
    constructor()
    constructor(entity: String, id: Long) : super("$entity id $id не найдено")
    constructor(entity: String, barcode: String) : super("$entity штрихкод $barcode не найдено")
    constructor(entity: String) : super("Не авторизован")
}