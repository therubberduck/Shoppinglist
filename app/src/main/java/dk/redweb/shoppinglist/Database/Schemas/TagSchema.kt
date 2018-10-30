package dk.redweb.shoppinglist.Database.Schemas

import dk.redweb.shoppinglist.Database.Model.DbTag
import org.jetbrains.anko.db.*

class TagSchema : DbSchema(tableName) {
    companion object {
        val tableName = "Tags"
        val id = "Id"
        val name = "Name"
        val type = "Type"

        val rowParser = DbTagRowParser()
    }

    init {
        columns(
                Pair(id, INTEGER + PRIMARY_KEY + AUTOINCREMENT),
                Pair(name, TEXT),
                Pair(type, INTEGER)
        )
    }
}

class DbTagRowParser : RowParser<DbTag> {
    override fun parseRow(columns: Array<Any?>): DbTag {
        val id = columns[0] as Long
        val name = columns[1] as String
        val type = columns[2] as Long
        return DbTag(id, name, type)
    }
}