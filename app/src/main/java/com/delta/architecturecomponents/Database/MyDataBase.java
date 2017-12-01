package com.delta.architecturecomponents.Database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;
import android.support.annotation.NonNull;

/**
 * @description :
 * @autHor :  Jason
 * @date : 2017/11/15 13:27
 */

@Database(entities = User.class,version = 2,exportSchema = true)
public abstract class MyDataBase  extends RoomDatabase{

    public abstract UserDao userDao();

    public static final Migration migration1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {

        }
    };
}
