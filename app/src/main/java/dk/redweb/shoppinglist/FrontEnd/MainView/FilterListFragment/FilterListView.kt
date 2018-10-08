package dk.redweb.shoppinglist.FrontEnd.MainView.FilterListFragment

import android.app.Activity
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.wealthfront.magellan.BaseScreenView
import dk.redweb.shoppinglist.FrontEnd.Custom.RecyclerViewInterface
import dk.redweb.shoppinglist.R
import dk.redweb.shoppinglist.Utility.visibleIf

class FilterListView(val activity: Activity) : BaseScreenView<FilterListScreen>(activity), RecyclerViewInterface {

    private val _list: RecyclerView
    private val _txtEmptyList: TextView
    private val _fabAddItem: FloatingActionButton

    init {
        View.inflate(context, R.layout.screen_filter_list, this)

        _list = findViewById(R.id.list)
        _txtEmptyList = findViewById(R.id.txtEmptyList)
        _fabAddItem = findViewById(R.id.fabAdd)
    }

    override fun recyclerViewIsEmpty(isEmpty: Boolean) {
        _txtEmptyList.visibleIf(activity) { return@visibleIf isEmpty }
    }

    fun setListAdapter(adapter: FilterListRecyclerViewAdapter) {
        _list.layoutManager = LinearLayoutManager(context)
        _list.adapter = adapter
    }

    fun setAddButton(listener: (View) -> Unit) {
        _fabAddItem.setOnClickListener(listener)
    }
}