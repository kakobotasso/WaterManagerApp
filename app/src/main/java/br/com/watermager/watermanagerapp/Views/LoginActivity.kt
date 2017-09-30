package br.com.watermager.watermanagerapp.Views

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import br.com.watermager.watermanagerapp.API.Services.LoginService
import br.com.watermager.watermanagerapp.Models.Login
import br.com.watermager.watermanagerapp.Models.User
import br.com.watermager.watermanagerapp.R
import br.com.watermager.watermanagerapp.Utils.UserShared

class LoginActivity : AppCompatActivity() {

    lateinit var etUserLogin: TextView
    lateinit var etPasswordLogin: TextView
    lateinit var btLogin: Button
    lateinit var tvNotYet: TextView
    lateinit var progressBar: ProgressBar
    lateinit var username: String
    lateinit var password: String
    lateinit var userShared: UserShared

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        prepareLayoutItens()
    }

    fun signIn(v: View?) {
        hideItensSubmit()

        username = etUserLogin.text.toString()
        password = etPasswordLogin.text.toString()

        if (validateFields()) {
            val login = Login()
            login.username = username
            login.password = password

            submitForm(login)
        } else {
            showItensSubmit()
        }
    }

    fun notYetRegistered(v: View?) {
        val signUpActivity = Intent(this, SignUpActivity::class.java)
        startActivity(signUpActivity)
        finish()
    }

    private fun prepareLayoutItens() {
        etUserLogin = findViewById(R.id.et_user_login) as TextView
        etPasswordLogin = findViewById(R.id.et_password_login) as TextView
        btLogin = findViewById(R.id.bt_login) as Button
        tvNotYet = findViewById(R.id.tv_not_yet) as TextView
        progressBar = findViewById(R.id.progress_bar) as ProgressBar
        progressBar.visibility = View.GONE

        userShared = UserShared(this)
    }

    private fun validateFields(): Boolean {
        var valid = true
        if (username.isEmpty()) {
            etUserLogin.error = getString(R.string.username_blank_login)
            valid = false
        } else if (password.isEmpty()) {
            etPasswordLogin.error = getString(R.string.password_blank_login)
            valid = false
        }
        return valid
    }

    private fun hideItensSubmit() {
        btLogin.visibility = View.GONE
        tvNotYet.visibility = View.GONE
        progressBar.visibility = View.VISIBLE
    }

    private fun showItensSubmit() {
        btLogin.visibility = View.VISIBLE
        tvNotYet.visibility = View.VISIBLE
        progressBar.visibility = View.GONE
    }

    private fun submitForm(login: Login) {
        val loginService = LoginService()

        loginService.loginUser(login, { response ->
            if( response.isSuccessful ){
                val user = response.body() as User
                userShared.addTokenAndSerial(user.token, user.serial)

                val mainActivity = Intent(this, MainActivity::class.java)
                startActivity(mainActivity)
                finish()
            } else {
                Toast.makeText(this, R.string.login_failed, Toast.LENGTH_SHORT).show()
                showItensSubmit()
            }

        }, { t ->
            println(t.message)
        })
    }
}
