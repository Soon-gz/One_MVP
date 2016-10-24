package com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseMvp;

/**
 * Created by Shuwen on 2016/10/13.
 */

public abstract class BasePresenter<T extends IBaseView> implements Presenter<T> {
    private T mMvpView;

    @Override
    public void attachViews(T mvpView) {
        mMvpView = mvpView;
        //检查Presenter是否绑定
        checkViewAttached();
        attachView(mMvpView);
    }

    /**
     * presenter中一些释放操作可在这里进行
     */
    @Override
    public void detachViews() {
        mMvpView = null;
    }

    public boolean isViewAttached() {
        return mMvpView != null;
    }

    public abstract void attachView(T mMvpView);

    public T getMvpView() {
        return mMvpView;
    }

    public void checkViewAttached() {
        if (!isViewAttached()) throw new MvpViewNotAttachedException();
    }

    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("Please call Presenter.attachView(MvpView) before" +
                    " requesting data to the Presenter");
        }
    }
}
