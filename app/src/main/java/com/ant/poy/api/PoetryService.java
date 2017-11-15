package com.ant.poy.api;

import com.ant.poy.entity.AncientBook;
import com.ant.poy.entity.AncientBookDetail;
import com.ant.poy.entity.GuWen;
import com.ant.poy.entity.Poem;
import com.ant.poy.entity.Poet;
import com.ant.poy.entity.Poetry;
import com.ant.poy.entity.PoetryDetail;
import com.ant.poy.entity.Recommend;
import com.ant.poy.entity.SearchEntity;
import com.ant.poy.entity.Wisdom;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by SnowDragon2015
 * <p>
 * 2017/9/12
 * <p>
 * Github ：https://github.com/SnowDragon2015
 */
public interface PoetryService {

    /**
     * 推荐: Header 没有设置的可以为有网的时候不进行缓存
     */
    // @Headers("Cache-Control: public, max-age=3600")
//    @GET("api/upTimeTop11.aspx?n=3353448025&pwd=&id=0&token=gswapi")
//    Observable<Rcommd> getRecommend(@Query("page") int page);

    /**
     * 诗文 c=唐代&p=1&x=诗&t=冬天
     */
    @GET("api/shiwen/type.aspx?page=2&n=707662769&pwd=&id=0&token=gswapi")
    Observable<Poetry> getPoetry(@Query("p") int page, @Query("c") String chaodai, @Query("x") String xingshi, @Query("t") String leixing);


    /**
     * 名句
     * p 页数
     * t 类型
     */
    @GET("api/mingju/Default.aspx?page=1&n=4294237622&pwd=&id=0&token=gswapi&c=")
    Observable<Wisdom> getWisdom(@Query("p") int page, @Query("t") String leixing);

    /**
     * 作者
     * p 页码
     * c 朝代
     */
    // @GET("api/author/Default.aspx?page=1&n=1353099458&pwd=&id=0&token=gswapi")
    // Observable<AuthorT> getAuthor(@Query("p") int page, @Query("c") String chaodai);

    /**
     * 古籍
     */
    @GET("api/guwen/Default.aspx?n=3877527548&pwd=&id=0&token=gswapi&type=&p=1")
    Observable<AncientBook> getAncientBook(@Query("page") int page);





    /**
     * 自有服务器
     *
     * @param page
     * @param chaodai 朝代
     * @param xingshi 形式
     * @param leixing 类型
     * @return
     */
    @GET("getConInfo.php?type=shiwen")
    Observable<Poem> getPoem(@Query("p") int page, @Query("c") String chaodai, @Query("x") String xingshi, @Query("t") String leixing);

    /**
     * 名句
     *
     * @param page
     * @param theme   主题
     * @param leixing 类型
     * @return
     */
    @GET("getConInfo.php?type=mingju")
    Observable<Wisdom> getWisdom(@Query("p") int page, @Query("c") String theme, @Query("t") String leixing);

    /**
     * 作者
     *
     * @param page    翻页
     * @param dynasty 朝代
     * @return
     */
    @GET("getConInfo.php?type=author")
    Observable<Poet> getPoet(@Query("p") int page, @Query("c") String dynasty);

    @GET("getConInfo.php?type=author")
    Observable<Poet> getAuthorDetail(@Query("id") int id);

    /**
     * 古文
     *
     * @param page 翻页
     * @param t    类型
     * @return
     */
    @GET("getConInfo.php?type=guwen")
    Observable<GuWen> getGuWen(@Query("p") int page, @Query("t") String t);

    /**
     * 推荐*/
    @GET("getConInfo.php?type=tuijie")
    Observable<Recommend> getRecommend();

   // http://192.168.53.222/poetry/api/getConInfo.php?type=guwenInfo&id=75

    /**古籍详情
     * */
    @GET("getConInfo.php?type=guwenInfo")
    Observable<AncientBookDetail> getAncientDetail(@Query("id") int id);

    /** 古文详情的赏析 翻译*/
    @GET("getConInfo.php?type=guwenInfo")
    Observable<AncientBookDetail> getAncientDetailContent(@Query("id") int id,@Query("infoId") int infoId);

    /**
     * 诗文详情
     * */

    @GET("getConInfo.php?type=shiwen&p=1")
    Observable<PoetryDetail> getPoetryDetail(@Query("infoId") int infoId);

    /**
     * 搜索界面
     *
     * */

    @GET("getConInfo.php?type=search")
    Observable<SearchEntity> getSearchEntity(@Query("s") String searchName,@Query("t") String ajax);







}
