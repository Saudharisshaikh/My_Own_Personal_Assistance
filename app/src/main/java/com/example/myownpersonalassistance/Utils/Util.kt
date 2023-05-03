package com.example.myownpersonalassistance.Utils

import android.R
import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import com.example.myownpersonalassistance.Fragments.Entities.TitleEntity

class Util {

    public interface selectedListener{
        fun onListItemSelected(item:Int);
    }

    fun <T> showListDialogGeneric(
        context: Context?,
        list: ArrayList<TitleEntity>?,
        @StringRes titleId: Int,
        listener: selectedListener
    ): AlertDialog? {
        if (list == null) return null
        if (context is Activity) {

            if (context.isDestroyed) return null
        }
        val items = arrayOfNulls<String>(list.size)
        for (i in list.indices) {
            items[i] = list[i].name
        }
        val view: View = LayoutInflater.from(context)
            .inflate(R.layout.dialoglist,null,false)
        val lv = view.findViewById<ListView>(R.id.lv)
        lv.adapter =
            ArrayAdapter(context!!, R.layout.simple_list_item_1, items)
        val tv: TextView = view.findViewById(R.id.tvTitle)
        tv.setText(titleId)
        val ad = AlertDialog.Builder(context)
            .setView(view)
            .show()
        lv.onItemClickListener =
            OnItemClickListener { parent: AdapterView<*>?, view1: View?, position: Int, id: Long ->
                ad.dismiss()
                listener.onListItemSelected(position)
            }
        return ad
    }
}