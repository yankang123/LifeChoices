package com.example.lifechoices.roomb

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Item")
data class Item(var isChecked :Int,var text :String,var meaning : Int, var drawerPosition : Int) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}