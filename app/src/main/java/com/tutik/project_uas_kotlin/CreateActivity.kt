package com.tutik.project_uas_kotlin

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class CreateActivity : AppCompatActivity(), View.OnClickListener {



    //Membuat Kode Permintaan
    private var auth: FirebaseAuth? = null
    private val RC_SIGN_IN = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)

        //Inisialisasi ID (Button)



        val simpan = findViewById<Button>(R.id.simpan)
        simpan.setOnClickListener(this)
        auth = FirebaseAuth.getInstance()


    }

    // Mengecek apakah ada data kosong, digunakan pada Tutorial Selanjutnya
    private fun isEmpty(s: String): Boolean {
        return TextUtils.isEmpty(s)
    }

    override fun onClick(v: View) {
        when (v.getId()) {
            R.id.simpan -> {
                // Statement program untuk simpan data
                //Mendapatkan UserID dari pengguna yang Terautentikasi
                val getUserID = auth!!.currentUser!!.uid
                //Mendapatkan Instance dari Database
                val database = FirebaseDatabase.getInstance()
                //Menyimpan Data yang diinputkan User kedalam Variable

                val nama = findViewById<EditText>(R.id.nama)
                val getNama: String = nama.getText().toString()
                val kota = findViewById<EditText>(R.id.kota)
                val getKota: String = kota.getText().toString()
                val notelp = findViewById<EditText>(R.id.notelp)
                val getNoTelp: String = notelp.getText().toString()
                val alamat = findViewById<EditText>(R.id.alamat)
                val getAlamat: String = alamat.getText().toString()
                // Mendapatkan Referensi dari Database
                val getReference: DatabaseReference
                getReference = database.reference
                // Mengecek apakah ada data yang kosong
                if (isEmpty(getNama) || isEmpty(getKota) || isEmpty(getNoTelp) || isEmpty(getAlamat))
                {  //Jika Ada, maka akan menampilkan pesan singkan seperti berikut  ini.
                    Toast.makeText(
                            this@CreateActivity, "Data tidak boleh ada yang  kosong",
                            Toast.LENGTH_SHORT
                    ).show()
                } else {


                    getReference.child("Admin").child(getUserID).child("DataRujukan").push()
                            .setValue(data_rujukan(getNama, getKota, getNoTelp, getAlamat))
                            .addOnCompleteListener(this) {
                                nama.setText("")
                                kota.setText("")
                                notelp.setText("")
                                alamat.setText("")
                                Toast.makeText(
                                        this@CreateActivity, "Data Tersimpan",
                                        Toast.LENGTH_SHORT
                                ).show()
                            }
                }
            }
        }
    }
}