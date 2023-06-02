package com.bagusok.github.ui.main

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SearchView
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bagusok.github.R
import com.bagusok.github.databinding.ActivityMainBinding
import com.bagusok.github.ui.data.model.User
import com.bagusok.github.ui.main.favorite.FavoriteUserActivity
import com.bagusok.github.ui.main.setting.*


private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    //private lateinit var viewModel: MainViewModel
    private lateinit var adapter: UserAdapter
    private val viewModel by viewModels<MainViewModel>()


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.option_menu, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.search).actionView as SearchView

        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = resources.getString(R.string.search_hint)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String): Boolean {
                getUser(query)
                searchView.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean = false
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.btn_setting -> {
                Intent(this@MainActivity, SettingActivity::class.java).also {

                    startActivity(it)
                }
            }

        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //darkMode()

        supportActionBar?.title = "GithubUser"

        adapter = UserAdapter()

        adapter.setOnItemClickCallback(object : UserAdapter.OnItemClickCallback {
            override fun onItemClicked(data: User) {
                val i = Intent(this@MainActivity, DetailUserActivity::class.java)
                i.putExtra(DetailUserActivity.EXTRA_USERNAME, data.login)
                startActivity(i)
                Toast.makeText(this@MainActivity, "Clicked: " + data.login, Toast.LENGTH_SHORT)
                    .show()
            }

        })
        /*viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(MainViewModel::class.java)*/

        binding.apply {
            rvParent.layoutManager = LinearLayoutManager(this@MainActivity)
            rvParent.setHasFixedSize(true)
            rvParent.adapter = adapter

            getUser("bagusok")

        }

        viewModel.getSearchUser().observe(this, {
            adapter.setData(it)
            // adapter.setList(it)
            showLoading(false)

        })

        binding.btnGoFav.setOnClickListener {
            val i = Intent(this@MainActivity, FavoriteUserActivity::class.java)
            startActivity(i)
        }

    }


    private fun getUser(query: String) {
        binding.apply {
            showLoading(true)
            viewModel.setSearchUser(query)
        }
    }

    private fun showLoading(state: Boolean) {
        binding.progressBar.visibility = if (state) View.VISIBLE else View.GONE
    }


    fun darkMode() {
        val pref = SettingPreferences.getInstance(dataStore)
        val mainViewModel = ViewModelProvider(this, ViewModelFactory(pref)).get(
            SettingViewModel::class.java
        )
        mainViewModel.getThemeSettings().observe(this,
            { isDarkModeActive: Boolean ->
                if (isDarkModeActive) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                }
            })

    }
}