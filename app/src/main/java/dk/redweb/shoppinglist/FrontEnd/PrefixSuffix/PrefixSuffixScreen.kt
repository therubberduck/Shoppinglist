package dk.redweb.shoppinglist.FrontEnd.PrefixSuffix

import android.content.Context
import com.wealthfront.magellan.Screen
import dk.redweb.shoppinglist.R
import dk.redweb.shoppinglist.Utility.hideKeyboard
import dk.redweb.shoppinglist.Utility.navigateBack
import dk.redweb.shoppinglist.ViewModel.Item
import dk.redweb.shoppinglist.ViewModel.MainViewModel

class PrefixSuffixScreen(private val _viewModel: MainViewModel, private val item: Item) : Screen<PrefixSuffixView>() {
    override fun createView(context: Context?): PrefixSuffixView {
        val view = PrefixSuffixView(context)

        view.setNavTitle(R.string.screen_prefixsuffix_title)
        view.setFieldsContents(item.getFullName(), item.getPrefix(), item.getSuffix())
        view.setupPrefixSuffixUpdate { updatePreview() }
        view.setClearButton { clearPrefixSuffix() }
        view.setCommitButton { updateItem() }

        return view
    }

    fun updatePreview() {
        val prefix = view.getPrefix()
        val name = item.getName()
        val suffix = view.getSuffix()

        view.updatePreviewName(item.getFullName(prefix, name, suffix))
    }

    fun clearPrefixSuffix() {
        activity.hideKeyboard()

        item.updatePrefixSuffix("", "")
        item.putOnList()
        
        _viewModel.editItem(item)

        navigateBack()
    }

    fun updateItem() {
        val prefix = view.getPrefix()
        val suffix = view.getSuffix()

        activity.hideKeyboard()

        item.updatePrefixSuffix(prefix, suffix)
        item.putOnList()

        _viewModel.editItem(item)

        navigateBack()
    }
}