package dk.redweb.shoppinglist.Database.Modules

import android.content.ContentValues
import dk.redweb.shoppinglist.Database.AppDatabase
import dk.redweb.shoppinglist.Database.Dbmodule
import dk.redweb.shoppinglist.Database.Model.DbTag
import dk.redweb.shoppinglist.Database.Schemas.ItemTagSchema
import dk.redweb.shoppinglist.Database.Schemas.TagSchema
import dk.redweb.shoppinglist.ViewModel.Tag
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

    fun clearDatabase() {
        _db.use {
            delete(TagSchema.tableName)
        }
    }
}