package com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.personaInfo;

/**
 * Created by Administrator on 2016/11/14.
 */

public class PersonMoreBean {

    /**
     * res : 0
     * data : {"diary":"","music":"http://image.wufazhuce.com/FhuQXh8DiSjlRreY4AD6AFREvTdS","other":1,"has_essay":0,"has_music":0}
     */

    private int res;
    /**
     * diary :
     * music : http://image.wufazhuce.com/FhuQXh8DiSjlRreY4AD6AFREvTdS
     * other : 1
     * has_essay : 0
     * has_music : 0
     */

    private DataBean data;

    public int getRes() {
        return res;
    }

    public void setRes(int res) {
        this.res = res;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private String diary;
        private String music;
        private int other;
        private int has_essay;
        private int has_music;

        public String getDiary() {
            return diary;
        }

        public void setDiary(String diary) {
            this.diary = diary;
        }

        public String getMusic() {
            return music;
        }

        public void setMusic(String music) {
            this.music = music;
        }

        public int getOther() {
            return other;
        }

        public void setOther(int other) {
            this.other = other;
        }

        public int getHas_essay() {
            return has_essay;
        }

        public void setHas_essay(int has_essay) {
            this.has_essay = has_essay;
        }

        public int getHas_music() {
            return has_music;
        }

        public void setHas_music(int has_music) {
            this.has_music = has_music;
        }
    }
}
