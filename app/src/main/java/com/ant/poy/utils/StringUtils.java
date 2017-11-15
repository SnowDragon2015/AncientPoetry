package com.ant.poy.utils;

import android.text.TextUtils;

import com.ant.poy.utils.SharedPreference.PoetryPreference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by SnowDragon2015
 * <p>
 * 2017/9/14
 * <p>
 * Github ：https://github.com/SnowDragon2015
 */
public class StringUtils {

    public static List<String> getKindList() {
        List<String> list = new ArrayList<String>();

        list.add("不限");
        list.add("写景");
        list.add("咏物");
        list.add("春天");
        list.add("夏天");
//        list.add("秋天");
//        list.add("冬天");
//        list.add("写雨");
//        list.add("写雪");
//        list.add("写风");
//        list.add("写花");
//


        return list;
    }

    public static List<String> getAllKindList() {
        List<String> list = new ArrayList<String>();

        list.add("不限");
        list.add("写景");
        list.add("咏物");
        list.add("春天");
        list.add("夏天");
        list.add("秋天");
        list.add("冬天");
        list.add("写雨");
        list.add("写雪");
        list.add("写风");
        list.add("写花");

        list.add("梅花");
        list.add("荷花");
        list.add("菊花");
        list.add("柳树");
        list.add("月亮");
        list.add("山水");
        list.add("写山");
        list.add("写水");
        list.add("长江");
        list.add("黄河");
        list.add("儿童");

        list.add("写鸟");
        list.add("写马");
        list.add("田园");
        list.add("边塞");
        list.add("地名");
        list.add("抒情");
        list.add("爱国");
        list.add("别离");
        list.add("送别");
        list.add("思乡");
        list.add("思念");

        list.add("爱情");
        list.add("励志");
        list.add("哲理");
        list.add("闺怨");
        list.add("悼亡");
        list.add("写人");
        list.add("老师");
        list.add("母亲");
        list.add("友情");
        list.add("战争");
        list.add("读书");

        list.add("惜时");
        list.add("婉约");
        list.add("豪放");
        list.add("诗经");
        list.add("民谣");
        list.add("节日");
        list.add("春节");
        list.add("元宵节");
        list.add("寒食节");
        list.add("清明节");

        list.add("端午节");
        list.add("七夕节");
        list.add("中秋节");
        list.add("重阳节");
        list.add("忧国忧民");
        list.add("咏史怀古");
        list.add("宋词精选");
        list.add("小学古诗");

        list.add("初中古诗");
        list.add("高中古诗");
        list.add("小学文言文");
        list.add("初中文言文");
        list.add("高中文言文");
        list.add("古诗十九首");

        list.add("唐诗三百首");
        list.add("古诗三百首");
        list.add("宋词三百首");
        list.add("古文观止");

        return list;
    }

    public static List<String> getDynatsyList() {
        List<String> list = new ArrayList<String>();
        list.add("不限");
        list.add("先秦");
        list.add("两汉");
        list.add("魏晋");
        list.add("南北朝");

        return list;
    }

    public static List<String> getAllDynatsyList() {
        List<String> list = new ArrayList<String>();
        list.add("不限");
        list.add("先秦");
        list.add("两汉");
        list.add("魏晋");
        list.add("南北朝");
        list.add("隋代");
        list.add("唐代");
        list.add("五代");
        list.add("宋代");
        list.add("金朝");
        list.add("元代");
        list.add("明代");
        list.add("清代");

        return list;
    }

    public static List<String> getFormList() {
        List<String> list = new ArrayList<String>();

        list.add("不限");
        list.add("诗");
        list.add("词");
        list.add("曲");
        list.add("文言文");

        return list;
    }

    public static List<String> getThemeList() {
        List<String> list = new ArrayList<String>();
        list.add("不限");
        list.add("抒情");
        list.add("四季");
        list.add("山水");
        list.add("天气");
        return list;
    }

    ;

    public static List<String> getThemeAllList() {
        List<String> list = new ArrayList<String>();
        list.add("不限");
        list.add("抒情");
        list.add("四季");
        list.add("山水");
        list.add("天气");
        list.add("人物");
        list.add("人生");
        list.add("生活");
        list.add("节日");
        list.add("动物");
        list.add("植物");
        list.add("食物");
        return list;
    }

    public static HashMap<String, List<String>> getThemeClassList() {
        HashMap<String, List<String>> hashMap = new HashMap<String, List<String>>();
        hashMap.put("抒情", getEmotionList());
        hashMap.put("四季", getSeasonsList());
        hashMap.put("山水", getLandspaceList());
        hashMap.put("天气", getWeatherList());
        hashMap.put("人物", getPersonList());
        hashMap.put("人生", getLifeList());
        hashMap.put("生活", getLiveList());
        hashMap.put("节日", getFestivalList());
        hashMap.put("动物", getAnimalsList());
        hashMap.put("植物", getPlantList());
        hashMap.put("食物", getFoodList());
        return hashMap;
    }

