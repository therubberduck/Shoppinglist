package dk.redweb.shoppinglist.FrontEnd

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface

class DialogFactory(val context: Context) {

    private val _builder = AlertDialog.Builder(context)
    private var _dialog: AlertDialog? = null

    constructor(context: Context, title: Int) : this(context) {
        _builder.setTitle(title)
    }

    constructor(context: Context, title: String) : this(context) {
        _builder.setTitle(title)
    }

    fun message(text: Int) : DialogFactory {
        _builder.setMessage(text)
        return this
    }

    fun message(text: String) : DialogFactory {
        _builder.setMessage(text)
        return this
    }

    fun negativeButton(text: Int, onClick: ((DialogInterface, Int) -> Unit)) : DialogFactory {
        _builder.setNegativeButton(text, onClick)
        return this
    }

    fun negativeButton(text: Int) : DialogFactory {
        return negativeButton(text){dialog, i ->
            dialog.dismiss()
        }
    }

    fun neutralButton(text: Int, onClick: ((DialogInterface, Int) -> Unit)) : DialogFactory {
        _builder.setNeutralButton(text, onClick)
        return this
    }

    fun positiveButton(text: Int, onClick: ((DialogInterface, Int) -> Unit)) : DialogFactory {
        _builder.setPositiveButton(text, onClick)
        return this
    }

    fun title(text: Int) : DialogFactory {
        _builder.setTitle(text)
        return this
    }

    fun show() : AlertDialog? {
        _dialog = _builder.create()
        _dialog?.show()
        return _dialog
    }

    //
    //Templates
    //

//    fun showErrorDialog(message: Int, title: Int = R.string.dialog_defaultErrorTitle) : AlertDialog? {
//        title(title)
//        message(message)
//        negativeButton(R.string.dialog_ok)
//        return show()
//    }
}