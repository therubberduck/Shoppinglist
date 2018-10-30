package dk.redweb.shoppinglist.FrontEnd.TagList

import android.app.Activity
import android.content.Context
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.wealthfront.magellan.BaseScreenView
import dk.redweb.shoppinglist.FrontEnd.Custom.RecyclerViewInterface
import dk.redweb.shoppinglist.R
import dk.redweb.shoppinglist.Utility.visibleIf

class TagListView(val activity: Activity?) : BaseScreenView<TagListScreen>(activity), RecyclerViewInterface {
    private val _list: RecyclerView
    private val _txtEmptyList: TextView
    private val _fabAddTag: FloatingActionButton

    init {
        View.inflate(context, R.layout.screen_taglist, this)

        _list = findViewById(R.id.list)
        _txtEmptyList = findViewById(R.id.txtEmptyList)
        _fabAddTag = findViewById(R.id.fabAdd)
    }

    override fun recyclerViewIsEmpty(isEmpty: Boolean) {
        _txtEmptyList.visibleIf(activity) { return@visibleIf isEmpty }
    }

    fun setListAdapter(adapter: TagListRecyclerViewAdapter) {
        _list.layoutManager = LinearLayoutManager(context)
        _list.adapter = adapter
    }

    fun setAddButton(listener: (View) -> Unit) {
        _fabAddTag.setOnClickListener(listener)
    }
}