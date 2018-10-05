package dk.redweb.shoppinglist.FrontEnd.MainView.FilterListFragment

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import dk.redweb.shoppinglist.FrontEnd.MainActivity
import dk.redweb.shoppinglist.FrontEnd.PrefixSuffix.PrefixSuffixScreen
import dk.redweb.shoppinglist.R
import dk.redweb.shoppinglist.ViewModel.Item
import dk.redweb.shoppinglist.ViewModel.MainViewModel
import kotlinx.android.synthetic.main.cell_filterlist.view.*

class FilterListRecyclerViewAdapter(private val _viewModel: MainViewModel, private val screen: FilterListScreen, private val companionViews: FilterListCompanionViews): RecyclerView.Adapter<FilterListRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType:Int): ViewHolder {
        val view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cell_filterlist, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position:Int) {
        val item = _viewModel.getItem(position, false);
        holder.item = item
        holder.txtTitle.text = item.getFullName()
        item.observeOnList(this) {itemIsChecked ->
            if(holder.chkSelected.isChecked != itemIsChecked) {
                holder.chkSelected.isChecked = itemIsChecked
            }
        }
        holder.chkSelected.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                holder.item!!.putOnList()
            } else {
                holder.item!!.removeFromList()
            }
        }
        holder.cell.btnAmount.setOnClickListener {
            screen.openSuffixPrefixScreen(item)
        }
        holder.cell.setOnLongClickListener {
            companionViews.showLongClickMenu(holder)
            true
        }
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