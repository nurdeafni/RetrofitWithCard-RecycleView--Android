package com.nurde.retrofitwithcardandrecycleview;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    private ContactViewModel contactViewModel;
    FloatingActionButton floatSave;
    public static final int ADD_CONTACT_REQUEST = 1 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        floatSave = (FloatingActionButton) findViewById(R.id.inputData);
        RecyclerView recyclerView = findViewById(R.id.recycle_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final ContactAdapter adapter = new ContactAdapter();

        recyclerView.setAdapter(adapter);

        contactViewModel = ViewModelProviders.of(this).get(ContactViewModel.class);
        contactViewModel.getListContact().observe(this, new Observer<List<Contact>>() {

            @Override
            public void onChanged(List<Contact> contacts) {
                adapter.setContacts(contacts);
                Toast.makeText(MainActivity.this, "onChange", Toast.LENGTH_SHORT).show();
            }

        });
        floatSave = (FloatingActionButton) findViewById(R.id.inputData);
        floatSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent add = new Intent(MainActivity.this,InputData.class);
                startActivityForResult(add,ADD_CONTACT_REQUEST);
            }
        });
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int id = adapter.getContactAt(viewHolder.getAdapterPosition()).getContactId();
                contactViewModel.deletContact(id,MainActivity.this);

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("Apakah Anda Ingin Menghapusnya")
                        .setTitle("Hapus Data");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int id = adapter.getContactAt(viewHolder.getAdapterPosition()).getContactId();
                        contactViewModel.deletContact(id,MainActivity.this);
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        adapter.notifyItemChanged(viewHolder.getAdapterPosition());
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        }).attachToRecyclerView(recyclerView);

        }

        protected void onActivityResult (int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode,resultCode,data);

        if(requestCode == ADD_CONTACT_REQUEST && resultCode == RESULT_OK){
            Integer idContact = null;
            String name = data.getStringExtra(InputData.EXTRA_NAME);
            String email = data.getStringExtra(InputData.EXTRA_EMAIL);
            String address = data.getStringExtra(InputData.EXTRA_ADDRESS);
            String phone = data.getStringExtra(InputData.EXTRA_PHONE);

            Contact contact = new Contact(idContact,name,email,address,phone);
            contactViewModel.insertContact(contact, MainActivity.this);
        }
        else{
            Toast.makeText(this,"Contact Not Saved",Toast.LENGTH_SHORT).show();
        }
    }
    }