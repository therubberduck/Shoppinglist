package dk.redweb.shoppinglist.FrontEnd.AddItem

import android.content.Context
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.wealthfront.magellan.BaseScreenView
import dk.redweb.shoppinglist.FrontEnd.NavigationBar
import dk.redweb.shoppinglist.R
import dk.redweb.shoppinglist.Utility.afterTextChanged
import org.jetbrains.anko.find

class AddItemView(context: Context?) : BaseScreenView<AddItemScreen>(context) {

    private val _navbar: NavigationBar
    private val _edtName: EditText
    private val _txtPreviewName: TextView
    private val _edtPrefix: EditText
    private val _edtSuffix: EditText
    private val _btnCommit: Button

    init {
        View.inflate(context, R.layout.screen_additem, this)

        _navbar = find(R.id.navbar)
        _edtName = find(R.id.edtItemName)
        _txtPreviewName = find(R.id.txtPreviewName)
        _edtPrefix = find(R.id.edtItemPrefix)
        _edtSuffix = find(R.id.edtItemSuffix)
        _btnCommit = find(R.id.btnCommit)

        _edtPrefix.visibility = View.GONE //These fields should not be shown when creating, only when editing
        _edtSuffix.visibility = View.GONE //These fields should not be shown when creating, only when editing
    }

    fun setNavTitle(resId: Int) {
        _navbar.setTitle(resId)
    }

    fun setFieldsContents(name: String, fullname: String, prefix: String, suffix: String) {
        _edtName.setText(name)
        _txtPreviewName.text = fullname
        _edtPrefix.setText(prefix)
        _edtSuffix.setText(suffix)
    }

    fun setCommitButton(resId: Int? = null, listener: (View) -> Unit) {
        if(resId != null) {
            _btnCommit.setText(resId)
        }
        _btnCommit.setOnClickListener(listener)
    }

    fun setupPrefixSuffixUpdate(listener: () -> Unit) {
        _edtPrefix.visibility = View.VISIBLE
        _edtSuffix.visibility = View.VISIBLE

        _edtName.afterTextChanged { listener() }
        _edtPrefix.afterTextChanged { listener() }
        _edtSuffix.afterTextChanged { listener() }
    }

    fun updatePreviewName(preview: String) {
        _txtPreviewName.text = preview
    }

    fun getName() : String {
        return _edtName.text.toString()
    }

    fun getPrefix() : String {
        return _edtPrefix.text.toString()
    }

    fun getSuffix() : String {
        return _edtSuffix.text.toString()
    }

    fun requestFocus_Name() {
        _edtName.requestFocus()
    }
}