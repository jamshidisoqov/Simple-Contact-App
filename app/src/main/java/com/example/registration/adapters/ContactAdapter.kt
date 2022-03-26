package com.example.registration.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.registration.R
import com.example.registration.data.models.Contact
import com.example.registration.databinding.ContactItemBinding

class ContactAdapter(var contactList: List<Contact>,var itemClick: onPhoneItemClickListener):RecyclerView.Adapter<ContactAdapter.Vh>() {
    private lateinit var binding: ContactItemBinding
    class Vh (var binding: ContactItemBinding,var itemClick: onPhoneItemClickListener): RecyclerView.ViewHolder(binding.root){
        fun populateModel(contact: Contact) {
            binding.tvItemName.text=contact.name
            binding.tvItemNumber.text=contact.number
            binding.phoneItemImg.setOnClickListener {
               itemClick.call(contact.number)
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        binding= ContactItemBinding.bind(LayoutInflater.from(parent!!.context).inflate(R.layout.contact_item,parent,false))
        return Vh(binding,itemClick)
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.populateModel(contactList[position])
    }

    override fun getItemCount(): Int = contactList.size

    interface onPhoneItemClickListener{
        fun call(number:String)
    }
}