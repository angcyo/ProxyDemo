package com.angcyo.proxydemo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import java.lang.reflect.Proxy

class MainActivity : AppCompatActivity() {

    //需要被代理的对象, 必须是接口类型
    var target: ITarget = Target()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //保存旧的真实对象
        val oldTarget = target

        //代理 target 对象, 拦截真实对象的操作, 做自己想做的事
        val proxy = Proxy.newProxyInstance(this.classLoader, arrayOf(ITarget::class.java)) { proxy, method, args ->
            if (method.name == "toString") {
                null
            } else {
                Log.i("angcyo", "调用方法:$method 返回类型:${method.returnType}")
                args?.let {
                    for (any in it) {
                        Log.i("angcyo", "参数类型:" + any.javaClass.simpleName)
                    }
                }

                if (method.name == "testFun4") {
                    method.invoke(oldTarget, *args)
                }
            }
            null
        }

        //通常情况下, 都是采用反射的方式, 用新的代理对象替换原来的真实对象.
        val targetField = this.javaClass.getDeclaredField("target")
        targetField.isAccessible = true
        targetField.set(this, proxy)

        //当然,也可以直接赋值
        //target = proxy as ITarget
    }

    fun onTest(view: View) {
        //之后调用对象的方法, 都会被代理类执行
        target.testFun1()
        target.testFun2("fun2", listOf("1", "2"))
        val res3 = target.testFun3("fun3", listOf("a", "b", "c"))
        Log.i("angcyo", "result:" + res3)
        val res4 = target.testFun4("fun4", arrayListOf("1", "2"))
        Log.i("angcyo", "result:" + res4)
    }
}
