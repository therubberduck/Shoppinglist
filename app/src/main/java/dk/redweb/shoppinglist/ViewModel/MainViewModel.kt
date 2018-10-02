package dk.redweb.shoppinglist.ViewModel

import dk.redweb.shoppinglist.Database.AppDatabase
import dk.redweb.shoppinglist.Database.Model.DbItem

/**
 * Created by redwebpraktik on 13/02/2018.
 */
class MainViewModel(private val _db: AppDatabase) : BaseViewModel(){
    private val _items: MutableList<Item> = mutableListOf()
    private val _selectedItems: MutableList<Item> = mutableListOf()

    private val _itemsSubscription: HashMap<Any, (MutableList<Item>) -> Unit> = hashMapOf()
    private val _selectedItemsSubscription: HashMap<Any, (MutableList<Item>) -> Unit> = hashMapOf()

    init {
        _db.Items.getItems {
            setupViewModel(it)
        }
    }

    fun setupViewModel(items: List<DbItem>) {
        for (dbitem in items) {
            val item = Item(dbitem.id, dbitem.name, dbitem.onList)
            handleAddItem(item)
        }
    }

    fun createItem(name: String) {
        _db.Items.createItem(name) {
            itemid ->
            _db.Items.getItem(itemid){
                dbitem ->
                val item = Item(dbitem)
                handleAddItem(item)
            }
        }
    }

    fun deleteItem(item: Item) {
        _db.Items.removeItem(item.getId())
        handleRemoveItem(item)
    }

    private fun handleAddItem(item: Item) {
        item.observeOnList(this) {
            onList ->
            if(onList) {
                _selectedItems.add(item)
            }
            else {
                _selectedItems.remove(item)
            }
            _db.Items.updateItemOnListStatus(item.getId(), onList)
            doCallback(_selectedItemsSubscription.values, _selectedItems)
        }

        _items.add(item)
        doCallback(_itemsSubscription.values, _items)
    }

    private fun handleRemoveItem(item: Item) {
        _items.remove(item)
        val wasSelected = _selectedItems.remove(item)
        if(wasSelected){
            doCallback(_selectedItemsSubscription.values, _selectedItems)
        }
        doCallback(_itemsSubscription.values, _items)
    }

    fun getItem(position: Int, onlySelected: Boolean = false) : Item {
        if(onlySelected) {
            return _selectedItems.get(position)
        }
        else {
            return _items.get(position)
        }
    }

    fun getCount(onlySelected: Boolean = false) : Int{
        if(onlySelected) {
            return _selectedItems.size
        }
        else {
            return _items.size
        }
    }

    fun observeItems(key: Any, callback: (item: MutableList<Item>) -> Unit) {
        _itemsSubscription.put(key, callback)
    }

    fun observeSelectedItems(key: Any, callback: (item: MutableList<Item>) -> Unit) {
        _selectedItemsSubscription.put(key, callback)
    }
}