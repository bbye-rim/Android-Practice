package com.example.activitypractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.MotionEvent
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintSet
import org.w3c.dom.Text

class EventTouch : AppCompatActivity(), View.OnTouchListener {

    private var txtResult: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_touch)

        txtResult = findViewById(R.id.txtResultTouch)
        txtResult?.movementMethod = ScrollingMovementMethod()  //textView 스크롤 가능하도록


        val vw = findViewById<View>(R.id.view1)
        val vw2 = findViewById<View>(R.id.view2)
        vw.setOnTouchListener(this)
        vw2.setOnTouchListener(this)


        /*
        val vw: View = findViewById<View>(R.id.view1)
        vw.setOnTouchListener(object: View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {

                val action: Int ?= event?.action
                val Xpos: Float ?= event?.x
                val Ypos: Float ?= event?.y

                when(action) {
                    MotionEvent.ACTION_DOWN -> {
                        txtResult?.append("View1 Down!")
                    }
                    MotionEvent.ACTION_MOVE -> {
                        txtResult?.append("View1 Down!")
                    }
                    MotionEvent.ACTION_UP -> {
                        txtResult?.append("View1 Down!")
                    }
                }
                return true
            }

        })

        val vw2: View = findViewById<View>(R.id.view2)
        vw2.setOnTouchListener(object: View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {

                val action: Int ?= event?.action
                val Xpos: Float ?= event?.x
                val Ypos: Float ?= event?.y

                when(action) {
                    MotionEvent.ACTION_DOWN -> {
                        txtResult?.append("View2 Down!")
                    }
                    MotionEvent.ACTION_MOVE -> {
                        txtResult?.append("View2 Down!")
                    }
                    MotionEvent.ACTION_UP -> {
                        txtResult?.append("View2 Down!")
                    }
                }
                return true
            }

        })
        */





    }

    override fun onTouch(v: View, event: MotionEvent?): Boolean {

        val action: Int ?= event?.action

        when(v.id) {
            R.id.view1 -> {
                when(action) {
                    MotionEvent.ACTION_DOWN -> {
                        txtResult?.append("View1 Down!")
                    }
                    MotionEvent.ACTION_MOVE -> {
                        txtResult?.append("View1 Down!")
                    }
                    MotionEvent.ACTION_UP -> {
                        txtResult?.append("View1 Down!")
                    }
                }
                return true
            }
            R.id.view2 -> {
                when(action) {
                    MotionEvent.ACTION_DOWN -> {
                        txtResult?.append("View2 Down!")
                    }
                    MotionEvent.ACTION_MOVE -> {
                        txtResult?.append("View2 Down!")
                    }
                    MotionEvent.ACTION_UP -> {
                        txtResult?.append("View2 Down!")
                    }
                }
                return true
            }
        }
        return true
    }
}
