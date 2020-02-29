package com.ysp.camep.ui.videoplayer

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.navigation.navArgs
import com.ysp.camep.R
import com.ysp.camep.databinding.VideoPlayerActivityBinding

class VideoPlayerActivity : AppCompatActivity(R.layout.video_player_activity) {
    private val args: VideoPlayerActivityArgs by navArgs()
    private val binding: VideoPlayerActivityBinding by lazy {
        VideoPlayerActivityBinding.bind(findViewById<ViewGroup>(android.R.id.content)[0]) }
    private val viewModel: VideoPlayerViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ステータスバーとナビゲーションバーを非表示.
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY

        // このアプリ起動中は画面オン時にロックは出ない.
        // 別のアプリやホームに移動するときにロックが出る.
        if (Build.VERSION_CODES.O_MR1 <= Build.VERSION.SDK_INT) {
            setShowWhenLocked(true)
        } else {
            window.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED)
        }

        binding.playerView.player = viewModel.getPlayer()
        viewModel.prepare(args.uris.toList())
    }

    override fun onResume() {
        super.onResume()
        viewModel.play()
    }

    override fun onPause() {
        super.onPause()
        viewModel.pause()
    }
}