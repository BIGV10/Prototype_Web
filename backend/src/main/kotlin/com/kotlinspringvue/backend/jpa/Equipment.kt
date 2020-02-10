package com.kotlinspringvue.backend.jpa

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import javax.persistence.*

@Entity
@Table(name = "equipment")
public data class Equipment(
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
) {
    @ManyToMany(cascade = [CascadeType.PERSIST, CascadeType.MERGE])
    @JsonIgnoreProperties("equipment")
    val request: MutableSet<Request> = HashSet()
}