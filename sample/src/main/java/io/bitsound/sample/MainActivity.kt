package io.bitsound.sample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import io.bitsound.siggen.RuntimeHelper
import io.bitsound.siggen.SiggenInterface

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        RuntimeHelper.loadNativeLibrary(this, "siggen")

        val siggen = SiggenInterface()
        Log.d("MainActivity", "Packet Size : " + siggen.packetSize)
    }
}
