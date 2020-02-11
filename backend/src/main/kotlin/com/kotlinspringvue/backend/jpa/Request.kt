package com.kotlinspringvue.backend.jpa

import com.fasterxml.jackson.annotation.*
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import java.util.*
import javax.persistence.*


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
        var status: Long? = null,

        @ManyToMany(fetch = FetchType.EAGER, cascade = [CascadeType.MERGE, CascadeType.PERSIST])
        @JoinTable(name = "equipment_request", joinColumns = [JoinColumn(name = "request_id", referencedColumnName = "id")],
                inverseJoinColumns = [JoinColumn(name = "equipment_id", referencedColumnName = "id")])
        @OnDelete(action = OnDeleteAction.CASCADE)
        @JsonIgnore
        var equipment: MutableSet<Equipment> = HashSet()
)
//    Версия 3
//    @ManyToMany
//    @JoinTable(name = "equipment_request", joinColumns = [JoinColumn(name = "equipment_id", referencedColumnName = "id")],
//            inverseJoinColumns = [JoinColumn(name = "request_id", referencedColumnName = "id")])
//    @JsonIgnoreProperties("request")
//    var equipment: MutableSet<Equipment> = HashSet()
//    Версия 2
//    @ManyToMany(fetch = FetchType.LAZY, cascade = [CascadeType.MERGE, CascadeType.PERSIST])
//    @JoinTable(name = "equipment_request", joinColumns = [JoinColumn(name = "equipment_id", referencedColumnName = "id")],
//            inverseJoinColumns = [JoinColumn(name = "request_id", referencedColumnName = "id")])
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    @JsonIgnoreProperties("equipment")
//    var equipment: MutableSet<Equipment> = HashSet()

//    Версия 1
//    @ManyToMany(mappedBy = "request")
//    @JsonIgnoreProperties("request")
//    val equipment: MutableSet<Equipment> = HashSet()
//{
//        fun getEquipments(): Set<Equipment> {
//                return equipment
//        }
//
//        fun setEquipments(equipments: Set<Equipment>) {
//                equipment = equipments as MutableSet<Equipment>
//        }
//}
