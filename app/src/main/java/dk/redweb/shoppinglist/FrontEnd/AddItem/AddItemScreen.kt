package dk.redweb.shoppinglist.FrontEnd.AddItem

import android.content.Context
import android.support.design.widget.TextInputLayout
import android.view.Menu
import android.view.View
import android.widget.PopupMenu
import com.wealthfront.magellan.Screen
import dk.redweb.shoppinglist.App
import dk.redweb.shoppinglist.FrontEnd.DialogFactory
import dk.redweb.shoppinglist.R
import dk.redweb.shoppinglist.Utility.hideKeyboard
import dk.redweb.shoppinglist.Utility.navigateBack
import dk.redweb.shoppinglist.ViewModel.AddItemViewModel
import dk.redweb.shoppinglist.ViewModel.Item
import dk.redweb.shoppinglist.ViewModel.MainViewModel
import org.jetbrains.anko.find

class AddItemScreen(private val _viewModel: MainViewModel, private val item: Item? = null) : Screen<AddItemView>() {

    private lateinit var _tagsVm: AddItemViewModel
    private lateinit var _tagAdapter: TagsFlowAdapter

    override fun createView(context: Context?): AddItemView {
        val db = (activity.application as App).getDatabase()
        _tagsVm = AddItemViewModel(db, item?.getId())
        _tagAdapter = TagsFlowAdapter(_tagsVm, this)

        val view = AddItemView(context)

        if(item == null) {
            view.setNavTitle(R.string.screen_additem_addtitle)
            view.setCommitButton { addItem() }
        }
        else {
            view.setNavTitle(R.string.screen_additem_edittitle)

            view.setCommitButton(R.string.screen_additem_edit) { editItem() }

            view.setupPrefixSuffixUpdate { updatePrefixSuffixPreview() }

            //Set existing values
            view.setFieldsContents(item.getName(), item.getFullName(), item.getPrefix(), item.getSuffix())
        }

        _tagsVm.observeTagsOnItem(this){
            view.setAdapter(_tagAdapter)
        }
        _tagsVm.setup()
        view.setAdapter(_tagAdapter)

        return view
    }

    fun updatePrefixSuffixPreview(){
        if(view == null) {
            return
        }
        val prefix = view.getPrefix()
        val name = view.getName()
        val suffix = view.getSuffix()
        view.updatePreviewName(item!!.getFullName(prefix, name, suffix))
    }

    fun openAddTagMenu(button: View) {
        val popupmenu = PopupMenu(activity, button)

        val tags = _tagsVm.getUnused()
        val menu = popupmenu.menu
        var i = 0
        tags.forEach {
            menu.add(Menu.NONE, it.getId().toInt(), i, it.getName())
            i++
        }

        popupmenu.setOnMenuItemClickListener {
            _tagsVm.selectTag(it.itemId.toLong())
            true
        }

        popupmenu.show()
    }

    fun addItem() {
        val name = view.getName()

        if(name.isBlank()) {
            val hint = view.find<TextInputLayout>(R.id.tilItemName).hint.toString()
            DialogFactory(activity).showValidationDialog(R.string.validation_fieldempty, hint) {dialog, i ->
                view.requestFocus_Name()
            }
            return
        }

        val tags = _tagsVm.getUsed()

        activity.hideKeyboard()

        _viewModel.createItem(name, tags)
        navigateBack()
    }

    fun editItem() {
        val name = view.getName()
        val prefix = view.getPrefix()
        val suffix = view.getSuffix()

        if(name.isBlank()) {
            val hint = view.find<TextInputLayout>(R.id.tilItemName).hint.toString()
            DialogFactory(activity).showValidationDialog(R.string.validation_fieldempty, hint) {dialog, i ->
                view.requestFocus_Name()
            }
            return
        }

        activity.hideKeyboard()

        if(item == null) {
            return
        }

        item.setName(name)
        item.updatePrefixSuffix(prefix, suffix)

        _viewModel.editItem(item, _tagsVm.getUsed())
        navigateBack()
    }
}