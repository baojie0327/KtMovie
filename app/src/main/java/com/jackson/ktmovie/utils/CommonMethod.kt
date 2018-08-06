package com.jackson.ktmovie.utils

import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.content.res.Resources
import android.text.TextUtils
import android.util.DisplayMetrics
import android.view.Gravity
import android.view.View
import android.widget.Toast
import com.jackson.ktmovie.R


import java.io.UnsupportedEncodingException
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.regex.Matcher
import java.util.regex.Pattern

/*
* CommonMethod  2016-11-10
* Copyright (c) 2016 HYB Co.Ltd. All right reserved.
*/
/*
* 此类存放一般的工具方法
* @author Borje
* @version 1.0.0
* since 2016-11-10
*/
object CommonMethod {

    /**
     * 验证手机格式
     */
    fun isMobileNO(mobiles: String): Boolean {
        val telRegex = "[1][34578]\\d{9}"//"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        return if (TextUtils.isEmpty(mobiles)) {
            false
        } else {
            mobiles.matches(telRegex.toRegex())
        }
    }

    /**
     * 判断email格式是否正确
     */
    fun isEmail(email: String): Boolean {
        val str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$"
        val p = Pattern.compile(str)
        val m = p.matcher(email)
        return m.matches()
    }

    /**
     * 获取版本名
     *
     * @return
     */
    fun checkVersion(context: Context): String? {
        //拿到包的管理器
        val pm = context.packageManager
        try {
            //封装了所有的功能清单中的数据
            val info = pm.getPackageInfo(context.packageName, 0)
            return info.versionName
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return null
    }

    /**
     * MD5加密
     *
     * @param info
     * @return
     */
    fun getMD5(info: String): String {
        try {
            val md5 = MessageDigest.getInstance("MD5")
            md5.update(info.toByteArray(charset("UTF-8")))
            val encryption = md5.digest()
            val strBuf = StringBuffer()
            for (i in encryption.indices) {
                if (Integer.toHexString(0xff and encryption[i].toInt()).length == 1) {
                    strBuf.append("0").append(Integer.toHexString(0xff and encryption[i].toInt()))
                } else {
                    strBuf.append(Integer.toHexString(0xff and encryption[i].toInt()))
                }
            }
            return strBuf.toString()
        } catch (e: NoSuchAlgorithmException) {
            return ""
        } catch (e: UnsupportedEncodingException) {
            return ""
        }

    }

    /**
     * 判断微信是否可用
     *
     * @param context
     * @return
     */
    fun isWeixinAvilible(context: Context): Boolean {
        val packageManager = context.packageManager// 获取packagemanager
        val pinfo = packageManager.getInstalledPackages(0)// 获取所有已安装程序的包信息
        if (pinfo != null) {
            pinfo.indices
                    .map { pinfo[it].packageName }
                    .filter { it == "com.tencent.mm" }
                    .forEach { return true }
        }

        return false
    }

    /**
     * 判断qq是否可用
     *
     * @param context
     * @return
     */
    fun isQQClientAvailable(context: Context): Boolean {
        val packageManager = context.packageManager
        val pinfo = packageManager.getInstalledPackages(0)
        if (pinfo != null) {
            for (i in pinfo.indices) {
                val pn = pinfo[i].packageName
                if (pn == "com.tencent.mobileqq") {
                    return true
                }
            }
        }
        return false
    }


    fun showToast(context: Context, text: String, isLongLength: Boolean) {
        var toast: Toast? = null
        val length: Int
        if (isLongLength) {
            length = Toast.LENGTH_LONG
        } else {
            length = Toast.LENGTH_SHORT
        }
        if (toast == null) {
            toast = Toast.makeText(context, text, length)
        } else {
            toast.setText(text)
            toast.duration = length
        }

        val view = toast!!.view
        view.setBackgroundResource(R.drawable.shape_toast_background)
        toast.view = view
        toast.setGravity(Gravity.CENTER, 0, 0)

        toast.show()
    }

    /**
     * 获得当前时间
     */
    fun getCurrentTime(format: String): String {
        var format = format
        if (format == "") {
            format = "yyyy年MM月dd日 HH:mm:ss"
        }
        val formatter = SimpleDateFormat(format)
        val curDate = Date(System.currentTimeMillis())
        return formatter.format(curDate)
    }

    fun isPhoneNum(phoneNum: String): Boolean {
        val regularExpression = "^((13[0-9])|(15[^4])|(18[0,1,2,3,4,5-9])|(17[0-8])|(147))\\d{8}$"
        val pattern = Pattern.compile(regularExpression)
        val matcher = pattern.matcher(phoneNum)
        return matcher.matches()
    }

    /**
     * lvliheng
     * dp 转换成 px
     * @param context
     * @param dp
     * @return
     */
    fun convertDpToPixel(context: Context, dp: Float): Int {
        val resources = context.resources
        val metrics = resources.displayMetrics
        val px = dp * (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
        return px.toInt()
    }
}