package dk.redweb.shoppinglist.FrontEnd.MainView.OnListFragment

import android.arch.lifecycle.Observer
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import dk.redweb.shoppinglist.R
import dk.redweb.shoppinglist.ViewModel.Item
import dk.redweb.shoppinglist.ViewModel.MainViewModel

/**
 * Created by redwebpraktik on 13/02/2018.
 */

class OnListRecyclerViewAdapter(private val _viewModel: MainViewModel, private val screen: OnListScreen): RecyclerView.Adapter<OnListRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType:Int): ViewHolder {
        val view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cell_onlist, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position:Int) {
        _viewModel.getLiveItem(position).observe(screen.activity, Observer<Item>{
            item ->
            if (item != null) {
                holder.item = item
                holder.txtTitle.text = item.getName()
                item.isOnList().observe(screen.activity, Observer {
                    isChecked ->
                    if(isChecked != null) {
                        holder.chkSelected.isChecked = isChecked
                    }
                })
                holder.chkSelected.setOnCheckedChangeListener { buttonView, isChecked ->
                    if(isChecked) {
                        holder.item!!.putOnList()
                    }
                    else {
                        holder.item!!.removeFromList()
                    }
                }
            }
        })
    }

    override fun getItemCount():Int {
        if(_viewModel.getCount() == 0) {
            screen.recyclerViewIsEmpty(true)
            return 0
        }
        screen.recyclerViewIsEmpty(false)
        return _viewModel.getCount()
    }

    inner class ViewHolder( val cell: View): RecyclerView.ViewHolder(cell) {
        val chkSelected: CheckBox
        val txtTitle: TextView
        var item: Item? = null

        init{
            chkSelected = cell.findViewById<View>(R.id.chkSelected) as CheckBox
            txtTitle = cell.findViewById<View>(R.id.txtTitle) as TextView
        }

        override fun toString():String {
            return super.toString() + " '" + txtTitle.getText() + "'"
        }
    }
}