package dk.redweb.shoppinglist

import android.app.Application
import dk.redweb.shoppinglist.Database.AppDatabase
import dk.redweb.shoppinglist.ViewModel.MainViewModel

class App : Application() {

    private lateinit var _db: AppDatabase
    private lateinit var _viewmodel: MainViewModel

    override fun onCreate() {
        super.onCreate()

        _db  = AppDatabase(this, "sldb", 2)

        _viewmodel = MainViewModel(_db)
    }

    fun getDatabase(): AppDatabase {
        return _db
    }

    fun getViewModel(): MainViewModel{
        return _viewmodel
    }
}