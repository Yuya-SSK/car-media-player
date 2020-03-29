package com.ysp.camep.ui.video

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.ssk.car.media.player.log.YLog
import com.ysp.camep.R
import com.ysp.camep.ui.videocontents.VideoContentsFragment
import com.ysp.camep.ui.videoplaylist.VideoPlaylistFragment

class VideoTabAdapter(fm: FragmentManager, context: Context) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    private data class Page(val fragment: Fragment, val title: String)

    private val pages: List<Page>

    init {
        YLog.methodIn()
        pages = listOf(
            Page(VideoContentsFragment(), context.getString(R.string.video_contents)),
            Page(VideoPlaylistFragment(), context.getString(R.string.video_playlist))
        )
    }

    override fun getItem(position: Int): Fragment {
        return pages[position].fragment
    }

    override fun getPageTitle(position: Int): CharSequence {
        return pages[position].title
    }

    override fun getCount(): Int {
        return pages.size
    }
}