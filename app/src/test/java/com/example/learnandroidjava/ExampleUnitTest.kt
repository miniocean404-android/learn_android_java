package com.example.learnandroidjava

import com.example.learnandroidjava.bean.Job
import com.example.learnandroidjava.bean.User
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.junit.Test

import org.junit.Assert.*
import java.lang.reflect.Type

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun json_serialize() {
        val userJson = Gson().toJson(
            User(
                "John", 30, "1037306928@qq.com",
                Job("Engineer", 5)
            )
        )

        println("序列化为 json $userJson")

        val user = Gson().fromJson(userJson, User::class.java)
        println("转换为对象 ${user.name} ${user.age} ${user.job.type}")
    }

    @Test
    fun arr_json_serialize() {
        val users = listOf(
            User("John", 30, "", Job("Engineer", 5)),
            User("Tom", 25, "", Job("Doctor", 3)),
            User("Jerry", 35, "", Job("Teacher", 10)),
            null
        )

        val usersJson = Gson().toJson(users)
        println("序列化为 json $usersJson")

        val users2 = Gson().fromJson(usersJson, Array<User>::class.java)
        println("转换为对象 ${users2[0].name} ${users2[0].age} ${users2[0].job.type}")


        val userJson2 = arrayOfNulls<User>(3)
        userJson2[0] = User("John", 30, "", Job("Engineer", 5))
        userJson2[1] = User("Tom", 25, "", Job("Doctor", 3))
        userJson2[2] = User("Jerry", 35, "", Job("Teacher", 10))

        val usersJson2 = Gson().toJson(userJson2)
        println("序列化为 json $usersJson2")

        val users3 = Gson().fromJson(usersJson2, Array<User>::class.java)
        println("转换为对象 ${users3[0].name} ${users3[0].age} ${users3[0].job.type}")


        // 特殊的 ArrayList
        val list = arrayListOf<User>()
        list.add(User("John", 30, "", Job("Engineer", 5)))
        list.add(User("Tom", 25, "", Job("Doctor", 3)))

        val usersJson3 = Gson().toJson(list)
        println("序列化为 json $usersJson3")


        val type: Type = object : TypeToken<List<User>>() {}.type
        val users4: List<User> = Gson().fromJson(usersJson3, type)
        println("转换为对象 ${users4[0].name} ${users4[0].age} ${users4[0].job.type}")
    }

    @Test
    fun map_json_serialize() {
        val map = hashMapOf<String, User>()
        map["John"] = User("John", 30, "", Job("Engineer", 5))
        map["Tom"] = User("Tom", 25, "", Job("Doctor", 3))

        val mapJson = Gson().toJson(map)
        println("序列化为 json $mapJson")

        val type: Type = object : TypeToken<Map<String, User>>() {}.type
        val map2: Map<String, User> = Gson().fromJson(mapJson, type)
        println("转换为对象 ${map2["John"]}")
    }

    @Test
    fun set_json_serialize() {
        val set: HashSet<User> = hashSetOf()
        set.add(User("John", 30, "", Job("Engineer", 5)))
        set.add(User("Tom", 25, "", Job("Doctor", 3)))

        val setJson = Gson().toJson(set)
        println("序列化为 json $setJson")

        val type: Type = object : TypeToken<Set<User>>() {}.type
        val set2: Set<User> = Gson().fromJson(setJson, type)
        println("转换为对象 ${set2.elementAt(0).age}")
    }

    /**
     * 默认忽略 null 值的序列化
     * 但是 set 与 list 会序列化 null 值
     */
    @Test
    fun null_json_serialize() {
    }

    @Test
    fun annotation_json_serialize() {
        val gson = GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
        val user = User("John", 30, "111", Job("Engineer", 5))

        val userJson = gson.toJson(user)
        println("序列化为 json $userJson")

//        val user2 = gson.fromJson(userJson, User::class.java)
//        println("转换为对象 ${user2.name} ${user2.age} ${user2.job.type}")
    }
}