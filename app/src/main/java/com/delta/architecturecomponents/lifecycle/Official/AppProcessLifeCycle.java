package com.delta.architecturecomponents.lifecycle.Official;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.util.Log;

/**
 * @description :用于进程间通信
 * @autHor :  Jason
 * @date : 2017/11/24 11:06
 */


public class AppProcessLifeCycle implements LifecycleObserver {

public static final String TAG="sdfsdf";
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onCreate() {


        Log.e(TAG, "onCreate: ");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume(){

        Log.e(TAG, "onResume: ");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onStop(){
        Log.e(TAG, "onStop: ");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestory(){

        Log.e(TAG, "onDestory: ");
    }
}