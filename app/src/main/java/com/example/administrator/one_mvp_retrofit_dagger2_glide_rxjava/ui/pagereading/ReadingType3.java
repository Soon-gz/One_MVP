package com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.pagereading;

/**
 * Created by shuwen on 2016/4/1.
 */
public class ReadingType3 {
    /**
     * time : 2016-03-31 21:00:00
     * type : 3
     * content : {"question_id":"1303","question_title":"如何面对童年阴影？","answer_title":"@医生马虫 答逊逊：","answer_content":"想起一位患者，年轻女性，不到三十岁。泌尿外科门诊是一个经常需要患者暴露隐私的地方。有些让你听了忍俊不","question_makettime":"2016-03-31 21:00:00"}
     */

    private String time;
    private String type;
    /**
     * question_id : 1303
     * question_title : 如何面对童年阴影？
     * answer_title : @医生马虫 答逊逊：
     * answer_content : 想起一位患者，年轻女性，不到三十岁。泌尿外科门诊是一个经常需要患者暴露隐私的地方。有些让你听了忍俊不
     * question_makettime : 2016-03-31 21:00:00
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
        private String question_id;
        private String question_title;
        private String answer_title;
        private String answer_content;
        private String question_makettime;

        public String getQuestion_id() {
            return question_id;
        }

        public void setQuestion_id(String question_id) {
            this.question_id = question_id;
        }

        public String getQuestion_title() {
            return question_title;
        }

        public void setQuestion_title(String question_title) {
            this.question_title = question_title;
        }

        public String getAnswer_title() {
            return answer_title;
        }

        public void setAnswer_title(String answer_title) {
            this.answer_title = answer_title;
        }

        public String getAnswer_content() {
            return answer_content;
        }

        public void setAnswer_content(String answer_content) {
            this.answer_content = answer_content;
        }

        public String getQuestion_makettime() {
            return question_makettime;
        }

        public void setQuestion_makettime(String question_makettime) {
            this.question_makettime = question_makettime;
        }
    }
}
