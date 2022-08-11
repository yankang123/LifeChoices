package com.example.lifechoices.roomb

import androidx.room.*


/**

 * @Author :Yankang

 * @Time : On 2022/8/11 17:02

 * @Description : DrawerItemDao

 */
@Dao
interface DrawerItemDao {
    @Insert
    fun insertItem(item: DrawerItem): Long
    @Update
    fun updateItem(item: DrawerItem)
    @Query("SELECT * FROM DrawerItem")
    fun loadAllItems(): List<DrawerItem>


    @Delete
    fun deleteItem(item: DrawerItem)
    @Query("delete from Item where drawerPosition  =:content")
    fun deleteById(content: String): Int
}