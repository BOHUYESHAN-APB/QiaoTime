package com.qiaotime.poc

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.chrisbanes.photoview.PhotoView
import coil.load

class ImagePreviewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val pv = PhotoView(this)
        setContentView(pv)

        val path = intent.getStringExtra("path")
        // For assets, Coil can load via "file:///android_asset/" prefix
        if (path != null) {
            val assetUri = "file:///android_asset/$path"
            pv.load(assetUri)
        }
    }
}
