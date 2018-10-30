package dk.redweb.shoppinglist.FrontEnd.TagList

import android.content.Context
import com.wealthfront.magellan.Screen
import dk.redweb.shoppinglist.App
import dk.redweb.shoppinglist.ViewModel.TagsViewModel

class TagListScreen : Screen<TagListView>() {

    private lateinit var _viewModel: TagsViewModel
    private lateinit var _companionViews: TagListSubViews
    private lateinit var _adapter: TagListRecyclerViewAdapter

    override fun createView(context: Context?): TagListView {
        val view = TagListView(activity)

        val db = (activity.application as App).getDatabase()
        _viewModel = TagsViewModel(db)

        _companionViews = TagListSubViews(this, _viewModel)

        _adapter = TagListRecyclerViewAdapter(_viewModel, view, _companionViews)
        view.setListAdapter(_adapter)

        return view
    }
}