package dk.redweb.shoppinglist.FrontEnd.MainView.OnListFragment

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import com.wealthfront.magellan.BaseScreenView
import dk.redweb.shoppinglist.R

class OnListView(context: Context?) : BaseScreenView<OnListScreen>(context) {

    val list: RecyclerView

    init {
        View.inflate(context, R.layout.fragment_on_list, this)

        list = findViewById(R.id.list)
    }
}