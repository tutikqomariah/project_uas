package com.tutik.project_uas_kotlin
//import androidx.room.Entity
//
//
//@Entity(tableName = "i_love_you")
class data_rujukan {

    var nama: String? = null
    var kota: String? = null
    var notelp: String? = null
    var alamat: String? = null
    var key: String? = null

    constructor() {}
    constructor(nama: String?, kota: String?, notelp: String?, alamat: String?) {
        this.nama = nama
        this.kota = kota
        this.notelp = notelp
        this.alamat = alamat
    }
}
