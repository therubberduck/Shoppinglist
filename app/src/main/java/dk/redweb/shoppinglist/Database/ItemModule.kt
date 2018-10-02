package dk.redweb.shoppinglist.Database

import android.content.ContentValues
import dk.redweb.shoppinglist.Database.Model.DbItem
import kotlinx.coroutines.experimental.selects.select
import org.jetbrains.anko.db.*

/**
 * Created by redwebpraktik on 15/02/2018.
 */
class ItemModule : Dbmodule() {
    fun initialize(db: AppDatabase) {
        _db = db;
    }

    fun createItem(name: String, callback: ((Long) -> Unit)? = null){
        _db.use {
            val id = insertOrThrow(
                    ItemSchema.tableName,
                    ItemSchema.name to name,
                    ItemSchema.onList to false
            )
            if(callback != null) {
                callback(id)
            }

        }
    }

    fun getItem(itemId: Long, callback: (DbItem) -> Unit) {
        _db.use {
            val whereString = ItemSchema.id + " = " + itemId
            select(ItemSchema.tableName, "*").whereArgs(whereString).exec {
                val item = parseSingle(ItemSchema.rowParser)
                callback(item)
            }
        }
    }

    fun getItems(callback: (List<DbItem>) -> Unit) {
        _db.use {
            select(ItemSchema.tableName, "*").orderBy(ItemSchema.name).exec {
                val items = parseList(ItemSchema.rowParser)
                callback(items)
            }
        }
    }

    fun getSelectedItems(callback: (List<DbItem>) -> Unit) {
        _db.use {
            select(ItemSchema.tableName, "*").whereSimple(ItemSchema.onList + " = 1").orderBy(ItemSchema.name).exec {
                val items = parseList(ItemSchema.rowParser)
                callback(items)
            }
        }
    }

    fun removeItem(itemId: Long) {
        _db.use {
            val whereString = ItemSchema.id + "=" + itemId;
            delete(ItemSchema.tableName, whereString)
        }
    }

    fun updateItemOnListStatus(id: Long, onList: Boolean) {
        val contentValues = ContentValues()
        contentValues.put(ItemSchema.onList, onList)

        val whereString = ItemSchema.id + "=" + id

        _db.use {
            update(ItemSchema.tableName, contentValues, whereString, null)
        }
    }

    fun clearDatabase() {
        _db.use {
            delete(ItemSchema.tableName)
        }
    }
}