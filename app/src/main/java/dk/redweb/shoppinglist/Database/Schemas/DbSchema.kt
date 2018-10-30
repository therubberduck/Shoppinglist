package dk.redweb.shoppinglist.Database.Schemas

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