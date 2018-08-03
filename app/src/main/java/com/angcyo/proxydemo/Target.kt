package com.angcyo.proxydemo

import android.util.Log

/**
 * Created by angcyo on 2018/08/03 14:29
 * 需要操作的真实对象
 */
class Target : ITarget {
    override fun testFun4(arg1: String, arg2: ArrayList<String>): String {
        Log.i("angcyo", "log fun4")
        return "fun4"
    }

    override fun testFun1() {
        Log.i("angcyo", "log fun1")
    }

    override fun testFun2(arg1: String, arg2: List<String>) {
        Log.i("angcyo", "log fun2")

    }

    override fun testFun3(arg1: String, arg2: List<String>): String {
        Log.i("angcyo", "log fun3")
        return "fun3"
    }
}