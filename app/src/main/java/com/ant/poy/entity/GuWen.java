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
public class GuWen implements Serializable{

    private static final long serialVersionUID = -5617989253431565215L;
    /**
     * "guwen":[
     * {
     * "author":"陈抟",
     * "axing":0,
     * "bxing":0,
     * "chaodai":null,
     * "classStr":null,
     * "cont":"　　《神相全编》是浩如烟海、灿若星汉的中国典籍文化中，集古代各时期著名相学家论述和著作大成的一书，几乎概括了中国相术的所有领域，成为自明、清以来最流行的相术技法大全。为宋代陈抟秘传，明代袁忠彻订正。 ",
     * "creTime":"\/Date(-62135596800000)\/",
     * "cxing":0,
     * "dxing":1,
     * "exing":7,
     * "fenlei":null,
     * "id":209,
     * "ipStr":null,
     * "nameStr":"神相全编",
     * "nameStrKey":null,
     * "pic":"shenxiangquanbian",
     * "type":"子部|术数类"
     * },
     */
    private List<Ancient> guwen;

    public List<Ancient> getGuwen() {
        return guwen;
    }

    public void setGuwen(List<Ancient> guwen) {
        this.guwen = guwen;
    }

   public static class Ancient implements Serializable{

        private static final long serialVersionUID = -2827838767012010013L;

        private String author;

        private int axing;

        private int bxing;

        private String chaodai;

        private String classStr;

        private String cont;

        private String creTime;

        private int cxing;

        private int dxing;

        private int exing;

        private String fenlei;

        private int id;

        private String nameStr;

        private String nameStrKey;

        private String pic;

        private String type;

        public static long getSerialVersionUID() {
            return serialVersionUID;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
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

        public String getChaodai() {
            return chaodai;
        }

        public void setChaodai(String chaodai) {
            this.chaodai = chaodai;
        }

        public String getClassStr() {
            return classStr;
        }

        public void setClassStr(String classStr) {
            this.classStr = classStr;
        }

        public String getCont() {
            return cont;
        }

        public void setCont(String cont) {
            this.cont = cont;
        }

        public String getCreTime() {
            return creTime;
        }

        public void setCreTime(String creTime) {
            this.creTime = creTime;
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

        public String getFenlei() {
            return fenlei;
        }

        public void setFenlei(String fenlei) {
            this.fenlei = fenlei;
        }

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

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
