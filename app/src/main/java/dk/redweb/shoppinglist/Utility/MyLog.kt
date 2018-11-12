package dk.redweb.shoppinglist.Utility

import android.util.Log
import dk.redweb.shoppinglist.BuildConfig

/**
 * Created by redwebpraktik on 21/11/2017.
 */
class MyLog {
    companion object {
        fun v(text: String?, ex: Exception? = null) {
            MyLog.v("", text, ex)
        }

        fun v(tag: String, text: String?, ex: Exception? = null) {
            if(BuildConfig.DEBUG) {
                Log.v(tag, text ?: "", ex)
            }
        }

        fun d(text: String?, ex: Exception? = null) {
            MyLog.d("", text, ex)
        }

        fun d(tag: String, text: String?, ex: Exception? = null) {
            if(BuildConfig.DEBUG) {
                Log.d(tag, text ?: "", ex)
            }
        }

        fun i(text: String?, ex: Exception? = null) {
            MyLog.i("", text, ex)
        }

        fun i(tag: String, text: String?, ex: Exception? = null) {
            Log.i(tag, text ?: "", ex)
        }

        fun w(text: String?, ex: Exception? = null) {
            MyLog.w("", text, ex)
        }

        fun w(tag: String, text: String?, ex: Exception? = null) {
            Log.w(tag, text ?: "", ex)
        }

        fun e(text: String?, ex: Exception? = null) {
            MyLog.e("", text, ex)
        }

        fun e(tag: String, text: String?, ex: Exception? = null) {
            Log.e(tag, text ?: "", ex)
        }

        fun wtf(text: String?, ex: Exception? = null) {
            MyLog.wtf("", text, ex)
        }

        fun wtf(tag: String, text: String?, ex: Exception? = null) {
            Log.wtf(tag, text ?: "", ex)
        }
    }
}