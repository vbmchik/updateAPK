package com.vbm.updateapk

import android.support.v7.app.AppCompatActivity
import android.widget.ProgressBar
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

open class MyAppCompatActivity : AppCompatActivity() {

    fun exceptmsg(str: String) {
        Toast.makeText(this, str + "\n" + "Свяжитесь с системным администратором", Toast.LENGTH_LONG).show()
        if (this::class == MainActivity::class) {
            buttonWMS.isEnabled = true
            progressBar2.visibility = ProgressBar.INVISIBLE
        }
    }
}