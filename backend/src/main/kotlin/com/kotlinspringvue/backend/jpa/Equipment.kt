package com.kotlinspringvue.backend.jpa

import com.fasterxml.jackson.annotation.*
import java.util.*
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
) {
    @ManyToMany(fetch = FetchType.EAGER, cascade = [CascadeType.MERGE, CascadeType.PERSIST], mappedBy = "equipment")
    @JsonIgnoreProperties("equipment")
    var request: MutableSet<Request> = HashSet()
}