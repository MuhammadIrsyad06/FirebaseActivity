package com.example.firebaseactivity.model;

public class User {
    private String id,name,email,posisi,gaji,syarat;
    public User(){

    }
    public User(String name, String email,String gaji,String posisi,String syarat){
        this.name=name;
        this.email=email;
        this.gaji=gaji;
        this.posisi=posisi;
        this.syarat=syarat;

    }
    public String getId(){
        return id;
    }
    public void setId(String id){
        this.id=id;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email=email;
    }
    public String getPosisi(){
        return posisi;
    }
    public void setPosisi(String posisi){
        this.posisi=posisi;
    }
    public String getGaji(){
        return gaji;
    }
    public void setGaji(String gaji){
        this.gaji=gaji;
    }
    public String getSyarat(){
        return syarat;
    }
    public void setSyarat(String syarat){
        this.syarat=syarat;
    }
}
