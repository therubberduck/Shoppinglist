package dk.redweb.shoppinglist.FrontEnd.TagList

import android.content.Context
import android.widget.PopupMenu
import dk.redweb.shoppinglist.FrontEnd.DialogFactory
import dk.redweb.shoppinglist.R
import dk.redweb.shoppinglist.ViewModel.Tag
import dk.redweb.shoppinglist.ViewModel.TagsViewModel

class TagListSubViews(private val _screen: TagListScreen, private val _viewModel: TagsViewModel) {

    fun showLongClickMenu(holder: TagListRecyclerViewAdapter.ViewHolder) {
        val menu: PopupMenu = PopupMenu(holder.cell.context, holder.cell )
        menu.inflate(R.menu.taglist_itemmenu)
        menu.setOnMenuItemClickListener { menuItem ->
            when(menuItem.itemId) {
                R.id.mn_delete ->  deleteItemDialog(holder.cell.context, holder.tag!!)
            }
            true
        }
        menu.show()
    }

    fun deleteItemDialog(context: Context, tag: Tag) {
        val dialogFac = DialogFactory(context, R.string.dialog_delete_tag_title)
        dialogFac.message(context.getString(R.string.dialog_delete_tag_message, tag.getName()))
        dialogFac.positiveButton(R.string.dialog_delete_button){ dialogInterface, i ->
            _viewModel.deleteTag(tag)
        }
        dialogFac.negativeButton(R.string.dialog_cancel)
        dialogFac.show()
    }
}