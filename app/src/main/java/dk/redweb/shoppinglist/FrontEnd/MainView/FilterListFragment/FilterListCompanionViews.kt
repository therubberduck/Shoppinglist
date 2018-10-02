package dk.redweb.shoppinglist.FrontEnd.MainView.FilterListFragment

import android.content.Context
import android.content.DialogInterface
import android.widget.PopupMenu
import dk.redweb.shoppinglist.FrontEnd.DialogFactory
import dk.redweb.shoppinglist.R
import dk.redweb.shoppinglist.ViewModel.Item
import dk.redweb.shoppinglist.ViewModel.MainViewModel

class FilterListCompanionViews(private val _viewModel: MainViewModel) {

    fun showLongClickMenu(holder: FilterListRecyclerViewAdapter.ViewHolder) {
        val menu: PopupMenu = PopupMenu(holder.cell.context, holder.cell )
        menu.inflate(R.menu.filterlist_itemmenu)
        menu.setOnMenuItemClickListener { menuItem ->
            when(menuItem.itemId) {
                R.id.mn_edit -> _viewModel.editItem(holder.item!!)
                R.id.mn_delete ->  deleteItemDialog(holder.cell.context, holder.item!!)
            }
            true
        }
        menu.show()
    }

    fun deleteItemDialog(context: Context, item: Item) {
        val dialogFac = DialogFactory(context, R.string.dialog_delete_title)
        dialogFac.message(context.getString(R.string.dialog_delete_message, item.getName()))
        dialogFac.positiveButton(R.string.dialog_delete_button){ dialogInterface, i ->
            _viewModel.deleteItem(item)
        }
        dialogFac.negativeButton(R.string.dialog_cancel)
        dialogFac.show()
    }
}