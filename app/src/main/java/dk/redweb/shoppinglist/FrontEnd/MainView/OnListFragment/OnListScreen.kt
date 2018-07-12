package dk.redweb.shoppinglist.FrontEnd.MainView.OnListFragment

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import com.wealthfront.magellan.Screen
import dk.redweb.shoppinglist.FrontEnd.Custom.RecyclerViewInterface
import dk.redweb.shoppinglist.Utility.visibleIf
import dk.redweb.shoppinglist.ViewModel.MainViewModel

class OnListScreen(private val _viewModel: MainViewModel) : Screen<OnListView>(), RecyclerViewInterface {

    private var _adapter: OnListRecyclerViewAdapter? = null

    override fun createView(context: Context?): OnListView {
        val view = OnListView(context)

        view.list.layoutManager = LinearLayoutManager(context)
        _adapter = OnListRecyclerViewAdapter(_viewModel, this)
        view.list.adapter = _adapter

        _viewModel.observeSelectedItems(this) {
            _adapter!!.notifyDataSetChanged();
        }

        return view
    }

    override fun recyclerViewIsEmpty(isEmpty: Boolean) {
        view.txtEmptyList.visibleIf(activity) { return@visibleIf isEmpty }
    }
}