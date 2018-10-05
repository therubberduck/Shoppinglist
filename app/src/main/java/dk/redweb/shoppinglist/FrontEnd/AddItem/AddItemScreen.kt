package dk.redweb.shoppinglist.FrontEnd.AddItem

import android.content.Context
import android.support.design.widget.TextInputLayout
import android.view.View
import com.wealthfront.magellan.Screen
import dk.redweb.shoppinglist.FrontEnd.DialogFactory
import dk.redweb.shoppinglist.FrontEnd.MainActivity
import dk.redweb.shoppinglist.R
import dk.redweb.shoppinglist.Utility.afterTextChanged
import dk.redweb.shoppinglist.Utility.hideKeyboard
import dk.redweb.shoppinglist.ViewModel.Item
import dk.redweb.shoppinglist.ViewModel.MainViewModel
import org.jetbrains.anko.find

class AddItemScreen(private val _viewModel: MainViewModel, private val item: Item? = null) : Screen<AddItemView>() {
    override fun createView(context: Context?): AddItemView {
        val view = AddItemView(context)

        if(item == null) {
            view.navbar.setTitle(R.string.screen_additem_addtitle)

            view.btnCommit.setOnClickListener {addItem()}
        }
        else {
            view.navbar.setTitle(R.string.screen_additem_edittitle)

            view.btnCommit.setText(R.string.screen_additem_edit)
            view.btnCommit.setOnClickListener {editItem()}

            view.edtPrefix.visibility = View.VISIBLE
            view.edtSuffix.visibility = View.VISIBLE

            view.edtName.afterTextChanged { updatePrefixSuffixPreview() }
            view.edtPrefix.afterTextChanged { updatePrefixSuffixPreview() }
            view.edtSuffix.afterTextChanged { updatePrefixSuffixPreview() }

            //Set existing values
            view.edtName.setText(item.getName())
            view.txtFormattedName.setText(item.getFullName())
            view.edtPrefix.setText(item.getPrefix())
            view.edtSuffix.setText(item.getSuffix())
        }

        return view
    }

    fun updatePrefixSuffixPreview(){
        if(view == null) {
            return
        }
        val prefix = view.edtPrefix.text.toString()
        val name = view.edtName.text.toString()
        val suffix = view.edtSuffix.text.toString()
        view.txtFormattedName.text = item!!.getFullName(prefix, name, suffix)
    }

    fun addItem() {
        val name = view.edtName.text.toString()

        if(name.isBlank()) {
            val hint = view.find<TextInputLayout>(R.id.tilItemName).hint.toString()
            DialogFactory(activity).showValidationDialog(R.string.validation_fieldempty, hint) {dialog, i ->
                view.edtName.requestFocus()
            }
            return
        }

        activity.hideKeyboard()

        _viewModel.createItem(name)
        (activity as MainActivity).navigator().goBack()
    }

    fun editItem() {
        val name = view.edtName.text.toString()
        val prefix = view.edtPrefix.text.toString()
        val suffix = view.edtSuffix.text.toString()

        if(name.isBlank()) {
            val hint = view.find<TextInputLayout>(R.id.tilItemName).hint.toString()
            DialogFactory(activity).showValidationDialog(R.string.validation_fieldempty, hint) {dialog, i ->
                view.edtName.requestFocus()
            }
            return
        }

        activity.hideKeyboard()

        if(item == null) {
            return
        }

        item.setName(name)
        item.updatePrefixSuffix(prefix, suffix)

        _viewModel.editItem(item)
        (activity as MainActivity).navigator().goBack()
    }
}