package dk.redweb.shoppinglist.FrontEnd.TagList

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import dk.redweb.shoppinglist.FrontEnd.Custom.RecyclerViewInterface
import dk.redweb.shoppinglist.R
import dk.redweb.shoppinglist.ViewModel.Tag
import dk.redweb.shoppinglist.ViewModel.TagsViewModel

class TagListRecyclerViewAdapter(private val _viewModel: TagsViewModel, private val recInterface: RecyclerViewInterface, private val subViews: TagListSubViews): RecyclerView.Adapter<TagListRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType:Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.cell_tag, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position:Int) {
        val tag = _viewModel.get(position)
        holder.tag = tag
        holder.txtTitle.text = tag.getName()
        holder.cell.setOnLongClickListener {
            subViews.showLongClickMenu(holder)
            true
        }
    }

    override fun getItemCount():Int {
        if(_viewModel.getCount() == 0) {
            recInterface.recyclerViewIsEmpty(true)
            return 0
        }
        recInterface.recyclerViewIsEmpty(false)
        return _viewModel.getCount()
    }



    inner class ViewHolder( val cell: View): RecyclerView.ViewHolder(cell) {
        val txtTitle: TextView
        var tag: Tag? = null

        init{
            txtTitle = cell.findViewById<View>(R.id.txtTitle) as TextView
        }

        override fun toString():String {
            return super.toString() + " '" + txtTitle.text + "'"
        }
    }
}