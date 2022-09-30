package com.example.lifechoices.roomb

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(var fenshu: Int,var position :Int) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}
