package dk.redweb.shoppinglist.FrontEnd.MainView.OnListFragment

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import com.wealthfront.magellan.Screen
import dk.redweb.shoppinglist.ViewModel.MainViewModel

class OnListScreen(val viewModel: MainViewModel) : Screen<OnListView>() {

    private var _adapter: OnListRecyclerViewAdapter? = null

    override fun createView(context: Context?): OnListView {
        val view = OnListView(context)

        view.list.layoutManager = LinearLayoutManager(context)
        _adapter = OnListRecyclerViewAdapter(viewModel, this)
        view.list.adapter = _adapter

        return view
    }


}