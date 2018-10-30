package dk.redweb.shoppinglist.FrontEnd.AddTag

import android.content.Context
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import com.wealthfront.magellan.BaseScreenView
import dk.redweb.shoppinglist.FrontEnd.NavigationBar
import dk.redweb.shoppinglist.R
import dk.redweb.shoppinglist.ViewModel.Tag
import org.jetbrains.anko.find
import java.lang.Exception

class AddTagView(context: Context?) : BaseScreenView<AddTagScreen>(context) {

    private val _navbar: NavigationBar
    private val _edtName: EditText
    private val _rgType: RadioGroup
    private val _btnCommit: Button

    init {
        View.inflate(context, R.layout.screen_addtag, this)

        _navbar = find(R.id.navbar)
        _edtName = find(R.id.edtItemName)
        _rgType = find(R.id.rgType)
        _btnCommit = find(R.id.btnCommit)
    }

    fun setNavTitle(resId: Int) {
        _navbar.setTitle(resId)
    }

    fun setFieldsContents(name: String, type: Int) {
        _edtName.setText(name)

        when(type) {
            Tag.FilterType -> _rgType.check(R.id.rbFilter)
            Tag.StoreType -> _rgType.check(R.id.rgType)
        }
    }

    fun setCommitButton(resId: Int? = null, listener: (View) -> Unit) {
        if(resId != null) {
            _btnCommit.setText(resId)
        }
        _btnCommit.setOnClickListener(listener)
    }

    fun getName() : String {
        return _edtName.text.toString()
    }

    fun getType() : Int {
        when(_rgType.checkedRadioButtonId) {
            R.id.rbFilter -> return Tag.FilterType
            R.id.rbStore -> return Tag.StoreType
            else -> throw Exception("RadioButton id does not exist")
        }
    }

    fun requestFocus_Name() {
        _edtName.requestFocus()
    }
}