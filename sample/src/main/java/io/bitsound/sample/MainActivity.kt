package io.bitsound.sample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import io.bitsound.siggen2.RuntimeHelper
import io.bitsound.siggen2.Siggen2Interface

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        RuntimeHelper.loadNativeLibrary(this, "siggen2")

        val siggen2 = Siggen2Interface()
        Log.d("MainActivity", "Packet Size : " + siggen2.packetSize)
    }
}
