package com.quyenln.qmeal.ui.favorite.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class FavoriteFragmentAdapter(
    private val fragmentManager: FragmentManager,
    private val fragments: List<Fragment>,
    private val titles: List<String>,
    private val behavior: Int = BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
) : FragmentStatePagerAdapter(fragmentManager, behavior) {

    override fun getCount(): Int = fragments.size

    override fun getItem(position: Int): Fragment = fragments[position]

    override fun getPageTitle(position: Int): CharSequence? = titles[position]
}
