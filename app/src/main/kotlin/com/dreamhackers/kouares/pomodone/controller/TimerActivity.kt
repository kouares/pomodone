package com.dreamhackers.kouares.pomodone.controller

import android.app.Activity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.dreamhackers.kouares.pomodone.R

/**
 * Created by dreamhackers.kouares 2016/08/12
 */
class TimerActivity : Activity() {

    var start : Button? = null
    var finish : Button? = null
    var timerText : TextView? = null
    var countDown : CountDown? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timer)

        start = findViewById(R.id.start) as Button
        finish = findViewById(R.id.finish) as Button

        timerText = findViewById(R.id.timer) as TextView
        timerText!!.text = "0:00.000"
    }

    fun onStartClick(v : View) {
        var time = (findViewById(R.id.time) as EditText).text
        if (time.matches(Regex("^[0-9]+$"))) {
            countDown = CountDown(time as Long, 100)
            countDown!!.start()
        }
    }

    fun onFinishClick(v : View) {
        if (countDown != null) {
            countDown!!.cancel()
        }

        timerText!!.text = "0:00.000";
    }

    inner class CountDown(millisInFuture : Long, countDownInterval : Long) : CountDownTimer(millisInFuture, countDownInterval) {

        override fun onFinish() {
            timerText!!.text = "0:00.000"
        }

        override fun onTick(millisUntilFinished: Long) {
            var mm = millisUntilFinished / 1000 / 60
            var ss = millisUntilFinished / 1000 % 60
            var ms = millisUntilFinished - ss * 1000 - mm * 1000 * 60

            timerText!!.text = String.format("%1$02d:%2$02d.%3$03d", mm, ss, ms)
        }
    }
}


