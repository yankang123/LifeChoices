package com.example.doinmac2.mpdel

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDao {

    @Insert
    fun insertUser(user: User): Long

    @Update
    fun updateUser(newUser: User)

    @Query("select * from user")
    fun loadAllUsers(): List<User>



    @Delete
    fun deleteUser(user: User)


}
