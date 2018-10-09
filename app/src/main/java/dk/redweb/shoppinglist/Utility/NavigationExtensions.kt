package dk.redweb.shoppinglist.Utility

import android.app.Activity
import com.wealthfront.magellan.Screen
import dk.redweb.shoppinglist.FrontEnd.MainActivity

fun Screen<*>.gotoScreen(screen: Screen<*>){
    (getActivity() as MainActivity).navigator().goTo(screen)
}

fun Screen<*>.showScreen(screen: Screen<*>){
    (getActivity() as MainActivity).navigator().show(screen)
}

fun Screen<*>.navigateBack() {
    (getActivity() as MainActivity).navigator().goBack()
}