package com.example.ashfaq_iroid.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.example.ashfaq_iroid.R
import kotlinx.android.synthetic.main.main_bottom_buttons.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setClick()
    }

    private fun setClick() {

        tvHome.setOnClickListener {
            clearButtonSelection()
            loadHome()
        }
        tvCategory.setOnClickListener {
            clearButtonSelection()
            loadCategory()
        }
        tvAccount.setOnClickListener {
            clearButtonSelection()
            loadAccount()
        }
    }

    private fun loadHome() {
        tvHome.setTextColor(ContextCompat.getColor(this, android.R.color.holo_green_dark))
        tvHome.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.home_clicked_icon, 0, 0)

        val fm = supportFragmentManager
        val fragment =
            HomeFragment()
        fm.beginTransaction().replace(R.id.flHomeContainer, fragment)
            .commitAllowingStateLoss()
    }

    private fun loadCategory() {
        tvCategory.setTextColor(
            ContextCompat.getColor(this, android.R.color.holo_green_dark))
                    tvCategory . setCompoundDrawablesWithIntrinsicBounds (0,
                        R.drawable.category_clicked_icon,
            0,
            0
        )

        val fm = supportFragmentManager
        val fragment = CategoryFragment()
        fm.beginTransaction().replace(R.id.flHomeContainer, fragment)
            .commitAllowingStateLoss()
    }

    private fun loadAccount() {
        tvAccount.setTextColor(ContextCompat.getColor(this, android.R.color.holo_green_dark))
        tvAccount.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.account_clicked_icon, 0, 0)

        val fm = supportFragmentManager
        val fragment =
            AccountFragment()
        fm.beginTransaction().replace(R.id.flHomeContainer, fragment)
            .commitAllowingStateLoss()
    }

    private fun clearButtonSelection() {
        tvHome.setTextColor(ContextCompat.getColor(this, android.R.color.white))
        tvHome.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.home_normal_icon, 0, 0)

        tvCategory.setTextColor(ContextCompat.getColor(this, android.R.color.white))
        tvCategory.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.category_normal_icon, 0, 0)

        tvAccount.setTextColor(ContextCompat.getColor(this, android.R.color.white))
        tvAccount.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.account_normal_icon, 0, 0)
    }
}