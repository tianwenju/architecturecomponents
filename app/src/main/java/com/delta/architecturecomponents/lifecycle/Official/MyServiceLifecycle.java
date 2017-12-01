package com.delta.architecturecomponents.lifecycle.Official;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.util.Log;

/**
 * @description :
 * @autHor :  Jason
 * @date : 2017/11/24 14:28
 */


public class MyServiceLifecycle  implements LifecycleObserver {

    private final String TAG =  this.getClass().getName() ;

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)

    public void onCreate() {

        Log.d(TAG, "onCreate() called");
    }

    

    @OnLifecycleEvent(Lifecycle.Event.ON_START)

    public void onStart() {
        Log.d(TAG, "onStart() called");
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)

    public void onStop() {
        Log.d(TAG, "onStop() called");
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)

    public void onDestory() {
        Log.d(TAG, "onDestory() called");
    }
    
}
