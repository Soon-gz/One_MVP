package com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.search;

import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseMvp.IBaseView;

/**
 * Created by Administrator on 2016/11/8.
 */

public interface SearchFragMvpView<SearchPictureBean> extends IBaseView<SearchPictureBean>{
    void showData(SearchReadingBean searchReadingBean);
    void showData(SearchMusicBean searchMusicBean);
    void showData(SearchMovieBean searchMovieBean);
    void showData(SearchAuthorBean searchAuthorBean);
}
