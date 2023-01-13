package com.example.musicplayer.activitys

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Build
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.example.musicplayer.R.*
import com.example.musicplayer.animation.DoAnimation
import com.example.musicplayer.mvvm.model.Song
import com.example.musicplayer.services.MusicPlayerService
import com.masoudss.lib.SeekBarOnProgressChanged
import com.masoudss.lib.WaveformSeekBar
import com.masoudss.lib.utils.WaveGravity
import info.abdolahi.CircularMusicProgressBar
import jp.wasabeef.glide.transformations.BlurTransformation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.Instant
import kotlin.math.ceil
import kotlin.math.floor
import kotlin.time.Duration


class MusicPlayingActivity :
    AppCompatActivity(),
    ServiceConnection,
    SeekBarOnProgressChanged {

    private val TAG = "serviceD"
    private var isLoading = false;

    companion object {
        const val CHANNEL_ID = "channel-1"
        const val PLAY = "play"
        const val NEXT = "next"
        const val PREVIOUS = "previous"
        const val EXIT = "exit"
        var musicPlayerService: MusicPlayerService? = null
        var data = MutableLiveData("IDLE")
    }


    private lateinit var rootView: ConstraintLayout
    private lateinit var background: ImageView
    private lateinit var albumArt: CircularMusicProgressBar
    private lateinit var musicPlayingAlbumName: TextView
    private lateinit var musicName: TextView
    private lateinit var artistName: TextView
    private lateinit var toggleButton: ImageButton
    private lateinit var nextButton: ImageButton
    private lateinit var previousButton: ImageButton
    private lateinit var backButton: ImageButton
    private lateinit var waveForm: WaveformSeekBar
    private var waveFormState = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_music_playing)
        initNotification()
        startService()
        bindViews()
        setViewsEvents()

        MusicPlayerService._currentPlayingSong.observe(this) {
            updateUI(it)
        }


    }

    private fun startService() {
        val intent = Intent(this, MusicPlayerService::class.java)
        bindService(intent, this, BIND_AUTO_CREATE)
        startService(intent)
    }

    override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
        musicPlayerService = (service as MusicPlayerService.MyBinder).currentService()
    }

    override fun onServiceDisconnected(name: ComponentName?) {
    }

    private fun updateUI(song: Song) {
        musicName.text = song.title;
        artistName.text = song.artist;
        musicPlayingAlbumName.text = song.album
        waveForm.progress = 0f
        albumArt.setValueWithNoAnimation(0f)
        DoAnimation.zoomInAnimation(albumArt)

        Glide
            .with(this)
            .load(song.albumArt)
            .placeholder(drawable.disk)
            .error(drawable.disk)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(albumArt)


        waveForm.setSampleFrom(song.data!!)

        DoAnimation.fadeInUpAnimation(waveForm)


    }

    private fun setViewsEvents() {
        waveForm.apply {
            waveGap = 1f
            waveWidth = 14f
            maxProgress = 100f
            waveMinHeight = 0f
            waveGravity = WaveGravity.CENTER
        }
        waveForm.onProgressChanged = this

        lifecycleScope.launch(Dispatchers.Main) {
            var counterForWaveForm = 0;
            while (true) {
                val waveCurrent = MusicPlayerService.currentPlayingDuration
                val waveTotalDuration = MusicPlayerService.totalDuration
                if (MusicPlayerService._isPlaying.value!!) {
                    val percentage = (waveCurrent * 100) / waveTotalDuration.toFloat()
                    albumArt.setValueWithNoAnimation(percentage)

                    if (waveFormState == false) {
                        waveForm.progress = percentage
                    } else {
                        if (counterForWaveForm == 6) {
                            waveFormState = false;
                            counterForWaveForm = 0;
                        } else {
                            counterForWaveForm++;
                        }
                    }

                }

                delay(60)
            }
        }

        backButton.setOnClickListener {
            finish()
            buttonAnimate(it)
        }
        nextButton.setOnClickListener {
            buttonAnimate(it)
            musicPlayerService!!.playNext()
        }
        previousButton.setOnClickListener {
            buttonAnimate(it)
            musicPlayerService!!.playPrevious()
        }
        toggleButton.setOnClickListener {
            musicPlayerService!!.toggleMusic()
            buttonAnimate(toggleButton)

        }

        MusicPlayerService._isPlaying.observe(this) {
            if (it!!) {
                Glide.with(this).load(drawable.pause_button).into(toggleButton)
            } else {
                Glide.with(this).load(drawable.play_button).into(toggleButton)
            }
        }


    }

    private fun initNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                CHANNEL_ID,
                "Now Playing Song",
                NotificationManager.IMPORTANCE_LOW
            )
            notificationChannel.description = "This is a important channel for showing songs!!"
            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(notificationChannel)
        }
    }

    private fun bindViews() {
        toggleButton = findViewById(id.music_play_activity_toggle)
        background = findViewById(id.music_play_activity_background)
        rootView = findViewById(id.music_playing_root)
        albumArt = findViewById(id.album_art)
        musicName = findViewById(id.music_name)
        artistName = findViewById(id.artist_name)
        toggleButton = findViewById(id.music_play_activity_toggle)
        nextButton = findViewById(id.music_playing_activity_next)
        previousButton = findViewById(id.music_playing_activity_previous)
        backButton = findViewById(id.music_playing_activity_back)
        waveForm = findViewById(id.music_playing_activity_waveform)
        musicPlayingAlbumName = findViewById(id.music_playing_album_name)
    }


    private fun getMinute(duration: Double): Int {
        val temp = (getSeconds(duration) / 60F)
        return floor(temp).toInt()
    }

    private fun getSeconds(duration: Double): Int {
        val temp = (duration / 1000);
        return ceil(temp).toInt()
    }


    private fun buttonAnimate(view: View) {
        YoYo
            .with(Techniques.BounceIn)
            .duration(700)
            .repeat(0)
            .playOn(view)
    }

    override fun onProgressChanged(
        waveformSeekBar: WaveformSeekBar,
        progress: Float,
        fromUser: Boolean
    ) {
        if (fromUser) {
            waveFormState = true;
            val onePercent = 1 * MusicPlayerService.totalDuration / 100
            val value = onePercent * progress.toLong()
            MusicPlayerService.exoPlayer.seekTo(value)
        }
    }


}