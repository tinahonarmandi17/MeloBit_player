package com.example.musicplayer.services.notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.example.musicplayer.activitys.MusicPlayingActivity
import com.example.musicplayer.services.MusicPlayerService
import kotlin.system.exitProcess

class NotificationReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        when(intent?.action) {
            MusicPlayingActivity.PREVIOUS -> playPrevious()
            MusicPlayingActivity.PLAY -> toggleMusic()
            MusicPlayingActivity.NEXT -> playNext()
            MusicPlayingActivity.EXIT -> stopApplication()
        }
    }

    private fun playMusic () {
        MusicPlayingActivity.musicPlayerService!!.playMusic();
        MusicPlayingActivity.data.value = "PLAY"
    }

    private fun toggleMusic () {
        MusicPlayingActivity.musicPlayerService!!.toggleMusic()
    }

    private fun stopApplication () {
        MusicPlayingActivity.musicPlayerService!!.stopForeground(true);
        exitProcess(1)
    }

    private  fun playPrevious () {
        MusicPlayingActivity.musicPlayerService!!.playPrevious()
        MusicPlayingActivity.data.value = "PREV"
    }

    private fun playNext() {
        MusicPlayingActivity.musicPlayerService!!.playNext()
        MusicPlayingActivity.data.value = "NEXT"
    }

}