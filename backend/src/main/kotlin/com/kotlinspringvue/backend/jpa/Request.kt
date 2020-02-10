package com.kotlinspringvue.backend.jpa

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import javax.persistence.*
import com.kotlinspringvue.backend.jpa.*

@Entity
@Table(name = "request")
data class Request(
        @Id
        @GeneratedValue
        @Column(name = "id")
        val id: Long? = null,

        @Column(name = "comment")
        var comment: String? = null,

        @Column(name = "status")
        var status: Long? = null
)
// {
//
//        @ManyToMany(cascade = [CascadeType.ALL])
//        val equipment: MutableSet<Equipment> = HashSet()
//
//}
        {

                @ManyToMany(mappedBy = "request")
                @JsonIgnoreProperties("request")
                val equipment: MutableSet<Equipment> = HashSet()
        }