    public static HashMap<String, List<String>> getThemeAllClassList() {
        HashMap<String, List<String>> hashMap = new HashMap<String, List<String>>();
        hashMap.put("抒情", getEmotionAllList());
        hashMap.put("山水", getLandspaceAllList());
        hashMap.put("天气", getWeatherAllList());
        hashMap.put("人物", getPersonAllList());
        hashMap.put("人生", getLifeAllList());
        hashMap.put("节日", getFestivalAllList());
        hashMap.put("植物", getPlantAllList());
        return hashMap;
    }


    /***
     * 抒情
     */
    public static List<String> getEmotionList() {
        List<String> list = new ArrayList<String>();
        list.add("不限");
        list.add("爱情");
        list.add("友情");
        list.add("离别");
        list.add("思念");
        return list;
    }

    public static List<String> getEmotionAllList() {
        List<String> list = new ArrayList<String>();
        list.add("不限");
        list.add("爱情");
        list.add("友情");
        list.add("离别");
        list.add("思念");
        list.add("思乡");
        list.add("伤感");
        list.add("孤独");
        list.add("闺怨");
        list.add("悼亡");
        list.add("怀古");
        list.add("爱国");
        list.add("感恩");
        return list;
    }

    /**
     * 四季
     */
    public static List<String> getSeasonsList() {
        List<String> list = new ArrayList<String>();
        list.add("不限");
        list.add("春天");
        list.add("夏天");
        list.add("秋天");
        list.add("冬天");
        return list;
    }

    /**
     * 山水
     */
    public static List<String> getLandspaceList() {
        List<String> list = new ArrayList<String>();
        list.add("不限");
        list.add("庐山");
        list.add("泰山");
        list.add("江河");
        list.add("长江");
        return list;
    }

    public static List<String> getLandspaceAllList() {
        List<String> list = new ArrayList<String>();
        list.add("不限");
        list.add("庐山");
        list.add("泰山");
        list.add("江河");
        list.add("长江");
        list.add("西湖");
        list.add("瀑布");
        return list;
    }

    /**
     * 天气
     */
    public static List<String> getWeatherList() {
        List<String> list = new ArrayList<String>();
        list.add("不限");
        list.add("写风");
        list.add("写云");
        list.add("写雨");
        list.add("写雪");
        return list;
    }

    public static List<String> getWeatherAllList() {
        List<String> list = new ArrayList<String>();
        list.add("不限");
        list.add("写风");
        list.add("写云");
        list.add("写雨");
        list.add("写雪");
        list.add("彩虹");
        list.add("太阳");
        list.add("月亮");
        list.add("星星");
        return list;
    }

    /**
     * 人物
     */
    public static List<String> getPersonList() {
        List<String> list = new ArrayList<String>();
        list.add("不限");
        list.add("女子");
        list.add("父亲");
        list.add("母亲");
        list.add("老师");
        return list;
    }

    public static List<String> getPersonAllList() {
        List<String> list = new ArrayList<String>();
        list.add("不限");
        list.add("女子");
        list.add("父亲");
        list.add("母亲");
        list.add("老师");
        list.add("儿童");
        return list;
    }

    /**
     * 人生
     */
    public static List<String> getLifeList() {
        List<String> list = new ArrayList<String>();
        list.add("不限");
        list.add("励志");
        list.add("哲理");
        list.add("青春");
        list.add("时光");
        return list;
    }

    public static List<String> getLifeAllList() {
        List<String> list = new ArrayList<String>();
        list.add("不限");
        list.add("励志");
        list.add("哲理");
        list.add("青春");
        list.add("时光");
        list.add("梦想");
        list.add("读书");
        list.add("战争");
        return list;
    }

    /**
     * 生活
     */
    public static List<String> getLiveList() {
        List<String> list = new ArrayList<String>();
        list.add("不限");
        list.add("乡村");
        list.add("田园");
        list.add("边塞");
        list.add("写桥");
        return list;
    }

    /**
     * 节日
     */
    public static List<String> getFestivalList() {
        List<String> list = new ArrayList<String>();
        list.add("不限");
        list.add("春节");
        list.add("元宵节");
        list.add("寒食节");
        return list;
    }

    public static List<String> getFestivalAllList() {
        List<String> list = new ArrayList<String>();
        list.add("不限");
        list.add("春节");
        list.add("元宵节");
        list.add("寒食节");
        list.add("清明节");
        list.add("端午节");
        list.add("七夕节");
        list.add("中秋节");
        list.add("重阳节");
        return list;
    }

