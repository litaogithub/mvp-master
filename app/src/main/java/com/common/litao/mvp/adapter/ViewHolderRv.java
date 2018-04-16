package isfaaghyth.app.mvp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * author: litao29
 * date: 2018/4/13
 * mail: litao29@jd.com
 * desc: 请简单描述
 */
public class ViewHolderRv extends RecyclerView.ViewHolder
{
    private SparseArray<View> mViews;
    private View mConvertView;

    public ViewHolderRv(View itemView)
    {
        super(itemView);
        mConvertView = itemView;
        mConvertView.setTag(this);
        mViews = new SparseArray<>();
    }


    public static ViewHolderRv get(Context context, ViewGroup parent, int layoutId)
    {

        View itemView = LayoutInflater.from(context).inflate(layoutId, parent,
                false);
        ViewHolderRv holder = new ViewHolderRv(itemView);
        return holder;
    }


    /**
     * 通过viewId获取控件
     *
     * @param viewId
     * @return
     */
    public <T extends View> T getView(int viewId)
    {
        View view = mViews.get(viewId);
        if (view == null)
        {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    public ViewHolderRv setText(int viewId, String text)
    {
        TextView tv = getView(viewId);
        tv.setText(text);
        return this;
    }

    public ViewHolderRv setOnClickListener(int viewId,
                                           View.OnClickListener listener)
    {
        View view = getView(viewId);
        view.setOnClickListener(listener);
        return this;
    }
}