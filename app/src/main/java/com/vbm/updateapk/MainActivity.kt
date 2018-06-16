package com.vbm.updateapk

import android.content.Intent
import android.os.Bundle
import android.os.StrictMode
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.system.exitProcess


class MainActivity : MyAppCompatActivity(), View.OnClickListener {
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item == null) return false
        when (item.itemId) {
            R.id.server -> raiseSettingsActivity(Globals.SettingsItem.SERVER)
            R.id.exit -> exitProcess(0)
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.mainmenu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onClick(p0: View?) {
        val url: String
        url = Globals.AXELOTURL

        val updateApp: UpdateApp
        updateApp = UpdateApp(this)
        updateApp.execute(url)

        progressBar2.visibility = ProgressBar.VISIBLE
        buttonWMS.isEnabled = false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progress(false)
        buttonWMS.setOnClickListener(this)
        Globals.requestAppPermissions(this)
        val builder = StrictMode.VmPolicy.Builder()
        StrictMode.setVmPolicy(builder.build())
    }


    fun progress(action: Boolean) {
        if (action)
            progressBar2.progress++
        else {
            progressBar2.visibility = ProgressBar.INVISIBLE
            buttonWMS.isEnabled = true
        }
    }

    fun raiseSettingsActivity(settingsType: Globals.SettingsItem) = when (settingsType) {
        Globals.SettingsItem.SERVER -> {
            val intent: Intent
            intent = Intent(this.applicationContext, SettingsActivity::class.java)
            intent.putExtra(Globals.SETTINGSKEY, Globals.SERVERVALUE)
            startActivity(intent)
        }
    }

}
