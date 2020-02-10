package com.kotlinspringvue.backend.jpa

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import javax.persistence.*
import com.kotlinspringvue.backend.jpa.*

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
        )
//{
//
//        @ManyToMany(mappedBy = "equipment")
//        val request: MutableSet<Request> = HashSet()
//
//}
{

        @ManyToMany(cascade = [CascadeType.PERSIST, CascadeType.MERGE])
        @JsonIgnoreProperties("equipment")
        val request: MutableSet<Request> = HashSet()

}

//        @ManyToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
//        @JoinTable(name="equipment_request",
//        joinColumns = [JoinColumn(name="equipment_id", referencedColumnName="equipment_id")],
//        inverseJoinColumns = [JoinColumn(name="request_id", referencedColumnName="request_id")])