    /**
     * 动物
     */
    public static List<String> getAnimalsList() {
        List<String> list = new ArrayList<String>();
        list.add("不限");
        list.add("写鸟");
        list.add("写马");
        list.add("写猫");
        return list;
    }

    /**
     * 植物
     */
    public static List<String> getPlantList() {
        List<String> list = new ArrayList<String>();
        list.add("不限");
        list.add("梅花");
        list.add("梨花");
        list.add("桃花");
        list.add("荷花");

        return list;
    }

    public static List<String> getPlantAllList() {
        List<String> list = new ArrayList<String>();
        list.add("不限");
        list.add("梅花");
        list.add("梨花");
        list.add("桃花");
        list.add("荷花");
        list.add("菊花");
        list.add("柳树");
        list.add("叶子");
        list.add("竹子");
        return list;
    }

    /**
     * 食物
     */
    public static List<String> getFoodList() {
        List<String> list = new ArrayList<String>();
        list.add("不限");
        list.add("写酒");
        list.add("写茶");
        list.add("荔枝");
        return list;
    }

    /**
     * 古籍 经部
     */

    public static List<String> getJIngBuList() {
        List<String> list = new ArrayList<String>();
        list.add("经部");
        list.add("易类");
        list.add("书类");
        list.add("礼类");
        list.add("春秋类");
        return list;
    }

    public static List<String> getJIngBuAllList() {
        List<String> list = new ArrayList<String>();
        list.add("经部");
        list.add("易类");
        list.add("书类");
        list.add("礼类");
        list.add("春秋类");
        list.add("孝经类");
        list.add("五经总义类");
        list.add("四书类");
        list.add("乐类");
        list.add("小学类");
        return list;
    }

    /**
     * 史部
     */
    public static List<String> getShiBuList() {
        List<String> list = new ArrayList<String>();
        list.add("史部");
        list.add("正史类");
        list.add("编年类");
        list.add("传记类");
        return list;
    }

    public static List<String> getShiBuAllList() {
        List<String> list = new ArrayList<String>();
        list.add("史部");
        list.add("正史类");
        list.add("编年类");
        list.add("传记类");

        list.add("杂史类");
        list.add("别史类");
        list.add("史钞类");
        list.add("载记类");

        list.add("时令类");
        list.add("地理类");
        list.add("职官类");
        list.add("诏令奏议类");

        list.add("政书类");
        list.add("目录类");
        list.add("史评类");
        list.add("纪事本末类");

        return list;
    }

    /**
     * 子部
     */
    public static List<String> getZiBuList() {
        List<String> list = new ArrayList<String>();
        list.add("子部");
        list.add("儒家类");
        list.add("兵家类");
        list.add("法家类");
        return list;
    }

    public static List<String> getZiBuAllList() {
        List<String> list = new ArrayList<String>();
        list.add("子部");
        list.add("儒家类");
        list.add("兵家类");
        list.add("法家类");

        list.add("道家类");
        list.add("农家类");
        list.add("医家类");
        list.add("杂家类");

        list.add("艺术类");
        list.add("谱录类");
        list.add("类书类");
        list.add("小说家类");

        list.add("释家类");
        list.add("术数类");
        list.add("天文算法类");
        return list;
    }

    /**
     * 集部
     */

    public static List<String> getJiBuList() {
        List<String> list = new ArrayList<String>();
        list.add("集部");
        list.add("楚辞类");
        list.add("词曲类");
        list.add("诗文评类");
        return list;
    }

    public static List<String> getJiBuAllList() {
        List<String> list = new ArrayList<String>();
        list.add("集部");
        list.add("楚辞类");
        list.add("词曲类");
        list.add("诗文评类");
        list.add("别集类");
        list.add("总集类");
        return list;
    }


    public static String getPoetryTagsTerm() {

        String tags = "";
        String kind = PoetryPreference.getInstence().getTagsKind();
        String dynasty = PoetryPreference.getInstence().getTagsDynasty();
        String form = PoetryPreference.getInstence().getTagsForm();

        if (!"不限".equals(dynasty))
            tags += dynasty + " ";
        if (!"不限".equals(kind))
            tags += kind + " ";
        if (!"不限".equals(form))
            tags += form + " ";

        if (TextUtils.isEmpty(tags.trim())) return "不限";

        return tags;
    }

    public static String getWisdomTagsTerm() {

        String theme = PoetryPreference.getInstence().getTagTheme();
        String themeClass = PoetryPreference.getInstence().getTagThemeClass();

        if (!themeClass.equals("不限")) {
            return themeClass;
        } else return theme;


    }

    public static String getAncientBookTagsTerm() {


        return PoetryPreference.getInstence().getTagAncient();
    }


}
