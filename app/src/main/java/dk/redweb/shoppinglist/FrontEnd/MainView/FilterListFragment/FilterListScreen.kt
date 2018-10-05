package dk.redweb.shoppinglist.FrontEnd.MainView.FilterListFragment

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import com.wealthfront.magellan.Screen
import dk.redweb.shoppinglist.Database.AppDatabase
import dk.redweb.shoppinglist.FrontEnd.AddItem.AddItemScreen
import dk.redweb.shoppinglist.FrontEnd.Custom.RecyclerViewInterface
import dk.redweb.shoppinglist.FrontEnd.MainActivity
import dk.redweb.shoppinglist.FrontEnd.MainView.OnListFragment.OnListView
import dk.redweb.shoppinglist.FrontEnd.PrefixSuffix.PrefixSuffixScreen
import dk.redweb.shoppinglist.Utility.visibleIf
import dk.redweb.shoppinglist.ViewModel.Item
import dk.redweb.shoppinglist.ViewModel.MainViewModel
import java.util.*

class FilterListScreen(private val _viewModel: MainViewModel) : Screen<FilterListView>(), RecyclerViewInterface {

    private val _companionViews: FilterListCompanionViews = FilterListCompanionViews(this, _viewModel)
    private var _adapter: FilterListRecyclerViewAdapter? = null

    override fun createView(context: Context?): FilterListView {
        val view = FilterListView(context)

        view.list.layoutManager = LinearLayoutManager(context)
        _adapter = FilterListRecyclerViewAdapter(_viewModel, this, _companionViews)
        view.list.adapter = _adapter

        view.fabAddItem.setOnClickListener {
            val addItemScreen = AddItemScreen(_viewModel)
            (activity as MainActivity).navigator().show(addItemScreen)
        }

        _viewModel.observeItems(this) {
            view.list.adapter.notifyDataSetChanged()
        }

        return view
    }

    override fun recyclerViewIsEmpty(isEmpty: Boolean) {
        view.txtEmptyList.visibleIf(activity) { return@visibleIf isEmpty }
    }

    fun openSuffixPrefixScreen(item: Item) {
        (activity as MainActivity).navigator().goTo(PrefixSuffixScreen(_viewModel, item))
    }

    fun openEditScreen(item: Item) {
        (activity as MainActivity).navigator().goTo(AddItemScreen(_viewModel, item))
    }
}