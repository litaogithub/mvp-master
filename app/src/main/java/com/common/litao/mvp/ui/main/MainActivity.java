package isfaaghyth.app.mvp.ui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import isfaaghyth.app.mvp.R;
import isfaaghyth.app.mvp.adapter.CommonAdapterRv;
import isfaaghyth.app.mvp.adapter.ViewHolderRv;
import isfaaghyth.app.mvp.base.BaseActivity;
import isfaaghyth.app.mvp.bean.MainDto;

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View {

    @BindView(R.id.lst_employee)
    RecyclerView mRecyclerView;
    private CommonAdapterRv mAdapter;
    private List<MainDto> mDatas = new ArrayList<>();

    @Override protected MainPresenter createPresenter() {
        return new MainPresenter(this);
    }

    @Override protected void initialize(Bundle savedInstanceState) {
        setBarTitle("主页");
        setBackBtn();
    }

    @Override
    protected void initData() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter = getAdapter());
    }

    @Override
    public CommonAdapterRv<MainDto> getAdapter() {
        return new CommonAdapterRv<MainDto>(this,R.layout.layout_employee_item,mDatas) {
            @Override
            public void convert(ViewHolderRv holder, MainDto s) {
                holder.setText(R.id.txt_title,s.getName());
                holder.setText(R.id.txt_desc,s.getDesc());
            }
        };
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);

    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        presenter.loadEmployees();
    }

    @Override
    protected int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void onSuccess(String data, int type) {
        mDatas.clear();
        mDatas.addAll(JSON.parseArray(data,MainDto.class));
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFailure(String failMsg) {
        toastShort(failMsg);
    }
}
