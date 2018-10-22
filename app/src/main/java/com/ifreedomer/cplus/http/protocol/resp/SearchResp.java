package com.ifreedomer.cplus.http.protocol.resp;

import java.util.List;

public class SearchResp {


    /**
     * data : [{"question_count":26,"name":"resin"},{"question_count":3,"name":"simpleadapter"},{"question_count":1,"name":"musicfx"},{"question_count":4,"name":"siri"},{"question_count":2,"name":"parsing"},{"question_count":19,"name":"ssis"},{"question_count":1,"name":"targetsdkversion"},{"question_count":4,"name":"sip语音"},{"question_count":5,"name":"design"},{"question_count":59,"name":"silverlight"},{"question_count":8,"name":"simpledateformat"},{"question_count":47,"name":"powerdesigner"},{"question_count":6,"name":"httpsession"},{"question_count":856,"name":"session"},{"question_count":5,"name":"session失效"},{"question_count":1,"name":"waitforsingleobject"},{"question_count":1,"name":"opensessioninview"},{"question_count":4,"name":"fusionchart"},{"question_count":1,"name":"asifromdatarequest"},{"question_count":3,"name":"asihttprequest"}]
     * page_size : 20
     * errors :
     * count : 584
     * status : 1
     * page : 1
     */

    private String page_size;
    private String errors;
    private int count;
    private int status;
    private int page;
    private List<DataBean> data;

    public String getPage_size() {
        return page_size;
    }

    public void setPage_size(String page_size) {
        this.page_size = page_size;
    }

    public String getErrors() {
        return errors;
    }

    public void setErrors(String errors) {
        this.errors = errors;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * question_count : 26
         * name : resin
         */

        private int question_count;
        private String name;

        public int getQuestion_count() {
            return question_count;
        }

        public void setQuestion_count(int question_count) {
            this.question_count = question_count;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
