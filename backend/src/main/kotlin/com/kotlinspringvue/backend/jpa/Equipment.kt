package com.kotlinspringvue.backend.jpa

import javax.persistence.*

@Entity
@Table(name = "equipment")
data class Equipment(

        @Id
        @GeneratedValue
        @Column(name = "id")
        val id: Long? = null,

        @Column(name = "barcode")
        var barcode: String? = null,

        @Column(name = "name")
        var name: String? = null,

        @Column(name = "comment")
        var comment: String? = null
)