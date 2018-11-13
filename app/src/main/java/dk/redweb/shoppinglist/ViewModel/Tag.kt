package dk.redweb.shoppinglist.ViewModel

import dk.redweb.shoppinglist.Database.Model.DbTag

class Tag(private val _id: Long, private var _name: String, private var _type: Int) : BaseViewModel() {

    companion object {
        val FilterType: Int = 1
        val StoreType: Int = 2
    }


    constructor(dbTag: DbTag) : this(dbTag.id, dbTag.name, dbTag.type.toInt())

    fun getId(): Long {
        return _id
    }

    fun getName(): String {
        return _name
    }

    fun setName(name: String) {
        _name = name
    }

    fun getType(): Int {
        return _type
    }

    fun setType(type: Int) {
        _type = type
    }
}