package dk.redweb.shoppinglist.FrontEnd.MainView.FilterListFragment

import android.content.Context
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.wealthfront.magellan.BaseScreenView
import dk.redweb.shoppinglist.R

class FilterListView(context: Context?) : BaseScreenView<FilterListScreen>(context) {

    val list: RecyclerView
    val txtEmptyList: TextView
    val fabAddItem: FloatingActionButton

    init {
        View.inflate(context, R.layout.screen_filter_list, this)

        list = findViewById(R.id.list)
        txtEmptyList = findViewById(R.id.txtEmptyList)
        fabAddItem = findViewById(R.id.fabAdd)
    }
}