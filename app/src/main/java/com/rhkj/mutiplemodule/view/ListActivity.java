package com.rhkj.mutiplemodule.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.rhkj.mutiplemodule.BR;
import com.rhkj.mutiplemodule.R;
import com.rhkj.mutiplemodule.UserBean;
import com.rhkj.mutiplemodule.adapter.TestAdapter;
import com.rhkj.mutiplemodule.bean.SimpleDataBean;
import com.rhkj.mutiplemodule.databinding.ActivityListBinding;
import com.rhkj.mutiplemodule.viewmodel.TestVM;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity implements XRecyclerView.LoadingListener {

    private ActivityListBinding binding;
    private TestAdapter testAdapter;
    private TestVM testVM;
    private UserBean userBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_list);

        userBean = new UserBean();
        binding.setVariable(BR.user_list_activity, userBean);
        initRecyclerView();
        //testVM = new TestVM();
        testVM = ViewModelProviders.of(this).get(TestVM.class);
        testVM.loadData();
        testVM.mutableLiveData.observe(this, new Observer<List<SimpleDataBean>>() {
            @Override
            public void onChanged(List<SimpleDataBean> data) {
                Toast.makeText(ListActivity.this, "onChanged" + data.size(), Toast.LENGTH_SHORT).show();
                testAdapter.refreshData(data);
                binding.rvList.loadMoreComplete(); //结束加载
                binding.rvList.refreshComplete(); //结束刷新
            }
        });
    }

    private void initRecyclerView() {
        binding.rvList.setLoadingMoreEnabled(true);
        binding.rvList.setRefreshProgressStyle(ProgressStyle.BallClipRotate);
        binding.rvList.setLoadingMoreProgressStyle(ProgressStyle.BallClipRotate);
        binding.rvList.setArrowImageView(R.mipmap.pull_down_arrow);
        binding.rvList.setLoadingListener(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        binding.rvList.setLayoutManager(linearLayoutManager);
        testAdapter = new TestAdapter(this);
        binding.rvList.setAdapter(testAdapter);

        testAdapter.setMyOnClickListener(new TestAdapter.MyOnClickListener() {
            @Override
            public void onClick(int position) {
                testVM.mutableLiveData.getValue().get(position).name.set("刘某某" + position);
                testVM.mutableLiveDataUserBean.setValue(userBean);
                testVM.transformData();
            }
        });
    }

    @Override
    public void onRefresh() {
        Toast.makeText(this, "下拉刷新", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoadMore() {
        Toast.makeText(this, "上拉加载", Toast.LENGTH_SHORT).show();
    }
}
