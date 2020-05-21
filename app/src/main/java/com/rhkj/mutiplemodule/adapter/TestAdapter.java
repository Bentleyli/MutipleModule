package com.rhkj.mutiplemodule.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.rhkj.mutiplemodule.BR;
import com.rhkj.mutiplemodule.R;
import com.rhkj.mutiplemodule.base.BaseAdapter;
import com.rhkj.mutiplemodule.base.BaseViewHolder;
import com.rhkj.mutiplemodule.bean.SimpleDataBean;

public class TestAdapter extends BaseAdapter<SimpleDataBean, BaseViewHolder> {

    private MyOnClickListener myOnClickListener;

    public TestAdapter(Context context) {
        super(context);
    }

    public interface MyOnClickListener {
        void onClick(int position);
    }

    public void setMyOnClickListener(MyOnClickListener myOnClickListener) {
        this.myOnClickListener = myOnClickListener;
    }

    @Override
    public BaseViewHolder onCreateVH(ViewGroup parent, int viewType) {
        ViewDataBinding dataBinding = DataBindingUtil.inflate(inflater, R.layout.item_test, parent, false);
        return new BaseViewHolder(dataBinding);
    }

    @Override
    public void onBindVH(BaseViewHolder baseViewHolder, int position) {
        ViewDataBinding binding = baseViewHolder.getBinding();
        binding.setVariable(BR.simpleDataBean, mList.get(position));
        binding.setVariable(BR.adapter, this);
        binding.setVariable(BR.position, position);
        binding.executePendingBindings();//防止闪烁

    }

    public void testClick(SimpleDataBean simpleDataBean, final int position) {
        myOnClickListener.onClick(position);
    }
}
