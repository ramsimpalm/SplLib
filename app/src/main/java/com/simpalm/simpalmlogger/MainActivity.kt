package com.simpalm.simpalmlogger

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.simpalm.loglib.Spl
import com.simpalm.loglib.SplConfig
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import java.lang.RuntimeException


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
            val number = ""
            try {
                val i = Integer.parseInt(number)
            } catch (e: NumberFormatException) {
                Spl.v(this, e)
                Spl.v(e, "Unable to parse $number", e)
                //throw RuntimeException("This is a run time crash");
            }
        }

        sendLogBtn.setOnClickListener {
            val body = SplConfig.get().allLogs
            val sharingIntent = Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto","ram@simpalm.com", null));
            sharingIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name))
            sharingIntent.putExtra(Intent.EXTRA_TEXT, body)
            startActivity(Intent.createChooser(sharingIntent, resources.getString(R.string.Share_Using)))
        }

        Spl.v(this, "test v log")
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Spl.v(this, "test v log menu item selected")
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
