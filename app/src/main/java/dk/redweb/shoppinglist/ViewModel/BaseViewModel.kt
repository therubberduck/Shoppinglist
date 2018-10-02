package dk.redweb.shoppinglist.ViewModel

abstract class BaseViewModel {

    fun <R> doCallback(callbacks: MutableCollection<(R) -> Unit>, value: R) {
        for (callback in callbacks.toList()) {
            callback(value)
        }
    }
    fun <R, S> doCallback(index: S, callbacksMap: HashMap<S, MutableList<(R) -> Unit>>, value: R) {
        val callbacks = callbacksMap.get(index)?.toList()
        if(callbacks != null) {
            for (callback in callbacks) {
                callback(value)
            }
        }
    }
}