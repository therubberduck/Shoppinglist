package dk.redweb.shoppinglist.ViewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import dk.redweb.shoppinglist.Database.AppDatabase

/**
 * Created by redwebpraktik on 13/02/2018.
 */
class MainViewModel : ViewModel(){
    private var _items: MutableList<Item> = mutableListOf<Item>()
    private var _liveItems: MutableLiveData<MutableList<MutableLiveData<Item>>> = MutableLiveData()
    private var _liveSelectedItems: MutableLiveData<MutableList<MutableLiveData<Item>>> = MutableLiveData()

    private lateinit var _db: AppDatabase

    init {

    }

    fun connectToDb(db: AppDatabase) {
        _db = db

        _db.Items.getItems {
            for (dbitem in it) {
                val item = Item(dbitem.id, dbitem.name, dbitem.onList)
                _items.add(item)

                val dataItem = MutableLiveData<Item>()
                dataItem.value = item
                _liveItems.value?.add(dataItem)

                if(dbitem.onList) {
                    _liveSelectedItems.value?.add(dataItem)
                }
            }
        }
    }

    fun getItem(position: Int) : Item {
        val items = _liveItems.value
        if(items != null) {
            val item = items.get(position).value
            if (item != null) {
                return item
            }
        }
        throw NullPointerException()
    }

    fun getLiveItem(position: Int) : LiveData<Item> {
        //Do not call unless you know the list had been initialized

        val items = _liveItems.value
        if(items != null) {
            return items.get(position)
        }
        throw NullPointerException()
    }

    fun getLiveItems() : LiveData<MutableList<MutableLiveData<Item>>> {
        return _liveItems
    }

    fun getLiveSelectedItems() : LiveData<MutableList<MutableLiveData<Item>>> {
        return _liveSelectedItems;
    }

    fun getCount() : Int{
        val items = getLiveItems().value
        if(items == null) {
            return 0
        }
        return items.size
    }
}