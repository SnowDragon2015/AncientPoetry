package com.ant.poy.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by SnowDragon2015
 * <p>
 * 2017/10/17
 * <p>
 * Github ：https://github.com/SnowDragon2015
 */
public class AncientBookDetail  implements Serializable{

    private static final long serialVersionUID = 2528609727182308223L;
    /***
     * "guwenInfo":[
     {
     "id":6160,
     "num":0,
     "nameStr":"修省",
     "author":"洪应明",
     "cont":"",
     "bookName":"菜根谭",
     "fenlei":"",
     "yiyi":false,
     "creTime":"/Date(-62135596800000)/",
     "bookID":0,
     "gwId":75,
     "fanyi":"",
     "shangxi":"",
     "updatetime":"2017-10-17 15:37:43"
     },
     * */

    private List<AncientInfo> guwenInfo;

    public List<AncientInfo> getGuwenInfo() {
        return guwenInfo;
    }

    public void setGuwenInfo(List<AncientInfo> guwenInfo) {
        this.guwenInfo = guwenInfo;
    }

    public static class AncientInfo implements Serializable{

        private static final long serialVersionUID = -166244882535302347L;

        private int id;

        private int num;

        private String nameStr;

        private String author;

        private String cont;

        private String bookName;

        private String fenlei;

        private boolean yiyi;

        private String creTime;

        private int bookID;

        private int gwId;

        private String fanyi;

        private String shangxi;

        private String updatetime;

        public static long getSerialVersionUID() {
            return serialVersionUID;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
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

        public String getCont() {
            return cont;
        }

        public void setCont(String cont) {
            this.cont = cont;
        }

        public String getBookName() {
            return bookName;
        }

        public void setBookName(String bookName) {
            this.bookName = bookName;
        }

        public String getFenlei() {
            return fenlei;
        }

        public void setFenlei(String fenlei) {
            this.fenlei = fenlei;
        }

        public boolean isYiyi() {
            return yiyi;
        }

        public void setYiyi(boolean yiyi) {
            this.yiyi = yiyi;
        }

        public String getCreTime() {
            return creTime;
        }

        public void setCreTime(String creTime) {
            this.creTime = creTime;
        }

        public int getBookID() {
            return bookID;
        }

        public void setBookID(int bookID) {
            this.bookID = bookID;
        }

        public int getGwId() {
            return gwId;
        }

        public void setGwId(int gwId) {
            this.gwId = gwId;
        }

        public String getFanyi() {
            return fanyi;
        }

        public void setFanyi(String fanyi) {
            this.fanyi = fanyi;
        }

        public String getShangxi() {
            return shangxi;
        }

        public void setShangxi(String shangxi) {
            this.shangxi = shangxi;
        }

        public String getUpdatetime() {
            return updatetime;
        }

        public void setUpdatetime(String updatetime) {
            this.updatetime = updatetime;
        }
    }
}
