package com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.search;

import java.util.List;

/**
 * Created by Administrator on 2016/11/8.
 */

public class SearchMovieBean {

    /**
     * res : 0
     * data : [{"id":"149","title":"驴得水","verse":"","verse_en":"","score":"82","revisedscore":"0","releasetime":"2016-10-28 00:00:00","scoretime":"2016-10-28 00:00:00","cover":"http://image.wufazhuce.com/Fl5fGQatJVhtUDcvUep_kC-uIpbi"}]
     */

    private int res;
    /**
     * id : 149
     * title : 驴得水
     * verse :
     * verse_en :
     * score : 82
     * revisedscore : 0
     * releasetime : 2016-10-28 00:00:00
     * scoretime : 2016-10-28 00:00:00
     * cover : http://image.wufazhuce.com/Fl5fGQatJVhtUDcvUep_kC-uIpbi
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

    public static class DataBean extends SearchBean {
        private String id;
        private String title;
        private String verse;
        private String verse_en;
        private String score;
        private String revisedscore;
        private String releasetime;
        private String scoretime;
        private String cover;

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

        public String getVerse() {
            return verse;
        }

        public void setVerse(String verse) {
            this.verse = verse;
        }

        public String getVerse_en() {
            return verse_en;
        }

        public void setVerse_en(String verse_en) {
            this.verse_en = verse_en;
        }

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }

        public String getRevisedscore() {
            return revisedscore;
        }

        public void setRevisedscore(String revisedscore) {
            this.revisedscore = revisedscore;
        }

        public String getReleasetime() {
            return releasetime;
        }

        public void setReleasetime(String releasetime) {
            this.releasetime = releasetime;
        }

        public String getScoretime() {
            return scoretime;
        }

        public void setScoretime(String scoretime) {
            this.scoretime = scoretime;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }
    }
}
