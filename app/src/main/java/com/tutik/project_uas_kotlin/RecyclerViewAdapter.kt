package com.tutik.project_uas_kotlin

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter(private var listRujukan: ArrayList<data_rujukan>, context: Context) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    private var context: Context
    //ViewHolder Digunakan Untuk Menyimpan Referensi Dari View-View
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val Nama: TextView
        val Kota: TextView
        val Notelp: TextView
        val Alamat: TextView
        val ListItem: LinearLayout
        init {//Menginisialisasi View yang terpasang pada layout RecyclerView kita
            Nama = itemView.findViewById(R.id.nama)
            Kota = itemView.findViewById(R.id.kota)
            Notelp = itemView.findViewById(R.id.no_telp)
            Alamat = itemView.findViewById(R.id.alamat)
            ListItem = itemView.findViewById(R.id.list_item)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {
        //Membuat View untuk Menyiapkan & Memasang Layout yang digunakan pada RecyclerView
        val V: View = LayoutInflater.from(parent.getContext()).inflate(
            R.layout.view_design, parent, false)
        return ViewHolder(V)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //Mengambil Nilai/Value pada RecyclerView berdasarkan Posisi Tertentu
        val Nama: String? = listRujukan.get(position).nama
        val Kota: String? = listRujukan.get(position).kota
        val Notelp: String? = listRujukan.get(position).notelp
        val Alamat: String? = listRujukan.get(position).alamat
        //Memasukan Nilai/Value kedalam View (TextView: NIM, Nama, Jurusan)
        holder.Nama.text = "Nama: $Nama"
        holder.Kota.text = "Kota: $Kota"
        holder.Notelp.text = "No. Telepon: $Notelp"
        holder.Alamat.text = "Alamat: $Alamat"
        holder.ListItem.setOnLongClickListener(object : View.OnLongClickListener {
            override fun onLongClick(v: View?): Boolean {
        //Kodingan untuk fungsi Edit dan Delete, yang dibahas pada Tutorial Berikutnya.
                holder.ListItem.setOnLongClickListener { view ->
                    val action = arrayOf("Update", "Delete")
                    val alert: AlertDialog.Builder = AlertDialog.Builder(view.context)
                    alert.setItems(action, DialogInterface.OnClickListener { dialog, i ->
                        when (i) {
                            0 -> {
                                val bundle = Bundle()
                                bundle.putString("Nama", listRujukan[position].nama)
                                bundle.putString("Kota", listRujukan[position].kota)
                                bundle.putString("Notelp", listRujukan[position].notelp)
                                bundle.putString("Alamat", listRujukan[position].alamat)
                                bundle.putString("getPrimaryKey", listRujukan[position].key)
                                val intent = Intent(view.context, UpdateData::class.java)
                                intent.putExtras(bundle)
                                context.startActivity(intent)

                                /* Berpindah Activity pada halaman layout updateData dan mengambil data pada
                                listrujukan, berdasarkan posisinya untuk dikirim pada activity selanjutnya */

                            }
                            1 -> {
                                //Menggunakan interface untuk mengirim data rujukan, yang akan dihapus
                                listener?.onDeleteData(listRujukan.get(position), position)
                            }
                        }
                    })
                    alert.create()
                    alert.show()
                    true
                }
                return true
            }
        })
    }
    override fun getItemCount(): Int {
//Menghitung Ukuran/Jumlah Data Yang Akan Ditampilkan Pada RecyclerView
        return listRujukan.size
    }
    //Membuat Konstruktor, untuk menerima input dari Database
    var listener: dataListener? = null
    init {
        this.context = context
        //Deklarasi objek dari Interfece
        this.listener = context as MyListData
    }

    //Membuat Interfece
    interface dataListener {
        fun onDeleteData(data: data_rujukan?, position: Int)
    }

    //Membuat Konstruktor, untuk menerima input dari Database
    fun RecyclerViewAdapter(listRujukan: ArrayList<data_rujukan>?, context: Context?) {
        this.listRujukan = listRujukan!!
        this.context = context!!
        listener = context as MyListData?
    }
}