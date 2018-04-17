package dk.redweb.shoppinglist.FrontEnd.MainView.FilterListFragment


import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dk.redweb.shoppinglist.FrontEnd.Custom.RecyclerViewInterface

import dk.redweb.shoppinglist.R
import dk.redweb.shoppinglist.Utility.visibleIf
import dk.redweb.shoppinglist.ViewModel.MainViewModel
import kotlinx.android.synthetic.main.fragment_filter_list.*

class FilterListFragment : Fragment(), RecyclerViewInterface {

    private var _adapter: FilterListRecyclerViewAdapter? = null

    private lateinit var _viewmodel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _viewmodel = ViewModelProviders.of(activity).get(MainViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_filter_list, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        list.layoutManager = LinearLayoutManager(context)
        _adapter = FilterListRecyclerViewAdapter(_viewmodel, this)
        list.adapter = _adapter
    }

    override fun recyclerViewIsEmpty(isEmpty: Boolean) {
        txtEmptyList.visibleIf(activity) { return@visibleIf isEmpty }
    }
}
