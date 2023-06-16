package com.example.myownpersonalassistance.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myownpersonalassistance.db.ScheduleEntity
import com.example.myownpersonalassistance.repositary.MainRepositary
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScheduleViewModel
@Inject constructor(val mainRepositary: MainRepositary)
    :ViewModel(){


  fun insertRun(scheduleEntity: ScheduleEntity){
      viewModelScope.launch {
          mainRepositary.insertSchedule(scheduleEntity)
      }
  }

  fun deleteRun(scheduleEntity: ScheduleEntity){
      viewModelScope.launch {
          mainRepositary.deleteSchedule(scheduleEntity)
      }
  }
}