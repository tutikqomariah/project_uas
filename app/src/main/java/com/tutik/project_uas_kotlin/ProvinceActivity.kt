package com.tutik.project_uas_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tutik.project_uas_kotlin.adabter.ProvinceAdabter
import com.tutik.project_uas_kotlin.api.RetrofitClient
import com.tutik.project_uas_kotlin.model.ProvinceResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProvinceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_province)
        showProvince()
    }

    private fun showProvince(){
        val rvProvince = findViewById<RecyclerView>(R.id.rvProvince)
        rvProvince.setHasFixedSize(true)
        rvProvince.layoutManager = LinearLayoutManager(this)

        RetrofitClient.instance.getProvince().enqueue(object :
            Callback<ArrayList<ProvinceResponse>> {
            override fun onFailure(call: Call<ArrayList<ProvinceResponse>>, t: Throwable) {
                Toast.makeText(this@ProvinceActivity, "${t.message}", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<ArrayList<ProvinceResponse>>,
                response: Response<ArrayList<ProvinceResponse>>
            ) {
                val list = response.body()
                val adapter =  list?.let { ProvinceAdabter(it) }
                rvProvince.adapter = adapter
            }

        })
    }
}