package dk.redweb.shoppinglist.FrontEnd

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.wealthfront.magellan.Navigator
import com.wealthfront.magellan.support.SingleActivity
import dk.redweb.shoppinglist.FrontEnd.MainView.MainScreen
import dk.redweb.shoppinglist.R
import dk.redweb.shoppinglist.ViewModel.MainViewModel

class FirstActivity : SingleActivity() {

    private lateinit var _viewmodel: MainViewModel

    override fun createNavigator(): Navigator {
        return Navigator.withRoot(MainScreen()).build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)

        _viewmodel = ViewModelProviders.of(this).get(MainViewModel::class.java)
    }
}
