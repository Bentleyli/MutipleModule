package com.rhkj.mutiplemodule.bean;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.ObservableField;

import com.rhkj.mutiplemodule.BR;

public class SimpleDataBean extends BaseObservable {

    public ObservableField<String> name = new ObservableField<>();

//    String name;
//
//    @Bindable
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//        notifyPropertyChanged(BR.name);
//    }
}
