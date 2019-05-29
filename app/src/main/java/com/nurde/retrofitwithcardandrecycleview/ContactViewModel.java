package com.nurde.retrofitwithcardandrecycleview;

import android.app.Application;
import android.content.Context;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class ContactViewModel extends AndroidViewModel {
    private LiveData<List<Contact>> allContacts = new MutableLiveData<>();
    private ContactRepository repository;
    public Context context;

    public ContactViewModel(@NonNull Application application) {
        super(application);
        repository = new ContactRepository();
        allContacts = repository.getAllContacts();

    }

    public LiveData<List<Contact>> getListContact() {
        return allContacts;
    }
    public void insertContact(Contact contact, Context context){
        repository.insert(contact,context);
    }
    public void deletContact(int id, Context context){
        repository.delet(id,context);
    }
}
