package com.tutik.project_uas_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.text.TextUtils.isEmpty
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_update_data.*

class UpdateData : AppCompatActivity() {
    //memasukan 4 data ini
    private var database: DatabaseReference? = null
    private var auth: FirebaseAuth? = null
    private var cekNama: String? = null
    private var cekKota: String? = null
    private var cekNotelp: String? = null
    private var cekAlamat: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_data)
//        supportActionBar!!.title ="Update Data"

        setSupportActionBar(toolbar)

        //mendapatkan intent autetasi dan referensi  dari database
        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance().reference
        data
        update.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                cekNama = new_nama.getText().toString()
                cekKota = new_kota.getText().toString()
                cekNotelp = new_notelp.getText().toString()
                cekAlamat = new_alamat.getText().toString()


                //ngecek datanya saat di update tidak kosong kita pakai if
                if (isEmpty(cekNama!!) || isEmpty(cekKota!!) || isEmpty(cekNotelp!!) || isEmpty(
                        cekAlamat!!
                    )
                ) {
                    Toast.makeText(
                        this@UpdateData,
                        "Data Tidak Boleh Kosong",
                        Toast.LENGTH_SHORT
                    ).show()
                    //isi else untuk menjalankan update data
                    //ada method seter untuk mendapatkan data yang baru diganti tadi yang sudah di inputkan oleh user
                } else {
                    val setdatadata_rujukan = data_rujukan()
                    setdatadata_rujukan.nama = new_nama.getText().toString()
                    setdatadata_rujukan.kota = new_kota.getText().toString()
                    setdatadata_rujukan.notelp = new_notelp.getText().toString()
                    setdatadata_rujukan.alamat = new_alamat.getText().toString()
                    updateRujukan(setdatadata_rujukan)


                }
            }

        })

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    // kembali ke halaman sebelumnya ketika tombol kembali ditekan
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    //mengecek apakah ada data yang kosong sebelum di update caranya menambhakn
    private fun isEmpty(s: String): Boolean {
        return TextUtils.isEmpty(s)
    }

    //menampilkan data yang akan di update
    private val data: Unit
        private get() {
            val getNama = intent.extras!!.getString("Nama")
            val getKota = intent.extras!!.getString("Kota")
            val getNotelp = intent.extras!!.getString("Notelp")
            val getAlamat = intent.extras!!.getString("Alamat")
            new_nama!!.setText(getNama)
            new_kota!!.setText(getKota)
            new_notelp!!.setText(getNotelp)
            new_alamat!!.setText(getAlamat)

        }

    //untuk proses update data yang telah ditentukan , yang tadi udah diganti yang baru kita proses updatenya
    private fun updateRujukan(pasien: data_rujukan) {
        var userID = auth!!.uid
        val getKey = intent.extras!!.getString("getPrimaryKey")
        database!!.child("Admin")
            .child(userID!!)
            .child("Pasien")
            .child(getKey!!)
            .setValue(pasien)
            .addOnSuccessListener {
                new_nama!!.setText("")
                new_kota!!.setText("")
                new_notelp!!.setText("")
                new_alamat!!.setText("")
                Toast.makeText(
                    this@UpdateData,
                    "Data Berhasil Di Simpan", Toast.LENGTH_SHORT
                ).show()
                finish()
            }
    }


}