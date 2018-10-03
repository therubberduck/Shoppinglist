package dk.redweb.shoppinglist.FrontEnd.AddItem

import android.content.Context
import android.support.design.widget.TextInputLayout
import com.wealthfront.magellan.Screen
import dk.redweb.shoppinglist.FrontEnd.DialogFactory
import dk.redweb.shoppinglist.FrontEnd.MainActivity
import dk.redweb.shoppinglist.R
import dk.redweb.shoppinglist.ViewModel.MainViewModel
import org.jetbrains.anko.find

class AddItemScreen(private val _viewModel: MainViewModel) : Screen<AddItemView>() {
    override fun createView(context: Context?): AddItemView {
        val view = AddItemView(context)

        view.navbar.setTitle(R.string.screen_additem_title)

        view.btnCommit.setOnClickListener {addItem()}

        return view
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

        _viewModel.createItem(name)
        (activity as MainActivity).navigator().goBack()
    }
}