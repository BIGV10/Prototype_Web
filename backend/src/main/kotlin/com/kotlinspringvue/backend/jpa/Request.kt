package com.kotlinspringvue.backend.jpa

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import java.util.*
import javax.persistence.*


@Entity
@Table(name = "request")
open class Request(
        @Id
        @GeneratedValue
        @Column(name = "id")
        val id: Long? = null,

        @Column(name = "comment")
        var comment: String? = null,

        @Column(name = "status")
        var status: Long? = null,

        @ManyToMany(fetch = FetchType.LAZY, cascade = [CascadeType.MERGE, CascadeType.PERSIST])
        @JoinTable(name = "equipment_request", joinColumns = [JoinColumn(name = "equipment_id")],
                inverseJoinColumns = [JoinColumn(name = "request_id")])
        @OnDelete(action = OnDeleteAction.CASCADE)
        @JsonIgnoreProperties("request")
        var equipment: MutableSet<Equipment> = HashSet()


) {
    //    @ManyToMany(mappedBy = "request")
    //    @JsonIgnoreProperties("request")
    //    val equipment: MutableSet<Equipment> = HashSet()

//    open fun getEquipments(): Set<Equipment> {
//        return equipment
//    }
//
//    open fun setEquipments(equipments: Set<Equipment>) {
//        equipment = equipments
//    }
}