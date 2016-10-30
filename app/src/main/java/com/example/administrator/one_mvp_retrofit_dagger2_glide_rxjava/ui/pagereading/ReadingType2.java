package com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.pagereading;

/**
 * Created by shuwen on 2016/4/1.
 */
public class ReadingType2 {
    /**
     * time : 2016-03-31 21:00:00
     * type : 2
     * content : {"id":"90","serial_id":"28","number":"2","title":"绿色火焰\u2014\u2014火锅杀（2）","excerpt":"季墨第一次看到有女生就这样穿着日本电影里那种水手制服跑出来\u2014\u2014白色的短袖上衣、蓝底白条的方形披肩、蓝色短裙、黑色的学生袜（这种天能穿出来也真厉害），令人浮想联翩。","read_num":"23300","maketime":"2016-03-31 21:00:00","author":{"user_id":"4813510","user_name":"王若虚","web_url":"http://image.wufazhuce.com/FqHv13MMStvCgEz6AuGMZe6TfKCq","desc":"作家，「一个」常驻作者。"},"has_audio":false}
     */

    private String time;
    private String type;
    /**
     * id : 90
     * serial_id : 28
     * number : 2
     * title : 绿色火焰——火锅杀（2）
     * excerpt : 季墨第一次看到有女生就这样穿着日本电影里那种水手制服跑出来——白色的短袖上衣、蓝底白条的方形披肩、蓝色短裙、黑色的学生袜（这种天能穿出来也真厉害），令人浮想联翩。
     * read_num : 23300
     * maketime : 2016-03-31 21:00:00
     * author : {"user_id":"4813510","user_name":"王若虚","web_url":"http://image.wufazhuce.com/FqHv13MMStvCgEz6AuGMZe6TfKCq","desc":"作家，「一个」常驻作者。"}
     * has_audio : false
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
        private String id;
        private String serial_id;
        private String number;
        private String title;
        private String excerpt;
        private String read_num;
        private String maketime;
        /**
         * user_id : 4813510
         * user_name : 王若虚
         * web_url : http://image.wufazhuce.com/FqHv13MMStvCgEz6AuGMZe6TfKCq
         * desc : 作家，「一个」常驻作者。
         */

        private AuthorBean author;
        private boolean has_audio;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getSerial_id() {
            return serial_id;
        }

        public void setSerial_id(String serial_id) {
            this.serial_id = serial_id;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getExcerpt() {
            return excerpt;
        }

        public void setExcerpt(String excerpt) {
            this.excerpt = excerpt;
        }

        public String getRead_num() {
            return read_num;
        }

        public void setRead_num(String read_num) {
            this.read_num = read_num;
        }

        public String getMaketime() {
            return maketime;
        }

        public void setMaketime(String maketime) {
            this.maketime = maketime;
        }

        public AuthorBean getAuthor() {
            return author;
        }

        public void setAuthor(AuthorBean author) {
            this.author = author;
        }

        public boolean isHas_audio() {
            return has_audio;
        }

        public void setHas_audio(boolean has_audio) {
            this.has_audio = has_audio;
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
