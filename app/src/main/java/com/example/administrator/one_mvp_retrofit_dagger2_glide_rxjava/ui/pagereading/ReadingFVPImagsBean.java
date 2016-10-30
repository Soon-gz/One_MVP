package com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.pagereading;

import java.util.List;

/**
 * Created by Administrator on 2016/10/30.
 */

public class ReadingFVPImagsBean {

    /**
     * res : 0
     * data : [{"id":"111","title":"昨日重现","cover":"http://image.wufazhuce.com/FgeoLpcgvKMRoA3e_jYjEuiv5CNu","bottom_text":"在近未来时代，人的记忆可以转让出售，并成为一个庞大的产业\u2026\u2026","bgcolor":"#4d4d4d","pv_url":"http://v3.wufazhuce.com:8000/api/reading/carousel/pv/111"},{"id":"110","title":"当你是作家的妻子\u2026","cover":"http://image.wufazhuce.com/FlwpjisEiDQShe9zPKNKyGSxOk6W","bottom_text":"当作家的妻子是种怎样的体验？","bgcolor":"#898989","pv_url":"http://v3.wufazhuce.com:8000/api/reading/carousel/pv/110"},{"id":"109","title":"宠物是人类最好的暖手器","cover":"http://image.wufazhuce.com/FkZo1hcTnPgGwnnVF1iLWHBBe0Dq","bottom_text":"\u201c水来我在水中等你，火来我在灰烬中等你。\u201d","bgcolor":"#3a2722","pv_url":"http://v3.wufazhuce.com:8000/api/reading/carousel/pv/109"},{"id":"106","title":"不正常人类症候群","cover":"http://image.wufazhuce.com/FkkHaZO6iMT1tF9gtIv4_l7sr8dQ","bottom_text":" 就算时空倒转重新选择，也想要过「不正常」的人生。","bgcolor":"#000000","pv_url":"http://v3.wufazhuce.com:8000/api/reading/carousel/pv/106"},{"id":"107","title":"玩家","cover":"http://image.wufazhuce.com/FnzElQ_wfubRltil-Ij4Isu3eMRZ","bottom_text":"网络时代，根本就没有什么是真正的秘密。","bgcolor":"#212f2f","pv_url":"http://v3.wufazhuce.com:8000/api/reading/carousel/pv/107"},{"id":"108","title":"那女孩对我说，说我是一个小偷","cover":"http://image.wufazhuce.com/Fq9bcERG1d5NahszZTeOyfV5HvfO","bottom_text":"\u201c我只不过是一个女孩，站在心爱的男孩的面前，需要他爱我的一个女孩。\u201d","bgcolor":"#0d223d","pv_url":"http://v3.wufazhuce.com:8000/api/reading/carousel/pv/108"},{"id":"105","title":"要做你自己，因为没人想做你","cover":"http://image.wufazhuce.com/FgA7ELCwoaD7NBJcpyKsF_jxdIS9","bottom_text":"\u201c我要有能做我自己的自由， 和敢做我自己的胆量。\u201d","bgcolor":"#fc553b","pv_url":"http://v3.wufazhuce.com:8000/api/reading/carousel/pv/105"},{"id":"104","title":"我的梦想就是成为你的梦想","cover":"http://image.wufazhuce.com/FtKBspBPdeI80gd-KZHX3a1YYlVx","bottom_text":"梦想还是要有的，万一实现了呢。","bgcolor":"#1b4692","pv_url":"http://v3.wufazhuce.com:8000/api/reading/carousel/pv/104"},{"id":"97","title":"毛利文章精选","cover":"http://image.wufazhuce.com/FuMLnn4LaZq25fR5St3QQX4gfPYI","bottom_text":"失败者才不管别的有多重要。任性一回，不然一辈子都憋屈。","bgcolor":"#2e2e2e","pv_url":"http://v3.wufazhuce.com:8000/api/reading/carousel/pv/97"}]
     */

    private int res;
    /**
     * id : 111
     * title : 昨日重现
     * cover : http://image.wufazhuce.com/FgeoLpcgvKMRoA3e_jYjEuiv5CNu
     * bottom_text : 在近未来时代，人的记忆可以转让出售，并成为一个庞大的产业……
     * bgcolor : #4d4d4d
     * pv_url : http://v3.wufazhuce.com:8000/api/reading/carousel/pv/111
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

    public static class DataBean {
        private String id;
        private String title;
        private String cover;
        private String bottom_text;
        private String bgcolor;
        private String pv_url;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getBottom_text() {
            return bottom_text;
        }

        public void setBottom_text(String bottom_text) {
            this.bottom_text = bottom_text;
        }

        public String getBgcolor() {
            return bgcolor;
        }

        public void setBgcolor(String bgcolor) {
            this.bgcolor = bgcolor;
        }

        public String getPv_url() {
            return pv_url;
        }

        public void setPv_url(String pv_url) {
            this.pv_url = pv_url;
        }
    }
}
