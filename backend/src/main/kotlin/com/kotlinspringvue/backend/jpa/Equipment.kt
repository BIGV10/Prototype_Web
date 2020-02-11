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
        var comment: String? = null,

        @ManyToMany(fetch = FetchType.EAGER, cascade = [CascadeType.MERGE, CascadeType.PERSIST], mappedBy = "equipment")
        @JsonIgnore
        var request: MutableSet<Request> = HashSet()
)
//    Версия 3
//    @ManyToMany(mappedBy = "equipment")
//    JsonIgnoreProperties("equipment")
//    var request: Set<Request> = HashSet()

//    Версия 2
//    @ManyToMany(fetch = FetchType.LAZY, cascade = [CascadeType.MERGE, CascadeType.PERSIST], mappedBy = "equipment")
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    @JsonIgnoreProperties("request")
//    var request: MutableSet<Request> = HashSet()

//    Версия 1
//    @ManyToMany(cascade = [CascadeType.PERSIST, CascadeType.MERGE])
//    @JsonIgnoreProperties("equipment")
//    val request: MutableSet<Request> = HashSet()
//
//{
//        fun getRequests(): Set<Request> {
//                return request
//        }
//
//        fun setRequests(requests: MutableSet<Request>) {
//                request = requests
//        }
//}