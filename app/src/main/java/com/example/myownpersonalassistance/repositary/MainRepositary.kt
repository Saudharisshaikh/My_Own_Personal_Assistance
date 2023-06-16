package com.example.myownpersonalassistance.repositary

import com.example.myownpersonalassistance.db.ScheduleDAO
import com.example.myownpersonalassistance.db.ScheduleEntity
import javax.inject.Inject

class MainRepositary
@Inject constructor(val scheduleDAO: ScheduleDAO) {

    suspend fun insertSchedule(scheduleEntity: ScheduleEntity)
    = scheduleDAO.insertMeeting(scheduleEntity)

    suspend fun deleteSchedule(scheduleEntity: ScheduleEntity)
    = scheduleDAO.deleteMeeting(scheduleEntity)

    fun  getAllSchedulesSortedByTime()
    = scheduleDAO.getAllMeetingsOrderbyTime()
}