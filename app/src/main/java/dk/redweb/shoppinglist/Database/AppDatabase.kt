package dk.redweb.shoppinglist.Database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import dk.redweb.shoppinglist.Database.Modules.ItemModule
import dk.redweb.shoppinglist.Database.Modules.TagModule
import dk.redweb.shoppinglist.Database.Schemas.DbSchema
import dk.redweb.shoppinglist.Database.Schemas.ItemSchema
import dk.redweb.shoppinglist.Database.Schemas.ItemTagSchema
import dk.redweb.shoppinglist.Database.Schemas.TagSchema
import org.jetbrains.anko.db.ManagedSQLiteOpenHelper
import org.jetbrains.anko.db.createTable

/**
 * Created by redwebpraktik on 15/02/2018.
 */
private const val dbversion = 3

class AppDatabase(context: Context, name: String?) : ManagedSQLiteOpenHelper(context, name, null, dbversion) {

    private val schemas: List<DbSchema> =  listOf(ItemSchema(), ItemTagSchema(), TagSchema())
    val Items = ItemModule()
    val Tags = TagModule()

    init {
        Items.initialize(this)
        Tags.initialize(this)
    }

    override fun onCreate(db: SQLiteDatabase) {
        for (schema in schemas) {
            db.createTable(schema.tableName, true, *schema.columns)
        }

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        if(oldVersion < 2) {
            db.execSQL("ALTER TABLE " + ItemSchema.tableName + " ADD COLUMN " + ItemSchema.prefix + " TEXT DEFAULT ''")
            db.execSQL("ALTER TABLE " + ItemSchema.tableName + " ADD COLUMN " + ItemSchema.suffix + " TEXT DEFAULT ''")
        }
        if(oldVersion < 3) {
            db.createTable(TagSchema.tableName, true, *TagSchema().columns)
        }
        if(oldVersion < 4) {
            db.createTable(ItemTagSchema.tableName, true, *ItemTagSchema().columns)
        }
    }
}