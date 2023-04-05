package com.example.appdoancoso3.list_work

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import com.example.appdoancoso3.R
import com.example.appdoancoso3.databinding.FragmentStatisticalListWorkBinding

import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.*


class StatisticalListWorkFragment : Fragment() {


    private lateinit var binding: FragmentStatisticalListWorkBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStatisticalListWorkBinding.inflate(inflater, container, false)



        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createPieChart()
    }

    private fun createPieChart() {
        // Khởi tạo Pie Chart
        val pieChart = binding.pieChart
        pieChart.setUsePercentValues(true)
        pieChart.description.isEnabled = false
        pieChart.legend.isEnabled = false

        // Thêm dữ liệu cho Pie Chart
        val entries = listOf(
            PieEntry(50f, "Item 1"),
            PieEntry(30f, "Item 2"),
            PieEntry(20f, "Item 3")
        )
        val dataSet = PieDataSet(entries, "My Data Set")
        dataSet.setColors(Color.RED, Color.BLUE, Color.GREEN)
        dataSet.valueTextColor = Color.WHITE
        dataSet.valueTextSize = 12f

        // Tạo đối tượng PieData từ dataSet và set vào Pie Chart
        val pieData = PieData(dataSet)
        pieChart.data = pieData
        pieChart.invalidate()
    }
}