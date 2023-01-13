
package com.example.musicplayer.activitys

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.musicplayer.R
import com.example.musicplayer.splash.AppMainPage
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener


class MainActivity : AppCompatActivity() {

companion object {
    const val TAG = "click";
}


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide();
        val goInsideButton : Button = findViewById(R.id.inside)


        println("")

        Dexter.withContext(this)
            .withPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE)
            .withListener(object : PermissionListener {
                override fun onPermissionGranted(response: PermissionGrantedResponse) {
                    startActivity(Intent(this@MainActivity, AppMainPage::class.java))
                }

                override fun onPermissionDenied(response: PermissionDeniedResponse) {
//                    Snackbar.make(this@MainActivity,window.decorView,"Permission Denied !",Snackbar.LENGTH_SHORT).show();
                }

                override fun onPermissionRationaleShouldBeShown(
                    permission: PermissionRequest?,
                    token: PermissionToken?
                ) {
                }
            }).check()

        goInsideButton.setOnClickListener {
            startActivity(Intent(this@MainActivity, AppMainPage::class.java))
        }

    }
}