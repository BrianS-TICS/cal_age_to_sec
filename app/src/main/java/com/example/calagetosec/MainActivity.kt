package com.example.calagetosec

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private var tvSelectedDate : TextView ? = null
    private var tvAgeInMinutes : TextView ? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker : Button = findViewById(R.id.btnPickerDate)
        tvSelectedDate = findViewById(R.id.tvSelectedDate)
        tvAgeInMinutes = findViewById(R.id.tvAgeInMinutes)

        btnDatePicker.setOnClickListener{
            clickDatePicker()

        }
    }

    private fun clickDatePicker(){
        val miCalendar = Calendar.getInstance()
        val year = miCalendar.get(Calendar.YEAR)
        val month = miCalendar.get(Calendar.MONTH)
        val day = miCalendar.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(this,
            {_, selectedYear, selectedMonth, selecteDayOfMonth ->

                Toast.makeText(this,"year was ${selectedYear} and the month ${selectedMonth + 1} and the day${selecteDayOfMonth}", Toast.LENGTH_LONG).show()

                val selectedDate = "${selecteDayOfMonth}/${selectedMonth + 1}/${selectedYear}"
                tvSelectedDate?.setText(selectedDate)

                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH )
                val theDate = sdf.parse(selectedDate)

                theDate?.let{
                    val selectedDateInMinutes = theDate.time / 60000

                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))

                    currentDate?.let{
                        val currentDateInMinutes = currentDate.time / 60000

                        val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes

                        tvAgeInMinutes?.setText(differenceInMinutes.toString())
                    }


                }

            },
            year,
            month,
            day
            )
        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()
    }
}