package com.example.lifechoices.roomb

import androidx.room.Entity
import androidx.room.PrimaryKey

/**

 * @Author :Yankang

 * @Time : On 2022/8/11 16:59

 * @Description : DrawerItem

 */
@Entity(tableName = "DrawerItem")
data class DrawerItem(var imgId : Int,var content : String,var count :Int) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}