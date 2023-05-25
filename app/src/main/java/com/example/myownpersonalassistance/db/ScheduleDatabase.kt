package com.example.myownpersonalassistance.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ScheduleEntity::class],
     version = 1
    )


abstract class ScheduleDatabase : RoomDatabase() {

    abstract fun getDAO():ScheduleDAO

}