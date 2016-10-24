package com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseMvp;

import java.util.List;

/**
 * Created by Shuwen on 2016/10/13.
 */
public interface IBaseView<T> {
    void showData(List<T> data);
    void showData(T data);
}
