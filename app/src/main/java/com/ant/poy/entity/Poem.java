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
public class Poem implements Serializable{

    private static final long serialVersionUID = 3588869674255788754L;
    /**
     * "siwens":[
     * {
     * "id":8218,
     * "nameStr":"答王十二寒夜独酌有怀",
     * "author":"李白",
     * "chaodai":"唐代",
     * "cont":"昨夜吴中雪，子猷佳兴发。",
     * "axing":7,
     * "bxing":5,
     * "cxing":6,
     * "dxing":8,
     * "exing":64,
     * "type":null,
     * "tag":"叙事|抒情|长诗",
     * "langsongAuthor":"",
     * "langsongAuthorPY":"",
     * "yizhu":"",
     * "yizhuAuthor":"佚名",
     * "yizhuCankao":"张燕瑾 等．唐诗鉴赏辞典．上海：上海辞书出版社，1983：317-320&詹福瑞 等．李白诗全译．石家庄：河北人民出版社，1997：691-695",
     * "yizhuYuanchuang":false,
     * "yizhuIspass":true,
     * "shangIspass":true,
     * "yi":"",
     * "zhu":"",
     * "shang":",
     * "yishang":"",
     * "zhushang":"",
     * "yizhushang":"",
     * "authorId":247,
     * "addtime":"2017-09-12 15:18:43"
     */

    private List<ShiWen> shiwens;

    public List<ShiWen> getShiwens() {
        return shiwens;
    }

    public void setShiwens(List<ShiWen> shiwens) {
        this.shiwens = shiwens;
    }

    public static class ShiWen implements Serializable{
        private static final long serialVersionUID = -962865315692797541L;
        private int id;

        private String nameStr;

        private String author;

        private String chaodai;

        private String cont;

        private int axing;

        private int bxing;

        private int cxing;

        private int dxing;

        private int exing;

        private String type;

        private String tag;

        private String langsongAuthor;

        private String langsongAuthorPY;

        private String yizhu;

        private String yizhuAuthor;

        private String yizhuCankao;

        private boolean yizhuYuanchuang;

        private boolean yizhuIspass;

        private boolean shangIspass;

        private String yi;
        private String zhu;
        private String shang;
        private String yishang;
        private String zhushang;
        private String yizhushang;
        private String authorId;
        private String addtime;

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

        public String getLangsongAuthor() {
            return langsongAuthor;
        }

        public void setLangsongAuthor(String langsongAuthor) {
            this.langsongAuthor = langsongAuthor;
        }

        public String getLangsongAuthorPY() {
            return langsongAuthorPY;
        }

        public void setLangsongAuthorPY(String langsongAuthorPY) {
            this.langsongAuthorPY = langsongAuthorPY;
        }

        public String getYizhu() {
            return yizhu;
        }

        public void setYizhu(String yizhu) {
            this.yizhu = yizhu;
        }

        public String getYizhuAuthor() {
            return yizhuAuthor;
        }

        public void setYizhuAuthor(String yizhuAuthor) {
            this.yizhuAuthor = yizhuAuthor;
        }

        public String getYizhuCankao() {
            return yizhuCankao;
        }

        public void setYizhuCankao(String yizhuCankao) {
            this.yizhuCankao = yizhuCankao;
        }

        public boolean isYizhuYuanchuang() {
            return yizhuYuanchuang;
        }

        public void setYizhuYuanchuang(boolean yizhuYuanchuang) {
            this.yizhuYuanchuang = yizhuYuanchuang;
        }

        public boolean isYizhuIspass() {
            return yizhuIspass;
        }

        public void setYizhuIspass(boolean yizhuIspass) {
            this.yizhuIspass = yizhuIspass;
        }

        public boolean isShangIspass() {
            return shangIspass;
        }

        public void setShangIspass(boolean shangIspass) {
            this.shangIspass = shangIspass;
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
    }
}
