package com.delta.architecturecomponents.Database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * @description :
 * @autHor :  Jason
 * @date : 2017/11/15 13:13
 */

@Dao
public interface UserDao {

    @Query("Select * from user")
    public List<User> getAll();
    @Insert
    public Long insertBothUsers(User user);
    @Insert
    public void insertUsers(List<User> users);
    @Query("select * from user where first_name like :first and  last_name like :last")
    public User findByName(String first,String last);

    @Delete
    public void deleteUser(User user);
    @Delete
    void deleteUsers(User ... users);

    @Update
    void upDataUser(User user);

}
