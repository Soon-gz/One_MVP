package com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.personaInfo;

/**
 * Created by Administrator on 2016/11/14.
 */

public class PersonInfoBean {

    /**
     * res : 0
     * data : {"web_url":"http://q.qlogo.cn/qqapp/1104596227/95B64A68B5D88D4E5342983EB50C5E1A/40","user_name":"哟、","background":"","score":"592","permission":{"diary":0,"music":1,"other":1},"isdisabled":0,"isauthor":0,"desc":""}
     */

    private int res;
    /**
     * web_url : http://q.qlogo.cn/qqapp/1104596227/95B64A68B5D88D4E5342983EB50C5E1A/40
     * user_name : 哟、
     * background :
     * score : 592
     * permission : {"diary":0,"music":1,"other":1}
     * isdisabled : 0
     * isauthor : 0
     * desc :
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
        private String web_url;
        private String user_name;
        private String background;
        private String score;
        /**
         * diary : 0
         * music : 1
         * other : 1
         */

        private PermissionBean permission;
        private int isdisabled;
        private int isauthor;
        private String desc;

        public String getWeb_url() {
            return web_url;
        }

        public void setWeb_url(String web_url) {
            this.web_url = web_url;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getBackground() {
            return background;
        }

        public void setBackground(String background) {
            this.background = background;
        }

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }

        public PermissionBean getPermission() {
            return permission;
        }

        public void setPermission(PermissionBean permission) {
            this.permission = permission;
        }

        public int getIsdisabled() {
            return isdisabled;
        }

        public void setIsdisabled(int isdisabled) {
            this.isdisabled = isdisabled;
        }

        public int getIsauthor() {
            return isauthor;
        }

        public void setIsauthor(int isauthor) {
            this.isauthor = isauthor;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public static class PermissionBean {
            private int diary;
            private int music;
            private int other;

            public int getDiary() {
                return diary;
            }

            public void setDiary(int diary) {
                this.diary = diary;
            }

            public int getMusic() {
                return music;
            }

            public void setMusic(int music) {
                this.music = music;
            }

            public int getOther() {
                return other;
            }

            public void setOther(int other) {
                this.other = other;
            }
        }
    }
}
