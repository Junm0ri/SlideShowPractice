package com.example.slideshowpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.slideshowpractice.databinding.ActivityMainBinding
import kotlin.concurrent.timer

class MainActivity : AppCompatActivity() {
    class MyAdapter(fa:FragmentActivity) : FragmentStateAdapter(fa) {

        private val resources = listOf(
            R.drawable.slide00,
            R.drawable.slide01,
            R.drawable.slide02,
            R.drawable.slide03,
            R.drawable.slide04,
            R.drawable.slide05,
            R.drawable.slide06,
            R.drawable.slide07,
            R.drawable.slide08,
            R.drawable.slide09
            )

        override fun getItemCount(): Int =resources.size

        override fun createFragment(position: Int): Fragment =ImageFragment.newInstance(resources[position])
    }

    private  lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.pager.adapter=MyAdapter(this)
        val handler= Handler(Looper.getMainLooper())
        timer(period=1000) {
            handler.post{
                binding.apply{
                    pager.currentItem=(pager.currentItem+1)%10
                }
            }
        }
    }
}