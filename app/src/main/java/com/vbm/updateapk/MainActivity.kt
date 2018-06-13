package com.vbm.updateapk

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onClick(p0: View) {
        var url: String


        url = ""

        if (p0.id == R.id.buttonWMS)
            url = Globals.AXELOTURL

        /* if (p0.id == R.id.buttonZEBRA)
             url = Globals.ZEBRAURL;*/

        if (url.equals("")) {
            Toast.makeText(this, "No url founded!", Toast.LENGTH_SHORT).show()
            return;
        }

        var updateApp: UpdateApp
        updateApp = UpdateApp(this)

        progressBar2.visibility = ProgressBar.VISIBLE
        buttonWMS.isEnabled = false
        updateApp.execute(url)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        progress(false)
        buttonWMS.setOnClickListener(this)
        //buttonZEBRA.setOnClickListener(this)
        Globals.requestAppPermissions(this)
    }

    fun progress(action: Boolean) {
        if( action )
           progressBar2.progress++
        else {
            progressBar2.visibility = ProgressBar.INVISIBLE;
            buttonWMS.isEnabled = true
        }
    }

}
