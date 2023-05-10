package com.example.myownpersonalassistance.Fragments

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.myownpersonalassistance.Fragments.Entities.TitleEntity
import com.example.myownpersonalassistance.R
import com.example.myownpersonalassistance.Utils.Constants
import com.example.myownpersonalassistance.Utils.Util
import com.example.myownpersonalassistance.databinding.FragmentAddScheduleBinding
import org.apache.commons.lang3.time.DateFormatUtils
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class AddScheduleFragment : Fragment() {

   lateinit var list: ArrayList<TitleEntity>

    var isOtherTitle:Boolean = false

    private val calendar = Calendar.getInstance()

    var selectedDateAndTime: String? = null

    var h = 0
    var m = 0
    var y = 0
    var mon = 0
    var d = 0

    lateinit var fragmentAddScheduleBinding: FragmentAddScheduleBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        fragmentAddScheduleBinding = FragmentAddScheduleBinding.inflate(inflater,container,false)

        // Inflate the layout for this fragment
        return fragmentAddScheduleBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        list = ArrayList<TitleEntity> ()

        val titleEntityOne:TitleEntity = TitleEntity(1,"Office Meeting")
        val titleEntityTwo:TitleEntity = TitleEntity(2,"Hangout")
        val titleEntityThree:TitleEntity = TitleEntity(3,"Emergency Meeting")
        val titleEntityFour :TitleEntity = TitleEntity(4,Constants.TITLE_OTHERS)

        list.add(titleEntityOne)
        list.add(titleEntityTwo)
        list.add(titleEntityThree)
        list.add(titleEntityFour)
        setActionListener()


    }


    public fun onSelectItem(postion:Int){

        var title = list.get(postion).name
        if(title.equals(Constants.TITLE_OTHERS)){
            fragmentAddScheduleBinding.others.visibility = View.VISIBLE
            isOtherTitle = true
        }


    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun setActionListener(){

        val c = Calendar.getInstance()
        val hour: Int = c.get(Calendar.HOUR_OF_DAY)
        val minute: Int = c.get(Calendar.MINUTE)

        // on below line we are getting our hour, minute.

        fragmentAddScheduleBinding.titleLayout.setOnClickListener {

            Util.showListDialogGeneric(
                context,
                list,
                R.string.list_title,
                listener = object :Util.selectedListener{
                    override fun onListItemSelected(item: Int) {

                        val titles: TitleEntity = list.get(item)
                        fragmentAddScheduleBinding.title.setText(titles.name)
                        onSelectItem(item)
                    }

                }
            )


        }

        fragmentAddScheduleBinding.date.setOnClickListener {

            val cl = Calendar.getInstance()

            val dateFormat = Constants.FORMAT_ADD_CLAIM_DATE

            val dialog = DatePickerDialog(requireContext(), R.style.DateTimePickerTheme,
                {
                        datePicker, year, month, day ->
                    y = year
                    mon = month
                    d = day
                    calendar.set(Calendar.YEAR, year)
                    calendar.set(Calendar.MONTH, month)
                    calendar.set(Calendar.DAY_OF_MONTH, day)
                    fragmentAddScheduleBinding.dateTxt.setText(
                        DateFormatUtils.format(calendar.time, dateFormat)
                    )
                    val inputParser = SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
                    inputParser.timeZone = TimeZone.getTimeZone("UTC")
                    val format = inputParser.format(calendar.getTime())
                }, cl[Calendar.YEAR], cl[Calendar.MONTH], cl[Calendar.DAY_OF_MONTH]
            )
            dialog.datePicker.minDate = System.currentTimeMillis() - 1000
            dialog.show()
            dialog.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK)
            dialog.getButton(DatePickerDialog.BUTTON_NEGATIVE).setTextColor(Color.BLACK)

        }

        fragmentAddScheduleBinding.timeLayout.setOnClickListener {

            val timePickerDialog = TimePickerDialog(
                activity, R.style.DateTimePickerTheme,
                { timePicker, i, i1 ->
                    h = i
                    m = i1
                    if (mon != 12) {
                        mon++
                    }
                    val sss = "$y-$mon-$d $h:$m:00"

                    // convert to Date string
                    selectedDateAndTime = LocalDateTime.of(y, mon, d, h, m).toString()

                    // convert to string to Date
                    val time = LocalDateTime.parse(selectedDateAndTime)
                    Log.d("--getDates", "onTimeSet: $time")

                    fragmentAddScheduleBinding.timeText.setText("$i:$i1")
                }, hour, minute, false
            )


            // at last we are calling show to
            // display our time picker dialog.


            // at last we are calling show to
            // display our time picker dialog.
            timePickerDialog.show()
            timePickerDialog.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK)
            timePickerDialog.getButton(DatePickerDialog.BUTTON_NEGATIVE).setTextColor(Color.BLACK)
        }

    }
}