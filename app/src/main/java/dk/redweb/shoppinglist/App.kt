package dk.redweb.shoppinglist

import android.app.Application
import dk.redweb.shoppinglist.Database.AppDatabase
import dk.redweb.shoppinglist.ViewModel.MainViewModel

class App : Application() {

    private lateinit var _db: AppDatabase

    override fun onCreate() {
        super.onCreate()

        _db  = AppDatabase(this, "sldb")
    }

    fun getDatabase(): AppDatabase {
        return _db
    }
}