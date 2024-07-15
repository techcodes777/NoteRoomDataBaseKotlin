package com.eclatsol.newadddata

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Student::class], version = 1, exportSchema = false)
abstract class StudentDatabase : RoomDatabase() {

    abstract fun getStudentData(): StudentDao

    companion object {

        @Volatile
        private var INSTANCE: StudentDatabase? = null

        fun getInstance(context: Context): StudentDatabase {
            return INSTANCE ?: synchronized(this) {
                var instance =
                    Room.databaseBuilder(context, StudentDatabase::class.java, "StudentDatabase")
                        .build()
                INSTANCE = instance
                instance
            }
        }
    }

}