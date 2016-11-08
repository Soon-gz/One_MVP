package com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.search;

import java.util.List;

/**
 * Created by Administrator on 2016/11/8.
 */

public class SearchMusicBean {

    /**
     * res : 0
     * data : [{"id":"1026","title":"没有寄的信","cover":"http://image.wufazhuce.com/FtU4w1O3I-BVNDIXn68a4bPwm3A7","platform":"1","music_id":"150829","author":{"user_id":"6585698","user_name":"萧潇","web_url":"http://image.wufazhuce.com/FlyGcGM9HOvrwITB9Ft6_nr1DIFa","desc":"歌手，演员，主持人"}},{"id":"925","title":"情歌没有告诉你","cover":"http://image.wufazhuce.com/FsejxiHW2k9nN12KUVusIpPSRrC6","platform":"1","music_id":"1769888606","author":{"user_id":"5701143","user_name":"梁静茹","web_url":"http://image.wufazhuce.com/FiRXdQseU1DP2JXL-N-iDJa68mRj","desc":"音乐人"}},{"id":"892","title":"你离开了南京，从此没有人和我说话","cover":"http://image.wufazhuce.com/FmgWZswgLTXgT61WcqCzay2YIYFK","platform":"2","music_id":"http://music.wufazhuce.com/FgUP7c0sKZqRoEsaeYToM2Rl1fid","author":{"user_id":"4814789","user_name":"李志","web_url":"http://image.wufazhuce.com/Fj4nGvdv40grnkuAPzD6nkiHeWIR","desc":"独立音乐人"}},{"id":"767","title":"就像是一块没有记忆的石头","cover":"http://image.wufazhuce.com/Fu-qoj4GZHWduoySlpTQ3_itZTXl","platform":"2","music_id":"http://music.wufazhuce.com/ljFqEB7rcX-cUWm3S-DhW4TP4pPw","author":{"user_id":"4814948","user_name":"陈小熊","web_url":"http://image.wufazhuce.com/FoM_aSuUi5D54q_rvd7V40QDQQQB","desc":"民谣歌手"}},{"id":"764","title":"没有颜色的爱人","cover":"http://image.wufazhuce.com/FhioBPB2QEfas9hYvjaaeLr1qQkJ","platform":"1","music_id":"1770266562","author":{"user_id":"5572010","user_name":"蒋明","web_url":"http://image.wufazhuce.com/FipVjkW7tu-H8yoxwPLXzf3uPrwT","desc":"音乐人"}},{"id":"712","title":"没有理想的人不伤心","cover":"http://image.wufazhuce.com/Fnfqtfb638AA7v7s4pJccAvasayJ","platform":"2","music_id":"http://music.wufazhuce.com/ltJm9h-758xqg3J-ttWNGHTSodR0","author":{"user_id":"6425695","user_name":"新裤子","web_url":"http://image.wufazhuce.com/FiArvfnYds--0Yt3rhPAckfALh1E","desc":"摇滚乐队"}},{"id":"675","title":"你有多久没有看过星星","cover":"http://image.wufazhuce.com/FhHIGt_WeScdb2sGnAnLbjZat_rw","platform":"2","music_id":"http://music.wufazhuce.com/lllNB91fbX4gIy-hqw7B8uL_1CHx","author":{"user_id":"4814948","user_name":"陈小熊","web_url":"http://image.wufazhuce.com/FoM_aSuUi5D54q_rvd7V40QDQQQB","desc":"民谣歌手"}},{"id":"378","title":"没有理想的人不伤心","cover":"http://image.wufazhuce.com/Fr0ip4J1xWVc6MECffIVm4pJkfYQ","platform":"1","music_id":"1772368369","author":{"user_id":"6425695","user_name":"新裤子","web_url":"http://image.wufazhuce.com/FiArvfnYds--0Yt3rhPAckfALh1E","desc":"摇滚乐队"}},{"id":"275","title":"没有你","cover":"http://image.wufazhuce.com/FiqBmI45V89rtl38X1CDMhrmYtkf","platform":"2","music_id":"http://music.wufazhuce.com/lsXngXmBRPd-KrW18pO0QX53rgg7","author":{"user_id":"6229063","user_name":"苏紫旭","web_url":"http://image.wufazhuce.com/FlVNN88MOag2zCwZVUJDAFIj78R_","desc":"音乐人，中国好歌曲学员"}},{"id":"33","title":"这一切没有想象的那么糟","cover":"http://image.wufazhuce.com/FrHJAzCNzBz1ZZ14xpvDcw25FcEj","platform":"2","music_id":"http://music.wufazhuce.com/lq3hHCY5P3aI46IdhHFe9ztfbNkz","author":{"user_id":"5524307","user_name":"万晓利","web_url":"http://image.wufazhuce.com/Fv9ACouQB_3jXGDpVV142n5lDHc-","desc":"民谣歌手"}},{"id":"215","title":"就像是一块没有记忆的石头","cover":"http://image.wufazhuce.com/FpsDz59FS0Ve-q8X5VOqZ0DH6A3e","platform":"1","music_id":"1774292433","author":{"user_id":"4814948","user_name":"陈小熊","web_url":"http://image.wufazhuce.com/FoM_aSuUi5D54q_rvd7V40QDQQQB","desc":"民谣歌手"}}]
     */

    private int res;
    /**
     * id : 1026
     * title : 没有寄的信
     * cover : http://image.wufazhuce.com/FtU4w1O3I-BVNDIXn68a4bPwm3A7
     * platform : 1
     * music_id : 150829
     * author : {"user_id":"6585698","user_name":"萧潇","web_url":"http://image.wufazhuce.com/FlyGcGM9HOvrwITB9Ft6_nr1DIFa","desc":"歌手，演员，主持人"}
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
        private String id;
        private String title;
        private String cover;
        private String platform;
        private String music_id;
        /**
         * user_id : 6585698
         * user_name : 萧潇
         * web_url : http://image.wufazhuce.com/FlyGcGM9HOvrwITB9Ft6_nr1DIFa
         * desc : 歌手，演员，主持人
         */

        private AuthorBean author;

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

        public String getPlatform() {
            return platform;
        }

        public void setPlatform(String platform) {
            this.platform = platform;
        }

        public String getMusic_id() {
            return music_id;
        }

        public void setMusic_id(String music_id) {
            this.music_id = music_id;
        }

        public AuthorBean getAuthor() {
            return author;
        }

        public void setAuthor(AuthorBean author) {
            this.author = author;
        }

        public static class AuthorBean {
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
}
