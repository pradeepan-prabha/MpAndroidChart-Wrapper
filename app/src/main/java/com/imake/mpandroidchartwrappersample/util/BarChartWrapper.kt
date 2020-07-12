package com.imake.mpandroidchartwrappersample.util

import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.LegendEntry
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.formatter.LargeValueFormatter
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import java.util.*
import kotlin.collections.HashMap


class BarChartWrapper() {
    private var colors = ArrayList<Int>()

    fun barStackChart(
        barChartView: BarChart,
        xAxisValues: Array<String>,
        YAxisValuesLinkedHashMap: LinkedHashMap<String, LinkedHashMap<String, FloatArray>>,
        dataSetColors: ArrayList<Int>,
        chartTitle: String
    ): BarChart {
        addColorTemplate(dataSetColors)
        var (barData, labelHm) = createStackBarChartData(YAxisValuesLinkedHashMap)
        configureMultiBarChartAppearance(barChartView, chartTitle)
        // set bar label
        val legend = legend(barChartView)
        legendEntry(legend, labelHm)

        xAxisConfiguration(barChartView, xAxisValues)

        barData.setValueFormatter(LargeValueFormatter())
        barChartView.data = barData
//        barChartView.barData.barWidth = barWidth
        barChartView.xAxis.axisMinimum = 0f
        barChartView.xAxis.axisMaximum = 12f
        barChartView.data.isHighlightEnabled = true

        val groupSize = YAxisValuesLinkedHashMap.size
        val barSpace = 0.01f
        val barWidth = 0.9f / groupSize // x groupSize dataSet
        barChartView.barData.barWidth = barWidth
        val groupEvaluatedSpace = 1f - (barSpace + barWidth) * groupSize
        barChartView.groupBars(0f, groupEvaluatedSpace, barSpace)

        barChartView.data = barData
        return barChartView
    }

    private fun legendEntry(
        legend: Legend,
        labelHm: HashMap<String, Int>
    ) {
        //        Customize Bar Chart Legends using Legend Entry
        var legendEntries = arrayListOf<LegendEntry>()
        for ((key, value) in labelHm) {
            legendEntries.add(
                LegendEntry(
                    key,
                    Legend.LegendForm.SQUARE,
                    8f,
                    8f,
                    null,
                    value
                )
            )
        }
        legend.setCustom(legendEntries)
    }

