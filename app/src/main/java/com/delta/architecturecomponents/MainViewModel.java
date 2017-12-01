package com.delta.architecturecomponents;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;

/**
 * @description :
 * @autHor :  Jason
 * @date : 2017/11/10 11:01
 */


public class MainViewModel extends AndroidViewModel {
    private int i=10;

    private MutableLiveData<Student> studentMutableLiveData =new MutableLiveData<>();

    public MainViewModel(Application application) {
        super(application);
    }


    public void changeName(Student student) {

        student.setAge(i++);
        studentMutableLiveData.setValue(student);

    }

    public MutableLiveData<Student> getStudentMutableLiveData() {
        return studentMutableLiveData;
    }
}
