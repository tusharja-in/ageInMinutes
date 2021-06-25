package com.example.ageminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val selectionButton=findViewById<Button>(R.id.selectionButton)
        selectionButton.setOnClickListener{view->
            clickDatePicker(view)
        }
    }
    fun clickDatePicker(view: View){
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        val dpd=DatePickerDialog(this,DatePickerDialog.OnDateSetListener{view,selectedYear,selectedMonth,selectedDayOfMonth->
            val selectedDate="$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"
            val selectedDateID=findViewById<TextView>(R.id.selectedDateID)
            selectedDateID.text=selectedDate
            val sdf=SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
            val sDate=sdf.parse(selectedDate)

            // to get selected date in minutes from 1970
            val selectedDateInMinutes = sDate!!.time/(60000)

            // to get current date in minutes from 1970
            val currentDateInMinutes = Date().time/(60000)

            val ageInMinutes = currentDateInMinutes-selectedDateInMinutes
            val ageInMinutesID=findViewById<TextView>(R.id.ageInMinutesID)
            ageInMinutesID.text=ageInMinutes.toString()

        },year,month,day)
        dpd.datePicker.maxDate = Date().time-86400000
        dpd.show()

    }

}
