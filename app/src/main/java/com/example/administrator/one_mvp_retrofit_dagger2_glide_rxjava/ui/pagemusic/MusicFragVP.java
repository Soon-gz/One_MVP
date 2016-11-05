package com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.pagemusic;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.bumptech.glide.Glide;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.R;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseMvp.BaseActivity;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseMvp.BaseFragment;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseUtils.BaseRecyclerViewHolder;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseUtils.TLog;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseUtils.ToastUtils;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.Comment.CommentActivity;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.oneUtils.Const;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.oneUtils.FragmentHelper;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.oneUtils.PostResultBean;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;

public class MusicFragVP extends BaseFragment implements MusicFragVPMvpView<MusicFragVPDataBean> {

    @Inject
    MusicFragVPresenter mPresenter;

    @Bind(R.id.music_page_frame)
    FrameLayout music_page_frame;
    @Bind(R.id.music_listView)
    RecyclerView music_listView;


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private MusicFragVPAdapter<MusicFragVPContentBean.DataBean0.DataBean>adapter;
    private List<MusicFragVPContentBean.DataBean0.DataBean> dataBeanList;
    private MusicFragVPDataBean headDataBean;
    private String pageNum = "0";
    private boolean isBottom = false;
    private boolean isCallback = false;

    public MusicFragVP() {
    }

