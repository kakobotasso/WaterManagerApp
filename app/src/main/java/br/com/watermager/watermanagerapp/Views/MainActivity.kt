package br.com.watermager.watermanagerapp.Views

import android.app.Fragment
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.FrameLayout
import android.widget.ProgressBar
import br.com.watermager.watermanagerapp.R
import br.com.watermager.watermanagerapp.Utils.UserShared
import br.com.watermager.watermanagerapp.Views.Fragments.BillFragment
import br.com.watermager.watermanagerapp.Views.Fragments.ConsumptionFragment

class MainActivity : AppCompatActivity() {

    private var container: FrameLayout? = null
    private var progressBar: ProgressBar? = null
    private var consumptionFragment: ConsumptionFragment? = null
    private var billFragment: BillFragment? = null

    lateinit var userShared: UserShared

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_consumption -> {
                loadConsumptionFragment()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_bill -> {
                loadBillFragment()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        container = findViewById(R.id.content) as FrameLayout
        progressBar = findViewById(R.id.progress_bar) as ProgressBar

        userShared = UserShared(this)
        val user = userShared.readUser()
        user.serial
        loadConsumptionFragment()

        val navigation = findViewById(R.id.navigation) as BottomNavigationView
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    private fun loadConsumptionFragment() {
        showProgressBar()
        consumptionFragment = ConsumptionFragment(userShared)
        fragmentManager.beginTransaction().replace(R.id.content, consumptionFragment).commit()
        hideProgressBar()
    }

    private fun loadBillFragment() {
        showProgressBar()
        billFragment = BillFragment(userShared)
        fragmentManager.beginTransaction().replace(R.id.content, billFragment).commit()
        hideProgressBar()
    }

    private fun showProgressBar() {
        progressBar!!.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        progressBar!!.visibility = View.GONE
    }

}
