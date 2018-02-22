package dk.redweb.shoppinglist.Database

import dk.redweb.shoppinglist.Database.Model.DbItem
import org.jetbrains.anko.db.*

/**
 * Created by redwebpraktik on 15/02/2018.
 */

open class DbSchema(val tableName: String) {
    var columns: Array<out Pair<String, SqlType>> = arrayOf()

    protected fun columns(vararg cols: Pair<String, SqlType>) {
        columns = cols
    }
}

class ItemSchema : DbSchema(tableName) {
    companion object {
        val tableName = "Items"
        val id = "Id"
        val name = "Name"
        val onList = "OnList"

        val rowParser = DbItemRowParser()
    }

    init {
        columns(
                Pair(id, INTEGER + PRIMARY_KEY + AUTOINCREMENT),
                Pair(name, TEXT),
                Pair(onList, INTEGER)
        )
    }
}

class DbItemRowParser : RowParser<DbItem> {
    override fun parseRow(columns: Array<Any?>): DbItem {
        val id = columns[0] as Long
        val name = columns[1] as String
        val onList = columns[2] as Long
        return DbItem(id.toInt(), name, onList == 1L)
    }
}