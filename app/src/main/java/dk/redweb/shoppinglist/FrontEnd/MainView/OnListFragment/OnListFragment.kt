package dk.redweb.shoppinglist.FrontEnd.MainView.OnListFragment


import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dk.redweb.shoppinglist.FrontEnd.Custom.RecyclerViewInterface

import dk.redweb.shoppinglist.R
import dk.redweb.shoppinglist.ViewModel.MainViewModel
import kotlinx.android.synthetic.main.fragment_filter_list.*
import dk.redweb.shoppinglist.Utility.visibleIf


class OnListFragment : Fragment(), RecyclerViewInterface {

    private var _adapter: OnListRecyclerViewAdapter? = null

    private lateinit var _viewmodel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _viewmodel = ViewModelProviders.of(activity).get(MainViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_on_list, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        list.layoutManager = LinearLayoutManager(context)
        _adapter = OnListRecyclerViewAdapter(_viewmodel, this)
        list.adapter = _adapter
    }

    override fun recyclerViewIsEmpty(isEmpty: Boolean) {
        txtEmptyList.visibleIf(activity) { return@visibleIf isEmpty }
    }
}