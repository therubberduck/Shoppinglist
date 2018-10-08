package dk.redweb.shoppinglist.FrontEnd.MainView.OnListFragment

import android.content.Context
import com.wealthfront.magellan.Screen
import dk.redweb.shoppinglist.ViewModel.MainViewModel

class OnListScreen(private val _viewModel: MainViewModel) : Screen<OnListView>() {

    private lateinit var _adapter: OnListRecyclerViewAdapter

    override fun createView(context: Context?): OnListView {
        val view = OnListView(activity)

        _adapter = OnListRecyclerViewAdapter(_viewModel, view, this)
        view.setListAdapter(_adapter)

        _viewModel.observeSelectedItems(this) {
            _adapter.notifyDataSetChanged()
        }

        return view
    }
}