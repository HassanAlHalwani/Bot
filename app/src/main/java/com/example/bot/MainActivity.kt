package com.example.bot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.media.MediaPlayer


class MainActivity : AppCompatActivity() {
    var mMediaPlayer: MediaPlayer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val smurfButton : Button = findViewById(R.id.SmurfButton)
        val visitorButton : Button = findViewById(R.id.visitorButton)



        playSound()

        smurfButton.setOnClickListener{
            stopSound()
        val intent = Intent(this,Smurfs::class.java);
            startActivity(intent)
        }

        visitorButton.setOnClickListener{
            stopSound()
            val intent = Intent(this,Visitor::class.java);
            startActivity(intent)
        }
    }
    fun playSound() {
        if (mMediaPlayer == null) {
            mMediaPlayer = MediaPlayer.create(this, R.raw.sound)
            mMediaPlayer!!.isLooping = true
            mMediaPlayer!!.start()
        } else mMediaPlayer!!.start()
    }

    fun stopSound() {
        if (mMediaPlayer != null) {
            mMediaPlayer!!.stop()
            mMediaPlayer!!.release()
            mMediaPlayer = null
        }
    }

    fun pauseSound() {
        if (mMediaPlayer != null && mMediaPlayer!!.isPlaying) mMediaPlayer!!.pause()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.admin_item -> {
                val intent = Intent(this,AdminActivity::class.java);
                startActivity(intent)

                }

            R.id.about_item ->{
                var dialog = aboutfragment()
                dialog.show(supportFragmentManager, "RateDialog")
            }

            R.id.pauseMusic-> pauseSound()
            R.id.startMusic-> playSound()
        }
        return true;
    }
    }