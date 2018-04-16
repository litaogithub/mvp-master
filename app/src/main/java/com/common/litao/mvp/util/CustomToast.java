package com.common.litao.mvp.util;

import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

/**
 * Created by litao29 on 2017/12/19.
 * 防止重复点击弹出Toast提示
 */
public class CustomToast {

	private static Toast mToast;
	private static Handler mHandler = new Handler();
	private static Runnable r = new Runnable() {
		public void run() {
			mToast.cancel();
		}
	};
	/**
	 * 2秒钟显示
	 * @param mContext
	 * @param text
	 */
	public static void showToast(Context mContext, String text,int duration) {
		mHandler.removeCallbacks(r);
		if (mToast != null) {
			mToast.setText(text);
		}else{
			mToast = Toast.makeText(mContext, text, duration);
		}
		mHandler.postDelayed(r, duration == Toast.LENGTH_SHORT ? 2000 : 4000);
		mToast.show();
	}
}
