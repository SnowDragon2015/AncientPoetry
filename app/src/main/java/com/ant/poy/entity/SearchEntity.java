package com.ant.poy.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by SnowDragon2015
 * <p>
 * 2017/10/19
 * <p>
 * Github ：https://github.com/SnowDragon2015
 */
public class SearchEntity implements Serializable {

    private static final long serialVersionUID = -6127800491088824723L;

    private List<ShiWen> shiwen;
    private List<MingJu> mingju;
    private List<GuJi> guwen;
    private List<ZuoZhe> author;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public List<ShiWen> getShiwen() {
        return shiwen;
    }

    public void setShiwen(List<ShiWen> shiwen) {
        this.shiwen = shiwen;
    }

    public List<MingJu> getMingju() {
        return mingju;
    }

    public void setMingju(List<MingJu> mingju) {
        this.mingju = mingju;
    }

    public List<GuJi> getGuwen() {
        return guwen;
    }

    public void setGuwen(List<GuJi> guwen) {
        this.guwen = guwen;
    }

    public List<ZuoZhe> getAuthor() {
        return author;
    }

    public void setAuthor(List<ZuoZhe> author) {
        this.author = author;
    }

    public static class ShiWen implements Serializable {
        private static final long serialVersionUID = -3084553363356438161L;
        /***
         * "shiwen":[
         [
         {
         "id":7919,
         "nameStr":"对雪献从兄虞城宰",
         "author":"李白",
         "chaodai":"唐代",
         "cont":" 昨夜梁园里，弟寒兄不知。庭前看玉树，肠断忆连枝。 "
         "tag":"",
         "yizhu":"",
         "yi":"",
         "zhu":"",
         "shang":"",
         "yishang":"",
         "zhushang":"",
         "yizhushang":""
         * */

        private int id;
        private String nameStr;
        private String author;
        private String chaodai;
        private String cont;

        private String tag;
        private String yi;
        private String zhu;
        private String shang;
        private String yizhu;
        private String zhushang;
        private String yizhushang;




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

        public static long getSerialVersionUID() {
            return serialVersionUID;
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

        public String getYizhu() {
            return yizhu;
        }

        public void setYizhu(String yizhu) {
            this.yizhu = yizhu;
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
    }

    public static class  MingJu implements Serializable{

        private static final long serialVersionUID = 5395261792076995408L;


        /**
         *
         * "mingju":[
         [
         {
         "id":393,
         "nameStr":"\u6591\u7af9\u679d\uff0c\u6591\u7af9\u679d\uff0c\u6cea\u75d5\u70b9\u70b9\u5bc4\u76f8\u601d\u3002",
         "shiID":71051,
         "author":"\u5218\u79b9\u9521"
         },
         * */

        private int id;
        private String nameStr;
        private  int shiID;
        private String author;

        public static long getSerialVersionUID() {
            return serialVersionUID;
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

        public int getShiID() {
            return shiID;
        }

        public void setShiID(int shiID) {
            this.shiID = shiID;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }
    }

    public static class GuJi implements Serializable{
        /**
         * "guwen":[
         [
         {
         "id":80,
         "nameStr":"\u7761\u864e\u5730\u79e6\u5893\u7af9\u7b80",
         "cont":"",
         * */
        private int id;
        private String nameStr;
        private String cont;

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
    }

    public static class ZuoZhe implements Serializable{
        /**
         * "author":[
         [
         {
         "cont"",
         "nameStr":"\u79e6\u7af9\u6751",
         "id":2869
         * */

        private int id;
        private String nameStr;
        private String cont;

        private String pic;

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
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

        public String getCont() {
            return cont;
        }

        public void setCont(String cont) {
            this.cont = cont;
        }
    }
}
