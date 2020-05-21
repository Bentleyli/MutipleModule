package com.rhkj.mutiplemodule.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.rhkj.mutiplemodule.UserBean;
import com.rhkj.mutiplemodule.bean.SimpleDataBean;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.login.LoginException;

public class TestVM extends ViewModel {
    public final MutableLiveData<List<SimpleDataBean>> mutableLiveData = new MutableLiveData<>();
    public final MutableLiveData<UserBean> mutableLiveDataUserBean = new MutableLiveData<>();

    private List<SimpleDataBean> list = new ArrayList<>();

    public void loadData() {

        for (int i = 0; i < 50; i++) {
            SimpleDataBean simpleDataBean = new SimpleDataBean();
            simpleDataBean.name.set("张某某" + i);
            list.add(simpleDataBean);
        }

        mutableLiveData.setValue(list);
    }

    public void getdata() {

    }

    public void transformData() {
        Log.e("transformData", "name: " + mutableLiveDataUserBean.getValue().getName() + "\tage:");
    }
}
