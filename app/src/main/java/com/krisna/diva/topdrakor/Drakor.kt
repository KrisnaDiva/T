package com.krisna.diva.topdrakor

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Drakor(
    val photo: Int,
    val name: String,
    val description: String,
    val synopsis: String,
    val year: String,
    val genres: String,
    val episodes: String,
    val aired: String,
    val duration: String
): Parcelable