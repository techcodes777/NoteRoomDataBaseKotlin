package com.eclatsol.newadddata

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "student")
class Student(@ColumnInfo(name = "studentName") var studentName: String) {
    @PrimaryKey(autoGenerate = true) var id = 0
}