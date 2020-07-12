# MpAndroidChart Wrapper
MpAndroidChart Wrapper will help you easy to display data into visualization of prepared data, Supporting Wrapper Charts are 
Single bar, Group bar, Single stacked bar, Group stacked bar, Single line, Group line, Pie chart.  

## Example Screenshots

### Wi-Fi Manager Wrapper Demo  
<p align="center">
<img src="/sample_screenshot.jpg" alt="Sample Screenshot" width="50%" height="50%">
</p>

## Dependency

Add this in your root `build.gradle` file (**not** your module `build.gradle` file):

```gradle
allprojects {
    repositories {
        maven { url "https://jitpack.io" }
    }
}
```

Then, add the library to your module `build.gradle`
```gradle
dependencies {

        implementation 'com.github.PhilJay:MPAndroidChart:v3.1.0'
        implementation 'com.github.pradeepan-prabha:MpAndroidChart-Wrapper:v0.0.4'
}
```

## Sample Data
```
     var xAxisVal = arrayOf(
        "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
    )
     var yAxisVal =
        floatArrayOf(1.5f, 5.4f, 4f, 8f, 4.3f, 5.1f, 7.6f, 6.7f, 8.9f, 4.8f, 12.1f, 3.5f)
     var yAxisVal2 =
        floatArrayOf(10f, 11f, 13f, 12f, 14f, 9f, 4f, 2f, 8f, 25f, 13f, 2f)
     var yAxisVal3 = floatArrayOf(2f, 1f, 4f, 5f, 7f, 9f, 4f, 5f, 7f, 6f, 7f, 5f)
     var yAxisValSize4 =
        floatArrayOf(1.5f, 5.4f, 4f, 8f, 4.3f, 5.1f, 7.6f, 6.7f, 8.9f, 4.8f, 12.1f, 3.5f)
     var yAxisValSize5 =
        floatArrayOf(2f, 1f, 4f, 5f, 7f, 9f, 4f, 5f, 7f, 6f, 7f, 5f)
     var yAxisValSize5 =
        floatArrayOf(10f, 2f, 4f, 5f, 7f, 9f, 4f, 2f, 7f, 12f, 14f, 1f)
     var dataSetColors = ArrayList<Int>()
        dataSetColors.add(rgb("#C0392B"))
        dataSetColors.add(rgb("#884EA0"))
        dataSetColors.add(rgb("#2471A3"))
        dataSetColors.add(rgb("#1ABC9C"))
        dataSetColors.add(rgb("#27AE60"))
        dataSetColors.add(rgb("#F1C40F"))
        dataSetColors.add(rgb("#F39C12"))
        dataSetColors.add(rgb("#E67E22"))
        dataSetColors.add(rgb("#D35400"))
        dataSetColors.add(rgb("#2E4053"))
        dataSetColors.add(rgb("#145A32"))
        dataSetColors.add(rgb("#0B5345"))

```

## Implementation MpAndroidChart Wrapper classes

### Multi Line Chart Component Init

```
        /*MultiLineChart*/

        val mulLine = LineChartWrapper()

        val linkedHashMap = LinkedHashMap<String, FloatArray>()
        linkedHashMap[yAxisSeriesName + 1] = yAxisVal //String, float[]
        linkedHashMap[yAxisSeriesName + 2] = yAxisVal2 //String, float[]
        linkedHashMap[yAxisSeriesName + 3] = yAxisVal3 //String, float[]

        val mulLineChart = mulLine.multiLineChart(mulLineChart, xAxisVal, linkedHashMap, dataSetColors, "")
        mulLineChart!!.invalidate()
 ```

### Single bar Chart Component Init

```
        /*SingleBarChart*/

        val linkedHashMap = LinkedHashMap<String, FloatArray>()
        linkedHashMap[yAxisValName + 1] = yAxisVal //String, float[]

        val singleBarChart = BarChartWrapper().barChart(barChart, xAxisVal, linkedHashMap, dataSetColors, "")
        singleBarChart.invalidate()
 ```

### Multi bar Chart Component Init

```
        /*MultiBarChart*/
        
        val linkedHashMap = LinkedHashMap<String, FloatArray>()
        linkedHashMap[yAxisValName + 1] = yAxisVal //String, float[]
        linkedHashMap[yAxisValName + 2] = yAxisVal2 //String, float[]
        linkedHashMap[yAxisValName + 3] = yAxisVal3 //String, float[]

        val multiBarChart = BarChartWrapper().multiBarChart(multiBarChart, xAxisVal, linkedHashMap, dataSetColors, "")
        multiBarChart.invalidate()
 ```

### Stacked bar Chart Component Init

```
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

        val barStackChart=BarChartWrapper().barStackChart(barChartView1, xAxisVal, linkedHashMap, dataSetColors,"")
        barStackChart.invalidate()
 ```

### Pie Chart Component Init

```
         /*Pie Chart*/
 
         // LargeValueFormatter "1.000" into "1k"
         // PercentFormatter  50 -> 50.0 %
         val yAxisValFormat = "PercentFormatter"
 
         val outSideSlice = true //Y axis value highlighted in OutSideSlice
 
         val pieChart = PieWrapper().pieChart(pieChart, xAxisVal, yAxisVal, dataSetColors, "Month", 
                                  yAxisValFormat, "", outSideSlice)
         pieChart!!.invalidate()
 ```


## Bar Chart Wrapper
    Class Name: BarChartWrapper
    
## Methods

 * ` barStackChart(
            barChartView: BarChart,
            xAxisValues: Array<String>,
            YAxisValuesLinkedHashMap: LinkedHashMap<String, 
            LinkedHashMap<String, FloatArray>>,
            dataSetColors: ArrayList<Int>,
            chartTitle: String
        ): Retrun BarChart`
 * ` barChart(
            barChart: BarChart,
            xAxisVal: Array<String>,
            yAxisVal: LinkedHashMap<String, FloatArray>,
            dataSetColors: ArrayList<Int>,
            chatTitle: String
         ): Retrun BarChart`
        
 * ` multiBarChart(
            multiBarChart: BarChart,
            xAxisVal: Array<String>,
            yAxisVal: LinkedHashMap<String, FloatArray>,
            dataSetColors: ArrayList<Int>,
            chatTitle: String
        ): Retrun BarChart`
        
## Line Chart Wrapper
    Class Name: LineChartWrapper
    
## Methods

 * ` multiLineChart(
             mChart: LineChart,
             xAxisVal: Array<String>,
             yValues: LinkedHashMap<String, FloatArray>,
             dataSetColors: ArrayList<Int>,
             graphTitle: String?
         ): Retrun LineChart`
         
## Pie Chart Wrapper
    Class Name: PieWrapper
    
## Methods

 * ` pieChart(
             chart: PieChart,
             xAxisVal: Array<String>,
             yAxisVal: FloatArray,
             yAxisName: String,
             yAxisValFormat: String?,
             dataSetColors: ArrayList<Int>,
             graphTitle: String?,
             outSideSlice: Boolean?
         ): Retrun PieChart`

