package com.example.learnandroidjava.shared.rx_observer

import com.example.learnandroidjava.model.bean.RxJavaResponse
import com.example.learnandroidjava.model.bean.RxJavaSuccessResponse
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable

/**
 * 自定义 RxJava 响应观察者
 */
abstract class RxJavaRespObserver : Observer<RxJavaResponse> {
    abstract fun success(data: RxJavaSuccessResponse)
    abstract fun error(message:String)

    override fun onSubscribe(d: Disposable) {
    }

    override fun onError(e: Throwable) {
        this.error("请求失败: ${e.message}")
    }

    override fun onComplete() {
    }

    override fun onNext(resp: RxJavaResponse) {
        if (resp.data == null) {
            this.error("请求失败: ${resp.message}")
        }else{
            success(resp.data!!)
        }
    }
}