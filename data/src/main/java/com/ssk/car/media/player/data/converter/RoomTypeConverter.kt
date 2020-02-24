package com.ssk.car.media.player.data.media

import android.net.Uri
import androidx.room.TypeConverter


class RoomTypeConverter {

    @TypeConverter
    fun fromUri(uri: Uri): String = uri.toString()

    @TypeConverter
    fun toUri(value: String): Uri = Uri.parse(value)

    @TypeConverter
    fun fromUriList(uriList: ArrayList<Uri>): String {
        var uris = ""
        for (uri in uriList) uris += ",$uri"
        return uris
    }

    @TypeConverter
    fun toUriList(uris: String): ArrayList<Uri> {
        val uriList = arrayListOf<Uri>()
        val array = uris.split(",".toRegex()).dropLastWhile {
            it.isEmpty()
        }.toTypedArray()
        for (s in array) {
            if (s.isNotEmpty()) {
                uriList.add(Uri.parse(s))
            }
        }
        return uriList
    }
}