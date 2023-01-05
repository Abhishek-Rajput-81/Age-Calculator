package abhishek.gravity.agecalculator

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private var textviewSelectedDate: TextView? = null
    private var textviewSelectedDateInMinute:TextView? = null
    private var textviewSelectedDateInHour:TextView? = null
    private var textviewSelectedDateInDay:TextView? = null
    private var textviewSelectedDateInYear:TextView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonDatePicker: Button = findViewById(R.id.btnDatePicker)
        textviewSelectedDate = findViewById(R.id.tvSelectedDate)
        textviewSelectedDateInMinute = findViewById(R.id.tvSelectedMinute)
        textviewSelectedDateInHour = findViewById(R.id.tvSelectedHour)
        textviewSelectedDateInDay = findViewById(R.id.tvSelectedDay)
        textviewSelectedDateInYear = findViewById(R.id.tvSelectedYear)
        buttonDatePicker.setOnClickListener {
            clickDatePicker()
        }
    }


       private fun clickDatePicker(){
            val myCalender = Calendar.getInstance()
            val year = myCalender.get(Calendar.YEAR)
            val month = myCalender.get(Calendar.MONTH)
            val day = myCalender.get(Calendar.DAY_OF_MONTH)

           val dpd = DatePickerDialog(
                this,
                { _, SelectedYear, SelectedMonth, SelectedDayOfMonth ->

                    Toast.makeText(this, "Selected Date is $SelectedDayOfMonth/${SelectedMonth+1}/$SelectedYear",
                        Toast.LENGTH_LONG).show()

                    val selectDate = "$SelectedDayOfMonth/${SelectedMonth+1}/$SelectedYear"
                    textviewSelectedDate?.text =selectDate
                    val sdf = SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH)

                    val theDate = sdf.parse(selectDate)
                    theDate?.let {
                        val selectedDateInMinutes = theDate.time / 60000

                        val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                        currentDate?.let {
                            val currentDateToMinutes = currentDate.time / 60000

                            val differenceInMinutes = currentDateToMinutes - selectedDateInMinutes
                            textviewSelectedDateInMinute?.text = differenceInMinutes.toString()

                            val differenceInHour = differenceInMinutes/60
                            textviewSelectedDateInHour?.text = differenceInHour.toString()

                            val differenceInDay = differenceInHour/24
                            textviewSelectedDateInDay?.text = differenceInDay.toString()

                            val differenceInYear = differenceInDay/365
                            textviewSelectedDateInYear?.text = differenceInYear.toString()


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
