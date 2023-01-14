package com.example.musicplayer.models

import android.content.ContentUris
import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Song(
    var id: String?,
    var artist: String?,
    var title: String?,
    var data: Uri?,
    var displayName: String?,
    var duration: String?,
    var albumArt: String?,
    var album: String? = null

) : Parcelable {

    class Builder(
        private var id: String? = null,
        private var artist: String? = null,
        private var title: String? = null,
        private var data: Uri? = null,
        private var displayName: String? = null,
        private var duration: String? = null,
        private var albumArt: String? = null,
        private var album: String? = null
    ) {
        fun id(id: String) = apply {
            this.id = id
            albumArt(id)
        }

        fun artist(artist: String) = apply { this.artist = artist }
        fun title(title: String) = apply { this.title = title }
        fun data(data: String) = apply {
            val uri = ContentUris.withAppendedId(
                android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                id!!.toLong()
            )
            this.data = uri
        }
        fun data(data: String , isInternet : Boolean) = apply {
            this.data = Uri.parse(data)
        }

        fun displayName(displayName: String) = apply { this.displayName = displayName }
        fun duration(duration: String) = apply { this.duration = duration }
        private fun albumArt(id: String) = apply {
            this.albumArt =
                "content://media/external/audio/media/${id}/albumart"
        }

        fun album(album: String) = apply { this.album = album }
        fun build() = Song(id, artist, title, data, displayName, duration, albumArt);
    }


}
