package com.ran.kolibri.entity.project

import com.ran.kolibri.entity.base.NamedEntity
import com.ran.kolibri.entity.other.Comment
import com.ran.kolibri.entity.other.CommentsHolder
import javax.persistence.*
import javax.persistence.CascadeType.*
import javax.validation.constraints.NotNull

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
abstract class Project : NamedEntity(), CommentsHolder {

    @NotNull
    var isTemplate: Boolean = false

    @OneToMany(cascade = arrayOf(PERSIST, REMOVE))
    @OrderColumn
    override val comments: MutableList<Comment> = ArrayList()

}
