package com.ant.poy.entity;

import java.util.List;

/**
 * Created by SnowDragon2015
 * <p>
 * 2017/9/20
 * <p>
 * Github ：https://github.com/SnowDragon2015
 */
public class AncientBook {
    /**
     * "sumCount":218,
     * "sumPage":22,
     * "currentPage":1,
     * "pageTitle":"古文典籍_古诗文网",
     * "keyStr":"不限",
     * "books":[
     * {
     * "id":2,
     * "nameStr":"论语",
     * "author":"佚名",
     * "chaodai":null,
     * "cont":"　　《论语》是儒家学派的经典著作之一，由孔子的弟子及其再传弟子编撰而成。它以语录体和对话文体为主，记录了孔子及其弟子言行，集中体现了孔子的政治主张、论理思想、道德观念及教育原则等。与《大学》《中庸》《孟子》《诗经》《尚书》《礼记》《易经》《春秋》并称“四书五经”。通行本《论语》共二十篇。 ",
     * "fenlei":null,
     * "axing":1505,
     * "bxing":315,
     * "cxing":394,
     * "dxing":660,
     * "exing":8233,
     * "ipStr":null,
     * "nameStrKey":null,
     * "pic":"lunyu",
     * "classStr":null,
     * "type":null,
     * "creTime":"\/Date(-62135596800000)\/"
     * }
     */

    private int sumCount;

    private int sumPage;

    private int currentPage;


    private String pageTitle;

    private String keyStr;

    public List<Book> books;

    public int getSumCount() {
        return sumCount;
    }

    public void setSumCount(int sumCount) {
        this.sumCount = sumCount;
    }

    public int getSumPage() {
        return sumPage;
    }

    public void setSumPage(int sumPage) {
        this.sumPage = sumPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public String getPageTitle() {
        return pageTitle;
    }

    public void setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
    }

    public String getKeyStr() {
        return keyStr;
    }

    public void setKeyStr(String keyStr) {
        this.keyStr = keyStr;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public static class Book {
        private int id;

        private String nameStr;

        private String author;

        private String chaodai;

        private String cont;

        private String fenlei;

        private int axing;

        private int bxing;

        private int cxing;

        private int dxing;

        private int exing;

        private String ipStr;

        private String nameStrKey;

        private String pic;

        private String classStr;

        private String type;

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

        public String getFenlei() {
            return fenlei;
        }

        public void setFenlei(String fenlei) {
            this.fenlei = fenlei;
        }

        public int getAxing() {
            return axing;
        }

        public void setAxing(int axing) {
            this.axing = axing;
        }

        public int getBxing() {
            return bxing;
        }

        public void setBxing(int bxing) {
            this.bxing = bxing;
        }

        public int getCxing() {
            return cxing;
        }

        public void setCxing(int cxing) {
            this.cxing = cxing;
        }

        public int getDxing() {
            return dxing;
        }

        public void setDxing(int dxing) {
            this.dxing = dxing;
        }

        public int getExing() {
            return exing;
        }

        public void setExing(int exing) {
            this.exing = exing;
        }

        public String getIpStr() {
            return ipStr;
        }

        public void setIpStr(String ipStr) {
            this.ipStr = ipStr;
        }

        public String getNameStrKey() {
            return nameStrKey;
        }

        public void setNameStrKey(String nameStrKey) {
            this.nameStrKey = nameStrKey;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getClassStr() {
            return classStr;
        }

        public void setClassStr(String classStr) {
            this.classStr = classStr;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getCreTime() {
            return creTime;
        }

        public void setCreTime(String creTime) {
            this.creTime = creTime;
        }
    }
}
