package dk.redweb.shoppinglist.Database.Model

import org.jetbrains.anko.db.RowParser

/**
 * Created by redwebpraktik on 15/02/2018.
 */
data class DbItem(
    val id: Int,
    val name: String,
    val onList: Boolean
)