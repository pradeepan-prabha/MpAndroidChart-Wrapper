package com.imake.mpandroidchartwrapper

import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.Legend.LegendForm
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.XAxis.XAxisPosition
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.github.mikephil.charting.utils.ColorTemplate
import java.util.*


class LineChartWrapper() {

    var colors = ArrayList<Int>()

    fun multiLineChart(
        mChart: LineChart,
        xAxisVal: Array<String>,
        yValues: LinkedHashMap<String, FloatArray>,
        graphTitle: String?
    ): LineChart? {
        var mChart = mChart
        run {
            for (c in ColorTemplate.COLORFUL_COLORS) colors.add(c)
            for (c in ColorTemplate.JOYFUL_COLORS) colors.add(c)
            for (c in ColorTemplate.LIBERTY_COLORS) colors.add(c)
            for (c in ColorTemplate.PASTEL_COLORS) colors.add(c)
        }
        run {
            mChart.description.isEnabled = true
            mChart.description.text = graphTitle
            mChart.setNoDataText("No chart data available.")
            mChart.setTouchEnabled(true)
            mChart.isDragEnabled = true
            mChart.setScaleEnabled(true)
            mChart.setPinchZoom(true)
        }
        run {
            xAxisConfiguration(mChart, xAxisVal)

        }
        run {

            // Controlling left side of y axis
            val leftAxis = mChart.axisLeft
            leftAxis.removeAllLimitLines()
            leftAxis.textSize = 8.0f
            leftAxis.textColor = -16777216
            leftAxis.spaceTop = 15.0f

/*            // the labels that should be drawn on the XAxis
            val formatter: ValueFormatter =
                object : ValueFormatter() {
                    override fun getAxisLabel(value: Float, axis: AxisBase): String {
                        return xAxisVal[value.toInt()]
                    }
                }
            val xAxis: XAxis = mChart.xAxis
            xAxis.granularity = 1f // minimum axis-step (interval) is 1

            xAxis.valueFormatter = formatter*/
        }
        mChart.axisRight.isEnabled = false
        mChart.data = linerData(yValues)
        mChart = legend(mChart)
        return mChart
    }

    private fun legend(lineChart: LineChart): LineChart {
        val legend = lineChart.legend
        legend.isEnabled = true
        legend.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
        legend.form = LegendForm.SQUARE
        legend.formSize = 9.0f
        legend.textSize = 11.0f
        legend.xEntrySpace = 5.0f
        lineChart.animateX(2000, Easing.EaseInOutQuart)
        lineChart.animateY(2500, Easing.EaseInOutQuart)
        return lineChart
    }

    private fun linerData(yValue: LinkedHashMap<String, FloatArray>): LineData {
        val dataSets: MutableList<ILineDataSet> =
            ArrayList()
        val keys: Set<String> = yValue.keys
        var colorCount = 0
        for (yLabelKey in keys) {
            val yEntry = ArrayList<Entry>()
            val floatyValue = yValue[yLabelKey]
            if (floatyValue != null) {
                for (j in floatyValue.indices) {
                    yEntry.add(BarEntry(j.toFloat(), floatyValue[j]))
                }
                val data1 = LineDataSet(yEntry, yLabelKey)
                data1.lineWidth = 2.5f
                data1.color = colors[colorCount]
                data1.setCircleColor(colors[colorCount])
                data1.circleHoleColor = colors[colorCount]
                //Reset count and increment Count
                colorCount = colorCountOperation(colorCount)
                dataSets.add(data1)
            }
        }
        return LineData(dataSets)
    }
    private fun xAxisConfiguration(
        chart: LineChart,
        xAxisValues: Array<String>
    ) {
        val xAxis = chart.xAxis
        xAxis.position = XAxis.XAxisPosition.TOP
        xAxis.valueFormatter = IndexAxisValueFormatter(xAxisValues)
        xAxis.granularity = 1f
        xAxis.isGranularityEnabled = true
        xAxis.textSize = 9f
        xAxis.labelCount = 12
        xAxis.mAxisMaximum = 12f
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
}