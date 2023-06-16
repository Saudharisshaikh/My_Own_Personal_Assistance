package com.example.myownpersonalassistance.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ScheduleDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMeeting(scheduleEntity: ScheduleEntity)

    @Delete
    suspend fun  deleteMeeting(scheduleEntity: ScheduleEntity)

    @Query("SELECT * FROM schedule_entity ORDER BY scheduleDateTime DESC")
    fun getAllMeetingsOrderbyTime() :LiveData<List<ScheduleEntity>>

}