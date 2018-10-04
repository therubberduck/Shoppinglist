package dk.redweb.shoppinglist.FrontEnd.PrefixSuffix

import android.content.Context
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.wealthfront.magellan.BaseScreenView
import dk.redweb.shoppinglist.FrontEnd.NavigationBar
import dk.redweb.shoppinglist.R
import org.jetbrains.anko.find

class PrefixSuffixView(context: Context?) : BaseScreenView<PrefixSuffixScreen>(context) {
    val navbar: NavigationBar
    val txtName: TextView
    val edtPrefix: EditText
    val edtSuffix: EditText
    val btnCommit: Button

    init {
        View.inflate(context, R.layout.screen_prefixsuffix, this)

        navbar = find(R.id.navbar)
        txtName = find(R.id.txtFormattedName)
        edtPrefix = find(R.id.edtItemPrefix)
        edtSuffix = find(R.id.edtItemSuffix)
        btnCommit = find(R.id.btnCommit)
    }
}