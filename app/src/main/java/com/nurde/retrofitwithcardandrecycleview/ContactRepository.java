package com.nurde.retrofitwithcardandrecycleview;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ContactRepository {

    ProgressDialog progressDialog;
    Retrofit connection = ContactConnection.getInstance();

    private ContactApi contactApi = connection.create(ContactApi.class);

    private MutableLiveData<List<Contact>> allContacts = new MutableLiveData<>();

    public LiveData<List<Contact>> getAllContacts(){
        Call<List<Contact>> call = contactApi.getAllContact();
        call.enqueue(new Callback<List<Contact>>() {
            @Override
            public void onResponse(Call<List<Contact>> call, Response<List<Contact>> response) {
                allContacts.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Contact>> call, Throwable t) {
                Log.e("Err : ", t.getMessage());
            }
        });
        return allContacts;
    }

    public  LiveData<List<Contact>> getAllContact() {
        return  allContacts;
    }

    public void insert (Contact contact, Context context){
        ProgressDialog pd = new ProgressDialog(context);
        pd.setMessage("Saving...");
        pd.show();
        Call<Contact> call = contactApi.saveContact(contact);
        call.enqueue(new Callback<Contact>() {
            @Override
            public void onResponse(Call<Contact> call, Response<Contact> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(context, response.code(), Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(context,"Saved Contact is Succses", Toast.LENGTH_SHORT).show();
                getAllContact();
                pd.dismiss();
            }

            @Override
            public void onFailure(Call<Contact> call, Throwable t) {
                Toast.makeText(context,t.getMessage(),Toast.LENGTH_SHORT).show();
                pd.dismiss();
            }
        });

    }
    public void delet (int id, Context context) {
        ProgressDialog pd = new ProgressDialog(context);
        pd.setMessage("Deleting...");
        pd.show();
        Call<Void> call = contactApi.deleteContact(id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(context,response.code(),Toast.LENGTH_LONG).show();
                }
                Toast.makeText(context,"Contact Deleted",Toast.LENGTH_LONG).show();
                getAllContact();
                pd.dismiss();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(context,t.getMessage(),Toast.LENGTH_LONG).show();
                pd.dismiss();
            }
        });
    }
}
