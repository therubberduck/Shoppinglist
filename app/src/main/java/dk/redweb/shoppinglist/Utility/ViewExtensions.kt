package dk.redweb.shoppinglist.Utility

import android.app.Activity
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText

fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }
    })
}

fun View.visibleIf(activity: Activity? = null, condition: () -> Boolean)  {
    if(condition() && visibility != View.VISIBLE) {
        visible(activity)
    }
    else if(visibility != View.GONE) {
        gone(activity)
    }
}

fun View.visible(activity: Activity? = null){
    if(activity == null) {
        visibility = View.VISIBLE
        return
    }
    activity.runOnUiThread { visibility = View.VISIBLE }
}

fun View.gone(activity: Activity? = null) {
    if(activity == null) {
        visibility = View.GONE
        return
    }
    activity.runOnUiThread { visibility = View.GONE }
}