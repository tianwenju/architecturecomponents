package com.delta.architecturecomponents;

import android.annotation.SuppressLint;
import android.arch.lifecycle.LifecycleActivity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.persistence.room.RoomDatabase;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;

import com.delta.architecturecomponents.Database.DatabaseCreator;
import com.delta.architecturecomponents.Database.MyDataBase;
import com.delta.architecturecomponents.Database.User;
import com.delta.architecturecomponents.Database.UserDao;
import com.delta.architecturecomponents.lifecycle.Official.MyLifecycle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends LifecycleActivity {

    private static final String TAG = "mainactivity";
    private static final String BLANK = "blank";
    private MainViewModel mainViewModel;
    private BlankFragment blankFragment;

    private Handler mainhander = new Handler(Looper.getMainLooper());
    private RoomDatabase mroomDatabase;


    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getLifecycle().addObserver(new MyLifecycle());
        DatabaseCreator test = DatabaseCreator.builder().with(this).dataBaseName("test").dataBaseClass(MyDataBase.class).initListener(new DatabaseCreator.onInitListener() {
            @Override
            public void OnDataBaseCreate(RoomDatabase.Builder builder) {

                Log.e(TAG, "OnDataBaseCreate: ");
            }

            @Override
            public void onInitData(RoomDatabase roomDatabase) {
                mroomDatabase = roomDatabase;

                Log.e(TAG, "onInitData: ");
                if (roomDatabase instanceof MyDataBase) {
                    UserDao userDao = ((MyDataBase) roomDatabase).userDao();
                    List<User> datas = new ArrayList<>();
                    for (int i = 0; i < 20; i++) {
                        User user = new User();
                        user.setFirstName("sdfsd1" + i);
                        user.setLastName("sdfsdfsdfsdf" + i);
                        datas.add(user);
                    }
                    userDao.insertUsers(datas);
                }
            }
        }).build();
        test.execute(new DatabaseCreator.Call<MyDataBase, User>() {
            @Override
            public User executeIO(MyDataBase roomDatabase) {
                Log.e(TAG, "executeIO: "+Thread.currentThread());
                User byName = roomDatabase.userDao().findByName("sdfsd110", "sdfsdfsdfsdf");
                User user = new User();
                user.setFirstName("sdfsd1" + 10);
                user.setLastName("sdfsdfsdfsdf" + 0);
                // datas.add(user);
                roomDatabase.userDao().deleteUsers(user);
                return byName;
            }

            @Override
            public void executeMain(User o) {
                Log.e(TAG, "executeMain: "+Thread.currentThread());
                //Log.e(TAG, "executeIO: ");
                System.out.println(Thread.currentThread());
            }
        });


//        final MyDataBase myDataBase = Room.databaseBuilder(this, MyDataBase.class, "my").addMigrations(MyDataBase.migration1_2).build();
//        final User user = new User();
//        user.setFirstName("sdfsd1");
//        user.setLastName("sdfsdfsdfsdf");

        //在io线程
//        ArchTaskExecutor.getIOThreadExecutor().executeIO(new Runnable() {
//            @Override
//            public void run() {
//                myDataBase.userDao().insertBothUsers(user);
//                List<User> all = myDataBase.userDao().getAll();
//                Log.e(TAG, "run: "+all.toString());
//                User byName = myDataBase.userDao().findByName("sdfsd", "sdfsdfsdfsdf");
//                Log.e(TAG, "run: "+byName);
//                myDataBase.userDao().deleteUser(user);
//                myDataBase.userDao().deleteUsers(user);
//            }
//        });
//        //在主线程
//        ArchTaskExecutor.getMainThreadExecutor().executeIO(new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        });

//        mainhander.post(new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        });


//        LiveData<Student> studentLiveData=new LiveData<Student>() {
//        }
        Fragment fragmentByTag = getSupportFragmentManager().findFragmentByTag(BLANK);
        if (fragmentByTag == null) {

            blankFragment = new BlankFragment();
            //blankFragment.setRetainInstance(true);
            getSupportFragmentManager().beginTransaction().add(R.id.cc, blankFragment, BLANK).commitAllowingStateLoss();
        }
        final Student mstudent = new Student();
        ViewModelProvider of = ViewModelProviders.of(this);
        mainViewModel = of.get(MainViewModel.class);
        Log.e(TAG, "ViewModelProvider: " + of);
        MainViewModel mainViewModel1 = of.get(MainViewModel.class);
        Log.e(TAG, "onCreate: " + mainViewModel1.hashCode() + mainViewModel.hashCode());
        this.mainViewModel.getStudentMutableLiveData().observe(this, new Observer<Student>() {
            @Override
            public void onChanged(@Nullable Student student) {

            }
        });
        findViewById(R.id.tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MainActivity.this.mainViewModel.changeName(mstudent);
                startActivity(new Intent(MainActivity.this,Main2Activity.class));


            }
        });
        //mainViewModel.changeName();

    }
}
