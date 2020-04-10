package com.ssk.car.media.player.data.entity

import android.net.Uri
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "playlist_table")
data class Playlist(
        @PrimaryKey(autoGenerate = true) val id: Long = 0,
        @ColumnInfo(name = "name") var name: String,
        @ColumnInfo(name = "description") var description: String = "",
        @ColumnInfo(name = "uris") var uris: ArrayList<Uri> = arrayListOf()
): Parcelable