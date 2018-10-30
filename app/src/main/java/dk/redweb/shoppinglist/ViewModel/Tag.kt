package dk.redweb.shoppinglist.ViewModel

import dk.redweb.shoppinglist.Database.Model.DbTag

class Tag(private val _id: Long, private val _name: String, private val _type: Long) {

    constructor(dbTag: DbTag) : this(dbTag.id, dbTag.name, dbTag.type)

    fun getId(): Long {
        return _id
    }

    fun getName(): String {
        return _name
    }

    fun getType(): Long {
        return _type
    }
}