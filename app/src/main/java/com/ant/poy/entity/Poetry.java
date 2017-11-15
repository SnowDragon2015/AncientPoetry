package com.ant.poy.entity;

import java.util.List;

/**
 * Created by SnowDragon2015
 * <p>
 * 2017/9/13
 * <p>
 * Github ：https://github.com/SnowDragon2015
 */
public class Poetry {


    /*
    *
    * "sumCount": 72418,
  "sumPage": 7242,
  "currentPage": 1,
  "pageTitle": "古诗文大全_古诗文网",
  "keyStr": "不限",
  "gushiwens": [
    {
      "id": 49386,
      "nameStr": "水调歌头·明月几时有",
      "author": "苏轼",
      "chaodai": "宋代",
      "cont": "\u003cp\u003e\u003cspan style=\"font-family:SimSun;\"\u003e丙辰中秋，欢饮达旦，大醉，作此篇，兼怀子由。\u003c/span\u003e\u003c/p\u003e\r\n\u003cp\u003e明月几时有？把酒问青天。不知天上宫阙，今夕是何年。我欲乘风归去，又恐琼楼玉宇，高处不胜寒。起舞弄清影，何似在人间？(何似 一作：何时；又恐 一作：惟 / 唯恐)\u003cbr /\u003e转朱阁，低绮户，照无眠。不应有恨，何事长向别时圆？人有悲欢离合，月有阴晴圆缺，此事古难全。但愿人长久，千里共婵娟。(长向 一作：偏向)\u003c/p\u003e",
      "axing": 6618,
      "bxing": 2039,
      "cxing": 2391,
      "dxing": 3487,
      "exing": 32560,
      "type": null,
      "tag": "宋词三百首|宋词精选|初中古诗|高中古诗|豪放|中秋节|月亮|怀人|祝福",
      "langsongAuthor": "蒋伟伟",
      "langsongAuthorPY": "jiangweiwei",
      "yizhu": "9933003333ff",
      "yizhuAuthor": "佚名",
      "yizhuCankao": "陆林 编注．宋词．北京：北京师范大学出版社，1992年11月版：第59-60页",
      "yizhuYuanchuang": false,
      "yizhuIspass": true,
      "shangIspass": true
    },
    * */

    private int sumCount;

    private int sumPage;

    private int currentPage;

    private String pageTitle;

    private String keyStr;

    private List<GushiWens> gushiwens;

    public int getSumCount() {
        return sumCount;
    }

    public int getSumPage() {
        return sumPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public String getPageTitle() {
        return pageTitle;
    }

    public String getKeyStr() {
        return keyStr;
    }

    public List<GushiWens> getGushiwens() {
        return gushiwens;
    }

    public static class GushiWens{

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
    }
}
