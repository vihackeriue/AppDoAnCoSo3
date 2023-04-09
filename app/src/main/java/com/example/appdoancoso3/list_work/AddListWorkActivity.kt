package com.example.appdoancoso3.list_work

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.Display
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.GridLayoutManager
import com.example.appdoancoso3.ListWorkActivity
import com.example.appdoancoso3.R
import com.example.appdoancoso3.adapter.AddWorkDetailAdapter
import com.example.appdoancoso3.database.MWorkDBHelper
import com.example.appdoancoso3.databinding.ActivityAddListWorkBinding
import com.example.appdoancoso3.databinding.ItemAddWorkListworkBinding
import com.example.appdoancoso3.model.DetailWorkModel
import java.time.LocalTime
import java.util.Calendar

class AddListWorkActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddListWorkBinding
    private lateinit var list: ArrayList<DetailWorkModel>
    private lateinit var helper: MWorkDBHelper
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddListWorkBinding.inflate(layoutInflater)
        setContentView(binding.root)
//      nút quay lại
        binding.imgBack.setOnClickListener {
            intent = Intent(this, ListWorkActivity::class.java)
            startActivity(intent)

        }
//        Nút chọn thời gian
        binding.chooseTime.setOnClickListener {
            showTimePickerDialog()

        }
        list = arrayListOf<DetailWorkModel>()
//        list.add(DetailWorkModel(0, "", "","",0, 0))
        val mAdapter = AddWorkDetailAdapter(list, object:AddWorkDetailAdapter.AddDetailWorkInterface{
            override fun onChooseItem(position: Int) {
            }
        })
        binding.recycleAddListWork.adapter = mAdapter
        binding.recycleAddListWork.layoutManager = GridLayoutManager(this, 1)
        binding.addListWork.setOnClickListener {
            addNewDetailWork(mAdapter)
        }

//        thêm vào csdl
        helper= MWorkDBHelper(applicationContext)
        binding.imgChecked.setOnClickListener {
            if(list.size <= 0){
                Toast.makeText(this, "Không có một công việc nào được thêm vào", Toast.LENGTH_SHORT).show()

            }else{
                addDataDetailWork()
            }
            finish()

        }
    }

    private fun addDataDetailWork() {
        for (item in list){
            helper.insertDataWorkDetail(item.title.toString(),item.content.toString(),item.date_created.toString(),item.time_hours.toString(), item.time_minute.toString(), 0, 1)
        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun addNewDetailWork(mAdapter: AddWorkDetailAdapter) {
        val currentLocalTime = LocalTime.now()
        val hour_current = currentLocalTime.hour
        val minute_current = currentLocalTime.minute

        var title = binding.titleWorkListwork.text.toString()
        var date_created = hour_current.toString() + ":"+ minute_current.toString()
        var time_hours = binding.countHour.text.toString()
        var time_minutes = binding.countMinute.text.toString()
        var content = binding.contentWorkListwork.text.toString()
        if(title.equals("")||time_hours.equals("")||time_minutes.equals("")||content.equals("")){
            Toast.makeText(this, "Bạn cần nhập đầy đủ các thông tin!", Toast.LENGTH_SHORT).show()
        }else{
//            thêm vào list
            list.add(DetailWorkModel(0, title, date_created,time_hours,time_minutes, content,0,1))
            mAdapter.notifyDataSetChanged();
        }
        binding.titleWorkListwork.setText("")
        binding.countHour.setText("00")
        binding.countMinute.setText("00")
        binding.contentWorkListwork.setText("")
    }

    fun showTimePickerDialog(){
            val c = Calendar.getInstance()
            val hour = c.get(Calendar.HOUR_OF_DAY)
            val minute = c.get(Calendar.MINUTE)

            // Create a new instance of TimePickerDialog
            val timePickerDialog = TimePickerDialog(this,
                TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                        val calendar = Calendar.getInstance()
                        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
                        calendar.set(Calendar.MINUTE, minute)
                        calendar.set(Calendar.SECOND, 0)
                    binding.countHour.text = hourOfDay.toString()
                    binding.countMinute.text = minute.toString()
                }, hour, minute, false)
            timePickerDialog.show()

    }


}