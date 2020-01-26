package com.kota.watchit.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.kota.watchit.R
import com.kota.watchit.databinding.ActivityMainBinding
import com.kota.watchit.fragment.CartFragment
import com.kota.watchit.fragment.ListProductFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    companion object {
        const val NAVIGATION_ID = "navigation_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding =DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.apply {
            bottomNavigation.setOnNavigationItemSelectedListener(mOnClickListener)
            val navigationId = intent.getIntExtra(NAVIGATION_ID, R.id.itemBeranda)
            bottomNavigation.selectedItemId = navigationId
        }
    }

    private val mOnClickListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        loadFragment(item.itemId)
        true
    }

    private fun loadFragment(itemId: Int) {
        val tag = itemId.toString()
        val fragment = supportFragmentManager.findFragmentByTag(tag) ?: when (itemId) {
            R.id.itemBeranda -> {
                ListProductFragment.newInstance()
            }
            R.id.itemKeranjang-> {
                CartFragment.newInstance()
            }
            else -> {
                null
            }
        }

        if (fragment != null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.framelayout, fragment, tag)
                .commit()
        }
    }
}
