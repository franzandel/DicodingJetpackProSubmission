package com.franzandel.dicodingjetpackprosubmission.ui.bookmark

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.franzandel.dicodingjetpackprosubmission.R

/**
 * Created by Franz Andel on 27/03/21.
 * Android Engineer
 */

class BookmarkTabAdapter(
    private val context: Context,
    private val fragments: List<Fragment>,
    fragmentManager: FragmentManager
) : FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getCount(): Int = fragments.size

    override fun getItem(position: Int): Fragment = fragments[position]

    override fun getPageTitle(position: Int): String =
        context.resources.getStringArray(R.array.bookmark_tab_title)[position]
}