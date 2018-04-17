package dk.redweb.shoppinglist.FrontEnd.MainView

import android.content.Context
import android.view.View
import com.wealthfront.magellan.BaseScreenView
import com.wealthfront.magellan.Screen
import com.wealthfront.magellan.ScreenGroup

class MainScreen(screens: List<Screen<*>>) : ScreenGroup<Screen<*>, MainView>(screens) {
    override fun createView(context: Context?): MainView {
        return MainView(context, screens)
    }

    override fun onShow(context: Context?) {
        super.onShow(context)
        for(screen in screens) {
            view.addPagerView(screen.getView())
        }
    }
}