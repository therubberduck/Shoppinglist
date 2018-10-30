package dk.redweb.shoppinglist.FrontEnd

import android.os.Bundle
import com.crashlytics.android.Crashlytics
import com.google.firebase.analytics.FirebaseAnalytics
import com.wealthfront.magellan.Navigator
import com.wealthfront.magellan.Screen
import com.wealthfront.magellan.support.SingleActivity
import dk.redweb.shoppinglist.App
import dk.redweb.shoppinglist.BuildConfig
import dk.redweb.shoppinglist.FrontEnd.MainView.FilterListFragment.FilterListScreen
import dk.redweb.shoppinglist.FrontEnd.MainView.MainScreen
import dk.redweb.shoppinglist.FrontEnd.MainView.OnListFragment.OnListScreen
import dk.redweb.shoppinglist.R
import dk.redweb.shoppinglist.ViewModel.MainViewModel
import io.fabric.sdk.android.Fabric

class MainActivity : SingleActivity() {

    private var _firebaseAnalytics: FirebaseAnalytics? = null
    private lateinit var _navigator: Navigator
    private lateinit var _viewmodel: MainViewModel

    fun navigator(): Navigator {
        if(_navigator == null) {
            createNavigator()
        }
        return _navigator
    }

    override fun createNavigator(): Navigator {
        val db = (application as App).getDatabase()
        _viewmodel = MainViewModel(db)

        val list = mutableListOf<Screen<*>>()
        list.add(OnListScreen(_viewmodel))
        list.add(FilterListScreen(_viewmodel))

        _navigator = Navigator.withRoot(MainScreen(list)).build()

        return _navigator
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)

        if(!BuildConfig.DEBUG) {
            _firebaseAnalytics = FirebaseAnalytics.getInstance(this) //TODO Ask user for permission?
            Fabric.with(this, Crashlytics()) //TODO Turns on Firebase Crashlytics. Ask for user confirmation first
        }
    }
}
