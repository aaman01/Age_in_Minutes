package com.example.dobcalculator

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {
    private var dateselect: TextView? = null
    private var dateview:TextView?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val buttondate: Button = findViewById(R.id.buttondate)
        dateselect = findViewById(R.id.dateselect)
        dateview=findViewById(R.id.dateview)
        buttondate.setOnClickListener {
            buttonclick()
        }

    }

     private fun buttonclick() {
        val mycalender = Calendar.getInstance()
        val year = mycalender.get(Calendar.YEAR)
        val month = mycalender.get(Calendar.MONTH)
        val day = mycalender.get(Calendar.DAY_OF_MONTH)
        val dpd= DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { view, selectedyear, selectedmonth, selecteddayOfMonth ->
                var selecteddate = "$selecteddayOfMonth/${selectedmonth + 1}/$selectedyear"
                dateselect?.text = selecteddate

                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val thedate = sdf.parse(selecteddate)
                val selectedtimeinminutes =
                    thedate.time / 60000  // time passed btween selected date and 1970
                val currentDate =
                    sdf.parse(sdf.format(System.currentTimeMillis()))// time passed between current date and 1970
                val currentDateinMinute = currentDate.time/60000
                val differnce= currentDateinMinute-selectedtimeinminutes;
                dateview?.text= differnce.toString()
            },
            year,
            month,
            day
        )
         dpd.datePicker.maxDate=System.currentTimeMillis()-86400000
         dpd.show()
    }


}