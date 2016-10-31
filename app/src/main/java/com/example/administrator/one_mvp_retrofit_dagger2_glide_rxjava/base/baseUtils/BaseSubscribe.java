package com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseUtils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;

import rx.Subscriber;

/**
 * Created by Shuwen on 2016/10/26.
 * 一般公司后台写协议会规定错误码，错误码也可以统一在这里处理
 */

public abstract class BaseSubscribe<T> extends Subscriber<T> {

    protected Context mContext;
    private long mStartTime;
    private boolean isProgress = true;   //显示进度条
    protected long mMaxProgressTime = 800;//最大等待时间
    protected String mProgressMes = "正在拼命加载...";

    public BaseSubscribe(Context mContext){
        this.mContext = mContext;
    }

    public BaseSubscribe(Context mContext,long mMaxProgressTime){
        this(mContext);
        this.mMaxProgressTime = mMaxProgressTime;
    }

    public BaseSubscribe(Context mContext,String mProgressMes){
        this(mContext);
        this.mProgressMes = mProgressMes;
    }

    public BaseSubscribe(Context mContext,boolean isProgress){
        this(mContext);
        this.isProgress = isProgress;
    }

    public BaseSubscribe(Context mContext,long mMaxProgressTime,String mProgressMes,boolean isProgress){
        this(mContext,mMaxProgressTime);
        this.mProgressMes = mProgressMes;
        this.isProgress = isProgress;
    }

    @Override
    public void onStart() {
        super.onStart();
        //判断是否有网络
        boolean isConnected = NetworkUtil.isNetworkConnected(mContext);
        //有网发起网络请求,没网弹出提示框
        if (isConnected){
            showProgress();
        }else {
            unsubscribe();
            dialog();
        }
    }

    //网络设置对话框
    protected void dialog() {
        CustomAlertDialog.dialogNetSet(mContext,"进入无网络的四次元，请检查并设置网络状态。",new CustomAlertDialog.OnDialogClickListener() {
            @Override
            public void doSomeThings() {
                Intent intent = new Intent("/");
                if (android.os.Build.VERSION.SDK_INT > 10) {
                    intent = new Intent(
                            android.provider.Settings.ACTION_SETTINGS);
                } else {
                    intent = new Intent();
                    ComponentName component = new ComponentName(
                            "com.android.settings",
                            "com.android.settings.Settings");
                    intent.setComponent(component);
                    intent.setAction("android.intent.action.VIEW");
                }
                mContext.startActivity(intent);
            }
        });
    }

    //显示等待条
    private void showProgress() {
        if (isProgress) {
            mStartTime = System.currentTimeMillis();
            ProgressDialogHelper.getInstance().showProgressDialog(mContext, mProgressMes);
        }
    }

    //隐藏等待条
    private void hideProgress() {
        if (isProgress) {
            long endTime = System.currentTimeMillis();
            long time = mMaxProgressTime - endTime + mStartTime;
            time = time < 0 ? 0 : time;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    hideProgressNow();
                }
            }, time);
        }
    }

    //马上结束进度条
    public void hideProgressNow() {
        isProgress = false;
        ProgressDialogHelper.getInstance().hideProgressDialog();
    }

    @Override
    public void onCompleted() {
        hideProgress();
    }

    @Override
    public void onError(Throwable e) {
        hideProgress();
        ToastUtils.showToast("网络数据请求失败"+"\n"+e.toString());
        TLog.getInstance().i(e.toString());
    }

    @Override
    public void onNext(T t) {
        hideProgress();
        onNextJSONObject(t);
    }

    public abstract void onNextJSONObject(T t);
}
