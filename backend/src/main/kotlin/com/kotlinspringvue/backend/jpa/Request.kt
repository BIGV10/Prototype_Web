package com.kotlinspringvue.backend.jpa

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
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
        var status: Long? = null
) {
    @ManyToMany(fetch = FetchType.EAGER, cascade = [CascadeType.MERGE, CascadeType.PERSIST])
    @JoinTable(name = "equipment_request", joinColumns = [JoinColumn(name = "request_id", referencedColumnName = "id")],
            inverseJoinColumns = [JoinColumn(name = "equipment_id", referencedColumnName = "id")])
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnoreProperties("request")
    var equipment: MutableSet<Equipment> = HashSet()
}