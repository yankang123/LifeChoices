package com.example.lifechoices.roomb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


/**

 * @Author :Yankang

 * @Time : On 2022/8/11 17:09

 * @Description : AppDatebaseDrawer

 */
@Database(version = 1,entities = [DrawerItem::class])
abstract class AppDatabaseDrawer : RoomDatabase() {//TODO(fault_Memory:@有个波浪，
// TODO(没报错，开始也没看见，所以room看不到注解，也就找不到Appdatabase)

    abstract fun DrawerItemDao(): DrawerItemDao

  companion object {
        private var instance: AppDatabaseDrawer? = null
        @Synchronized
    fun getDatabase(context: Context): AppDatabaseDrawer {
            instance?.let { return it }
            return Room.databaseBuilder(context.applicationContext, AppDatabaseDrawer::class.java,"app_database2")
                .fallbackToDestructiveMigration() .allowMainThreadQueries()  .build().apply { instance =this }
        }
    }
}