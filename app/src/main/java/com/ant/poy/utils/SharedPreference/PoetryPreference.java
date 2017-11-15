package com.ant.poy.utils.SharedPreference;


import com.ant.poy.utils.AppUtils;

/**
 * Created by SnowDragon on 2017/7/3.
 */
public class PoetryPreference extends AppSharedPreferences {

    public static final String NAME_PREFERENCE = "poetry_extra";
    public static PoetryPreference instance = null;

    public static final String FONT_SIZE = "font_size";

    public static final String FIRST_OPEN = "first_open";

    public static final String TAGS_KIND = "tags_kind";

    public static final String TAGS_DYNASTY = "tags_dynasty";

    public static final String TAGS_FORM = "tags_form";
    public static final String TAGS_THEME = "tags_theme";

    public static final String TAGS_THEME_CLASS = "tags_theme_class";

    public static final String TAGS_AUTHOR_DYNASTY = "tags_author_dynasty";

    public static final String TAGS_ANCIENT = "tag_ancient";


    public static final String SWITCH_MODE_KEY = "mode_key";


    public static PoetryPreference getInstence() {
        if (instance == null) {
            synchronized (PoetryPreference.class) {
                if (instance == null) {
                    instance = new PoetryPreference();
                }

            }
        }
        return instance;
    }

    private PoetryPreference() {
        super(AppUtils.getAppContext(), NAME_PREFERENCE);
    }

    public void putFirstOpenIsTrue() {
        putBoolean(FIRST_OPEN, true);

    }

    public boolean getFirstOpen() {
        return getBoolean(FIRST_OPEN, false);
    }

    /**
     * Tags
     */

    public void putTagsKind(String kind) {
        putString(TAGS_KIND, kind);
    }

    public String getTagsKind() {
        return getString(TAGS_KIND, "不限");
    }

    public void putTagsDynasty(String kind) {
        putString(TAGS_DYNASTY, kind);
    }

    public String getTagsDynasty() {
        return getString(TAGS_DYNASTY, "不限");
    }

    public void putTagsForm(String kind) {
        putString(TAGS_FORM, kind);
    }

    public String getTagsForm() {
        return getString(TAGS_FORM, "不限");
    }

    /**
     * 主题类型
     *
     * @param theme
     */
    public void putTagTheme(String theme) {
        putString(TAGS_THEME, theme);
    }

    public String getTagTheme() {
        return getString(TAGS_THEME, "不限");
    }

    public void putTagThemeClass(String theme) {
        putString(TAGS_THEME_CLASS, theme);
    }

    public String getTagThemeClass() {
        return getString(TAGS_THEME_CLASS, "不限");
    }

    /**
     * 作者朝代
     */
    public void putTagAuthorDynasty(String theme) {
        putString(TAGS_AUTHOR_DYNASTY, theme);
    }

    public String getTagAuthorDynasty() {
        return getString(TAGS_AUTHOR_DYNASTY, "不限");
    }


    /**
     * 古籍
     */
    public void putTagAncient(String tagName) {
        putString(TAGS_ANCIENT, tagName);
    }

    public String getTagAncient() {
        return getString(TAGS_ANCIENT, "经部");
    }


    /**
     * 字体大小
     */
    public void putFontSize(int font_size) {
        putInt(FONT_SIZE, font_size);
    }

    public int getFontSize() {
        return getInt(FONT_SIZE, 18);
    }

    /**
     * 日渐间夜间模式
     */
    public void putSwitchMode(boolean isDay) {
        putBoolean(SWITCH_MODE_KEY, isDay);
    }

    public boolean geSwidthMode() {
        return getBoolean(SWITCH_MODE_KEY,false);
    }


}
