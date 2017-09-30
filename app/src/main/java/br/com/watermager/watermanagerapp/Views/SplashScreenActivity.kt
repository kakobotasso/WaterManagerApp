package br.com.watermager.watermanagerapp.Views

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import br.com.watermager.watermanagerapp.API.Services.VersionService
import br.com.watermager.watermanagerapp.Models.Version

import br.com.watermager.watermanagerapp.R
import br.com.watermager.watermanagerapp.Utils.UserShared

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler().postDelayed(this::checkVersionAndRedirect, 3000)
    }

    fun checkVersionAndRedirect() {
        val versionService = VersionService()
        versionService.checkVersion({ response ->
            val version = response.body() as Version
            val minVersion = version.version.replace(".", "").toInt()

            val packageInfo = this.getPackageManager().getPackageInfo(getPackageName(), 0)

            if (minVersion > packageInfo.versionCode){
                val versionExpired = Intent(this, VersionExpiredActivity::class.java)
                startActivity(versionExpired)
                finish()
            } else {
                val userShared = UserShared(this)
                val user = userShared.readUser()

                if( user.token.isEmpty() || user.serial.isEmpty() ) {
                    val login = Intent(this, LoginActivity::class.java)
                    startActivity(login)
                    finish()
                } else {
                    val main = Intent(this, MainActivity::class.java)
                    startActivity(main)
                    finish()
                }

            }

        }, { t ->
            println(t.message)
        })
    }
}
