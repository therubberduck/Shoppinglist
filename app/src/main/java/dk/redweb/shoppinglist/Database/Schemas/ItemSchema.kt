package dk.redweb.shoppinglist.Database.Schemas

import dk.redweb.shoppinglist.Database.Model.DbItem
import org.jetbrains.anko.db.*

class ItemSchema : DbSchema(tableName) {
    companion object {
        val tableName = "Items"
        val id = "Id"
        val name = "Name"
        val onList = "OnList"
        val prefix = "Prefix"
        val suffix = "Suffix"

        val rowParser = DbItemRowParser()
    }

    init {
        columns(
                Pair(id, INTEGER + PRIMARY_KEY + AUTOINCREMENT),
                Pair(name, TEXT),
                Pair(onList, INTEGER),
                Pair(prefix, TEXT),
                Pair(suffix, TEXT)
        )
    }
}

class DbItemRowParser : RowParser<DbItem> {
    override fun parseRow(columns: Array<Any?>): DbItem {
        val id = columns[0] as Long
        val name = columns[1] as String
        val onList = columns[2] as Long
        val prefix = columns[3] as String
        val suffix = columns[4] as String
        return DbItem(id, name, onList == 1L, prefix, suffix)
    }
}