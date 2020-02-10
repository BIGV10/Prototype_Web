package com.kotlinspringvue.backend.jpa

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import java.util.*
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
        var comment: String? = null,

        @ManyToMany(fetch = FetchType.LAZY, cascade = [CascadeType.MERGE, CascadeType.PERSIST], mappedBy = "equipment")
        @OnDelete(action = OnDeleteAction.CASCADE)
        @JsonIgnoreProperties("equipment")
        var request: MutableSet<Request> = HashSet()
) {
//    @ManyToMany(cascade = [CascadeType.PERSIST, CascadeType.MERGE])
//    @JsonIgnoreProperties("equipment")
//    val request: MutableSet<Request> = HashSet()
    open fun getRequests(): Set<Request> {
        return request
    }

    fun setRequests(requests: MutableSet<Request>) {
        request = requests
    }
}