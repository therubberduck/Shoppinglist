package dk.redweb.shoppinglist.ViewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

/**
 * Created by redwebpraktik on 13/02/2018.
 */
class Item(id: Int, name: String, onList: Boolean) : ViewModel() {


    private var _id: MutableLiveData<Int> = MutableLiveData()
    fun getId(): Int {
        if(_id.value == null) throw NullPointerException()
        return _id.value!!
    }
    fun setId(id: Int) {
        _id.postValue(id)
    }

    private var _name: MutableLiveData<String> = MutableLiveData()
    fun getLiveName(): LiveData<String> {
        return _name
    }
    fun getName(): String {
        if(_name.value == null) throw NullPointerException()
        return _name.value!!
    }
    fun setName(name: String) {
        _name.postValue(name)
    }

    private var _onList: MutableLiveData<Boolean> = MutableLiveData()
    fun isOnList(): LiveData<Boolean> {
        return _onList;
    }
    fun putOnList() {
        _onList.postValue(true)
    }
    fun removeFromList() {
        _onList.postValue(false)
    }

    init {
        _id.value = id
        _name.value = name
        _onList.value = onList
    }
}