package dk.redweb.shoppinglist.FrontEnd.MainView.FilterListFragment

import android.content.Context
import com.wealthfront.magellan.Screen
import dk.redweb.shoppinglist.FrontEnd.AddItem.AddItemScreen
import dk.redweb.shoppinglist.FrontEnd.PrefixSuffix.PrefixSuffixScreen
import dk.redweb.shoppinglist.Utility.gotoScreen
import dk.redweb.shoppinglist.Utility.showScreen
import dk.redweb.shoppinglist.ViewModel.Item
import dk.redweb.shoppinglist.ViewModel.MainViewModel

class FilterListScreen(private val _viewModel: MainViewModel) : Screen<FilterListView>() {

    private lateinit var _adapter: FilterListRecyclerViewAdapter
    private val _companionViews: FilterListSubViews = FilterListSubViews(this, _viewModel)

    override fun createView(context: Context?): FilterListView {
        val view = FilterListView(activity)

        _adapter = FilterListRecyclerViewAdapter(_viewModel, view, this, _companionViews)
        view.setListAdapter(_adapter)

        view.setAddButton { addItem() }

        _viewModel.observeItems(this) {
            _adapter.notifyDataSetChanged()
        }

        return view
    }

    private fun addItem() {
        AddItemScreen(_viewModel).showScreen()
    }

    fun openSuffixPrefixScreen(item: Item) {
        PrefixSuffixScreen(_viewModel, item).gotoScreen()
    }

    fun openEditScreen(item: Item) {
        AddItemScreen(_viewModel, item).gotoScreen()
    }
}