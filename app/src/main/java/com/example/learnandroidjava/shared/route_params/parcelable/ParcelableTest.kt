package com.example.learnandroidjava.shared.route_params.parcelable

import android.os.Parcel
import android.os.Parcelable

/**
 * parcel 读取顺序必须和写入循序一致，否则报错
 */
class ParcelableTest() : Parcelable {
    var name: String? = null
    var age: Int = 0

    constructor(parcel: Parcel) : this() {
        this@ParcelableTest.name = parcel.readString()
        this@ParcelableTest.age = parcel.readInt()
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(name)
        dest.writeInt(age)
    }

    // 系统扩展用的
    override fun describeContents(): Int {
        return 0
    }

    // CREATOR 一定要有
    companion object CREATOR : Parcelable.Creator<ParcelableTest> {
        // 创建 ParcelableTest 对象，并且构建好传递给 ParcelableTest 构造函数的参数
        override fun createFromParcel(parcel: Parcel): ParcelableTest {
            return ParcelableTest(parcel)
        }

        override fun newArray(size: Int): Array<ParcelableTest?> {
            return arrayOfNulls(size)
        }
    }
}