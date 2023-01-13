package com.example.musicplayer.animation

import android.view.View
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo

class DoAnimation {

    companion object {
        fun fadeOutAnimation ( view : View) {
            YoYo
                .with(Techniques.FadeOutUp)
                .duration(700)
                .repeat(0)
                .playOn(view)
        }
        fun fadeInUpAnimation ( view : View) {
            YoYo
                .with(Techniques.FadeInUp)
                .duration(700)
                .repeat(0)
                .playOn(view)
        }

        fun rotateInAnimation ( view : View) {
            YoYo
                .with(Techniques.RotateIn)
                .duration(700)
                .repeat(0)
                .playOn(view)
        }
        fun zoomInAnimation ( view : View) {
            YoYo
                .with(Techniques.ZoomIn)
                .duration(700)
                .repeat(0)
                .playOn(view)
        }





    }
}