package dk.redweb.shoppinglist.FrontEnd.MainView.FilterListFragment

import android.content.Context
import com.wealthfront.magellan.Screen
import dk.redweb.shoppinglist.FrontEnd.AddItem.AddItemScreen
import dk.redweb.shoppinglist.FrontEnd.MainActivity
import dk.redweb.shoppinglist.FrontEnd.PrefixSuffix.PrefixSuffixScreen
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
        val addItemScreen = AddItemScreen(_viewModel)
        (activity as MainActivity).navigator().show(addItemScreen)
    }

    fun openSuffixPrefixScreen(item: Item) {
        (activity as MainActivity).navigator().goTo(PrefixSuffixScreen(_viewModel, item))
    }

    fun openEditScreen(item: Item) {
        (activity as MainActivity).navigator().goTo(AddItemScreen(_viewModel, item))
    }
}