package com.ant.poy.api;

import android.os.Environment;
import android.util.Log;

import com.ant.poy.utils.AppUtils;
import com.ant.poy.utils.CommonUtil;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by hcc on 16/8/4 21:18
 * 100332338@qq.com
 * <p>
 * Retrofit帮助类
 */
public class RetrofitHelper {

    private static final String TAG = "OkHttp-RetrofitHelper";
    private static final long CONNECT_TIMEOUT = 8;//链接超时时间：秒
    private static final long WRITE_TIMEOUT = 20;//写时间：秒
    private static final long READ_TIMEOUT = 20;//读取时间：秒

    private static final long HAVE_NETWORK_CACHE_TIME = 60*60;//有网络是缓存时间
    private static final long HAVE_NO_NETWORK_CACHE_TIME = 60 * 60 * 24;//没有网路时缓存时间

    private static OkHttpClient mOkHttpClient;

    static {
        initOkHttpClient();
    }


    public static PoetryService getPoetryGuAPI() {

        return createApi(PoetryService.class, ApiConstants.BASE_GUSHIWEN_URL);
    }


    public static PoetryService getPoetryApi() {
        return createApi(PoetryService.class, ApiConstants.BASE_URL);
    }



    /**
     * 根据传入的baseUrl，和api创建retrofit
     *
     * @param clazz
     * @param baseUrl
     * @param <T>
     * @return
     */
    private static <T> T createApi(Class<T> clazz, String baseUrl) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(mOkHttpClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(clazz);
    }


    /**
     * 初始化OKHttpClient,设置缓存,设置超时时间,设置打印日志,设置UA拦截器
     */
    private static void initOkHttpClient() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        if (mOkHttpClient == null) {
            synchronized (RetrofitHelper.class) {
                if (mOkHttpClient == null) {
                    //设置Http缓存
                    Log.i("OkHttp", new File(Environment.getExternalStorageDirectory(), "HttpCache").getPath() + " <<<<-----");

                    Cache cache = new Cache(new File(AppUtils.getAppContext().getCacheDir(), "HttpCache"), 1024 * 1024 * 50);


                    mOkHttpClient = new OkHttpClient.Builder()
                            .cache(cache)
                            .addInterceptor(interceptor)
                            .addNetworkInterceptor(new CacheInterceptor())
                            //使用chrome（google浏览器）调试请求，查看数据库
                            .addNetworkInterceptor(new StethoInterceptor())
                            .retryOnConnectionFailure(true)
                            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                            // .addInterceptor(new UserAgentInterceptor())
                            .build();


                }
            }
        }
    }


    /**
     * 添加UA拦截器，B站请求API需要加上UA才能正常使用
     */
    private static class UserAgentInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {

            Request originalRequest = chain.request();
            Request requestWithUserAgent = originalRequest.newBuilder()
                    .removeHeader("User-Agent")
                    //  .addHeader("User-Agent", ApiConstants.COMMON_UA_STR)
                    .build();
            return chain.proceed(requestWithUserAgent);
        }
    }


    /**
     * 为okhttp添加缓存，这里是考虑到服务器不支持缓存时，从而让okhttp支持缓存
     * <p>
     * <META HTTP-EQUIV = "Pragma" CONTENT="no-cache">告诉浏览器当前页面不背缓存
     */
    private static class CacheInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {

            // 有网络时 设置缓存超时时间1个小时
            // 无网络时，设置超时为1天

            /**
             * 将Request到Response这一过程当作一根链条，也就是chain.
             * chain.request()获取发送到服务端的Request。
             * 然后通过chain.proceed()获取服务器返回来的原始的Response。
             * 我们通过对这个原始的Response进行加工，
             * 然后产生新的Response返回给客户端，而这一切用户其实是不知道的*/

            Request request = chain.request();
            if (CommonUtil.isNetworkAvailable(AppUtils.getAppContext())) {
                //有网络时只从网络获取
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_NETWORK)
                        .build();
            } else {
                //无网络时只从缓存中读取
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
            }
            Response response = chain.proceed(request);
            if (CommonUtil.isNetworkAvailable(AppUtils.getAppContext())) {
                response = response.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public, max-age=" + HAVE_NETWORK_CACHE_TIME)
                        .build();
            } else {
                response = response.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + HAVE_NO_NETWORK_CACHE_TIME)
                        .build();
            }
            return response;
        }
    }


}
