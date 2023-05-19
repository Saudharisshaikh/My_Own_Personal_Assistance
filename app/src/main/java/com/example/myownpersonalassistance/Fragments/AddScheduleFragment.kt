package com.example.myownpersonalassistance.Fragments

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.app.TimePickerDialog.OnTimeSetListener
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.myownpersonalassistance.Fragments.Entities.TitleEntity
import com.example.myownpersonalassistance.R
import com.example.myownpersonalassistance.Utils.Constants
import com.example.myownpersonalassistance.Utils.Util
import com.example.myownpersonalassistance.databinding.FragmentAddScheduleBinding
import org.apache.commons.lang3.time.DateFormatUtils
import org.joda.time.DateTime
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.util.*
import java.util.concurrent.TimeUnit

class AddScheduleFragment : Fragment() {

   lateinit var list: ArrayList<TitleEntity>


    var isOtherTitle:Boolean = false
    var isDateSelected = false

    var dateTime:Long = 0L
    var title:String = ""

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

    @RequiresApi(Build.VERSION_CODES.O)
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

        var titles = list.get(postion).name
        title = titles
        if(titles.equals(Constants.TITLE_OTHERS)){
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

        fragmentAddScheduleBinding.others.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // This method is called before the text is changed
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // This method is called when the text is being changed
            }

            override fun afterTextChanged(s: Editable?) {
                // This method is called after the text has been changed
            }
        })

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
                    calendar.set(Calendar.DATE, day)

                    isDateSelected = true
                    Log.d("--dateSelected", "setActionListener: "+calendar.timeInMillis)


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

            if(isDateSelected) {
                val timePickerDialog = TimePickerDialog(
                    activity, R.style.DateTimePickerTheme,
                    { timePicker, i, i1 ->
                        h = i
                        m = i1
                        if (mon != 12) {
                            mon++
                        }

                        calendar.set(Calendar.YEAR, y)
                        calendar.set(Calendar.MONTH, mon - 1)
                        calendar.set(Calendar.DATE, d - 1)
                        calendar.set(Calendar.HOUR, h)
                        calendar.set(Calendar.MINUTE, m)
                        calendar.set(Calendar.SECOND, 0)

                        Log.d("--newDateTime ", "setActionListener: " + calendar.timeInMillis)
                        dateTime = calendar.timeInMillis
                        // convert to Date string
                        selectedDateAndTime = LocalDateTime.of(y, mon, d, h, m).toString()

                        // convert to string to Date
                        val time = LocalDateTime.parse(selectedDateAndTime)
                        Log.d("--getDates", "onTimeSet: $time")


                        val timeFormate = formatTime(i,i1);

                        fragmentAddScheduleBinding.timeText.setText("$timeFormate")
                        Log.d("--ytime", "setActionListener: ")
                    }, hour, minute, false
                )


                timePickerDialog.show()
                timePickerDialog.getButton(DatePickerDialog.BUTTON_POSITIVE)
                    .setTextColor(Color.BLACK)
                timePickerDialog.getButton(DatePickerDialog.BUTTON_NEGATIVE)
                    .setTextColor(Color.BLACK)
            }
            else {
                AddScheduleFragment.showMessage(requireContext(),"Please Select Date First!")
            }
        }
        fragmentAddScheduleBinding.saveBtn.setOnClickListener{


        }

    }

    private fun formatTime(i: Int, i1: Int) :String{

        var selectedHour = i
        var selectedMinute = i1

        var amOrPm = ""
        if(i < 11 )
           amOrPm = "am"
        else
            amOrPm = "pm"

        when(selectedHour){

            13 -> selectedHour = 1
            14 -> selectedHour = 2
            15 -> selectedHour = 3
            16 -> selectedHour = 4
            17 -> selectedHour = 5
            18 -> selectedHour = 6
            19 -> selectedHour = 7
            20 -> selectedHour = 8
            21 -> selectedHour = 9
            22 -> selectedHour = 10
            23 -> selectedHour = 11

        }
        var convertedHour = selectedHour.toString()
        var convertedMinute = selectedMinute.toString()

        if(selectedHour < 10)
            convertedHour = "0${selectedHour}"

        if(selectedMinute < 10)
            convertedMinute = "0${selectedMinute}"



       return "${convertedHour}:${convertedMinute}:${amOrPm}"
    }

    companion object


}

public fun AddScheduleFragment.Companion.showMessage(requireContext: Context, s: String) {

    Toast.makeText(requireContext,s,Toast.LENGTH_LONG).show()
}
