package dk.redweb.shoppinglist.FrontEnd.MainView.OnListFragment

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.wealthfront.magellan.BaseScreenView
import dk.redweb.shoppinglist.R

class OnListView(context: Context?) : BaseScreenView<OnListScreen>(context) {

    val list: RecyclerView
    val txtEmptyList: TextView

    init {
        View.inflate(context, R.layout.screen_on_list, this)

        list = findViewById(R.id.list)
        txtEmptyList = findViewById(R.id.txtEmptyList)
    }
}