package com.tutik.project_uas_kotlin

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.firebase.ui.auth.AuthUI
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth


class home : AppCompatActivity() {
    private var auth: FirebaseAuth? = null
    private val RC_SIGN_IN = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

//        val kasus_per_provinsi = findViewById<ConstraintLayout>(R.id.kasus_per_provinsi)
//
//        kasus_per_provinsi.setOnClickListener(View.OnClickListener {
//            val i = Intent(this@home, com.tutik.project_uas_kotlin.kasus_per_provinsi::class.java)
//            startActivity(i)
//        })

        val logout = findViewById<ImageView>(R.id.logout)
        logout.setOnClickListener({AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener(object : OnCompleteListener<Void> {
                    override fun onComplete(p0: Task<Void>) {
                        Toast.makeText(this@home, "Logout Berhasil", Toast.LENGTH_SHORT).show()
                        intent = Intent(applicationContext,
                                LoginActivity::class.java)
                        startActivity(intent)

                        finish()

                    }
                })})

        auth = FirebaseAuth.getInstance()

        val mengenal = findViewById<ImageView>(R.id.mengenal)

        mengenal.setOnClickListener(View.OnClickListener {
            val i = Intent(this@home, com.tutik.project_uas_kotlin.mengenal::class.java)
            startActivity(i)
        })

        val mencegah = findViewById<ImageView>(R.id.mencegah)

        mencegah.setOnClickListener(View.OnClickListener {
            val i = Intent(this@home, com.tutik.project_uas_kotlin.mencegah::class.java)
            startActivity(i)
        })

        val mengobati = findViewById<ImageView>(R.id.mengobati)

        mengobati.setOnClickListener(View.OnClickListener {
            val i = Intent(this@home, com.tutik.project_uas_kotlin.mengobati::class.java)
            startActivity(i)
        })

        val banner = findViewById<ImageView>(R.id.banner)

        banner.setOnClickListener(View.OnClickListener {
            val i = Intent(this@home, com.tutik.project_uas_kotlin.MainActivity::class.java)
            startActivity(i)
        })
    }
}