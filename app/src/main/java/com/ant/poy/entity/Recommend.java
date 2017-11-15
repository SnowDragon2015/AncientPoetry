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
public class Recommend  implements Serializable{
    private static final long serialVersionUID = 8043630329886500057L;

    private List<TuiJian> tuijie;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public List<TuiJian> getTuijie() {
        return tuijie;
    }

    public void setTuijie(List<TuiJian> tuijie) {
        this.tuijie = tuijie;
    }

    public static class TuiJian{
        /***
         * "addtime":"2017-09-12 18:17:21",
         "author":"\u9f50\u5df1",
         "authorId":2,
         "axing":0,
         "bxing":0,
         "chaodai":"\u5510\u4ee3",
         "cont":"\n\u632a\u5434\u4e1d\uff0c\u96d5\u695a\u7af9\uff0c\u9ad8\u6258\u5929\u98ce\u62c2\u4e3a\u66f2\u3002\u4e00\u4e00\u5bab\u5546\u5728\u7d20\u7a7a\uff0c\n\u003cbr \/\u003e\n\u9e3e\u9e23\u51e4\u8bed\u7fd8\u68a7\u6850\u3002\u591c\u6df1\u5929\u78a7\u677e\u98ce\u591a\uff0c\u5b64\u7a97\u5bd2\u68a6\u60ca\u6d41\u6ce2\u3002\n\u003cbr \/\u003e\n\u6101\u9b42\u508d\u6795\u4e0d\u80af\u53bb\uff0c\u7ffb\u7591\u4f4f\u5904\u90bb\u6e58\u5a25\u3002\u91d1\u98ce\u58f0\u5c3d\u718f\u98ce\u53d1\uff0c\n\u003cbr \/\u003e\n\u51b7\u6cdb\u865a\u5802\u97f5\u96be\u6b47\u3002\u5e38\u6050\u542c\u591a\u8033\u6e10\u70e6\uff0c\u6e05\u97f3\u4e0d\u7edd\u77e5\u97f3\u7edd\u3002\n\n",
         "cxing":1,
         "dxing":1,
         "exing":1,
         "id":45296,
         "langsongAuthor":"",
         "langsongAuthorPY":"",
         "nameStr":"\u98ce\u7434\u5f15",
         "shang":"",
         "shangIspass":false,
         "tag":"",
         "type":"\u8bd7",
         "updatetime":"2017-09-27 16:15:43",
         "yi":"",
         "yishang":"",
         "yizhu":"",
         "yizhuAuthor":"",
         "yizhuCankao":"",
         "yizhuIspass":false,
         "yizhuYuanchuang":false,
         "yizhushang":"",
         "zhu":"",
         "zhushang":""
         * */
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
