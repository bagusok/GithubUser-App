package com.bagusok.github.ui.main.favorite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bagusok.github.databinding.ActivityFavoriteUserBinding
import com.bagusok.github.ui.data.model.DataFavoriteUser
import com.bagusok.github.ui.data.model.User
import com.bagusok.github.ui.main.DetailUserActivity
import com.bagusok.github.ui.main.UserAdapter


class FavoriteUserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteUserBinding
    private lateinit var adapter: UserAdapter
    private val viewModel by viewModels<FavoriteUserViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = UserAdapter()

        adapter.setOnItemClickCallback(object : UserAdapter.OnItemClickCallback {
            override fun onItemClicked(data: User) {
                val i = Intent(this@FavoriteUserActivity, DetailUserActivity::class.java)
                i.putExtra(DetailUserActivity.EXTRA_USERNAME, data.login)
                startActivity(i)
                Toast.makeText(
                    this@FavoriteUserActivity,
                    "Clicked: " + data.login,
                    Toast.LENGTH_SHORT
                ).show()
            }

        })

        binding.apply {
            rvParent.setHasFixedSize(true)
            rvParent.layoutManager = LinearLayoutManager(this@FavoriteUserActivity)
            rvParent.adapter = adapter
        }

        showLoading(true)

        viewModel.getAllFavoriteUser()?.observe(this, {
            val list = mapList(it)
            adapter.setData(list)
            showLoading(false)

        })
    }

    private fun mapList(users: List<DataFavoriteUser>): ArrayList<User> {
        val listUser = ArrayList<User>()
        for (user in users) {
            val userMapped = User(
                user.username.toString(),
                user.id,
                user.imgUrl.toString(),
                user.htmUrl.toString()

            )
            listUser.add(userMapped)
        }
        return listUser
    }

    private fun showLoading(state: Boolean) {
        binding.progressBar.visibility = if (state) View.VISIBLE else View.GONE
    }

}