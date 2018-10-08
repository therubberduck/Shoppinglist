package dk.redweb.shoppinglist.FrontEnd.MainView.OnListFragment

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import dk.redweb.shoppinglist.FrontEnd.Custom.RecyclerViewInterface
import dk.redweb.shoppinglist.R
import dk.redweb.shoppinglist.ViewModel.Item
import dk.redweb.shoppinglist.ViewModel.MainViewModel

/**
 * Created by redwebpraktik on 13/02/2018.
 */

class OnListRecyclerViewAdapter(private val _viewModel: MainViewModel, private val recInterface: RecyclerViewInterface, private val screen: OnListScreen): RecyclerView.Adapter<OnListRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType:Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.cell_onlist, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position:Int) {
        val item = _viewModel.getItem(position, true)
        holder.item = item
        holder.txtTitle.text = item.getFullName()
        holder.callback = item.observeOnList(this) {
            if(it == false) {
                item.unobserveOnList(this)
            }
            if(holder.chkSelected.isChecked != it) {
                holder.chkSelected.isChecked = it
            }
        }
        holder.chkSelected.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked) {
                holder.item!!.putOnList()
            }
            else {
                holder.item!!.removeFromList()
            }
        }
    }

    override fun getItemCount():Int {
        val count = _viewModel.getCount(true)
        if(count == 0) {
            recInterface.recyclerViewIsEmpty(true)
            return 0
        }
        recInterface.recyclerViewIsEmpty(false)
        return count
    }

    inner class ViewHolder( val cell: View): RecyclerView.ViewHolder(cell) {
        val chkSelected: CheckBox
        val txtTitle: TextView
        var item: Item? = null

        var callback: (id: Boolean) -> Unit = {}

        init{
            chkSelected = cell.findViewById<View>(R.id.chkSelected) as CheckBox
            txtTitle = cell.findViewById<View>(R.id.txtTitle) as TextView
        }

        override fun toString():String {
            return super.toString() + " '" + txtTitle.text + "'"
        }
    }
}