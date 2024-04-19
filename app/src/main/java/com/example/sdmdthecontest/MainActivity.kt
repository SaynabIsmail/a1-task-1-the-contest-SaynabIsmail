package com.example.sdmdthecontest
import android.annotation.SuppressLint
import android.graphics.Color
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    //Private variable to set inital increment number
    private var incrementnumber = 0
    //Private variables to create MediaPlayer instances
    private lateinit var scoresound: MediaPlayer
    private lateinit var stealsound: MediaPlayer
    private lateinit var resetsound: MediaPlayer
    private lateinit var winnersound: MediaPlayer


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Values to define id's
        val numberadd = findViewById<TextView>(R.id.numberadd)
        val score = findViewById<Button>(R.id.Score)
        val steal = findViewById<Button>(R.id.Steal)
        val reset = findViewById<Button>(R.id.Reset)

        scoresound = MediaPlayer.create(this, R.raw.positive)
        stealsound = MediaPlayer.create(this, R.raw.negative)
        resetsound = MediaPlayer.create(this, R.raw.reset)
        winnersound = MediaPlayer.create(this, R.raw.winner)

        //Function to add color when between certain scores
        fun color() {
            when (incrementnumber) {
                in 5..9 -> numberadd.setTextColor(Color.parseColor("#00FF00"))
                in 10..15 -> numberadd.setTextColor(Color.parseColor("#FF008D"))
                else -> numberadd.setTextColor(Color.parseColor("#000000"))
            }
        }
        //Displays score saved
        if(savedInstanceState != null){
            incrementnumber = savedInstanceState.getInt("increment_number")
            numberadd.text = incrementnumber.toString()
            color()
        }

            //score increment on click
            score.setOnClickListener {
                if (incrementnumber < 15) {
                    incrementnumber += 1
                    check()
                    color()
                    //log displayed in logcat
                    Log.d("MainActivity", "Number Scored!! Score is now: $incrementnumber")
                    //plays audio when sound is clicked
                    scoresound.start()
                }
                //converts number to string
                numberadd.text = incrementnumber.toString()

            }
             // score stolen on click
             steal.setOnClickListener {
                if (incrementnumber in 0..14) {
                    incrementnumber -= 1
                    check()
                    color()
                    //log displayed in logcat
                    Log.d("MainActivity", "Number Stolen!!, Score is now: $incrementnumber")
                    //plays audio when sound is clicked
                    stealsound.start()
                }
                 //converts number to string
                numberadd.text = incrementnumber.toString()

             }

            // score reset on click
            reset.setOnClickListener {
                if (incrementnumber >= 0) {
                    incrementnumber = 0
                    check()
                    color()
                    //log displayed in logcat
                    Log.d("MainActivity", "Number Reset!!, Score is now: $incrementnumber")
                    //plays audio when sound is clicked
                    resetsound.start()
                }
                //converts number to string
                numberadd.text = incrementnumber.toString()


            }



        }


    private fun check(){
        if (incrementnumber == 15) {
            winnersound.setOnPreparedListener {
                winnersound.start()
                Log.d("MainActivity", "Media Player Started")
            }
            winnersound.prepareAsync()
        }else{
            winnersound.stop()
            Log.d("MainActivity", "Media Player Stopped")

        }
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("increment_number", incrementnumber )
    }
    }











