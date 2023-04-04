package com.example.appdoancoso3

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.Fragment
import com.example.appdoancoso3.databinding.ActivityListWorkBinding
import com.example.appdoancoso3.list_work.HistoryListWorkFragment
import com.example.appdoancoso3.list_work.HomeListWorkFragment
import com.example.appdoancoso3.list_work.StatisticalListWorkFragment
import java.text.SimpleDateFormat
import java.util.*

class ListWorkActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListWorkBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityListWorkBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.imgBack.setOnClickListener{
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)

        }
        binding.imgCalendar.setOnClickListener{
            setDate();
        }

        replaceFragment(HomeListWorkFragment())
        binding.bottomNavigation.setOnItemSelectedListener {
            when(it.itemId){
                R.id.listwork_tab1 -> replaceFragment(HomeListWorkFragment())
                R.id.listwork_tab2 -> replaceFragment(StatisticalListWorkFragment())
                R.id.listwork_tab3 -> replaceFragment(HistoryListWorkFragment())
                else ->{

                }
            }
            true


        }
    }

    private fun setDate() {
        val datePicker = Calendar.getInstance()
        val date = DatePickerDialog.OnDateSetListener{
            view: DatePicker?, year: Int,month: Int, dayOfMonth: Int ->
            datePicker[Calendar.YEAR] = year
            datePicker[Calendar.MONTH] = month
            datePicker[Calendar.DAY_OF_MONTH]=dayOfMonth
            val dateFormat = "dd-MMMM-yyyy"
            val simpleDateFormat = SimpleDateFormat(dateFormat, Locale.getDefault())

            }

        DatePickerDialog(
            this@ListWorkActivity,date,
            datePicker[Calendar.YEAR],
            datePicker[Calendar.MONTH],
            datePicker[Calendar.DAY_OF_MONTH]

        ).show()
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment, fragment)
            commit()
        }

    }


}