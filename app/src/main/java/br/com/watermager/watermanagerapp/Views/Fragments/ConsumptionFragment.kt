package br.com.watermager.watermanagerapp.Views.Fragments

import android.app.Activity
import android.app.Fragment
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import br.com.watermager.watermanagerapp.API.Services.ConsumptionService
import br.com.watermager.watermanagerapp.Models.Consumption
import br.com.watermager.watermanagerapp.R
import br.com.watermager.watermanagerapp.Utils.UserShared
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry

class ConsumptionFragment() : Fragment() {
    lateinit var chart: BarChart
    lateinit var listView: ListView
    lateinit var consumptionList: List<Consumption>
    lateinit var userShared: UserShared

    constructor(userShared: UserShared) : this() {
        this.userShared = userShared
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_consumption, container, false)

        val user = userShared.readUser()

        chart = view.findViewById(R.id.chart) as BarChart
        listView = view.findViewById(R.id.list_view) as ListView

        val service = ConsumptionService()
        service.getConsumptionList(user.serial, "liter", {
            response ->
            if(response.isSuccessful){
                consumptionList = response.body()!!
                prepareChart()
                prepareListView()
            }


        }, {
            t ->
            println(t.message)
        })

        return view
    }

    private fun prepareChart(){
        var entries = ArrayList<BarEntry>()

        for( consumption in consumptionList ) {
            val xValue = consumption.month.split("/")[0].toFloat()
            val yValue = consumption.liter.toFloat()
            entries.add( BarEntry(xValue, yValue) )
        }

        val dataSet = BarDataSet(entries, "Litros")
        dataSet.addColor(R.color.colorAccent)

        val description = Description()
        description.text = ""

        val barData = BarData(dataSet)


        chart.data = barData
        chart.description = description
        chart.setDrawBorders(false)
        chart.setDrawGridBackground(false)
        chart.setPinchZoom(false)

        val xAxis = chart.xAxis
        xAxis.setDrawGridLines(false)

        val yAxis = chart.axisLeft
        yAxis.setDrawGridLines(false)

        chart.invalidate()
    }

    private fun prepareListView(){
        val adapter = ArrayAdapter<Consumption>(
                activity, android.R.layout.simple_list_item_1, toArray<Consumption>(consumptionList))
        listView.adapter = adapter
    }

    inline fun <reified T> toArray(list: List<*>): Array<T> {
        return (list as List<T>).toTypedArray()
    }
}