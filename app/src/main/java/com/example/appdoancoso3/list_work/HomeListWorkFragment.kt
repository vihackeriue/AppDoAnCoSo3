package com.example.appdoancoso3.list_work

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.appdoancoso3.R
import com.example.appdoancoso3.adapter.HomeListWorkAdapter
import com.example.appdoancoso3.database.MWorkDBHelper
import com.example.appdoancoso3.databinding.FragmentHomelistworkBinding
import com.example.appdoancoso3.model.DetailWorkModel
import com.example.appdoancoso3.model.WorkDetailViewModel
import java.util.*


class HomeListWorkFragment : Fragment(), PopupMenu.OnMenuItemClickListener {

    private lateinit var binding: FragmentHomelistworkBinding

    private lateinit var helper: MWorkDBHelper
    private lateinit var adapter: HomeListWorkAdapter
    private lateinit var list: ArrayList<DetailWorkModel>

    private lateinit var workDetailViewModel: WorkDetailViewModel


    var selectedIDDetailWork: Int = 0
     var pos: Int = 0
//    private lateinit var db: SQLiteDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        list = arrayListOf<DetailWorkModel>()
        binding = FragmentHomelistworkBinding.inflate(inflater, container, false)

        StartActivity()

        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }
   @SuppressLint("MissingInflatedId")
   fun StartActivity(){
       helper= MWorkDBHelper(requireContext())

       showDataWorkDetail()
           val workNow = helper.getDataWorkNow()
                for (item in workNow){
                  binding.titleCardView.text = item.Title
                  binding.dateCreatedCardView.text = item.day_created
                }

           binding.addDetailwork.setOnClickListener {
               if(helper.getIDWorksNow() != -1){
               val intent = Intent(activity, AddListWorkActivity::class.java)
               startActivity(intent)
               }else{
                   Toast.makeText(context, "Bạn Chưa tạo phần công việc cho hôm nay!", Toast.LENGTH_SHORT).show()
               }

           }




       binding.addWork.setOnClickListener {
           openDialogAddWorks()
       }
   }

    override fun onStart() {
        super.onStart()
        Log.wtf("start", "bắt đầu lại")
        StartActivity()


//        workDetailViewModel = ViewModelProvider(this).get(WorkDetailViewModel::class.java)
////        workDetailViewModel = ViewModelProviders.of(requireActivity()).get(WorkDetailViewModel::class.java)
//
//
//        workDetailViewModel.getMyLiveData().observe(viewLifecycleOwner){
//                listDetailWorks ->
//            Log.wtf("arrr", listDetailWorks[0].title)
//            adapter.updateList(listDetailWorks)
//
//        }


    }

    private fun openDialogAddWorks() {
        val mDialog = AlertDialog.Builder(requireContext())
        val inflater = layoutInflater
        val mDialogView = inflater.inflate(R.layout.addworks_dialog, null)
        val date_now = mDialogView.findViewById<TextView>(R.id.date_Work_dialogAddWork)
        val btnAddWorks = mDialogView.findViewById<Button>(R.id.add_work_dialogAddWork)
        val nameDialogAddWorks = mDialogView.findViewById<EditText>(R.id.name_Work_dialogAddWork)
//        Thêm ID User Tại Đây
        val idUser = 1

        date_now.text =getDateNow()
        mDialog.setView(mDialogView)
        mDialog.setTitle("Tạo Công Việc Hôm Nay")
        val alertDialog = mDialog.create()
        alertDialog.show()

        btnAddWorks.setOnClickListener{
            AddDataWorks(nameDialogAddWorks.text.toString(),date_now.text.toString(), idUser)
            alertDialog.dismiss()

        }

    }

    private fun AddDataWorks(name: String, date: String, idUser: Int) {
        helper.insertDataWorks(name,date,idUser)
        binding.titleCardView.text = name
        binding.dateCreatedCardView.text = date
        binding.txtLoadingData.visibility = View.GONE
        binding.addWork.visibility = View.GONE
    }


    fun getDateNow(): String {
        val calendar: Calendar = Calendar.getInstance()

        val year = calendar[Calendar.YEAR]
        val month = calendar[Calendar.MONTH] + 1 // Vì tháng bắt đầu từ 0 nên phải cộng thêm 1

        val day = calendar[Calendar.DAY_OF_MONTH]
        val currentDate = "$day/$month/$year"
        return currentDate
    }

    private fun showDataWorkDetail() {


//        helper.createAccount("11","11","11")
//        helper.insertDataWorks("11","11",1)
//        helper.insertDataWorkDetail("111","11","111","11","2",0,1)
        list = helper.getDataWorkDetail()
        if(helper.getIDWorksNow() != -1){
            binding.addWork.visibility = View.GONE
            binding.txtLoadingData.visibility = View.GONE
        }
//       list.add(DetailWorkModel(1,"1","11","1",1,1))
        adapter = HomeListWorkAdapter(list, object: HomeListWorkAdapter.AddWorkClickListener{
            override fun onItemClick(position: Int) {
                val intent = Intent(activity, WorkDetailActivity::class.java)
                startActivity(intent)

            }

            override fun onItemLongClick(position: Int, cardView: CardView) {
                selectedIDDetailWork = list[position].ID
                popUpDisplayDetailWorks(cardView)
                pos = position
            }

            override fun onItemLongClick(
                p0: AdapterView<*>?,
                p1: View?,
                p2: Int,
                p3: Long
            ): Boolean {
                TODO("Not yet implemented")
            }

        })


        binding.recyclerViewListwork.adapter = adapter
        binding.recyclerViewListwork.layoutManager = GridLayoutManager(requireContext(), 1)
        binding.recyclerViewListwork.setHasFixedSize(true)



    }
    private fun popUpDisplayDetailWorks(cardView: CardView) {
        val popup = PopupMenu(context, cardView)
        popup.setOnMenuItemClickListener (this@HomeListWorkFragment)
        popup.inflate(R.menu.menu_listwork_detail)
        popup.show()
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        if(item?.itemId == R.id.update_detail_menu){
//

            return true
        }
        if(item?.itemId == R.id.delete_detail_menu){
//
            AlertDialog.Builder(requireContext())
                .setMessage("Bạn có chắc chắn muốn xóa công việc này?")
                .setPositiveButton("Có") { _, _ ->
                    val check = helper.deleteData(selectedIDDetailWork, "DetailWorks")
                    if (check > 0) {
                        Log.wtf("aaaa", "đã xóa")
                        list.removeAt(pos)
                        adapter.notifyDataSetChanged()
                    } else {
                        // Không tìm thấy bản ghi cần xóa
                    }

                }
                .setNegativeButton("Không", null)
                .show()
                return true
        }

        return false
    }


}