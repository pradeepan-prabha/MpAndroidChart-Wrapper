package com.imake.mpandroidchartwrappersample


import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.charts.BarChart
import com.imake.mpandroidchartwrappersample.util.BarChartWrapper
import com.imake.mpandroidchartwrappersample.util.LineChartWrapper
import com.imake.mpandroidchartwrappersample.util.PieWrapper
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {
    var xAxisVal = arrayOf(
        "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
    )
    var yAxisVal =
        floatArrayOf(1.5f, 5.4f, 4f, 8f, 4.3f, 5.1f, 7.6f, 6.7f, 8.9f, 4.8f, 12.1f, 3.5f)
    var yAxisVal2 =
        floatArrayOf(10f, 11f, 13f, 12f, 14f, 9f, 4f, 2f, 8f, 25f, 13f, 2f)
    var yAxisVal3 = floatArrayOf(2f, 1f, 4f, 5f, 7f, 9f, 4f, 5f, 7f, 6f, 7f, 5f)
    var yAxisValSize2 =
        floatArrayOf(1.5f, 5.4f, 4f, 8f, 4.3f, 5.1f, 7.6f, 6.7f, 8.9f, 4.8f, 12.1f, 3.5f)
    var yAxisValSize1 =
        floatArrayOf(2f, 1f, 4f, 5f, 7f, 9f, 4f, 5f, 7f, 6f, 7f, 5f)
    var yAxisValSize3 =
        floatArrayOf(10f, 2f, 4f, 5f, 7f, 9f, 4f, 2f, 7f, 12f, 14f, 1f)
    var stickHigh =
        floatArrayOf(10f, 2f, 4f, 5f, 7f, 9f, 4f, 2f, 7f, 12f, 14f, 1f)
    var stickLow = floatArrayOf(2f, 1f, 4f, 5f, 7f, 9f, 4f, 5f, 7f, 6f, 7f, 5f)
    var candleOpen =
        floatArrayOf(1.5f, 5.4f, 4f, 8f, 4.3f, 5.1f, 7.6f, 6.7f, 8.9f, 4.8f, 12.1f, 3.5f)
    var candleClose =
        floatArrayOf(2f, 1f, 4f, 5f, 7f, 9f, 4f, 5f, 7f, 6f, 7f, 5f)
    var yAxisValName: String = ("Units")
    var yAxisSeriesName: String = ("Series")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        barChartComponentInit()
        stackBarChartComponentInit()

        pieChartComponentInit()

        multiLineChartComponentInit()

        multiBarChartComponentInit()
    }

    private fun barChartComponentInit() {
        /*SingleBarChart*/

        val linkedHashMap = LinkedHashMap<String, FloatArray>()
        linkedHashMap[yAxisValName + 1] = yAxisVal //String, float[]

        val singleBarChart = BarChartWrapper().barChart(barChart, xAxisVal, linkedHashMap, "")
        singleBarChart.invalidate()
    }

    private fun multiBarChartComponentInit() {
        /*MultiBarChart*/

        val linkedHashMap = LinkedHashMap<String, FloatArray>()
        linkedHashMap[yAxisValName + 1] = yAxisVal //String, float[]
        linkedHashMap[yAxisValName + 2] = yAxisVal2 //String, float[]
        linkedHashMap[yAxisValName + 3] = yAxisVal3 //String, float[]

        val multiBarChart = BarChartWrapper().multiBarChart(multiBarChart, xAxisVal, linkedHashMap, "")
        multiBarChart.invalidate()
    }

    private fun stackBarChartComponentInit() {
        /*StackBarChart*/

        val barChartView1 = findViewById<BarChart>(R.id.stackBarChat)

        val linkedHashMap = LinkedHashMap<String, LinkedHashMap<String, FloatArray>>()

        val hashMap1 = LinkedHashMap<String, FloatArray>()
        hashMap1[yAxisValName + 1] = yAxisValSize1 //String, float[]
        hashMap1[yAxisValName + 2] = yAxisVal2 //String, float[]
        hashMap1[yAxisValName + 3] = yAxisValSize1 //String, float[]
        linkedHashMap["Category1"] = hashMap1

        val hashMap2 = LinkedHashMap<String, FloatArray>()
        hashMap2[yAxisValName + 1] = yAxisVal2 //String, float[]
        hashMap2[yAxisValName + 2] = yAxisValSize3 //String, float[]
        hashMap2[yAxisValName + 3] = yAxisValSize1 //String, float[]
        linkedHashMap["Category2"] = hashMap2

        val hashMap3 = LinkedHashMap<String, FloatArray>()
        hashMap3[yAxisValName + 1] = yAxisVal2 //String, float[]
        hashMap3[yAxisValName + 2] = yAxisValSize3 //String, float[]
        hashMap3[yAxisValName + 3] = yAxisValSize1 //String, float[]
        linkedHashMap["Category3"] = hashMap3

        val barStackChart=BarChartWrapper().barStackChart(barChartView1, xAxisVal, linkedHashMap, "")
        barStackChart.invalidate()

    }

    private fun pieChartComponentInit() {
        /*Pie Chart*/

        // LargeValueFormatter "1.000" into "1k"
        // PercentFormatter  50 -> 50.0 %
        val yAxisValFormat = "PercentFormatter"

        val outSideSlice = true //Y axis value highlighted in OutSideSlice

        val pieChart = PieWrapper().pieChart(pieChart, xAxisVal, yAxisVal, "Month",
            yAxisValFormat, "", outSideSlice)
        pieChart!!.invalidate()
    }

    private fun multiLineChartComponentInit() {
        /*MultiLineChart*/
        val mulLine = LineChartWrapper()
        mulLineChart.visibility = View.VISIBLE
        val linkedHashMap = LinkedHashMap<String, FloatArray>()
        linkedHashMap[yAxisSeriesName + 1] = yAxisVal //String, float[]

        linkedHashMap[yAxisSeriesName + 2] = yAxisVal2 //String, float[]

        linkedHashMap[yAxisSeriesName + 3] = yAxisVal3 //String, float[]

        val mulLineChart = mulLine.multiLineChart(mulLineChart, xAxisVal, linkedHashMap, "")
        mulLineChart!!.invalidate()
    }
}

