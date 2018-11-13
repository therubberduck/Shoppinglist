package dk.redweb.shoppinglist.Database.Modules

import android.content.ContentValues
import dk.redweb.shoppinglist.Database.AppDatabase
import dk.redweb.shoppinglist.Database.Dbmodule
import dk.redweb.shoppinglist.Database.Model.DbTag
import dk.redweb.shoppinglist.Database.Schemas.ItemSchema
import dk.redweb.shoppinglist.Database.Schemas.ItemTagSchema
import dk.redweb.shoppinglist.Database.Schemas.TagSchema
import dk.redweb.shoppinglist.ViewModel.Tag
import kotlinx.coroutines.experimental.selects.select
import org.jetbrains.anko.db.*

/**
 * Created by redwebpraktik on 15/02/2018.
 */
class TagModule : Dbmodule() {
    fun initialize(db: AppDatabase) {
        _db = db
    }

    fun createTag(name: String, type: Int, callback: ((Long) -> Unit)? = null){
        _db.use {
            val id = insertOrThrow(
                    TagSchema.tableName,
                    TagSchema.name to name,
                    TagSchema.type to type
            )
            if(callback != null) {
                callback(id)
            }
        }
    }

    fun getTag(tagId: Long, callback: (DbTag) -> Unit) {
        _db.use {
            val whereString = TagSchema.id + " = " + tagId
            select(TagSchema.tableName, "*").whereArgs(whereString).exec {
                val item = parseSingle(TagSchema.rowParser)
                callback(item)
            }
        }
    }

    fun getTags(callback: (List<DbTag>) -> Unit) {
        _db.use {
            select(TagSchema.tableName, "*").orderBy(TagSchema.name).exec {
                val items = parseList(TagSchema.rowParser)
                callback(items)
            }
        }
    }

    fun removeTag(tagId: Long) {
        _db.use {
            val whereString = TagSchema.id + "=" + tagId
            delete(TagSchema.tableName, whereString)
        }
    }

    fun updateTag(tag: Tag) {
        val contentValues = ContentValues()
        contentValues.put(TagSchema.name, tag.getName())
        contentValues.put(TagSchema.type, tag.getType())

        val whereString = ItemTagSchema.id + "=" + tag.getId()

        _db.use {
            update(TagSchema.tableName, contentValues, whereString, null)
        }
    }

    //
    // ItemTag functions
    //

    fun getTags(itemId: Long, callback: (List<DbTag>) -> Unit) {
        _db.use {
            val query = "SELECT * FROM " + TagSchema.tableName + " AS t " +
                    " INNER JOIN " + ItemTagSchema.tableName + " AS it " +
                    " ON t." + TagSchema.id + " = " + " it." + ItemTagSchema.tagId +
                    " WHERE it." + ItemTagSchema.itemId + " = " + itemId +
                    " ORDER BY t." + TagSchema.name
            val cursor = _db.writableDatabase.rawQuery(query, null)
            val dbtags = cursor.parseList(TagSchema.rowParser)

            callback(dbtags)
        }
    }

    fun getUnselectedTags(itemId: Long, callback: (List<DbTag>) -> Unit) {
        _db.use {
            val query = "SELECT * FROM " + TagSchema.tableName + " AS t " +
                    " LEFT JOIN " + ItemTagSchema.tableName + " AS it " +
                    " ON t." + TagSchema.id + " = " + " it." + ItemTagSchema.tagId + " AND it." + ItemTagSchema.itemId + " = " + itemId
                    " WHERE it." + ItemTagSchema.itemId + " is null" +
                    " ORDER BY t." + TagSchema.name
            val cursor = _db.writableDatabase.rawQuery(query, null)
            val dbtags = cursor.parseList(TagSchema.rowParser)

            callback(dbtags)
        }
    }

    fun setItemTags(itemid: Long, tags: List<Tag>, callback: (() -> Unit)? = null) {
        _db.use {
            //Remove any old tags attached to the item
            val whereString = ItemTagSchema.itemId + "=" + itemid
            delete(ItemTagSchema.tableName, whereString)

            //Add all currents tags attached to the item
            tags.forEach {
                insertOrThrow(ItemTagSchema.tableName,
                    ItemTagSchema.itemId to itemid,
                            ItemTagSchema.tagId to it.getId())
            }
            callback?.invoke()
        }
    }

    fun clearDatabase() {
        _db.use {
            delete(TagSchema.tableName)
        }
    }
}