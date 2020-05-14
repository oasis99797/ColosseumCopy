package com.bklee.colosseumcopy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import com.bklee.colosseumcopy.utils.ServerUtil
import kotlinx.android.synthetic.main.activity_sign_up.*
import org.json.JSONObject

class SignUpActivity : BaseActivity() {

    var isEmailCheckOk = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

        emailEdt.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                isEmailCheckOk = false

                emailCheckResultTxt.setText("중복확인을 해주세요.")
                emailCheckResultTxt.setTextColor(resources.getColor(R.color.darkGray))
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

        })

        emailCheckBtn.setOnClickListener {

            val inputEmail = emailEdt.text.toString()

            ServerUtil.getRequestEmailDuplCheck(mContext, inputEmail, object : ServerUtil.JsonResponseHandler{
                override fun onResponse(json: JSONObject) {
                    Log.d("이메일중복응답", json.toString())

                    val code = json.getInt("code")

                    if(code == 200) {

                        isEmailCheckOk = true

                        runOnUiThread {
                            emailCheckResultTxt.setTextColor(resources.getColor(R.color.azul))
                            emailCheckResultTxt.setText(R.string.id_check_success_message)
                        }

                    } else {

                        isEmailCheckOk = false

                        runOnUiThread {
                            emailCheckResultTxt.setTextColor(resources.getColor(R.color.grapefruit))
                            emailCheckResultTxt.setText(R.string.id_check_fail_message)
                        }
                    }
                }

            })
        }

    }

    override fun setValues() {

    }


}
