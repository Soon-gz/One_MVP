package com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.Comment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.R;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseMvp.BaseActivity;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseMvp.IBaseView;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseUtils.StringUtils;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseUtils.ToastUtils;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.oneUtils.Const;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.oneUtils.PostResultBean;

import java.util.Timer;
import java.util.TimerTask;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;

public class CommentActivity extends BaseActivity implements IBaseView<PostResultBean> {

    @Inject
    CommentPresenter mPresenter;

    @Bind(R.id.comment_edit)
    EditText comment_edit;
    @Bind(R.id.comment_text_number)
    TextView comment_text_number;
    @Bind(R.id.comment_complete)
    TextView comment_complete;

    private String ItemId;
    private String CommentType;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_comment;
    }

    @Override
    protected void iniInjector() {
        activityComponent().inject(this);
        mPresenter.attachView(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachViews();
    }

    @Override
    protected void initViewsAndEvents(Bundle savedInstanceState) {
        ItemId = getIntent().getStringExtra(Const.ITEM_ID);
        CommentType = getIntent().getStringExtra(Const.COMMETNT_TYPE);
        comment_edit.setFocusable(true);
        //打开软键盘
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {

            public void run() {
                InputMethodManager inputManager = (InputMethodManager) comment_edit.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.showSoftInput(comment_edit, 0);
            }

        }, 500);
        comment_edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                comment_text_number.setText((Integer.parseInt(comment_text_number.getText().toString()) - charSequence.length()) + "");
                checkCanComplete();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public void checkCanComplete(){
        if (!StringUtils.isEmpty(comment_edit.getText().toString())){
            comment_complete.setTextColor(getResources().getColor(R.color.coarse_blue));
        }else {
            comment_complete.setTextColor(getResources().getColor(R.color.gray));
        }
    }

    @OnClick({R.id.comment_close_ivbtn,R.id.comment_complete})
    public void commentOnClick(View view){
        switch (view.getId()){
            case R.id.comment_close_ivbtn:
                finish();
                break;
            case R.id.comment_complete:
                if (isCanSend()){
                    mPresenter.postComment(ItemId,CommentType,"0",comment_edit.getText().toString());
                }
                break;
        }
    }

    public boolean isCanSend(){
        if (StringUtils.isEmpty(comment_edit.getText().toString())){
            ToastUtils.showToast("无法发送空内容:)");
            return false;
        }
        return true;
    }

    @Override
    public void showData(PostResultBean data) {
        if (Const.POST_SUCCESS.equals(data.getMsg())){
            ToastUtils.showToast("发布成功");
            Intent intent = getIntent();
            setResult(Const.COMMENT_RESULT_CODE,intent);
            finish();
        }else {
            ToastUtils.showToast("发布失败");
        }
    }
}
