package dk.redweb.shoppinglist.FrontEnd.PrefixSuffix

import android.content.Context
import android.text.TextWatcher
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

        view.edtPrefix.afterTextChanged { updateTextView() }
        view.edtSuffix.afterTextChanged { updateTextView() }

        view.btnCommit.setOnClickListener {updateItem()}

        return view
    }

    fun updateTextView(){
        val newText = view.edtPrefix.text.toString() + " " + item.getName() + " " + view.edtSuffix.text.toString()
        view.txtName.text = newText
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