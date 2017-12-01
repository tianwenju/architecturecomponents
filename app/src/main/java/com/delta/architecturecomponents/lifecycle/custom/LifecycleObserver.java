package com.delta.architecturecomponents.lifecycle.custom;



/**
 * @description :
 * @autHor :  Jason
 * @date : 2017/11/23 10:25
 */


public interface LifecycleObserver {
    public abstract void onStateChanged(Lifecycle.Event mEvent);
}