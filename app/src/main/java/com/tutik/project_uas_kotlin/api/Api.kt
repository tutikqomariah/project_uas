package com.tutik.project_uas_kotlin.api

import com.tutik.project_uas_kotlin.model.IndonesiaResponse
import com.tutik.project_uas_kotlin.model.ProvinceResponse
import retrofit2.http.GET

interface Api {

    @GET("indonesia")
    fun getIndonesia(): retrofit2.Call<ArrayList<IndonesiaResponse>>

    @GET("indonesia/provinsi")
    fun getProvince(): retrofit2.Call<ArrayList<ProvinceResponse>>

}



