package com.example.firebaserealtimedatabasekullanimi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val database = FirebaseDatabase.getInstance()

        val refKisiler = database.getReference("kisiler")

        //val kisi = Kisiler("Sedat", 10)                                          //KAYIT İŞLEMİ

        //refKisiler.push().setValue(kisi)                                         //KAYIT İŞLEMİ

        //refKisiler.child("-N9RWitbIViDACCDWuq9").removeValue()                   //SİLME İŞLEMİ

        /*val updateInfo = HashMap<String,Any>()

        updateInfo["kisi_ad"] = "Yeni Mehmet"
        updateInfo["kisi_yas"] = 100

        refKisiler.child("-N9RW0e_r4fiBpW2DTrt").updateChildren(updateInfo)*/      //GÜNCELLEME İŞLEMİ

        //val sorgu = refKisiler.orderByChild("kisi_ad").equalTo("Ahmet")          //EŞİTLİK SORGUSU

        //val sorgu = refKisiler.limitToLast(2)                                   //LİMİT SORGUSU, FIRST İLE BAŞTAN LAST İLE SONDAN, SAYI İLE KAÇ VERİ İSTEDİĞİMİZİ SEÇİYORUZ

        val sorgu = refKisiler.orderByChild("kisi_yas").startAt(30.0).endAt(50.0)

        sorgu.addValueEventListener(object : ValueEventListener{

            override fun onDataChange(ds: DataSnapshot) {

                for(p in ds.children){
                    val kisi = p.getValue(Kisiler::class.java)

                    if (kisi != null){
                        val key = p.key
                        Log.e("**********","***********")
                        Log.e("Kişi key",key.toString())
                        Log.e("Kişi ad",(kisi.kisi_ad).toString())
                        Log.e("Kişi yaş",(kisi.kisi_yas).toString())
                    }
                }

            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }
}