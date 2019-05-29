package com.nurde.retrofitwithcardandrecycleview;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactHolder> {
    private List<Contact> contacts = new ArrayList<>();

    @NonNull
    @Override
    public ContactHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.card_view, parent, false);
        return new ContactHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactHolder holder, int potision) {
       Contact currentCintact = contacts.get(potision);
        holder.txtName.setText(currentCintact.getName());
        holder.txtEmail.setText(currentCintact.getEmail());
        holder.txtAddr.setText(currentCintact.getAddres());
        holder.txtnoHp.setText(currentCintact.getPhone());
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public void setContacts(List<Contact> contacts){
        this.contacts = contacts;
        notifyDataSetChanged();
    }
    public Contact getContactAt(int position){
        return contacts.get(position);
    }

    class ContactHolder extends  RecyclerView.ViewHolder{
        private TextView txtName, txtEmail, txtAddr, txtnoHp;
        public  ContactHolder(@NonNull View itemView){
            super(itemView);
            txtName = (TextView) itemView.findViewById(R.id.txt_nama);
            txtEmail = (TextView) itemView.findViewById(R.id.txt_email);
            txtAddr = (TextView) itemView.findViewById(R.id.txt_alamat);
            txtnoHp = (TextView) itemView.findViewById(R.id.txt_nohp);
        }
    }
}
