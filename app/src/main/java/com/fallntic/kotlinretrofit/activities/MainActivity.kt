package com.fallntic.kotlinretrofit.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.fallntic.kotlinretrofit.R
import com.fallntic.kotlinretrofit.api.RetrofitClient
import com.fallntic.kotlinretrofit.models.DefaultResponse
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var email: String
    lateinit var password: String
    lateinit var name: String
    lateinit var school: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textViewLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        buttonSignUp.setOnClickListener{

            email = editTextEmail.text.toString().trim()
            password = editTextPassword.text.toString().trim()
            name = editTextName.text.toString().trim()
            school = editTextSchool.text.toString().trim()

            checkValidation()

            RetrofitClient.instance.createUser(email, name, password, school)
                .enqueue(object: Callback<DefaultResponse>{
                    override fun onResponse(call: Call<DefaultResponse>, response: Response<DefaultResponse>) {

                        if (response.isSuccessful){
                            Toast.makeText(applicationContext, "response.isSuccessful = true", Toast.LENGTH_LONG).show()
                        }else{
                            Toast.makeText(applicationContext, "response.isSuccessful = false", Toast.LENGTH_LONG).show()
                        }


                        Toast.makeText(applicationContext, response.body()?.message, Toast.LENGTH_LONG)

                    }

                    override fun onFailure(call: Call<DefaultResponse>, t: Throwable) {
                        Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                        Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG)
                    }
                })

        }
    }

    private fun checkValidation() {
        if (email.isEmpty()){
            editTextEmail.error = "Email required"
            editTextEmail.requestFocus()
            return
        }
        if (password.isEmpty()){
            editTextPassword.error = "Email required"
            editTextPassword.requestFocus()
            return
        }
        if (name.isEmpty()){
            editTextName.error = "Email required"
            editTextName.requestFocus()
            return
        }
        if (school.isEmpty()){
            editTextSchool.error = "Email required"
            editTextSchool.requestFocus()
            return
        }
    }
}
