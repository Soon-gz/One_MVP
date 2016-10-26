package com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseUtils;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.R;


/**
 * Created by Shuwen on 2016/9/25.
 */
public class CustomAlertDialog {
    private static AlertDialog myDialog = null;

    private static final int DIALOG_CANCEL = 5;//进入视频弹出的dialog取消键
    private static final int DIALOG_SURE = 6;//进入视频弹窗的dialog确定键


    /**
     * 创建dialog实例
     * @param context 传入的上下，在其上显示弹窗
     * @param layoutId 自定义布局id
     */
    private static void createDialog(Context context,int layoutId){
        myDialog = new AlertDialog.Builder(context).create();
        myDialog.show();
        Window window = myDialog.getWindow();
        window.setWindowAnimations(R.style.dialog_anim);
        myDialog.setCanceledOnTouchOutside(false);
        myDialog.getWindow().setContentView(layoutId);
    }

    /**
     * 网络设置对话框
     * @param context
     * @param onItemClickListener
     */
    public static void dialogNetSet(Context context,String msg,OnItemClickListener onItemClickListener){
        createDialog(context,R.layout.alert_dialog_layout);
        setItemOnClickListener(R.id.dialog_sure,onItemClickListener,DIALOG_SURE);
        setItemOnClickListener(R.id.dialog_cancel,onItemClickListener,DIALOG_CANCEL);
        ((TextView)myDialog.getWindow().findViewById(R.id.dialog_msg)).setText(msg);
    }


    /**
     * 设置指定项点击事件
     * @param id
     * @param onDialogItemClickListener
     * @param position
     */
    private static void setItemOnClickListener(final int id, final OnItemClickListener onDialogItemClickListener, final int position){
        myDialog.getWindow().findViewById(id).setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        if (onDialogItemClickListener == null){
                            myDialog.dismiss();
                            return;
                        }
                        switch (position){
                            case DIALOG_CANCEL:
                                break;
                            case DIALOG_SURE:
                                ((OnDialogClickListener)onDialogItemClickListener).doSomeThings();
                                break;
                        }
                        myDialog.dismiss();
                    }

                });
    }

    private interface OnItemClickListener{

    }

    public interface OnDialogClickListener extends OnItemClickListener{
        void doSomeThings();
    }
}
