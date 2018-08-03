package com.angcyo.proxydemo

/**
 * Created by angcyo on 2018/08/03 14:42
 */
interface ITarget {
    fun testFun1()

    fun testFun2(arg1: String, arg2: List<String>)

    fun testFun3(arg1: String, arg2: List<String>): String

    fun testFun4(arg1: String, arg2: ArrayList<String>): String
}