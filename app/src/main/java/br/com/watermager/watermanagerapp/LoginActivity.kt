package br.com.watermager.watermanagerapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import br.com.watermager.watermanagerapp.API.Services.VersionService
import br.com.watermager.watermanagerapp.Models.Version
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val versionService = VersionService()
        versionService.checkVersion({ response ->
            val version = response.body() as Version
            println("----------------------------------------------------------")
            println(version.version)
            println("----------------------------------------------------------")
        }, { t ->
            println(t.message)
        })
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
