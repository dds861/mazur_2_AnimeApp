package com.wallpapers.cartoons.ui.content

import com.wallpapers.cartoons._core.BaseActivity
import com.wallpapers.cartoons.R


/**
 * Created by Andriy Deputat email(andriy.deputat@gmail.com) on 3/13/19.
 *
 * TODO: add here content for bots or unsuitable users
 */
class ContentActivity : BaseActivity() {
    override fun getContentView(): Int = R.layout.activity_content

    override fun initUI() {
    }

    override fun setUI() {
        logEvent("content-screen")
    }
}