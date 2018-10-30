package dk.redweb.shoppinglist.FrontEnd.AddTag

import android.content.Context
import android.support.design.widget.TextInputLayout
import com.wealthfront.magellan.Screen
import dk.redweb.shoppinglist.FrontEnd.DialogFactory
import dk.redweb.shoppinglist.R
import dk.redweb.shoppinglist.Utility.hideKeyboard
import dk.redweb.shoppinglist.Utility.navigateBack
import dk.redweb.shoppinglist.ViewModel.Tag
import dk.redweb.shoppinglist.ViewModel.TagsViewModel
import org.jetbrains.anko.find

class AddTagScreen(private val _viewModel: TagsViewModel, private val tag: Tag? = null) : Screen<AddTagView>() {
    override fun createView(context: Context?): AddTagView {
        val view = AddTagView(context)

        if(tag == null) {
            view.setNavTitle(R.string.screen_addtag_addtitle)
            view.setCommitButton { addTag() }
        }
        else {
            view.setNavTitle(R.string.screen_addtag_edittitle)

            view.setCommitButton(R.string.screen_addtag_edit) { editTag() }

            //Set existing values
            view.setFieldsContents(tag.getName(), tag.getType())
        }

        return view
    }

    fun addTag() {
        val name = view.getName()
        val type = view.getType()

        if(name.isBlank()) {
            val hint = view.find<TextInputLayout>(R.id.tilTagName).hint.toString()
            DialogFactory(activity).showValidationDialog(R.string.validation_fieldempty, hint) {dialog, i ->
                view.requestFocus_Name()
            }
            return
        }

        activity.hideKeyboard()

        _viewModel.createTag(name, type)
        navigateBack()
    }

    fun editTag() {
        val name = view.getName()
        val type = view.getType()

        if(name.isBlank()) {
            val hint = view.find<TextInputLayout>(R.id.tilItemName).hint.toString()
            DialogFactory(activity).showValidationDialog(R.string.validation_fieldempty, hint) {dialog, i ->
                view.requestFocus_Name()
            }
            return
        }

        activity.hideKeyboard()

        if(tag == null) {
            return
        }

        tag.setName(name)
        tag.setType(type)

        _viewModel.updateTag(tag)
        navigateBack()
    }
}