package com.example.appdoancoso3.list_work

import android.app.Activity
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.appdoancoso3.R
import com.example.appdoancoso3.adapter.AddWorkDetailAdapter
import com.example.appdoancoso3.database.MWorkDBHelper
import com.example.appdoancoso3.databinding.ActivityAddListWorkBinding
import com.example.appdoancoso3.model.DetailWorkModel
import com.example.appdoancoso3.model.WorkDetailViewModel
import java.time.LocalTime
import java.util.Calendar

class AddListWorkActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddListWorkBinding
    private lateinit var list: ArrayList<DetailWorkModel>
    private lateinit var helper: MWorkDBHelper

    private lateinit var workDetailViewModel: WorkDetailViewModel
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityAddListWorkBinding.inflate(layoutInflater)
        setContentView(binding.root)
//      nút quay lại
        binding.imgBack.setOnClickListener {
            onBackPressed()
//            intent = Intent(this, ListWorkActivity::class.java)
//            startActivity(intent)

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
        binding.recycleAddListWork.layoutManager = GridLayoutManager(this, 2)
        binding.addListWork.setOnClickListener {
            addNewDetailWork(mAdapter)
        }
//        thêm vào csdl
        helper= MWorkDBHelper(applicationContext)
        binding.imgChecked.setOnClickListener {
            if(list.size <= 0){
                if(binding.titleWorkListwork.text.equals("") || binding.contentWorkListwork.text.equals("")
                    || binding.countHour.text.equals("00") || binding.countMinute.text.equals("00")
                        ){
                    Toast.makeText(this, "Không có một công việc nào được thêm vào", Toast.LENGTH_SHORT).show()
                }else
                {

                    helper.insertDataWorkDetail(binding.titleWorkListwork.text.toString(),
                                                binding.contentWorkListwork.text.toString(),
                                                Day_Current(),
                                                binding.countHour.text.toString(),
                                                binding.countMinute.text.toString(),
                                            0,
                                            helper.getIDWorksNow())
                }
            }else{
                if(binding.titleWorkListwork.text.equals("") || binding.contentWorkListwork.text.equals("")
                    || binding.countHour.text.equals("00") || binding.countMinute.text.equals("00")
                ){

                }else {
                    helper.insertDataWorkDetail(binding.titleWorkListwork.text.toString(),
                        binding.contentWorkListwork.text.toString(),
                        Day_Current(),
                        binding.countHour.text.toString(),
                        binding.countMinute.text.toString(),
                        0,
                        helper.getIDWorksNow())


                }

                addDataDetailWork()

            }

//
//            val myFragment = HomeListWorkFragment()
//
//
//            val fragmentManager = supportFragmentManager
//            val fragmentTransaction = fragmentManager.beginTransaction()
//            fragmentTransaction.detach(myFragment)
//            fragmentTransaction.attach(myFragment)
//            fragmentTransaction.commit()



            finish()


        }
    }
    private fun addDataDetailWork() {
        for (item in list){
            helper.insertDataWorkDetail(item.title.toString(),item.content.toString(),item.date_created.toString(),item.time_hours.toString(), item.time_minute.toString(), 0, item.IDWorks!!.toInt())
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun addNewDetailWork(mAdapter: AddWorkDetailAdapter) {


        var title = binding.titleWorkListwork.text.toString()
        var date_created = Day_Current()
        var time_hours = binding.countHour.text.toString()
        var time_minutes = binding.countMinute.text.toString()
        var content = binding.contentWorkListwork.text.toString()
        if(title.equals("")||time_hours.equals("")||time_minutes.equals("")||content.equals("")){
            Toast.makeText(this, "Bạn cần nhập đầy đủ các thông tin!", Toast.LENGTH_SHORT).show()
        }else{
//            thêm vào list
            list.add(DetailWorkModel(0, title, date_created,time_hours,time_minutes, content,0,helper.getIDWorksNow()))
            mAdapter.notifyDataSetChanged();
        }
        binding.titleWorkListwork.setText("")
        binding.countHour.setText("00")
        binding.countMinute.setText("00")
        binding.contentWorkListwork.setText("")
    }
    @RequiresApi(Build.VERSION_CODES.O)
    private fun Day_Current(): String? {
        val currentLocalTime = LocalTime.now()
        val hour_current = currentLocalTime.hour
        val minute_current = currentLocalTime.minute
        return hour_current.toString() + ":"+ minute_current.toString()
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