    private fun createStackBarChartData(barDateSetHM: LinkedHashMap<String, LinkedHashMap<String, FloatArray>>): Pair<BarData, HashMap<String, Int>> {

        var barDataSetSize = barDateSetHM.size
        println("barDataSetSize = $barDataSetSize")
        var barDateSetKeyArray = barDateSetHM.keys.toTypedArray()
        var ySeriesFloatKeyArray = barDateSetHM[barDateSetKeyArray[0]]!!.keys.toTypedArray()
        var categorySeriesSize = barDateSetHM[barDateSetKeyArray[0]]?.keys!!.size
        println("categorySeriesSize = $categorySeriesSize")
        var ySeriesFloatArraySize =
            barDateSetHM[barDateSetKeyArray[0]]!![ySeriesFloatKeyArray[0]]!!.size
        println("ySeriesFloatArraySize = $ySeriesFloatArraySize")
        val dataSets: ArrayList<IBarDataSet> = ArrayList()
        val legendLabelHm: HashMap<String, Int> = HashMap()
        var label: String? = null
        for (barDataSetIndex in 0 until barDataSetSize) {
            val yEntry = ArrayList<BarEntry>()
            val labelHm: HashMap<String, Int> = HashMap()
            for (ySeriesIndex in 0 until ySeriesFloatArraySize) {
                val yBarEntryFloatArray = FloatArray(categorySeriesSize)
                println("**Init Size yBarEntryArray = $categorySeriesSize")
                var colorCount = 0
                for (categoryIndex in 0 until categorySeriesSize) {
                    var yBarDateSetValues = barDateSetHM[barDateSetKeyArray[barDataSetIndex]]
                    println("**yBarDateSetValues = ${yBarDateSetValues.toString()}")

                    var ySeriesValue =
                        yBarDateSetValues!![ySeriesFloatKeyArray[categoryIndex]]?.get(ySeriesIndex)
                    println("**yBarDateSetValues = ${ySeriesValue.toString()}")

                    label = ySeriesFloatKeyArray[categoryIndex]
                    println("label = $label")
                    labelHm[label] = colors[colorCount]
                    //Reset count and increment Count
                    colorCount = colorCountOperation(categoryIndex)

                    yBarEntryFloatArray[categoryIndex] = ySeriesValue!!
                    println("**Add yBarEntryFloatArray $categoryIndex = $ySeriesValue")
                    println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ Added BarEntryFloatArray $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$")
                }
                println("**Size yBarEntryArray = ${yBarEntryFloatArray.size}")

                yEntry.add(BarEntry(ySeriesIndex.toFloat(), yBarEntryFloatArray))
                println("**Added ArrayList<BarEntry> X= ${ySeriesIndex.toFloat()},Y= yBarEntryFloatArray ${yBarEntryFloatArray.toString()}")
                println("**************************Added*****BarEntry**********************************")
                //        Prepare Group Data

            }
            var dataSet = BarDataSet(yEntry, barDateSetKeyArray[barDataSetIndex])
            println("**Added label X= ${label},Y= ArrayList<BarEntry> ${yEntry}")
            println("**Size label ${labelHm.values.toList().size}")
            dataSet.colors = labelHm.values.toList()
            dataSet.label = barDateSetKeyArray[barDataSetIndex]
            legendLabelHm.putAll(labelHm)
            dataSet.setDrawIcons(false)

            println("**Added ArrayList<IBarDataSet> ${dataSet.toString()}")
            dataSets.add(dataSet)
            println("**Size ArrayList<IBarDataSet> ${dataSets.size}")
            println("##################Added###IBarDataSet###################################")
        }

        /*    // Define and Prepare Y-Axis (Stacked Data)
            val yValueGroup1 = ArrayList<BarEntry>()
            val yValueGroup2 = ArrayList<BarEntry>()

            // Draw the graph
            val barDataSet1: BarDataSet
            val barDataSet2: BarDataSet


            yValueGroup1.add(BarEntry(1f, floatArrayOf(9.toFloat(), 3.toFloat())))
            yValueGroup2.add(BarEntry(1f, floatArrayOf(2.toFloat(), 7.toFloat())))


            yValueGroup1.add(BarEntry(2f, floatArrayOf(3.toFloat(), 3.toFloat())))
            yValueGroup2.add(BarEntry(2f, floatArrayOf(4.toFloat(), 8.toFloat())))

            yValueGroup1.add(BarEntry(3f, floatArrayOf(3.toFloat(), 3.toFloat())))
            yValueGroup2.add(BarEntry(3f, floatArrayOf(4.toFloat(), 2.toFloat())))

            yValueGroup1.add(BarEntry(4f, floatArrayOf(3.toFloat(), 3.toFloat())))
            yValueGroup2.add(BarEntry(4f, floatArrayOf(4.toFloat(), 5.toFloat())))


            yValueGroup1.add(BarEntry(5f, floatArrayOf(9.toFloat(), 3.toFloat())))
            yValueGroup2.add(BarEntry(5f, floatArrayOf(10.toFloat(), 6.toFloat())))

            yValueGroup1.add(BarEntry(6f, floatArrayOf(11.toFloat(), 1.toFloat())))
            yValueGroup2.add(BarEntry(6f, floatArrayOf(12.toFloat(), 2.toFloat())))


            yValueGroup1.add(BarEntry(7f, floatArrayOf(11.toFloat(), 7.toFloat())))
            yValueGroup2.add(BarEntry(7f, floatArrayOf(12.toFloat(), 5.toFloat())))


            yValueGroup1.add(BarEntry(8f, floatArrayOf(11.toFloat(), 9.toFloat())))
            yValueGroup2.add(BarEntry(8f, floatArrayOf(12.toFloat(), 8.toFloat())))


            yValueGroup1.add(BarEntry(9f, floatArrayOf(11.toFloat(), 3.toFloat())))
            yValueGroup2.add(BarEntry(9f, floatArrayOf(12.toFloat(), 2.toFloat())))

            yValueGroup1.add(BarEntry(10f, floatArrayOf(11.toFloat(), 2.toFloat())))
            yValueGroup2.add(BarEntry(10f, floatArrayOf(12.toFloat(), 7.toFloat())))

            yValueGroup1.add(BarEntry(11f, floatArrayOf(11.toFloat(), 6.toFloat())))
            yValueGroup2.add(BarEntry(11f, floatArrayOf(12.toFloat(), 5.toFloat())))

            yValueGroup1.add(BarEntry(12f, floatArrayOf(11.toFloat(), 2.toFloat())))
            yValueGroup2.add(BarEntry(12f, floatArrayOf(12.toFloat(), 3.toFloat())))


            //        Prepare Group Data
            barDataSet1 = BarDataSet(yValueGroup1, "")
            barDataSet1.setColors(
                ColorTemplate.MATERIAL_COLORS[0],
                ColorTemplate.MATERIAL_COLORS[1]
            )

            barDataSet1.label = "2016"
            barDataSet1.setDrawIcons(false)

            barDataSet2 = BarDataSet(yValueGroup2, "")
            barDataSet2.label = "2017"
            barDataSet2.setColors(
                ColorTemplate.MATERIAL_COLORS[0],
                ColorTemplate.MATERIAL_COLORS[1]
            )
            barDataSet2.setDrawIcons(false)*/
        return Pair(BarData(dataSets), legendLabelHm)
    }

    fun barChart(
        barChart: BarChart,
        xAxisVal: Array<String>,
        yAxisVal: LinkedHashMap<String, FloatArray>,
        dataSetColors: ArrayList<Int>,
        chatTitle: String
    ): BarChart {
        addColorTemplate(dataSetColors)
        barChart.setPinchZoom(true)
        barChart.description.text = chatTitle

        val data = createChartData(yAxisVal)
        xAxisConfigSingleBar(barChart, xAxisVal)

        barChart.data = data
        barChart.setFitBars(true) // make the x-axis fit exactly all bars
        legend(barChart)
        return barChart
    }

