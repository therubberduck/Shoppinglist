package dk.redweb.shoppinglist.FrontEnd.PrefixSuffix

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

class PrefixSuffixView(context: Context?) : BaseScreenView<PrefixSuffixScreen>(context) {
    private val _navbar: NavigationBar
    private val _txtName: TextView
    private val _edtPrefix: EditText
    private val _edtSuffix: EditText
    private val _btnCommit: Button

    init {
        View.inflate(context, R.layout.screen_prefixsuffix, this)

        _navbar = find(R.id.navbar)
        _txtName = find(R.id.txtPreviewName)
        _edtPrefix = find(R.id.edtItemPrefix)
        _edtSuffix = find(R.id.edtItemSuffix)
        _btnCommit = find(R.id.btnCommit)
    }

    fun setNavTitle(resId: Int) {
        _navbar.setTitle(resId)
    }

    fun setFieldsContents(name: String, prefix: String, suffix: String) {
        _txtName.text = name
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
        _edtPrefix.afterTextChanged { listener() }
        _edtSuffix.afterTextChanged { listener() }
    }

    fun updatePreviewName(preview: String) {
        _txtName.text = preview
    }

    fun getPrefix() : String {
        return _edtPrefix.text.toString()
    }

    fun getSuffix() : String {
        return _edtSuffix.text.toString()
    }
}