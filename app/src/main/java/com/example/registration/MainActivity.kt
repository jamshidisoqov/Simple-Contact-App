package com.example.registration

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import com.example.registration.data.models.User
import com.example.registration.databinding.SignUpLayoutBottomBinding
import com.example.registration.utils.MySharedPreference
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainActivity : AppCompatActivity() {
    private lateinit var spBinding: SignUpLayoutBottomBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        MySharedPreference.init(this)

        val cardSignUp: CardView = findViewById(R.id.card_btn_up)
        val cardSignIn: CardView = findViewById(R.id.card_btn_in)

        cardSignUp.setOnClickListener {
            val dialog = BottomSheetDialog(
                this,
                com.google.android.material.R.style.Theme_MaterialComponents_Light_BottomSheetDialog
            )
            spBinding = SignUpLayoutBottomBinding.bind(
                layoutInflater.inflate(
                    R.layout.sign_up_layout_bottom,
                    null,
                    false
                )
            )
            dialog.setContentView(spBinding.root)
            spBinding.floatingActionButton.setOnClickListener {
                spBinding.apply {
                    val name = this.edName.text.toString()
                    val email = this.edEmail.text.toString()
                    val password = this.edPassword.text.toString()
                    val confirm = this.edConfirmPassword.text.toString()
                    if (password.equals(confirm)) {
                        if (properyIsNotEmpty(name, email, password)) {
                            val user = User(name, email, password)
                            val users = MySharedPreference.user
                            val type = object : TypeToken<ArrayList<User>>() {}.type
                            val userList = Gson().fromJson<ArrayList<User>>(users, type)
                            userList.add(user)
                            val userToJson = Gson().toJson(userList, type)
                            MySharedPreference.user = userToJson
                            dialog.dismiss()
                            Snackbar.make(it, "Succesfuly added", Snackbar.LENGTH_LONG).show()
                        } else {
                            Snackbar.make(it, "propertys is not filled", Snackbar.LENGTH_LONG)
                                .show()
                        }
                    } else {
                        Snackbar.make(it, "password is not equals", Snackbar.LENGTH_LONG).show()
                    }

                }
            }
            dialog.show()
        }

        cardSignIn.setOnClickListener {
            val alertDialog = AlertDialog.Builder(this)
            alertDialog.setTitle("Users")
            alertDialog.setMessage(MySharedPreference.user)
            alertDialog.show()
        }
    }

    fun properyIsNotEmpty(name: String, email: String, password: String): Boolean {

        return !TextUtils.isEmpty(name) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)

    }
}