    private fun xAxisConfigSingleBar(
        barChart: BarChart,
        xAxisVal: Array<String>
    ) {
        val xAxis = barChart.xAxis
        xAxis.granularity = 1f
        xAxis.isGranularityEnabled = true
        xAxis.textSize = 9f
        xAxis.labelCount = 12
        if (xAxisVal.size > 12)
            xAxis.labelCount = xAxisVal.size / 3
        xAxis.mAxisMaximum = 12f
        xAxis.position = XAxis.XAxisPosition.TOP
        xAxis.valueFormatter = IndexAxisValueFormatter(xAxisVal)
    }

    fun multiBarChart(
        multiBarChart: BarChart,
        xAxisVal: Array<String>,
        yAxisVal: LinkedHashMap<String, FloatArray>,
        dataSetColors: ArrayList<Int>,
        chatTitle: String
    ): BarChart {

        addColorTemplate(dataSetColors)
        val data = createChartData(yAxisVal)
        val multiBarChartConfigured = configureMultiBarChartAppearance(multiBarChart, chatTitle)

        return prepareChartData(
            data!!,
            xAxisVal,
            multiBarChartConfigured, yAxisVal
        )
    }

    private fun addColorTemplate(colors: ArrayList<Int>) {
        if (this.colors.size == 0) {
            this.colors=colors
        }
    }

    private fun configureMultiBarChartAppearance(
        chart: BarChart,
        chatTitle: String
    ): BarChart {
        chart.setPinchZoom(true)
        chart.description.text = chatTitle

        val xAxis = chart.xAxis
        xAxis.granularity = 1f
        xAxis.setCenterAxisLabels(true)

        val leftAxis = chart.axisLeft
//        leftAxis.setDrawGridLines(false)
        leftAxis.spaceTop = 25f
        leftAxis.axisMinimum = 0f

        chart.axisRight.isEnabled = false

        chart.xAxis.axisMinimum = 0f
        chart.xAxis.axisMaximum = 7f
        return chart
    }

    private fun createChartData(
        yValue: LinkedHashMap<String, FloatArray>
    ): BarData? {
        var colorCount = 0
        val dataSets: ArrayList<IBarDataSet> = ArrayList()
        for (yLabelKey in yValue.keys) {
            val yEntry = ArrayList<BarEntry>()
            val floatyValue: FloatArray? = yValue[yLabelKey]
            if (floatyValue != null && floatyValue.isNotEmpty()) {
                for (j in floatyValue.indices) {
                    yEntry.add(BarEntry(j.toFloat(), floatyValue[j]))
                }
                val set1 = BarDataSet(yEntry, yLabelKey)
                set1.setColors(colors[colorCount])
                //Reset count and increment Count
                colorCount = colorCountOperation(colorCount)
                dataSets.add(set1)
            }
        }
        return BarData(dataSets)
    }

    private fun colorCountOperation(colorCount: Int): Int {
        var colorCount1 = colorCount
        if (colorCount1 == colors.size) {
            colorCount1 = 0
        } else {
            colorCount1++
        }
        return colorCount1
    }

    private fun prepareChartData(
        data: BarData,
        xAxisValues: Array<String>,
        chart: BarChart,
        yAxisVal: LinkedHashMap<String, FloatArray>
    ): BarChart {
        val groupSize = yAxisVal.size
        val barSpace = 0.01f
        val barWidth = 0.9f / groupSize // x groupSize dataSet

        chart.data = data
        xAxisConfiguration(chart, xAxisValues)

        chart.barData.barWidth = barWidth
        val groupEvaluatedSpace = 1f - (barSpace + barWidth) * groupSize
        chart.groupBars(0f, groupEvaluatedSpace, barSpace)
        legend(chart)
        return chart
    }

    private fun xAxisConfiguration(
        chart: BarChart,
        xAxisValues: Array<String>
    ) {
        val xAxis = chart.xAxis
        xAxis.position = XAxis.XAxisPosition.TOP
        xAxis.valueFormatter = IndexAxisValueFormatter(xAxisValues)
        xAxis.granularity = 1f
        xAxis.isGranularityEnabled = true
        xAxis.setCenterAxisLabels(true)
        xAxis.textSize = 9f
        xAxis.labelCount = 12
        xAxis.mAxisMaximum = 12f
        xAxis.setAvoidFirstLastClipping(true)
        xAxis.spaceMin = 4f
        xAxis.spaceMax = 4f
    }

    private fun legend(barChart: BarChart): Legend {
        val legend = barChart.legend
        legend.isEnabled = true
        legend.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
        legend.form = Legend.LegendForm.SQUARE
        legend.formSize = 9.0f
        legend.textSize = 11.0f
        legend.xEntrySpace = 5.0f
        barChart.animateX(2000, Easing.EaseInOutQuart)
        barChart.animateY(2500, Easing.EaseInOutQuart)
        return legend
    }
}