package com.example.activitypractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity(), View.OnClickListener {

    //입력받을 값 변수 초기화
    private var strName: String? = null
    private var strSchoolNum: String? = null
    private var strPhoneNum: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        /*
        //입력받을 값 변수 초기화
        var strName: String? = null
        var strSchoolNum: String? = null
        var strPhoneNum: String? = null


        //1. 각 버튼별 이벤트 처리
        val btn: Button = findViewById<Button>(R.id.btnSave)  //xml 내의 버튼을 코드로 불러오기
        btn.setOnClickListener{

            val editName: EditText = findViewById(R.id.editName)
            val editSchoolNum: EditText = findViewById(R.id.editSchoolNum)
            val editPhoneNum: EditText = findViewById(R.id.editPhoneNum)
            val txtResult: TextView = findViewById(R.id.txtResult)

            //텍스트에 대한 스트링을 변수에 대입
            strName = editName.text.toString()
            strSchoolNum = editSchoolNum.text.toString()
            strPhoneNum = editPhoneNum.text.toString()

            txtResult.text = "이름 : "+strName+", 학번 : "+strSchoolNum+", 전화번호 : "+strPhoneNum

        }
        */



        val btn = findViewById<Button>(R.id.btnSave)  //val editName: EditText = findViewById(R.id.editName) 이 식의 약식이다.
        btn.setOnClickListener(this)  //this는 this가 써져 있는 즉, this가 속해 있는 곳을 의미한다. 지금의 경우 MainActivity.kt가 this


    }


    //onClickListener에 대한 function 추가
    override fun onClick(v: View) {
        when(v.id) {
            R.id.btnSave -> {
                val editName: EditText = findViewById(R.id.editName)
                val editSchoolNum: EditText = findViewById(R.id.editSchoolNum)
                val editPhoneNum: EditText = findViewById(R.id.editPhoneNum)
                val txtResult: TextView = findViewById(R.id.txtResult)

                strName = editName.text.toString()
                strSchoolNum = editSchoolNum.text.toString()
                strPhoneNum = editPhoneNum.text.toString()

                //txtResult.text = "이름 : "+strName+"\n학번 : "+strSchoolNum+"\n전화번호 : "+strPhoneNum
                txtResult.text = "이름 : $strName \n학번 : $strSchoolNum \n전화번호 : $strPhoneNum"
            }
        }
    }

}