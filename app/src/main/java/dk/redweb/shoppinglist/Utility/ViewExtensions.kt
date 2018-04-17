package dk.redweb.shoppinglist.Utility

import android.app.Activity
import android.view.View

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