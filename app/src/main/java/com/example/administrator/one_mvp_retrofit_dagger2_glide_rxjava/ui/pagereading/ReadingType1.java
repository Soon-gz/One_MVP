package com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.pagereading;

import java.util.List;

/**
 * Created by shuwen on 2016/4/1.
 */
public class ReadingType1 {
    /**
     * time : 2016-03-31 21:00:00
     * type : 1
     * content : {"content_id":"1362","hp_title":"如果西游记是一个爱情故事","hp_makettime":"2016-03-31 21:00:00","guide_word":"猴子爱过三个姑娘。 三个姑娘，就是猴子漫长的一生。","author":[{"user_id":"4814725","user_name":"宋小君","web_url":"http://image.wufazhuce.com/FppWA8rblE8AOyK9Z5Rv2PRBuwnA","desc":"宋小君，作家、编剧。","wb_name":"@宋小君同学"}],"has_audio":true}
     */

    private String time;
    private String type;
    /**
     * content_id : 1362
     * hp_title : 如果西游记是一个爱情故事
     * hp_makettime : 2016-03-31 21:00:00
     * guide_word : 猴子爱过三个姑娘。 三个姑娘，就是猴子漫长的一生。
     * author : [{"user_id":"4814725","user_name":"宋小君","web_url":"http://image.wufazhuce.com/FppWA8rblE8AOyK9Z5Rv2PRBuwnA","desc":"宋小君，作家、编剧。","wb_name":"@宋小君同学"}]
     * has_audio : true
     */

    private ContentBean content;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ContentBean getContent() {
        return content;
    }

    public void setContent(ContentBean content) {
        this.content = content;
    }

    public static class ContentBean {
        private String content_id;
        private String hp_title;
        private String hp_makettime;
        private String guide_word;
        private boolean has_audio;
        /**
         * user_id : 4814725
         * user_name : 宋小君
         * web_url : http://image.wufazhuce.com/FppWA8rblE8AOyK9Z5Rv2PRBuwnA
         * desc : 宋小君，作家、编剧。
         * wb_name : @宋小君同学
         */

        private List<AuthorBean> author;

        public String getContent_id() {
            return content_id;
        }

        public void setContent_id(String content_id) {
            this.content_id = content_id;
        }

        public String getHp_title() {
            return hp_title;
        }

        public void setHp_title(String hp_title) {
            this.hp_title = hp_title;
        }

        public String getHp_makettime() {
            return hp_makettime;
        }

        public void setHp_makettime(String hp_makettime) {
            this.hp_makettime = hp_makettime;
        }

        public String getGuide_word() {
            return guide_word;
        }

        public void setGuide_word(String guide_word) {
            this.guide_word = guide_word;
        }

        public boolean isHas_audio() {
            return has_audio;
        }

        public void setHas_audio(boolean has_audio) {
            this.has_audio = has_audio;
        }

        public List<AuthorBean> getAuthor() {
            return author;
        }

        public void setAuthor(List<AuthorBean> author) {
            this.author = author;
        }

        public static class AuthorBean {
            private String user_id;
            private String user_name;
            private String web_url;
            private String desc;
            private String wb_name;

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

            public String getWb_name() {
                return wb_name;
            }

            public void setWb_name(String wb_name) {
                this.wb_name = wb_name;
            }
        }
    }
}
