package com.vbm.updateapk

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : AppCompatActivity() {

    private lateinit var key: String;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        key = intent.getStringExtra(Globals.SETTINGSKEY)

        when (key) {
            Globals.SERVERVALUE -> {
                var s: String = Globals.readFromSettings(this, Globals.SERVERVALUE)
                if (s.equals("")) s = Globals.AXELOTURL
                editText.setText(s)
                s = Globals.readFromSettings(this, Globals.REPOTAG)
                if (s.equals("")) s = Globals.REPOURL
                editText2.setText(s)
            }
        }

        button.setOnClickListener({
            run {
                when (key) {
                    Globals.SERVERVALUE -> Globals.AXELOTURL = editText.text.toString()
                    Globals.REPOVALUE -> Globals.REPOURL = editText2.text.toString()
                }
                if (editText.text.length > 0) {
                    Globals.writeToSettings(this.applicationContext, Globals.SERVERVALUE, editText.text.toString())
                    Globals.AXELOTURL = editText.text.toString()
                }
                if (editText2.text.length > 0) {
                    Globals.writeToSettings(this.applicationContext, Globals.REPOTAG, editText2.text.toString())
                    Globals.REPOURL = editText2.text.toString()
                }
                finish()
            }
        })

        readbutton.setOnClickListener {
            run {
                if (editText2.text.length != 0) {
                    val updateApp: UpdateApp = UpdateApp(this);
                    updateApp.execute("repo", editText2.text.toString())
                }

            }
        }
    }

    fun repoUpdate(repoString: String) {
        editText.setText(repoString)
    }
}
