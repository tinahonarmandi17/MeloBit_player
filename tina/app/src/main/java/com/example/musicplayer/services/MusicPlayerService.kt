package com.example.musicplayer.services

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.media.MediaMetadataRetriever
import android.os.Binder
import android.os.IBinder
import android.support.v4.media.session.MediaSessionCompat
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.LifecycleService
import androidx.lifecycle.MutableLiveData
import androidx.media.app.NotificationCompat.*
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.musicplayer.R
import com.example.musicplayer.activitys.MusicPlayingActivity
import com.example.musicplayer.mvvm.model.Song
import com.example.musicplayer.services.notification.NotificationReceiver
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.MediaMetadata
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.offline.DownloadService.startForeground
import kotlinx.coroutines.*


class MusicPlayerService : Service(), Player.Listener {

    private var songs: ArrayList<Song> = ArrayList()
    private var myBinder = MyBinder();
    private lateinit var mediaSessionCompat: MediaSessionCompat
    private val job = SupervisorJob()
    private val scope = CoroutineScope(Dispatchers.Main + job)


    companion object {
        lateinit var exoPlayer: ExoPlayer
        const val TAG = "serviceD"
        var _currentPlayingIndex = MutableLiveData(2);
        var _songs: MutableLiveData<ArrayList<Song>> = MutableLiveData()
        var _isPlaying: MutableLiveData<Boolean> = MutableLiveData(false)
        var _currentDuration: MutableLiveData<Long> = MutableLiveData()
        var _currentPlayingSong: MutableLiveData<Song> = MutableLiveData()
        var currentPlayingDuration = 0L;
        var totalDuration = 0L;
    }


    override fun onBind(intent: Intent?): IBinder {
        _songs.observeForever {
            songs = it
        }
        mediaSessionCompat = MediaSessionCompat(baseContext, "My Music")
        exoPlayer = ExoPlayer.Builder(this).build()
        exoPlayer.addListener(this)
        scope.launch(Dispatchers.Main) {
            while (true) {
                currentPlayingDuration = exoPlayer.currentPosition
                delay(50)
            }
        }
        return myBinder;
    }


    override fun onPlaybackStateChanged(playbackState: Int) {
        super.onPlaybackStateChanged(playbackState)
        if (playbackState == 3) {
            totalDuration = exoPlayer.duration
        }
    }


    override fun onIsPlayingChanged(state: Boolean) {
        showNotification()
        _isPlaying.value = state
        super.onIsPlayingChanged(state)
    }


    inner class MyBinder : Binder() {
        fun currentService(): MusicPlayerService {
            return this@MusicPlayerService
        }
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        playMusic()
        return super.onStartCommand(intent, flags, startId)
    }


    @SuppressLint("InlinedApi")
    private fun showNotification() {

        val prevIntent = Intent(
            baseContext,
            NotificationReceiver::class.java
        ).setAction(MusicPlayingActivity.PREVIOUS)
        val prevPendingIntent =
            PendingIntent.getBroadcast(baseContext, 0, prevIntent, PendingIntent.FLAG_IMMUTABLE)


        val playIntent = Intent(
            baseContext,
            NotificationReceiver::class.java
        ).setAction(MusicPlayingActivity.PLAY)
        val playPendingIntent =
            PendingIntent.getBroadcast(baseContext, 0, playIntent, PendingIntent.FLAG_IMMUTABLE)


        val nextIntent = Intent(
            baseContext,
            NotificationReceiver::class.java
        ).setAction(MusicPlayingActivity.NEXT)
        val nextPendingIntent =
            PendingIntent.getBroadcast(baseContext, 0, nextIntent, PendingIntent.FLAG_IMMUTABLE)


        val exitIntent = Intent(
            baseContext,
            NotificationReceiver::class.java
        ).setAction(MusicPlayingActivity.EXIT)
        val exitPendingIntent =
            PendingIntent.getBroadcast(baseContext, 0, exitIntent, PendingIntent.FLAG_IMMUTABLE)







        Glide.with(this)
            .asBitmap()
            .load(songs[_currentPlayingIndex.value!!].albumArt)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .error(R.drawable.disk)
            .into(object : CustomTarget<Bitmap>() {
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    val notification =
                        NotificationCompat.Builder(baseContext, MusicPlayingActivity.CHANNEL_ID)
                            .setContentTitle(songs[_currentPlayingIndex.value!!].title)
                            .setContentText(songs[_currentPlayingIndex.value!!].artist)
                            .setSmallIcon(R.drawable.app_icon)
                            .setStyle(MediaStyle().setMediaSession(mediaSessionCompat.sessionToken))
                            .setPriority(NotificationCompat.PRIORITY_LOW)
                            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                            .setOnlyAlertOnce(true)
                            .addAction(R.drawable.prev_button, "prev", prevPendingIntent)
                            .addAction(
                                if (_isPlaying.value == true) R.drawable.play_button else R.drawable.pause_button,
                                "play",
                                playPendingIntent
                            )
                            .addAction(R.drawable.next_button, "next", nextPendingIntent)
                            .addAction(R.drawable.arrow_back, "close", exitPendingIntent)
                    notification.setLargeIcon(resource)
                    startForeground(13, notification.build())
                }

                override fun onLoadCleared(placeholder: Drawable?) {

                }
            })


    }

    fun toggleMusic() {
        exoPlayer.playWhenReady = !exoPlayer.isPlaying
    }

    fun pauseMusic() {
        exoPlayer.playWhenReady = false;
    }

    fun playMusic() {
        val mediaItem: MediaItem = MediaItem.fromUri(songs[_currentPlayingIndex.value!!].data!!)
        exoPlayer.setMediaItem(mediaItem)
        exoPlayer.prepare()
        exoPlayer.play()
        _currentPlayingSong.value = songs[_currentPlayingIndex.value!!]
    }

    fun playPrevious() {
        if (_currentPlayingIndex.value == 0)
            _currentPlayingIndex.value = songs.size - 1
        else {
            _currentPlayingIndex.value = _currentPlayingIndex.value!! - 1
        }
        val firstItem: MediaItem = MediaItem.fromUri(songs[_currentPlayingIndex.value!!].data!!)
        exoPlayer.setMediaItem(firstItem)
        exoPlayer.prepare()
        exoPlayer.play()
        _currentPlayingSong.value = songs[_currentPlayingIndex.value!!]

    }

    fun playNext() {
        if (_currentPlayingIndex.value == songs.size - 1)
            _currentPlayingIndex.value = 0
        else {
            _currentPlayingIndex.value = _currentPlayingIndex.value!! + 1
        }
        val firstItem: MediaItem = MediaItem.fromUri(songs[_currentPlayingIndex.value!!].data!!)
        exoPlayer.setMediaItem(firstItem)
        exoPlayer.prepare()
        exoPlayer.play()
        _currentPlayingSong.value = songs[_currentPlayingIndex.value!!]

    }


}
