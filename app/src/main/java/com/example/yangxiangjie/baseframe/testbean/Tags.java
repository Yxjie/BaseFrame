package com.example.yangxiangjie.baseframe.testbean;

import java.util.List;

/**
 * Created by yangxiangjie on 2017/12/4.
 */

public class Tags {

    private List<Tag> list;

    public List<Tag> getList() {
        return list;
    }

    public void setList(List<Tag> list) {
        this.list = list;
    }

    public static class Tag {

        private String id;

        private String text;

        private String img_url;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getImg_url() {
            return img_url;
        }

        public void setImg_url(String img_url) {
            this.img_url = img_url;
        }

    }


}
