package com.rhkj.mutiplemodule;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

public class UserBean extends BaseObservable {

    private String name;
    private int age;

    public UserBean() {
    }

    public UserBean(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
        notifyPropertyChanged(BR.age);
    }
}
