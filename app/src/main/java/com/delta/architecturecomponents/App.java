package com.delta.architecturecomponents;

import android.app.Application;
import android.app.Service;
import android.arch.lifecycle.ProcessLifecycleOwner;
import android.arch.persistence.room.Room;
import android.content.Intent;

import com.delta.architecturecomponents.Database.MyDataBase;
import com.delta.architecturecomponents.lifecycle.Official.AppProcessLifeCycle;

/**
 * @description :
 * @autHor :  Jason
 * @date : 2017/11/15 13:33
 */


public class App extends Application {

    private MyDataBase myDataBase;

    @Override
    public void onCreate() {
        super.onCreate();
        myDataBase = Room.databaseBuilder(this, MyDataBase.class, "my").build();
        ProcessLifecycleOwner.get().getLifecycle().addObserver(new AppProcessLifeCycle());
        Service service = new MyService();
        Intent intent = new Intent(this, MyService.class);
        startService(intent);

    }

    public MyDataBase getMyDataBase() {
        return myDataBase;
    }
}
