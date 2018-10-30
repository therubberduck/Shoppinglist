package dk.redweb.shoppinglist.Database.Schemas

import org.jetbrains.anko.db.AUTOINCREMENT
import org.jetbrains.anko.db.INTEGER
import org.jetbrains.anko.db.PRIMARY_KEY

class ItemTagSchema : DbSchema(tableName) {
    companion object {
        val tableName = "Tags"
        val id = "Id"
        val itemId = "ItemId"
        val tagId = "TagId"
    }

    init {
        columns(
                Pair(id, INTEGER + PRIMARY_KEY + AUTOINCREMENT),
                Pair(itemId, INTEGER),
                Pair(tagId, INTEGER)
        )
    }
}