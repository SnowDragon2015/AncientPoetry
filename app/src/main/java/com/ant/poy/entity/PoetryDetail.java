package com.ant.poy.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by SnowDragon2015
 * <p>
 * 2017/9/26
 * <p>
 * Github ：https://github.com/SnowDragon2015
 */
public class PoetryDetail implements Serializable {


    private static final long serialVersionUID = -7279697710435593311L;

    private List<Poetry> shiwens;

    public List<Poetry> getShiwens() {
        return shiwens;
    }

    public void setShiwens(List<Poetry> shiwens) {
        this.shiwens = shiwens;
    }

    public static class Poetry implements Serializable {

        /**
         * "id":7722,
         * "nameStr":"将进酒",
         * "author":"李白",
         * "chaodai":"唐代",
         * "cont":""，
         * "type":"诗",
         * "tag":"乐府|唐诗三百首|咏物|抒情|哲理|宴饮",
         * yi":"",
         * zhu":"",
         * shang":"",
         * yishang":"",
         * zhushang":"",
         * yizhushang":"",
         * "authorId":247,
         * "addtime":"2017-10-17 16:57:29",
         * "updatetime":"2017-10-19 09:22:49",
         */
        private int id;
        private String nameStr;
        private String author;
        private String chaodai;
        private String cont;
        private String type;
        private String tag;
        private String yi;
        private String zhu;
        private String shang;

        private String yishang;

        private String zhushang;

        private String yizhu;

        private String yizhushang;


        private String authorId;
        private String addtime;

        private String updatetime;

        private List<Info> info;

        private Author info_author;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getNameStr() {
            return nameStr;
        }

        public void setNameStr(String nameStr) {
            this.nameStr = nameStr;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getChaodai() {
            return chaodai;
        }

        public void setChaodai(String chaodai) {
            this.chaodai = chaodai;
        }

        public String getCont() {
            return cont;
        }

        public void setCont(String cont) {
            this.cont = cont;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public String getYi() {
            return yi;
        }

        public String getYizhu() {
            return yizhu;
        }

        public void setYizhu(String yizhu) {
            this.yizhu = yizhu;
        }

        public void setYi(String yi) {
            this.yi = yi;
        }

        public String getZhu() {
            return zhu;
        }

        public void setZhu(String zhu) {
            this.zhu = zhu;
        }

        public String getShang() {
            return shang;
        }

        public void setShang(String shang) {
            this.shang = shang;
        }

        public String getYishang() {
            return yishang;
        }

        public void setYishang(String yishang) {
            this.yishang = yishang;
        }

        public String getZhushang() {
            return zhushang;
        }

        public void setZhushang(String zhushang) {
            this.zhushang = zhushang;
        }

        public String getYizhushang() {
            return yizhushang;
        }

        public void setYizhushang(String yizhushang) {
            this.yizhushang = yizhushang;
        }

        public String getAuthorId() {
            return authorId;
        }

        public void setAuthorId(String authorId) {
            this.authorId = authorId;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public String getUpdatetime() {
            return updatetime;
        }

        public void setUpdatetime(String updatetime) {
            this.updatetime = updatetime;
        }

        public List<Info> getInfo() {
            return info;
        }

        public void setInfo(List<Info> info) {
            this.info = info;
        }

        public Author getInfo_author() {
            return info_author;
        }

        public void setInfo_author(Author info_author) {
            this.info_author = info_author;
        }

        public static class Info implements Serializable {
            /**
             * "id":967,
             * "nameStr":"译文及注释",
             * "author":"佚名",
             * "shiID":7722,
             * "ok":7370,
             * "noOk":1150,
             * "cont":"",
             * "cankao"",
             * "isYuanchuang":false
             */
            private int id;
            private String nameStr;
            private String author;
            private int shiID;
            private int ok;
            private int noOk;
            private String cont;
            private String cankao;
            private boolean isYuanchuang;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getNameStr() {
                return nameStr;
            }

            public void setNameStr(String nameStr) {
                this.nameStr = nameStr;
            }

            public String getAuthor() {
                return author;
            }

            public void setAuthor(String author) {
                this.author = author;
            }

            public int getShiID() {
                return shiID;
            }

            public void setShiID(int shiID) {
                this.shiID = shiID;
            }

            public int getOk() {
                return ok;
            }

            public void setOk(int ok) {
                this.ok = ok;
            }

            public int getNoOk() {
                return noOk;
            }

            public void setNoOk(int noOk) {
                this.noOk = noOk;
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

            public boolean isYuanchuang() {
                return isYuanchuang;
            }

            public void setYuanchuang(boolean yuanchuang) {
                isYuanchuang = yuanchuang;
            }
        }

        public static class Author implements Serializable {
            /**
             * "id":247,
             * "nameStr":"李白",
             * "cont":"";
             * "chaodai":"唐代",
             * "pic":"libai",
             * "likes":4877,
             * "ipStr":"123.57.117.76",
             * "creTime":"/Date(-62135596800000)/"
             */

            private int id;
            private String nameStr;
            private String cont;
            private String chaodai;
            private String pic;
            private int likes;
            private String ipStr;
            private String creTime;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getNameStr() {
                return nameStr;
            }

            public void setNameStr(String nameStr) {
                this.nameStr = nameStr;
            }

            public String getCont() {
                return cont;
            }

            public void setCont(String cont) {
                this.cont = cont;
            }

            public String getChaodai() {
                return chaodai;
            }

            public void setChaodai(String chaodai) {
                this.chaodai = chaodai;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public int getLikes() {
                return likes;
            }

            public void setLikes(int likes) {
                this.likes = likes;
            }

            public String getIpStr() {
                return ipStr;
            }

            public void setIpStr(String ipStr) {
                this.ipStr = ipStr;
            }

            public String getCreTime() {
                return creTime;
            }

            public void setCreTime(String creTime) {
                this.creTime = creTime;
            }
        }
    }
}
