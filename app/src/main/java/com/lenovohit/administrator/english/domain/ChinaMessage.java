package com.lenovohit.administrator.english.domain;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Administrator on 2017-05-11.
 */

public class ChinaMessage {

/*

    {
        "translation": [
        "好"
    ],
        "basic": {
        "us-phonetic": "ɡʊd",
                "phonetic": "gʊd",
                "uk-phonetic": "gʊd",
                "explains": [
        "n. 好处；善行；慷慨的行为",
                "adj. 好的；优良的；愉快的；虔诚的",
                "adv. 好",
                "n. (Good)人名；(英)古德；(瑞典)戈德"
        ]
    },
        "query": "good",
            "errorCode": 0,
            "web": [
        {
            "value": [
            "好",
                    "善",
                    "商品"
            ],
            "key": "Good"
        },
        {
            "value": [
            "公共物品",
                    "公益事业",
                    "公共财"
            ],
            "key": "public good"
        },
        {
            "value": [
            "干的出色",
                    "干得好",
                    "好工作"
            ],
            "key": "Good Job"
        }
    ]
    }
*/

    /**
     * translation : ["好"]
     * basic : {"us-phonetic":"ɡʊd","phonetic":"gʊd","uk-phonetic":"gʊd","explains":["n. 好处；善行；慷慨的行为","adj. 好的；优良的；愉快的；虔诚的","adv. 好","n. (Good)人名；(英)古德；(瑞典)戈德"]}
     * query : good
     * errorCode : 0
     * web : [{"value":["好","善","商品"],"key":"Good"},{"value":["公共物品","公益事业","公共财"],"key":"public good"},{"value":["干的出色","干得好","好工作"],"key":"Good Job"}]
     */

    private BasicBean basic;
    private String query;
    private int errorCode;
    private List<String> translation;
    private List<WebBean> web;

    public BasicBean getBasic() {
        return basic;
    }

    public void setBasic(BasicBean basic) {
        this.basic = basic;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

        public List<String> getTranslation() {
            return translation;
        }

        public void setTranslation(List<String> translation) {
            this.translation = translation;
        }

    public List<WebBean> getWeb() {
        return web;
    }

    public void setWeb(List<WebBean> web) {
        this.web = web;
    }

    public  class BasicBean {
        /**
         * us-phonetic : ɡʊd
         * phonetic : gʊd
         * uk-phonetic : gʊd
         * explains : ["n. 好处；善行；慷慨的行为","adj. 好的；优良的；愉快的；虔诚的","adv. 好","n. (Good)人名；(英)古德；(瑞典)戈德"]
         */

        @SerializedName("us-phonetic")
        private String usphonetic;
        private String phonetic;
        @SerializedName("uk-phonetic")
        private String ukphonetic;
        private List<String> explains;

        public String getUsphonetic() {
            return usphonetic;
        }

        public void setUsphonetic(String usphonetic) {
            this.usphonetic = usphonetic;
        }

        public String getPhonetic() {
            return phonetic;
        }

        public void setPhonetic(String phonetic) {
            this.phonetic = phonetic;
        }

        public String getUkphonetic() {
            return ukphonetic;
        }

        public void setUkphonetic(String ukphonetic) {
            this.ukphonetic = ukphonetic;
        }

        public List<String> getExplains() {
            return explains;
        }

        public void setExplains(List<String> explains) {
            this.explains = explains;
        }
    }

    public  class WebBean {
        /**
         * value : ["好","善","商品"]
         * key : Good
         */

        private String key;
        private List<String> value;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public List<String> getValue() {
            return value;
        }

        public void setValue(List<String> value) {
            this.value = value;
        }
    }
}
