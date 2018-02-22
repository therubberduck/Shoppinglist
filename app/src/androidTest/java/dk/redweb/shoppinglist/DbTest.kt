package dk.redweb.shoppinglist

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import dk.redweb.shoppinglist.Database.AppDatabase
import dk.redweb.shoppinglist.Database.Model.DbItem
import dk.redweb.shoppinglist.Utility.MyLog
import kotlinx.coroutines.experimental.runBlocking
import org.junit.*
import org.junit.runner.RunWith

/**
 * Created by redwebpraktik on 15/02/2018.
 */
@RunWith(AndroidJUnit4::class)
class DbTest {
    private lateinit var _db: AppDatabase

    companion object {
        private var _testsRun = 0

        @AfterClass
        @JvmStatic
        fun logging() {
            MyLog.v("androidTest", "ItemDatabaseTest finished " + _testsRun + " tests")
        }
    }

    @Before
    fun createDb(){
        val appContext = InstrumentationRegistry.getTargetContext()
        _db = AppDatabase(appContext, "dbItemTest", 1)

        Assert.assertNotNull("Database failed to initialize", _db)
    }

    @After
    fun closeDb() {
        _db.Items.clearDatabase()
    }

    @Test
    fun dbIsOpen(){
        Assert.assertNotNull(_db.writableDatabase)

        finishTest()
    }

    @Test
    fun addAndReadItem() {
        //Arrange
        val itemName = "TestItem"
        lateinit var items: List<DbItem>

        //Act
        runBlocking {
            _db.Items.createItem(itemName)
        }

        //Assert
        runBlocking {
            _db.Items.getItems {

                items = it
            }
        }

        Assert.assertEquals(1, items.size)

        val insertedItem = items.first()

        Assert.assertEquals(itemName, insertedItem.name)
        Assert.assertEquals(false, insertedItem.onList)

        finishTest()
    }

    @Test
    fun getAllItems() {
        //Arrange
        val item1Name = "CItem1"
        val item2Name = "BItem2"
        val item3Name = "AItem3"
        lateinit var items: List<DbItem>

        runBlocking {
            _db.Items.createItem(item1Name)
            _db.Items.createItem(item2Name)
            _db.Items.createItem(item3Name)
        }

        //Act
        runBlocking {
            _db.Items.getItems() {
                items = it
            }
        }

        //Assert
        Assert.assertEquals(3, items.size)
        Assert.assertEquals(item3Name, items.first().name)
        Assert.assertEquals(item1Name, items.last().name)

        finishTest()
    }

    @Test
    fun getSelectedItems() {
        //Arrange
        val item1Name = "CItem1"
        val item2Name = "BItem2"
        val item3Name = "AItem3"
        lateinit var items: List<DbItem>

        //Act
        runBlocking {
            _db.Items.createItem(item1Name) {
                id ->
                _db.Items.updateItemOnListStatus(id, true)
            }
            _db.Items.createItem(item2Name) {
                id ->
                _db.Items.updateItemOnListStatus(id, true)
            }
            _db.Items.createItem(item3Name)
        }

        //Assert
        runBlocking {
            _db.Items.getSelectedItems {

                items = it
            }
        }

        Assert.assertEquals(2, items.size)
        Assert.assertEquals(item2Name, items.first().name)
        Assert.assertEquals(item1Name, items.last().name)

        Assert.assertEquals(true, items.first().onList)
        Assert.assertEquals(true, items.last().onList)

        finishTest()
    }

    @Test
    fun updateItemOnListStatus() {
        //Arrange
        val itemName = "TestItem"
        lateinit var items: List<DbItem>

        //Act
        runBlocking {
            _db.Items.createItem(itemName) {
                id ->
                _db.Items.updateItemOnListStatus(id, true)
            }

        }

        //Assert
        runBlocking {
            _db.Items.getItems {

                items = it
            }
        }

        val testedItem = items.first()
        Assert.assertEquals(true, testedItem.onList)

        finishTest()
    }

    fun finishTest() {
        MyLog.v("androidTest", this::class.simpleName + ":" + Thread.currentThread().stackTrace[3].methodName + ": is successful");
        _testsRun++
    }
}