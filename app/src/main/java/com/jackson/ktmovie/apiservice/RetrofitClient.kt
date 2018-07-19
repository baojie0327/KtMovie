package com.jackson.ktmovie.apiservice

import com.jackson.ktmovie.utils.Constant
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.KeyManagementException
import java.security.NoSuchAlgorithmException
import java.security.SecureRandom
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

/**
 * RetrofitClient  2018-06-27
 * Copyright (c) 2018 JS Co.Ltd. All right reserved.
 */
/**
 * Retrofit客户端类
 * @author Jackson
 * @version 1.0.0
 * since 2018 06 27
 */
class RetrofitClient(baseUrl: String) {

    private var mRetrofit: Retrofit

    /**
     * 初始化方法，主要作用
     *  设置Retrofit的拦截器
     *  创建okHttpClient
     */
    init {
        // 初始化拦截器
        var interceptor = HttpLoggingInterceptor()
        if (Constant.LOG_DEBUG) {
            interceptor.level = HttpLoggingInterceptor.Level.BODY
        } else {
            interceptor.level = HttpLoggingInterceptor.Level.NONE
        }

        // https认证
        val xtm = object : X509TrustManager {

            @Throws(CertificateException::class)
            override fun checkClientTrusted(p0: Array<out X509Certificate>?, p1: String?) = Unit

            @Throws(CertificateException::class)
            override fun checkServerTrusted(p0: Array<out X509Certificate>?, p1: String?) = Unit

            override fun getAcceptedIssuers(): Array<X509Certificate?> = arrayOfNulls(0)

        }

        var sslContext: SSLContext? = null

        try {
            sslContext = SSLContext.getInstance("SSL")
            sslContext.init(null, arrayOf<TrustManager>(xtm), SecureRandom())
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        } catch (e: KeyManagementException) {
            e.printStackTrace()
        }

        val DO_NOT_VERIFY = HostnameVerifier { hostname, session -> true }

        //创建okHttpClient
        var okHttpClient = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .sslSocketFactory(sslContext!!.socketFactory)
                .hostnameVerifier(DO_NOT_VERIFY)
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .build()

        // 创建Retrofit2的实例，并设置BaseUrl和Gson转换
        mRetrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())  //设置 Json 转换器
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) //RxJava 适配器
                .client(okHttpClient)
                .build()

    }

    /**
     * 创建Retrofit请求服务   ApiService apiService = retrofit.create(ApiService.class);
     *
     * @param
     * @param
     * @return
     */

    fun <T> create(apiInterfaceClass: Class<T>): T = mRetrofit.create(apiInterfaceClass)


}