package com.example.firstproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var iResult: Int = 1

        for(i:Int in 1..10) {
            iResult = addOne(i)
            iResult += addTwo(i)

            Log.v("App_Debug", "addOne value = "+iResult)
        }
    }

    fun addOne(input: Int): Int {

        var iRtn: Int = input
        iRtn += 1
        //iRtn = iRtn + 1

        Log.v("App_Debug", "value = "+iRtn)

        return iRtn
    }

    fun addTwo(input: Int): Int {
        var iRtn: Int = input
        iRtn += 2

        Log.v("App_Debug", "value = "+iRtn)

        return iRtn
    }
}