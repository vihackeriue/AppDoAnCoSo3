package com.example.appdoancoso3

import android.app.TimePickerDialog
import android.content.Intent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.provider.AlarmClock
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.appdoancoso3.databinding.ActivityAlarmBinding
import java.util.Calendar

class AlarmActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAlarmBinding
    var timeHour: EditText? = null
    var timeMinute: EditText? = null
    var setTime: Button? = null
    var setAlarm: Button? = null
    var timePickerDialog: TimePickerDialog? = null
//    var calendar: Calendar? = null
    var currentHour = 0
    var currentMinute = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlarmBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnTime.setOnClickListener {
            val calendar = Calendar.getInstance()


            currentHour = calendar.get(Calendar.HOUR_OF_DAY)
            currentMinute = calendar.get(Calendar.MINUTE)
            timePickerDialog = TimePickerDialog(this@AlarmActivity,
                { timePicker, hourOfDay, minutes ->
                    binding.etHour.setText(String.format("%02d", hourOfDay))
                    binding.etMinute.setText(String.format("%02d", minutes))
                }, currentHour, currentMinute, false
            )
            timePickerDialog!!.show()
        }
        binding.btnAlarm.setOnClickListener{
            if (!binding.etHour.getText().toString().isEmpty() && !binding.etMinute.getText().toString()
                    .isEmpty()
            ) {


                //-------------------------1st Alarm---------------------------------------
                val intent = Intent(AlarmClock.ACTION_SET_ALARM)
                intent.putExtra(AlarmClock.EXTRA_HOUR, binding.etHour.getText().toString().toInt())
                intent.putExtra(AlarmClock.EXTRA_MINUTES, binding.etMinute.getText().toString().toInt())
                intent.putExtra(AlarmClock.EXTRA_MESSAGE, "1st Alarm")
                //--------------------------End----------------------------------------------

                //-------------------------2nd Alarm---------------------------------------
                val intent2 = Intent(AlarmClock.ACTION_SET_ALARM)
                //If it is 7pm, simply put 19
                //intent2.putExtra(AlarmClock.EXTRA_HOUR, 19);
                intent2.putExtra(AlarmClock.EXTRA_HOUR, binding.etHour.getText().toString().toInt())

                //If it is 7:20pm, simply put 20
                //intent2.putExtra(AlarmClock.EXTRA_MINUTES, 20);
                intent2.putExtra(
                    AlarmClock.EXTRA_MINUTES,
                    binding.etMinute.getText().toString().toInt() + 5
                )
                intent2.putExtra(AlarmClock.EXTRA_MESSAGE, "2nd alarm")
                //--------------------------End----------------------------------------------

                //-------------------------3rd Alarm---------------------------------------
                val intent3 = Intent(AlarmClock.ACTION_SET_ALARM)
                intent3.putExtra(AlarmClock.EXTRA_HOUR, binding.etHour.getText().toString().toInt())
                intent3.putExtra(
                    AlarmClock.EXTRA_MINUTES,
                    binding.etMinute.getText().toString().toInt() + 10
                )
                intent3.putExtra(AlarmClock.EXTRA_MESSAGE, "3rd alarm")
                //--------------------------End----------------------------------------------
                if (intent.resolveActivity(packageManager) != null) {
                    startActivity(intent)
                    finish()
                    val handler2 = Handler()
                    handler2.postDelayed({
                        startActivity(intent2)
                        finish()
                    }, 300)
                    val handler3 = Handler()
                    handler3.postDelayed({
                        startActivity(intent3)
                        finish()
                    }, 700)
                } else {
                    Toast.makeText(
                        this@AlarmActivity,
                        "There is no app that support this action",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                Toast.makeText(this@AlarmActivity, "Please choose a time", Toast.LENGTH_SHORT).show()
            }
        }

    }
}