package com.ifreedomer.cplus.http.protocol.resp;

import java.util.List;

public class CollectNumResp {

    /**
     * data : [{"num":"1","domain":"blog.csdn.net"}]
     * success : 1
     */

    private int success;
    private List<DataBean> data;

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * num : 1
         * domain : blog.csdn.net
         */

        private String num;
        private String domain;

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public String getDomain() {
            return domain;
        }

        public void setDomain(String domain) {
            this.domain = domain;
        }
    }
}
