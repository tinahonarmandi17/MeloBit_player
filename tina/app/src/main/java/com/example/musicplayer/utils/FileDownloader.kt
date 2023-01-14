package com.example.musicplayer.utils

import android.annotation.SuppressLint
import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Context.DOWNLOAD_SERVICE
import android.content.Intent
import android.os.Environment
import android.widget.Toast
import androidx.core.net.toUri
import com.downloader.PRDownloader
import com.downloader.PRDownloaderConfig


class FileDownloader {


    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context

        fun setInitContext(context: Context) {
            this.context = context
            val config = PRDownloaderConfig.newBuilder()
                .setReadTimeout(30000)
                .setConnectTimeout(30000)
                .setDatabaseEnabled(true)
                .build()
            PRDownloader.initialize(Companion.context, config)
        }

        fun download(url: String , fileName : String , fileType : String) {

            Toast.makeText(context, "Download Music ${fileName} Started !", Toast.LENGTH_SHORT).show()
            val request = DownloadManager.Request(url.toUri())
            request.setTitle("Music Player");
            request.setDescription("Downloading Music ....")
            request.setMimeType(fileType)
            val cookie = android.webkit.CookieManager.getInstance().getCookie(url)
            request.addRequestHeader("cookie",cookie)
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE)
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_MUSIC,fileName + fileType)
            val downloadManager = context.getSystemService(DOWNLOAD_SERVICE) as DownloadManager
            downloadManager.enqueue(request)


        }






    }



}