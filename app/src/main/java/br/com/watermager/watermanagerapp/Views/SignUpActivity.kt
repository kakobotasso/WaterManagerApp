package br.com.watermager.watermanagerapp.Views

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import br.com.watermager.watermanagerapp.API.Services.UserService
import br.com.watermager.watermanagerapp.Models.User
import br.com.watermager.watermanagerapp.R

class SignUpActivity : AppCompatActivity() {

    lateinit var etName: EditText
    lateinit var etEmail: EditText
    lateinit var etCpf: EditText
    lateinit var etUser: EditText
    lateinit var etPassword: EditText
    lateinit var btSignup: Button
    lateinit var progressBar: ProgressBar
    lateinit var name: String
    lateinit var email: String
    lateinit var cpf: String
    lateinit var username: String
    lateinit var password: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        prepareLayout()
    }

    fun registerNewUser(v: View?) {
        showProgressBar()
        getValuesFromForm()

        if (validFields()) {
            var user = User()

            user.name = name
            user.email = email
            user.cpf = cpf
            user.username = username
            user.password = password

            val service = UserService()
            service.signUpUser(user, { response ->
                if (response.isSuccessful) {
                    val mainActivity = Intent(this, MainActivity::class.java)
                    startActivity(mainActivity)
                    finish()
                } else {
                    Toast.makeText(this, R.string.signup_failed, Toast.LENGTH_SHORT).show()
                    hideProgressBar()
                }
            }, { t ->
                println(t.message)
            })
        } else {
            hideProgressBar()
        }


    }

    private fun prepareLayout() {
        etName = findViewById(R.id.et_name_signup) as EditText
        etEmail = findViewById(R.id.et_email_signup) as EditText
        etCpf = findViewById(R.id.et_cpf_signup) as EditText
        etUser = findViewById(R.id.et_user_signup) as EditText
        etPassword = findViewById(R.id.et_password_signup) as EditText
        btSignup = findViewById(R.id.bt_signup) as Button
        progressBar = findViewById(R.id.progress_bar) as ProgressBar
        progressBar.visibility = View.GONE
    }

    private fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
        btSignup.visibility = View.GONE
    }

    private fun hideProgressBar() {
        progressBar.visibility = View.GONE
        btSignup.visibility = View.VISIBLE
    }

    private fun getValuesFromForm() {
        name = etName.text.toString()
        email = etEmail.text.toString()
        cpf = etCpf.text.toString()
        username = etUser.text.toString()
        password = etPassword.text.toString()
    }

    private fun validFields(): Boolean {
        var valid = true

        if (name.isEmpty()) {
            valid = false
            etName.error = getString(R.string.name_blank_signup)
        }
        if (email.isEmpty()) {
            valid = false
            etEmail.error = getString(R.string.email_blank_signup)
        }
        if (cpf.isEmpty()) {
            valid = false
            etCpf.error = getString(R.string.cpf_blank_signup)
        }
        if (username.isEmpty()) {
            valid = false
            etUser.error = getString(R.string.username_blank_signup)
        }
        if (password.isEmpty()) {
            valid = false
            etPassword.error = getString(R.string.password_blank_signup)
        }

        return valid
    }
}
