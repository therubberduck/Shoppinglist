package dk.redweb.shoppinglist.FrontEnd.PrefixSuffix

import android.content.Context
import com.wealthfront.magellan.Screen
import dk.redweb.shoppinglist.FrontEnd.MainActivity
import dk.redweb.shoppinglist.R
import dk.redweb.shoppinglist.Utility.hideKeyboard
import dk.redweb.shoppinglist.ViewModel.Item
import dk.redweb.shoppinglist.ViewModel.MainViewModel

class PrefixSuffixScreen(private val _viewModel: MainViewModel, private val item: Item) : Screen<PrefixSuffixView>() {
    override fun createView(context: Context?): PrefixSuffixView {
        val view = PrefixSuffixView(context)

        view.setNavTitle(R.string.screen_prefixsuffix_title)
        view.setFieldsContents(item.getFullName(), item.getPrefix(), item.getSuffix())
        view.setupPrefixSuffixUpdate { updatePreview() }
        view.setCommitButton { updateItem() }

        return view
    }

    fun updatePreview(){
        val prefix = view.getPrefix()
        val name = item.getName()
        val suffix = view.getSuffix()

        view.updatePreviewName(item.getFullName(prefix, name, suffix))
    }

    fun updateItem() {
        val prefix = view.getPrefix()
        val suffix = view.getSuffix()

        activity.hideKeyboard()

        item.updatePrefixSuffix(prefix, suffix)
        _viewModel.updateItemPrefixSuffix(item)

        (activity as MainActivity).navigator().goBack()
    }
}