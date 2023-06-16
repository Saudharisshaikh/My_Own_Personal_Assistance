package com.example.myownpersonalassistance.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myownpersonalassistance.db.ScheduleEntity

class ScheduleAdapter :ListAdapter<ScheduleEntity,ScheduleAdapter.ScheduleViewModel>(DiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleViewModel {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ScheduleViewModel, position: Int) {
        TODO("Not yet implemented")
    }

    class ScheduleViewModel(itemView:View) : RecyclerView.ViewHolder(itemView) {


    }

    class DiffCallback : DiffUtil.ItemCallback<ScheduleEntity>(){
        override fun areItemsTheSame(oldItem: ScheduleEntity, newItem: ScheduleEntity): Boolean {
            return   oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ScheduleEntity, newItem: ScheduleEntity): Boolean {
            return oldItem == newItem
        }

    }
}


