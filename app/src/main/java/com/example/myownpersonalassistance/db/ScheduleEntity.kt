package com.example.myownpersonalassistance.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.myownpersonalassistance.Utils.Constants.ENTITY_NAME
import java.time.LocalDateTime

@Entity(tableName = ENTITY_NAME)
data class ScheduleEntity(
    var scheduleTitle:String,
    var scheduleDateTime: Long,
    var scheduleDescription:String,
    var scheduleCurrentTime:Long
) {
    @PrimaryKey(autoGenerate = true)
     var id:Int? = null
}
