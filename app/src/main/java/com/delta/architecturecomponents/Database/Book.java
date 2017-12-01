package com.delta.architecturecomponents.Database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;

/**
 * @description :
 * @autHor :  Jason
 * @date : 2017/11/15 13:27
 */

@Entity
public class Book {

    @ColumnInfo
    private String name;
    @ColumnInfo
    private double price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
