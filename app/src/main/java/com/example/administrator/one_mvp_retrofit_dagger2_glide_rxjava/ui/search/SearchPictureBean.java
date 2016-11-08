package com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.search;

import java.util.List;

/**
 * Created by Administrator on 2016/11/8.
 */

public class SearchPictureBean {

    /**
     * res : 0
     * data : [{"hpcontent_id":"1048","hp_title":"VOL.1027","author_id":"-1","hp_img_url":"http://image.wufazhuce.com/FiMnY_ZpmRZSKbWAjqYuSS5kP01J","hp_img_original_url":"http://image.wufazhuce.com/FiMnY_ZpmRZSKbWAjqYuSS5kP01J","hp_author":"Hello Stranger！&白白 作品","ipad_url":"http://image.wufazhuce.com/Frr1Se5YOOYtjws91pENjW1Yz-Fj","hp_content":"我要一步一步往上爬，在最高点乘着叶片往前飞。小小的天留过的泪和汗，总有一天我有属于我的天。 from 周杰伦《蜗牛》","hp_makettime":"2015-07-30 22:00:00","last_update_date":"2015-12-22 19:38:30","web_url":"http://m.wufazhuce.com/one/1048","wb_img_url":"","praisenum":30578,"sharenum":72,"commentnum":308},{"hpcontent_id":"305","hp_title":"VOL.290","author_id":"-1","hp_img_url":"http://image.wufazhuce.com/FoawVqtbPXYOHUMEExdxwVZF5Fr1","hp_img_original_url":"http://image.wufazhuce.com/FoawVqtbPXYOHUMEExdxwVZF5Fr1","hp_author":"蓝色假期&摄影/陶立夏","ipad_url":"http://image.wufazhuce.com/FoawVqtbPXYOHUMEExdxwVZF5Fr1","hp_content":"挫折，是老天在帮你规划更长远的东西，只是现在还不能告诉你。by 周杰伦","hp_makettime":"2013-07-23 22:00:00","last_update_date":"2015-12-22 16:21:58","web_url":"http://m.wufazhuce.com/one/305","wb_img_url":"","praisenum":975,"sharenum":40,"commentnum":566}]
     */

    private int res;
    /**
     * hpcontent_id : 1048
     * hp_title : VOL.1027
     * author_id : -1
     * hp_img_url : http://image.wufazhuce.com/FiMnY_ZpmRZSKbWAjqYuSS5kP01J
     * hp_img_original_url : http://image.wufazhuce.com/FiMnY_ZpmRZSKbWAjqYuSS5kP01J
     * hp_author : Hello Stranger！&白白 作品
     * ipad_url : http://image.wufazhuce.com/Frr1Se5YOOYtjws91pENjW1Yz-Fj
     * hp_content : 我要一步一步往上爬，在最高点乘着叶片往前飞。小小的天留过的泪和汗，总有一天我有属于我的天。 from 周杰伦《蜗牛》
     * hp_makettime : 2015-07-30 22:00:00
     * last_update_date : 2015-12-22 19:38:30
     * web_url : http://m.wufazhuce.com/one/1048
     * wb_img_url :
     * praisenum : 30578
     * sharenum : 72
     * commentnum : 308
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
        private String hpcontent_id;
        private String hp_title;
        private String author_id;
        private String hp_img_url;
        private String hp_img_original_url;
        private String hp_author;
        private String ipad_url;
        private String hp_content;
        private String hp_makettime;
        private String last_update_date;
        private String web_url;
        private String wb_img_url;
        private int praisenum;
        private int sharenum;
        private int commentnum;

        public String getHpcontent_id() {
            return hpcontent_id;
        }

        public void setHpcontent_id(String hpcontent_id) {
            this.hpcontent_id = hpcontent_id;
        }

        public String getHp_title() {
            return hp_title;
        }

        public void setHp_title(String hp_title) {
            this.hp_title = hp_title;
        }

        public String getAuthor_id() {
            return author_id;
        }

        public void setAuthor_id(String author_id) {
            this.author_id = author_id;
        }

        public String getHp_img_url() {
            return hp_img_url;
        }

        public void setHp_img_url(String hp_img_url) {
            this.hp_img_url = hp_img_url;
        }

        public String getHp_img_original_url() {
            return hp_img_original_url;
        }

        public void setHp_img_original_url(String hp_img_original_url) {
            this.hp_img_original_url = hp_img_original_url;
        }

        public String getHp_author() {
            return hp_author;
        }

        public void setHp_author(String hp_author) {
            this.hp_author = hp_author;
        }

        public String getIpad_url() {
            return ipad_url;
        }

        public void setIpad_url(String ipad_url) {
            this.ipad_url = ipad_url;
        }

        public String getHp_content() {
            return hp_content;
        }

        public void setHp_content(String hp_content) {
            this.hp_content = hp_content;
        }

        public String getHp_makettime() {
            return hp_makettime;
        }

        public void setHp_makettime(String hp_makettime) {
            this.hp_makettime = hp_makettime;
        }

        public String getLast_update_date() {
            return last_update_date;
        }

        public void setLast_update_date(String last_update_date) {
            this.last_update_date = last_update_date;
        }

        public String getWeb_url() {
            return web_url;
        }

        public void setWeb_url(String web_url) {
            this.web_url = web_url;
        }

        public String getWb_img_url() {
            return wb_img_url;
        }

        public void setWb_img_url(String wb_img_url) {
            this.wb_img_url = wb_img_url;
        }

        public int getPraisenum() {
            return praisenum;
        }

        public void setPraisenum(int praisenum) {
            this.praisenum = praisenum;
        }

        public int getSharenum() {
            return sharenum;
        }

        public void setSharenum(int sharenum) {
            this.sharenum = sharenum;
        }

        public int getCommentnum() {
            return commentnum;
        }

        public void setCommentnum(int commentnum) {
            this.commentnum = commentnum;
        }
    }
}
