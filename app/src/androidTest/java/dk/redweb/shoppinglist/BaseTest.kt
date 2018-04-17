package dk.redweb.shoppinglist

import dk.redweb.shoppinglist.Utility.MyLog
import org.junit.AfterClass

/**
 * Created by redwebpraktik on 22/02/2018.
 */
open class BaseTest(classname: String?) {

    companion object {
        protected lateinit var _classname: String
        private var _testsRun = 0

        @AfterClass
        @JvmStatic
        fun logging() {
            MyLog.v("androidTest", _classname + " finished " + _testsRun + " tests")
        }
    }

    init {
        _classname = classname!!
    }

    fun finishTest() {
        MyLog.v("androidTest", _classname + ":" + Thread.currentThread().stackTrace[3].methodName + ": is successful");
        _testsRun++
    }
}