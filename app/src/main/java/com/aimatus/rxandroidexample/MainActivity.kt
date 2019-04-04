package com.aimatus.rxandroidexample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.jakewharton.rxbinding2.view.RxView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setClickButtonAction()
    }

    private fun setClickButtonAction() {
        RxView.clicks(b_click_me)
            .map {
                incrementBeforeThrottleCounter()
            }
            .throttleFirst(1000, TimeUnit.MILLISECONDS)
            .subscribe{
                incrementAfterThrottleCounter()
            }
    }

    private fun incrementBeforeThrottleCounter() {
        var currentBeforeThrottleCounter = tv_before_throttle_counter.text.toString().toInt()
        currentBeforeThrottleCounter++
        tv_before_throttle_counter.text = currentBeforeThrottleCounter.toString()
    }

    private fun incrementAfterThrottleCounter() {
        var currentAfterThrottleCounter = tv_after_throttle_counter.text.toString().toInt()
        currentAfterThrottleCounter++
        tv_after_throttle_counter.text = currentAfterThrottleCounter.toString()
    }
}
