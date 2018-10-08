package dk.redweb.shoppinglist.Utility

import com.wealthfront.magellan.Screen
import dk.redweb.shoppinglist.FrontEnd.MainActivity

fun Screen<*>.gotoScreen(){
    (getActivity() as MainActivity).navigator().goTo(this)
}

fun Screen<*>.showScreen(){
    (getActivity() as MainActivity).navigator().show(this)
}

fun Screen<*>.navigateBack() {
    (getActivity() as MainActivity).navigator().goBack()
}