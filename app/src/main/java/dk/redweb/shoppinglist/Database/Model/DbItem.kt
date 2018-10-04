package dk.redweb.shoppinglist.Database.Model

/**
 * Created by redwebpraktik on 15/02/2018.
 */
data class DbItem(
        val id: Long,
        val name: String,
        val onList: Boolean,
        val prefix: String,
        val suffix: String
)