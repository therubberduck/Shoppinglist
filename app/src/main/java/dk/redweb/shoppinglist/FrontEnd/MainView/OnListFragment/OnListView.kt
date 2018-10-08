package dk.redweb.shoppinglist.FrontEnd.MainView.OnListFragment

import android.app.Activity
import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.wealthfront.magellan.BaseScreenView
import dk.redweb.shoppinglist.FrontEnd.Custom.RecyclerViewInterface
import dk.redweb.shoppinglist.R
import dk.redweb.shoppinglist.Utility.visibleIf

class OnListView(val activity: Activity) : BaseScreenView<OnListScreen>(activity), RecyclerViewInterface {

    private val _list: RecyclerView
    private val _txtEmptyList: TextView

    init {
        View.inflate(context, R.layout.screen_on_list, this)

        _list = findViewById(R.id.list)
        _txtEmptyList = findViewById(R.id.txtEmptyList)
    }

    override fun recyclerViewIsEmpty(isEmpty: Boolean) {
        _txtEmptyList.visibleIf(activity) { return@visibleIf isEmpty }
    }

    fun setListAdapter(adapter: OnListRecyclerViewAdapter) {
        _list.layoutManager = LinearLayoutManager(context)
        _list.adapter = adapter
    }
}