package br.com.watermager.watermanagerapp.Views

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import br.com.watermager.watermanagerapp.R

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    fun signIn(v: View?) {
        val mainActivity = Intent(this, MainActivity::class.java)
        startActivity(mainActivity)
        finish()
    }

    fun notYetRegistered(v: View?) {
        val signUpActivity = Intent(this, SignUpActivity::class.java)
        startActivity(signUpActivity)
        finish()
    }
}
