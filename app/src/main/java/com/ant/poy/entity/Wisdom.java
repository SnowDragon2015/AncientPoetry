package com.ant.poy.entity;

import java.util.List;

/**
 * Created by SnowDragon2015
 * <p>
 * 2017/10/10
 * <p>
 * Github ：https://github.com/SnowDragon2015
 */
public class Wisdom {

    /***
     * String parse JS eval
     * {
     * "mingju":[
     * {
     * "id":342,
     * "nameStr":"愿得一心人，白头不相离。",
     * "classStr":null,
     * "type":null,
     * "shiID":47866,
     * "exing":0,
     * "author":"卓文君",
     * "shiName":"白头吟",
     * "ipStr":null,
     * "t":"爱情",
     * "c":"抒情"
     * },
     */

    private List<MingJu> mingju;


    public List<MingJu> getMingju() {
        return mingju;
    }

    public void setMingju(List<MingJu> mingju) {
        this.mingju = mingju;
    }

    public static class MingJu {

        private int id;

        private String nameStr;

        private String classStr;

        private String type;

        private int shiID;

        private int exing;

        private String author;

        private String shiName;


        private String ipStr;

        private String t;

        private String c;


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

        public int getShiID() {
            return shiID;
        }

        public void setShiID(int shiID) {
            this.shiID = shiID;
        }

        public int getExing() {
            return exing;
        }

        public void setExing(int exing) {
            this.exing = exing;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getShiName() {
            return shiName;
        }

        public void setShiName(String shiName) {
            this.shiName = shiName;
        }

        public String getIpStr() {
            return ipStr;
        }

        public void setIpStr(String ipStr) {
            this.ipStr = ipStr;
        }

        public String getT() {
            return t;
        }

        public void setT(String t) {
            this.t = t;
        }

        public String getC() {
            return c;
        }

        public void setC(String c) {
            this.c = c;
        }
    }
}
