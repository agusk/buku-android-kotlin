package id.ilmudata.manajemenpegawai

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

data class Pegawai(
    @SerializedName("id") var id: Int,
    @SerializedName("nip") var nip:String,
    @SerializedName("nama") var nama:String,
    @SerializedName("email") var email:String,
    @SerializedName("jeniskelamin") var jeniskelamin:String,
    @SerializedName("umur") var umur: Int) : Serializable
