package com.delta.architecturecomponents;

import android.arch.lifecycle.LifecycleService;

import com.delta.architecturecomponents.lifecycle.Official.MyServiceLifecycle;

public class MyService extends LifecycleService {


    @Override
    public void onCreate() {
        super.onCreate();

        getLifecycle().addObserver(new MyServiceLifecycle());
    }
}
