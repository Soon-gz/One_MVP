package com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.search;

import java.util.List;

/**
 * Created by Administrator on 2016/11/8.
 */

public class SearchAuthorBean {

    /**
     * res : 0
     * data : [{"user_id":"7448679","user_name":"阿枣","web_url":"http://image.wufazhuce.com/FjM-TK7UjhU11Rdshf-a6SeA2_uL","desc":"写作者，翻译。"}]
     */

    private int res;
    /**
     * user_id : 7448679
     * user_name : 阿枣
     * web_url : http://image.wufazhuce.com/FjM-TK7UjhU11Rdshf-a6SeA2_uL
     * desc : 写作者，翻译。
     */

    private List<DataBean> data;

    public int getRes() {
        return res;
    }

    public void setRes(int res) {
        this.res = res;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean extends SearchBean{
        private String user_id;
        private String user_name;
        private String web_url;
        private String desc;

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getWeb_url() {
            return web_url;
        }

        public void setWeb_url(String web_url) {
            this.web_url = web_url;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }
}
