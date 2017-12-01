package com.delta.architecturecomponents.Database;

import android.annotation.SuppressLint;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.xml.transform.Result;

/**
 * @description :
 * @autHor :  Jason
 * @date : 2017/11/15 17:03
 */


public class DatabaseCreator  {

    private String name;
    private Context context;
    private Class dataBaseClass;
    private List<onInitListener> listeners;


    AtomicBoolean minitalizing = new AtomicBoolean(true);
    private ThreadPoolExecutor threadPoolExecutor;
    private RoomDatabase roomDatabase;
    private Handler mhander;


    private DatabaseCreator (Builder builder){

        name=builder.name;
        context=builder.context;
        dataBaseClass=builder.dataBaseClass;
        listeners = builder.listeners;



    }

    class NetWorkAsynTask extends AsyncTask<String,Integer,Result>{

        @Override
        protected Result doInBackground(String... strings) {
            return null;
        }

        @Override
        protected void onPostExecute(Result result) {
            super.onPostExecute(result);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    @SuppressLint("RestrictedApi")
    private void createDb(final Context context, @NonNull final Class TClass, final String DATABASE_NAME) {

        //是否是第一次初始化
        if (!minitalizing.compareAndSet(true, false)) {
            return;
        }
        mhander = new Handler(Looper.getMainLooper());
        threadPoolExecutor = new ThreadPoolExecutor(1, 1, 0, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(3));
        threadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                RoomDatabase.Builder myDataBaseBuilder = Room.databaseBuilder(context, TClass, DATABASE_NAME);

                for (onInitListener listener : listeners) {
                    listener.OnDataBaseCreate(myDataBaseBuilder);
                }
                roomDatabase = myDataBaseBuilder.build();
                for (onInitListener listener : listeners) {
                    listener.onInitData(roomDatabase);
                }
                System.out.println(Thread.currentThread()+"1----------------"+roomDatabase);
            }


        });
        //开始准备核心线程
        threadPoolExecutor.prestartAllCoreThreads();

    }
    public void execute(Call call){
        threadPoolExecutor.execute(new RoomDatabaseRunnable(call));
    }
    public interface Call<T extends RoomDatabase,M>{


        M executeIO(T roomDatabase);
        void executeMain(M m);
    }

    //共享变量千万不要通过构造传入进来
    private class RoomDatabaseRunnable implements Runnable {


        private   Call mCall;

        public RoomDatabaseRunnable() {
        }

        public RoomDatabaseRunnable(Call mCall) {
            this.mCall = mCall;
        }

        @Override
        public void run() {

            final Object text= mCall.executeIO(roomDatabase);


            mhander.post(new Runnable() {
                @Override
                public void run() {
                    mCall.executeMain(text);
                }
            });
            System.out.println(Thread.currentThread()+"2----------------"+roomDatabase);
        }
    }



    //必须在create之前调用
    public interface onInitListener{
        void OnDataBaseCreate(RoomDatabase.Builder builder);
        void onInitData(RoomDatabase roomDatabase);
    }

    public static class Builder{


        private String name;
        private Context context;
        private Class dataBaseClass;
        private List<onInitListener> listeners = Collections.synchronizedList(new ArrayList<onInitListener>());

        public Builder dataBaseName(@NonNull String name) {
           this. name =name;
            return this;
        }

        public Builder with(@NonNull Context context) {
            this.context = context;
            return this;
        }

        public Builder initListener(@Nullable onInitListener onInitListener) {
            if (!listeners.contains(onInitListener)){
                listeners.add(onInitListener);

            }
            return this;
        };

        public Builder removeInitListener(@Nullable onInitListener onInitListener) {
            if (listeners.contains(onInitListener)){
                listeners.remove(onInitListener);

            }
            return this;
        }
        public Builder dataBaseClass(@NonNull Class dataBaseClass) {
            this.dataBaseClass =dataBaseClass;
            return this;
        }

        public DatabaseCreator build(){
            DatabaseCreator databaseCreator=new DatabaseCreator(this);
            databaseCreator.createDb(context, dataBaseClass, name);
            return databaseCreator;

        }
    }
}
