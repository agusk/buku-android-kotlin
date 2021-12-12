package id.ilmudata.mynote

import java.util.*
import java.io.Serializable

data class Note(var id: Int,var note:String,var created:Date) : Serializable
