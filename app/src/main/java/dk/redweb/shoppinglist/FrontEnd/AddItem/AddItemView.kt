package dk.redweb.shoppinglist.FrontEnd.AddItem

import android.content.Context
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.wealthfront.magellan.BaseScreenView
import dk.redweb.shoppinglist.FrontEnd.NavigationBar
import dk.redweb.shoppinglist.R
import org.jetbrains.anko.find

class AddItemView(context: Context?) : BaseScreenView<AddItemScreen>(context) {

    val navbar: NavigationBar
    val edtName: EditText
    val txtFormattedName: TextView
    val edtPrefix: EditText
    val edtSuffix: EditText
    val btnCommit: Button

    init {
        View.inflate(context, R.layout.screen_additem, this)

        navbar = find(R.id.navbar)
        edtName = find(R.id.edtItemName)
        txtFormattedName = find(R.id.txtFormattedName)
        edtPrefix = find(R.id.edtItemPrefix)
        edtSuffix = find(R.id.edtItemSuffix)
        btnCommit = find(R.id.btnCommit)

        edtPrefix.visibility = View.GONE //These fields should not be shown when creating, only when editing
        edtSuffix.visibility = View.GONE //These fields should not be shown when creating, only when editing
    }
}