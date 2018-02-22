package dk.redweb.shoppinglist.FrontEnd.MainView.OnListFragment


import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import dk.redweb.shoppinglist.R
import dk.redweb.shoppinglist.ViewModel.MainViewModel


/**
 * A simple [Fragment] subclass.
 */
class OnListFragment : Fragment() {

    private var _adapter: OnListRecyclerViewAdapter? = null

    private lateinit var _viewmodel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _viewmodel = ViewModelProviders.of(activity).get(MainViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_on_list, container, false)

        if(view is RecyclerView) {
            val context = view.getContext()
            val recyclerview = view
            recyclerview.layoutManager = LinearLayoutManager(context)
            _adapter = OnListRecyclerViewAdapter(_viewmodel, activity)
            recyclerview.adapter = _adapter

//            _viewmodel.getSelected
        }

        return view
    }

}// Required empty public constructor