    public static MusicFragVP newInstance(String param1, String param2) {
        MusicFragVP fragment = new MusicFragVP();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected void initViewsAndEvents() {
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        switchLayoutId();
    }

    private void switchLayoutId() {
        switch (mParam1){
            case Const.PAGE_MAIN_IS_FIRST:
                FragmentHelper.showLayoutId(music_page_frame,0);
                break;
            case Const.PAGE_MAIN_OTHER:
                FragmentHelper.showLayoutId(music_page_frame,1);
                initListener();
                mPresenter.getMusicDetail(mParam2,pageNum);
                break;
            case Const.PAGE_MAIN_IS_LAST:
                FragmentHelper.showLayoutId(music_page_frame,2);
                break;
        }
    }

    private void initListener() {
        dataBeanList = new ArrayList<>();
        dataBeanList.add(new MusicFragVPContentBean.DataBean0.DataBean());//头视图占位置
        dataBeanList.add(new MusicFragVPContentBean.DataBean0.DataBean());//音乐相关信息
        dataBeanList.add(new MusicFragVPContentBean.DataBean0.DataBean());//评论相关信息
        adapter = new MusicFragVPAdapter<MusicFragVPContentBean.DataBean0.DataBean>(getActivity(),dataBeanList) {
            @Override
            public int getItemLayoutId(int viewType) {
                int layoutId = 0;
                switch (viewType){
                    case Const.MUSIC_HEAD_ITEM:
                        layoutId = R.layout.item_music_first;
                        break;
                    case Const.MUSIC_SECOND_ITEM:
                        layoutId = R.layout.item_music_second;
                        break;
                    case Const.MUSIC_THRID_ITEM:
                        layoutId = R.layout.item_music_thrid;
                        break;
                    case Const.MUSIC_CONTENT_ITEM:
                        layoutId = R.layout.item_music_content;
                        break;
                }
                return layoutId;
            }

            @Override
            public void bindData(final BaseRecyclerViewHolder holder, int position, final MusicFragVPContentBean.DataBean0.DataBean item) {
                switch (getItemViewType(position)){
                    case Const.MUSIC_HEAD_ITEM:
                        Glide.with(getActivity()).load(headDataBean.getData().getCover()).placeholder(R.drawable.default_indi_bg).thumbnail(0.1f).dontAnimate().into(holder.getImageView(R.id.img_music_first_head));
                        Glide.with(getActivity()).load(headDataBean.getData().getAuthor().getWeb_url()).placeholder(R.drawable.head).thumbnail(0.1f).dontAnimate().into(holder.getCircleImageView(R.id.img_music_pro_head));
                        holder.getTextView(R.id.text_music_pro_name).setText(headDataBean.getData().getAuthor().getUser_name());
                        holder.getTextView(R.id.music_head_contry).setText(headDataBean.getData().getAuthor().getDesc());
                        holder.getTextView(R.id.music_head_date_time).setText(headDataBean.getData().getMaketime().substring(0,10));
                        holder.getTextView(R.id.text_music_title).setText(headDataBean.getData().getTitle());
                        break;
                    case Const.MUSIC_SECOND_ITEM:
                        holder.getRadioGroup(R.id.radioGroup_music).setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                                switch (i) {
                                    case R.id.radiobtn_music_story:
                                        holder.getTextView(R.id.text_music_story).setText("音乐故事");
                                        FragmentHelper.showLayoutId(holder.getFrameLayout(R.id.music_frameLayout),0);
                                        break;
                                    case R.id.radiobtn_music_lyric:
                                        holder.getTextView(R.id.text_music_story).setText("歌词");
                                        FragmentHelper.showLayoutId(holder.getFrameLayout(R.id.music_frameLayout),1);
                                        break;
                                    case R.id.radiobtn_music_about:
                                        holder.getTextView(R.id.text_music_story).setText("歌曲信息");
                                        FragmentHelper.showLayoutId(holder.getFrameLayout(R.id.music_frameLayout),2);
                                        break;
                                }
                            }
                        });
                        //音乐故事
                        holder.getTextView(R.id.text_music_story_name).setText(headDataBean.getData().getStory_title());
                        holder.getTextView(R.id.text_music_story_singer).setText(headDataBean.getData().getAuthor().getUser_name());
                        holder.getTextView(R.id.text_music_story_content).setText(headDataBean.getData().getStory().replaceAll("<br>","\n"));
                        holder.getTextView(R.id.text_music_story_author).setText(headDataBean.getData().getCharge_edt());
                        //歌词
                        holder.getTextView(R.id.text_music_lyric_content).setText(headDataBean.getData().getLyric());
                        holder.getTextView(R.id.text_music_lyric_author).setText(headDataBean.getData().getCharge_edt());
                        //相关信息
                        holder.getTextView(R.id.text_music_detail_about).setText(headDataBean.getData().getInfo());
                        holder.getTextView(R.id.text_music_about_author).setText(headDataBean.getData().getCharge_edt());
                        //评论、分享、点赞
                        holder.getTextView(R.id.text_music_like_num).setText(headDataBean.getData().getPraisenum()+"");
                        holder.getTextView(R.id.text_music_pinglun).setText(headDataBean.getData().getCommentnum()+"");
                        holder.getTextView(R.id.text_music_share_num).setText(headDataBean.getData().getSharenum()+"");
                        holder.getImageView(R.id.item_music_second_comment).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(getActivity(), CommentActivity.class);
                                intent.putExtra(Const.ITEM_ID,mParam2);
                                intent.putExtra(Const.COMMETNT_TYPE,Const.MUSIC);
                                startActivityForResult(intent,Const.COMMENT_REQUEST_CODE);
                            }
                        });
                        holder.getCheckBox(R.id.item_music_second_praise).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                                if (b){
                                    holder.getTextView(R.id.text_music_like_num).setText((Integer.parseInt(holder.getTextView(R.id.text_music_like_num).getText().toString()) + 1)+"");
                                    mPresenter.postMusicPraise(mParam2,Const.MUSIC);
                                }else {
                                    holder.getTextView(R.id.text_music_like_num).setText((Integer.parseInt(holder.getTextView(R.id.text_music_like_num).getText().toString()) - 1)+"");
                                }
                            }
                        });
                        break;
                    case Const.MUSIC_CONTENT_ITEM:
                        Glide.with(getActivity()).load(item.getUser().getWeb_url()).dontAnimate().thumbnail(0.1f).placeholder(R.drawable.head).into(holder.getCircleImageView(R.id.img_music_content_head));
                        holder.getTextView(R.id.text_music_content_username).setText(item.getUser().getUser_name());
                        holder.getTextView(R.id.text_music_content_laund).setText(item.getPraisenum()+"");
                        holder.getTextView(R.id.text_music_content_time).setText(item.getCreated_at().substring(0,10));
                        holder.getTextView(R.id.text_music_content_data).setText(item.getContent());
                        if (Const.NOT_HOT_CONTENT.equals(item.getType()+"") && Const.HOT_CONTENT.equals(dataBeanList.get(position - 1).getType()+"")){
                            holder.getRelativeLayout(R.id.item_music_content_hot).setVisibility(View.VISIBLE);
                        }else {
                            holder.getRelativeLayout(R.id.item_music_content_hot).setVisibility(View.GONE);
                        }
                        if (item.getTouser() != null){
                            holder.getLinearLayout(R.id.linearLayout_music_content).setVisibility(View.VISIBLE);
                            holder.getTextView(R.id.text_music_content_tousername).setText(item.getTouser().getUser_name()+"：");
                            holder.getTextView(R.id.text_music_content_quote).setText(item.getQuote());
                        }else {
                            holder.getLinearLayout(R.id.linearLayout_music_content).setVisibility(View.GONE);
                        }
                        holder.getCheckBox(R.id.checkbox_content).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                                if (b){
                                    holder.getTextView(R.id.text_music_content_laund).setText((Integer.parseInt(holder.getTextView(R.id.text_music_content_laund).getText().toString()) + 1)+"");
                                    mPresenter.postMusicLike(mParam2,Const.MUSIC,item.getId());
                                }else {
                                    holder.getTextView(R.id.text_music_content_laund).setText((Integer.parseInt(holder.getTextView(R.id.text_music_content_laund).getText().toString()) - 1)+"");
                                    mPresenter.postMusicUnPraise(mParam2,Const.MUSIC,item.getId());
                                }
                            }
                        });
                        break;
                }
            }
        };
        music_listView.setItemAnimator(new DefaultItemAnimator());
        music_listView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    protected void iniInjector() {
        ((BaseActivity)getActivity()).activityComponent().inject(this);
        mPresenter.attachView(this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Const.COMMENT_REQUEST_CODE && resultCode == Const.COMMENT_RESULT_CODE){
            TLog.getInstance().i("回调成功。");
            isCallback = true;
            pageNum = "0";
            dataBeanList.clear();
            mPresenter.getMusicDetail(mParam2,pageNum);
        }
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_music_frag_v;
    }

    @Override
    public void showData(MusicFragVPDataBean data) {
        headDataBean = data;
        music_listView.setAdapter(adapter);
    }

    @Override
    public void showContentData(List<MusicFragVPContentBean.DataBean0.DataBean> list) {
        dataBeanList.addAll(list);
        if (isCallback){
            isCallback = false;
            music_listView.scrollToPosition(7);
        }
        music_listView.addOnScrollListener(new RecyclerView.OnScrollListener() {//设置上拉刷新
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (isBottom && newState == RecyclerView.SCROLL_STATE_IDLE){
                    pageNum = dataBeanList.get(dataBeanList.size() - 1).getId();
                    mPresenter.getMusicContentMore(mParam2,pageNum);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                isBottom =((layoutManager.findLastVisibleItemPosition() + 1) == adapter.getItemCount());
            }
        });
        adapter.notifyDataSetChanged();
    }

    @Override
    public void praise(PostResultBean resultBean) {
        if (Const.POST_SUCCESS.equals(resultBean.getMsg())){
            ToastUtils.showToast("操作成功");
        }else {
            ToastUtils.showToast("操作失败");
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.detachViews();
    }
}
