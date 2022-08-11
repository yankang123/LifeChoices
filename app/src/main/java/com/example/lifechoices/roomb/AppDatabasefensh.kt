package com.example.lifechoices.roomb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(version = 1,entities = [User::class])
abstract class AppDatabasefensh : RoomDatabase() {//TODO(fault_Memory:@有个波浪，
// TODO(没报错，开始也没看见，所以room看不到注解，也就找不到Appdatabase)

    abstract fun itemDao(): UserDao

    companion object {
        private var instance: AppDatabasefensh? = null
        @Synchronized
        fun getDatabase(context: Context): AppDatabasefensh {
           instance?.let { return it }
            return Room.databaseBuilder(context.applicationContext, AppDatabasefensh::class.java,"app_database")
                .fallbackToDestructiveMigration() .allowMainThreadQueries()  .build().apply { instance =this }
        }
    }
}
//@Database(version = 1, entities = [User::class])
