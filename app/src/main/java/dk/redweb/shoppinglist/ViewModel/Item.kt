package dk.redweb.shoppinglist.ViewModel

import dk.redweb.shoppinglist.Database.Model.DbItem
import kotlin.properties.Delegates

/**
 * Created by redwebpraktik on 13/02/2018.
 */
open class Item(private var _id: Long, private var _name: String, private var _onList: Boolean) : BaseViewModel() {

    constructor(dbItem: DbItem) : this(dbItem.id, dbItem.name, dbItem.onList)

    fun getId(): Long {
        return _id
    }

    fun setId(id: Long) {
        _id = id
    }

    fun getName(): String {
        return _name
    }

    fun setName(name: String) {
        _name = name
    }

    fun isOnList(): Boolean {
        return _onList;
    }

    fun putOnList() {
        if(_onList != true) {
            _onList = true
            doCallback(onListObservationList.values, _onList)
        }
    }

    fun removeFromList() {
        if(_onList != false) {
            _onList = false
            doCallback(onListObservationList.values, _onList)
        }
    }

    private val onListObservationList: HashMap<Any, (id: Boolean) -> Unit> = hashMapOf()
    fun observeOnList(key: Any, callback: (id: Boolean) -> Unit) : (id: Boolean) -> Unit {
        onListObservationList.put(key, callback)
        callback(_onList)
        return callback
    }

    fun unobserveOnList(key: Any): Unit {
        onListObservationList.remove(key)
    }
}