package dk.redweb.shoppinglist.FrontEnd

import android.os.Bundle
import com.crashlytics.android.Crashlytics
import com.google.firebase.analytics.FirebaseAnalytics
import com.wealthfront.magellan.Navigator
import com.wealthfront.magellan.Screen
import com.wealthfront.magellan.support.SingleActivity
import dk.redweb.shoppinglist.App
import dk.redweb.shoppinglist.FrontEnd.MainView.FilterListFragment.FilterListScreen
import dk.redweb.shoppinglist.FrontEnd.MainView.MainScreen
import dk.redweb.shoppinglist.FrontEnd.MainView.OnListFragment.OnListScreen
import dk.redweb.shoppinglist.R
import io.fabric.sdk.android.Fabric

class MainActivity : SingleActivity() {

    private var _firebaseAnalytics: FirebaseAnalytics? = null

    override fun createNavigator(): Navigator {
        val viewmodel = (application as App).getViewModel()

        val list = mutableListOf<Screen<*>>()
        list.add(OnListScreen(viewmodel))
        list.add(FilterListScreen(viewmodel))

        return Navigator.withRoot(MainScreen(list)).build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)

        _firebaseAnalytics = FirebaseAnalytics.getInstance(this) //TODO Ask user for permission?
        Fabric.with(this, Crashlytics()) //TODO Turns on Firebase Crashlytics. Ask for user confirmation first
    }
}
