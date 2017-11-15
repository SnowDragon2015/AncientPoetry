package com.ant.poy.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by SnowDragon2015
 * <p>
 * 2017/10/10
 * <p>
 * Github ：https://github.com/SnowDragon2015
 */
public class Poet implements Serializable {
    private static final long serialVersionUID = -707871057832450158L;
    /**
     * "author":[
     * {
     * "cont":"　　苏轼（1037－1101），北宋文学家、书画家、美食家。字子瞻，号东坡居士。汉族，四川人，葬于颍昌（今河南省平顶山市郏县）。一生仕途坎坷，学识渊博，天资极高，诗文书画皆精。其文汪洋恣肆，明白畅达，与欧阳修并称欧苏，为“唐宋八大家”之一；诗清新豪健，善用夸张、比喻，艺术表现独具风格，与黄庭坚并称苏黄；词开豪放一派，对后世有巨大影响，与辛弃疾并称苏辛；书法擅长行书、楷书，能自创新意，用笔丰腴跌宕，有天真烂漫之趣，与黄庭坚、米芾、蔡襄并称宋四家；画学文同，论画主张神似，提倡“士人画”。著有《苏东坡全集》和《东坡乐府》等。",
     * "pic":"sushi",
     * "ziliaos":[
     * {}],
     * "ipStr":null,
     * "likes":2815,
     * "creTime":"\/Date(-62135596800000)\/",
     * "nameStr":"苏轼",
     * "chaodai":"宋代",
     * "masterTitle":"作者资料列表",
     * "id":183
     * },
     */
    private List<Author> author;

    public List<Author> getAuthor() {
        return author;
    }

    public void setAuthor(List<Author> author) {
        this.author = author;
    }

    public static class Author implements Serializable {

        private static final long serialVersionUID = -2678012301745377426L;

        private String cont;

        private String pic;

        private List<Introduce> ziliaos;

        private String ipStr;

        private int likes;

        private String creTime;

        private String nameStr;

        private String chaodai;

        private String masterTitle;

        private int id;

        public String getCont() {
            return cont;
        }

        public void setCont(String cont) {
            this.cont = cont;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public List<Introduce> getZiliaos() {
            return ziliaos;
        }

        public void setZiliaos(List<Introduce> ziliaos) {
            this.ziliaos = ziliaos;
        }

        public String getIpStr() {
            return ipStr;
        }

        public void setIpStr(String ipStr) {
            this.ipStr = ipStr;
        }

        public int getLikes() {
            return likes;
        }

        public void setLikes(int likes) {
            this.likes = likes;
        }

        public String getCreTime() {
            return creTime;
        }

        public void setCreTime(String creTime) {
            this.creTime = creTime;
        }

        public String getNameStr() {
            return nameStr;
        }

        public void setNameStr(String nameStr) {
            this.nameStr = nameStr;
        }

        public String getChaodai() {
            return chaodai;
        }

        public void setChaodai(String chaodai) {
            this.chaodai = chaodai;
        }

        public String getMasterTitle() {
            return masterTitle;
        }

        public void setMasterTitle(String masterTitle) {
            this.masterTitle = masterTitle;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public static class Introduce implements Serializable {
            private static final long serialVersionUID = -5732385008896562724L;
            /**
             * "isYuanchuang":false,
             * "ok":2530,
             * "author":"佚名",
             * "cont":"\u003cp\u003e　　（一） 苏轼是个大才子，佛印是个高僧，两人经常一起参禅、打坐。佛印老实",
             * "cankao":"",
             * "noOk":409,
             * "ipStr":null,
             * "authorID":183,
             * "creTime":"\/Date(-62135596800000)\/",
             * "nameStr":"轶闻：佛印",
             * "isPass":false,
             * "id":367
             */
            private boolean isYuanchuang;

            private int ok;

            private String author;

            private String cont;

            private String cankao;

            private int noOk;

            private String ipStr;

            private int authorID;

            private String creTime;

            private String nameStr;

            private boolean isPass;

            private int id;

            public boolean isYuanchuang() {
                return isYuanchuang;
            }

            public void setYuanchuang(boolean yuanchuang) {
                isYuanchuang = yuanchuang;
            }

            public int getOk() {
                return ok;
            }

            public void setOk(int ok) {
                this.ok = ok;
            }

            public String getAuthor() {
                return author;
            }

            public void setAuthor(String author) {
                this.author = author;
            }

            public String getCont() {
                return cont;
            }

            public void setCont(String cont) {
                this.cont = cont;
            }

            public String getCankao() {
                return cankao;
            }

            public void setCankao(String cankao) {
                this.cankao = cankao;
            }

            public int getNoOk() {
                return noOk;
            }

            public void setNoOk(int noOk) {
                this.noOk = noOk;
            }

            public String getIpStr() {
                return ipStr;
            }

            public void setIpStr(String ipStr) {
                this.ipStr = ipStr;
            }

            public int getAuthorID() {
                return authorID;
            }

            public void setAuthorID(int authorID) {
                this.authorID = authorID;
            }

            public String getCreTime() {
                return creTime;
            }

            public void setCreTime(String creTime) {
                this.creTime = creTime;
            }

            public String getNameStr() {
                return nameStr;
            }

            public void setNameStr(String nameStr) {
                this.nameStr = nameStr;
            }

            public boolean isPass() {
                return isPass;
            }

            public void setPass(boolean pass) {
                isPass = pass;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }
        }

    }
}
