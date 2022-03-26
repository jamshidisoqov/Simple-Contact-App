package com.example.registration

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import com.example.registration.adapters.ContactAdapter
import com.example.registration.data.models.Contact
import com.example.registration.databinding.ActivityMain2Binding
import com.example.registration.databinding.AddContactBinding
import com.example.registration.databinding.ContactItemBinding
import com.example.registration.databinding.ShowContactBinding
import com.example.registration.utils.MySharedPreference
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding
    private lateinit var clBinding: AddContactBinding
    private lateinit var shBinding: ShowContactBinding
    private lateinit var adapter: ContactAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        MySharedPreference.init(this)
        binding.cardAddContact.setOnClickListener {
            val dialog = BottomSheetDialog(this)
            clBinding =
                AddContactBinding.bind(layoutInflater.inflate(R.layout.add_contact, null, false))
            dialog.setContentView(clBinding.root)
            clBinding.addContactBtn.setOnClickListener {
                val name = clBinding.edName.text.toString()
                val number = clBinding.edNumber.text!!.toString()
                if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(number)) {
                    addContact(Contact(name, number))
                } else {

                }
            }
            dialog.show()

        }

        binding.cardShowContact.setOnClickListener {
            val dialog = BottomSheetDialog(this)
            shBinding =
                ShowContactBinding.bind(
                    layoutInflater.inflate(
                        R.layout.show_contact, null, false
                    )
                )
            dialog.setContentView(shBinding.root)
            adapter = ContactAdapter(readData(), object : ContactAdapter.onPhoneItemClickListener {
                override fun call(number: String) {
                    val phone = "+998$number"
                    val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + Uri.encode(phone)))
                   startActivity(intent)
                }
            })
            shBinding.contactRcv.adapter = adapter
            dialog.show()


        }


    }

    fun readData(): List<Contact> = Gson().fromJson<ArrayList<Contact>>(
        MySharedPreference.contact,
        object : TypeToken<ArrayList<Contact>>() {}.type
    )

    fun addContact(contact: Contact) {
        val list = readData() as ArrayList<Contact>
        list.add(contact)
        MySharedPreference.contact =
            Gson().toJson(list, object : TypeToken<ArrayList<Contact>>() {}.type)
    }


}