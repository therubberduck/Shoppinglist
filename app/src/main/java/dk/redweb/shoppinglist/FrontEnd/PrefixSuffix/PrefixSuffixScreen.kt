package dk.redweb.shoppinglist.FrontEnd.PrefixSuffix

import android.content.Context
import com.wealthfront.magellan.Screen
import dk.redweb.shoppinglist.FrontEnd.MainActivity
import dk.redweb.shoppinglist.R
import dk.redweb.shoppinglist.Utility.afterTextChanged
import dk.redweb.shoppinglist.Utility.hideKeyboard
import dk.redweb.shoppinglist.ViewModel.Item
import dk.redweb.shoppinglist.ViewModel.MainViewModel

class PrefixSuffixScreen(private val _viewModel: MainViewModel, private val item: Item) : Screen<PrefixSuffixView>() {
    override fun createView(context: Context?): PrefixSuffixView {
        val view = PrefixSuffixView(context)

        view.navbar.setTitle(R.string.screen_prefixsuffix_title)

        view.txtName.text = item.getFullName()

        view.edtPrefix.setText(item.getPrefix())
        view.edtSuffix.setText(item.getSuffix())

        view.edtPrefix.afterTextChanged { updatePreview() }
        view.edtSuffix.afterTextChanged { updatePreview() }

        view.btnCommit.setOnClickListener {updateItem()}

        return view
    }

    fun updatePreview(){
        val prefix = view.edtPrefix.text.toString()
        val name = item.getName()
        val suffix = view.edtSuffix.text.toString()
        view.txtName.text = item.getFullName(prefix, name, suffix)
    }

    fun updateItem() {
        val prefix = view.edtPrefix.text.toString()
        val suffix = view.edtSuffix.text.toString()

        activity.hideKeyboard()

        item.updatePrefixSuffix(prefix, suffix)
        _viewModel.updateItemPrefixSuffix(item)

        (activity as MainActivity).navigator().goBack()
    }
}