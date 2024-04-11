package com.example.learnandroidjava.learn.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Student {
    @ColumnInfo(name = "_id")
    @PrimaryKey(autoGenerate = true)
    @JvmField var id: Int? = null // 将 id 声明为可空类型并延迟初始化

    @JvmField var name: String = ""

    @JvmField var age: Int = 0

    constructor(name: String, age: Int) {
        this.name = name
        this.age = age
    }

    constructor() : this("", 0) // 无参构造函数
}