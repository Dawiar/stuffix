package com.doberman.it.stuffix.common.util

import android.util.Log
import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.util.concurrent.atomic.AtomicBoolean


open class SingleHandledEvent<T> : MutableLiveData<T>() {
    private val TAG = "SingleLiveEvent"

    private val mPending = AtomicBoolean(false)

    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        if (hasActiveObservers()) {
            Log.w(TAG, "Multiple observers registered but only one will be notified of changes.")
        }

        super.observe(owner, Observer { t ->
            if (mPending.compareAndSet(true, false)) {
                observer.onChanged(t)
            }
        })
    }

    /**
     * Returns the content, even if it's already been handled.
     */
    fun softObserve(owner: LifecycleOwner, observer: Observer<in T>) {
        super.observe(owner, observer)
    }

    @MainThread
    override fun setValue(t: T?) {
        mPending.set(true)
        super.setValue(t)
    }

    @MainThread
    open fun call() {
        value = null
    }
}