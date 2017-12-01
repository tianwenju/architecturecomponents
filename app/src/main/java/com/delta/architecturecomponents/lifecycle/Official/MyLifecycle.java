package com.delta.architecturecomponents.lifecycle.Official;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;

/** ativity的lifecycle
 * @description :
 * @autHor :  Jason
 * @date : 2017/11/23 15:48
 */


public class MyLifecycle implements LifecycleObserver{

    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    void onAny(LifecycleOwner owner, Lifecycle.Event event) {
        System.out.println("onAny:" + event.name());
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    void onCreate() {
        System.out.println("onCreate");
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void onDestroy() {
        System.out.println("onDestroy");
    }
}
