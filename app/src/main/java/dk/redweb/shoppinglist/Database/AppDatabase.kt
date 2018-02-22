package dk.redweb.shoppinglist.Database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.ManagedSQLiteOpenHelper
import org.jetbrains.anko.db.createTable

/**
 * Created by redwebpraktik on 15/02/2018.
 */
class AppDatabase(context: Context, name: String?, version: Int) : ManagedSQLiteOpenHelper(context, name, null, version) {

    private val schemas: List<DbSchema> =  listOf(ItemSchema())
    val Items = ItemModule()

    init {
        Items.initialize(this)
    }

    override fun onCreate(db: SQLiteDatabase) {
        for (schema in schemas) {
            db.createTable(schema.tableName, true, *schema.columns)
        }

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}