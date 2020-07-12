package com.imake.mpandroidchartwrapper

import android.graphics.Color
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.LargeValueFormatter
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import java.util.*

class PieWrapper {

    private var colors = ArrayList<Int>()

    fun pieChart(
        chart: PieChart,
        xAxisVal: Array<String>,
        yAxisVal: FloatArray,
        colors: ArrayList<Int>,
        yAxisName: String,
        yAxisValFormat: String?,
        graphTitle: String?,
        outSideSlice: Boolean?
    ): PieChart? {
        //Add Colors for Data sets
        this.colors=colors

        chart.setUsePercentValues(true)
        chart.description.isEnabled = false
        chart.centerText = graphTitle;
        chart.setExtraOffsets(5f, 10f, 5f, 5f)
        chart.dragDecelerationFrictionCoef = 0.95f
        chart.setExtraOffsets(20f, 0f, 20f, 0f)
        chart.isDrawHoleEnabled = true
        chart.setHoleColor(Color.WHITE)
        chart.setTransparentCircleColor(Color.WHITE)
        chart.setTransparentCircleAlpha(110)
        chart.holeRadius = 58f
        chart.transparentCircleRadius = 61f
        chart.setDrawCenterText(true)
        chart.rotationAngle = 0f

        // enable rotation of the chart by touch
        chart.isRotationEnabled = true
        chart.isHighlightPerTapEnabled = true
        chart.animateY(1400, Easing.EaseInOutQuad)

        val legend = chart.legend
        legend.isEnabled = true
        legend.verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
        legend.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
        legend.orientation = Legend.LegendOrientation.HORIZONTAL
        legend.setDrawInside(false)
        legend.form = Legend.LegendForm.CIRCLE
        legend.formSize = 9f
        legend.textSize = 11f
        legend.isWordWrapEnabled = true
        legend.xEntrySpace = 10f

        chart.animateX(2000, Easing.EaseInOutQuart)
        chart.animateY(2500, Easing.EaseInOutQuart)
        chart.data = pieData(xAxisVal, yAxisVal, yAxisName, yAxisValFormat, outSideSlice, chart)
        return chart
    }

    private fun pieData(
        xAxisVals: Array<String>,
        yAxisVals: FloatArray,
        yAxisName: String,
        yAxisValFormat: String?,
        outSideSlice: Boolean?,
        chart: PieChart
    ): PieData {
        val yVals1 = ArrayList<PieEntry>()

        // IMPORTANT: In a PieChart, no values (Entry) should have the same
        // xIndex (even if from different DataSets), since no values can be
        // drawn above each other.
        for (i in yAxisVals.indices) {
            yVals1.add(PieEntry(yAxisVals[i], xAxisVals[i]))
        }
        val dataSet = PieDataSet(yVals1, "")
        dataSet.sliceSpace = 3f
        dataSet.selectionShift = 5f


        dataSet.colors = colors
        if (outSideSlice != null && outSideSlice) {
            dataSet.yValuePosition = PieDataSet.ValuePosition.OUTSIDE_SLICE
        } else {
            dataSet.yValuePosition = PieDataSet.ValuePosition.INSIDE_SLICE
        }
        dataSet.valueLinePart1OffsetPercentage = 80f
        dataSet.valueLinePart1Length = 0.2f
        dataSet.valueLinePart2Length = 0.4f
        val data = PieData(dataSet)
        if (yAxisValFormat != null && yAxisValFormat.equals(
                "PercentFormatter",
                ignoreCase = true
            )
        ) {
            println("***********PercentFormatter")
            data.setValueFormatter(PercentFormatter(chart))
            chart.setUsePercentValues(true)
        } else if (yAxisValFormat != null && yAxisValFormat.equals(
                "LargeValueFormatter",
                ignoreCase = true
            )
        ) {
            data.setValueFormatter(LargeValueFormatter())
        }
        data.setValueTextSize(11f)
        data.setValueTextColor(Color.BLACK)
        return data
    }
}