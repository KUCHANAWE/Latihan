package com.example.latihan

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class tasklist(
    var tanggal :String,
    var deskripsi:String,
    var judul:String,
    var kategori:String,
):Parcelable
