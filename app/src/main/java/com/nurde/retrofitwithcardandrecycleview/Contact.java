package com.nurde.retrofitwithcardandrecycleview;

import com.google.gson.annotations.SerializedName;

public class Contact {

    @SerializedName("id")
    private Integer idContact;

    @SerializedName("nama")
    private String name;

    private String email;

    @SerializedName("nohp")
    private String phone;

    @SerializedName("alamat")
    private String addres;

    public Contact (Integer idContact,String name, String email, String phone, String addres) {
        this.idContact = idContact;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.addres = addres;
    }

    public Integer getIdContact() {
        return idContact;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddres() {
        return addres;
    }

    public void setIdContact(int idContact) {
        this.idContact = idContact;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddres(String addres) {
        this.addres = addres;
    }
    public int getContactId(){
        return idContact;
    }
}

