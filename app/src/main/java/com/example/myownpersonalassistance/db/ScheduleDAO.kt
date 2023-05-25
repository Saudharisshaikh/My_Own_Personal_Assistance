package com.example.myownpersonalassistance.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ScheduleDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMeeting(scheduleDAO: ScheduleDAO)

    @Delete
    suspend fun  deleteMeeting(scheduleDAO: ScheduleDAO)

    @Query("SELECT * FROM schedule_entity ORDER BY scheduleDateTime DESC")
    fun getAllMeetingsOrderbyTime() :LiveData<List<ScheduleEntity>>

}