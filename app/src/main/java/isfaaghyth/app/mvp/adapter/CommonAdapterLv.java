package isfaaghyth.app.mvp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * author: litao29
 * date: 2018/4/13
 * mail: litao29@jd.com
 * desc: 请简单描述
 */

public abstract class CommonAdapterLv<T> extends BaseAdapter {

    protected Context mContext;
    protected int mLayoutId;
    protected List<T> mDatas;
    protected LayoutInflater mInflater;


    public CommonAdapterLv(Context context, int layoutId, List<T> datas)
    {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mLayoutId = layoutId;
        mDatas = datas;
    }

    @Override
    public int getCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolderRv viewHolder = ViewHolderRv.get(mContext, parent, mLayoutId);
        convert(viewHolder,mDatas.get(position));
        return convertView;
    }

    public abstract void convert(ViewHolderRv holder, T t);
}
