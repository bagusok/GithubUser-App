package com.bagusok.github.ui.main

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.viewpager2.widget.ViewPager2
import com.bagusok.github.R
import com.bagusok.github.databinding.ActivityDetailUserBinding
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailUserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        val username = intent.getStringExtra(EXTRA_USERNAME)
        val bundle = Bundle()
        bundle.putString(EXTRA_USERNAME, username)

        val viewModel: DetailViewModel by viewModels()
        viewModel.setUser(username)
        viewModel.getUser().observe(this) {
            if (it != null) {
                binding.apply {
                    longNameVal.text = it.name
                    usernameVal.text = it.login
                    locationVal.text = it.location
                    followersVal.text = it.followers
                    followingVal.text = it.following
                    companyVal.text = it.company
                    repoVal.text = it.publicRepos
                    Glide.with(this@DetailUserActivity)
                        .load(it.avatarUrl)
                        .placeholder(R.drawable.dummy)
                        .centerCrop()
                        .into(avImg)

                    val idFav = it.id
                    val nameFav = it.login
                    val imgFav = it.avatarUrl
                    val htmUrl = it.html_url
                    var _isChecked = false

                    CoroutineScope(Dispatchers.IO).launch {
                        val count = viewModel.checkUser(idFav)
                        withContext(Dispatchers.Main) {
                            if (count != null) {
                                if (count > 0) {
                                    binding.tgFav.isChecked = true
                                    _isChecked = true
                                } else {
                                    binding.tgFav.isChecked = false
                                    _isChecked = false

                                }
                            }
                        }
                    }

                    tgFav.setOnClickListener {
                        _isChecked = !_isChecked
                        if (_isChecked) {
                            viewModel.addToFavorite(idFav, nameFav, imgFav, htmUrl)
                        } else {
                            viewModel.removeFavorite(idFav)
                        }
                        tgFav.isChecked = _isChecked
                    }
                }
            }
        }


        val pagerAdapter = SectionPagerAdapter(this, data = bundle)
        val viewPager: ViewPager2 = binding.viewPager
        viewPager.adapter = pagerAdapter
        val tabs: TabLayout = binding.tabLayout
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()

        supportActionBar?.elevation = 0f

        binding.btnSee.setOnClickListener {
            val goGitHub = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/${username}"))
            startActivity(goGitHub)
        }

        binding.backBtnProfile.setOnClickListener {
            onBackPressed()
        }


    }

    companion object {
        const val EXTRA_USERNAME = "extra_username"

        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.followers,
            R.string.following
        )
    }

    fun showToast(data: Boolean) {
        if (data) {
            Toast.makeText(this, "Sukses Add Data", Toast.LENGTH_SHORT).show()

        } else {
            Toast.makeText(this, "Gagal Add Data", Toast.LENGTH_SHORT).show()

        }
    }
}