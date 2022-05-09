package com.picpay.desafio.android.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.R
import com.picpay.desafio.android.network.users.models.User
import com.picpay.desafio.android.ui.adapter.UserAdapter
import com.picpay.desafio.android.utis.listen
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val userAdapter by lazy { UserAdapter() }
    private val userViewModel: UserViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        userViewModel.getUsers()
        initObservers()
        setupRecyclerView()
    }

    private fun initObservers() {
        userViewModel.listUser.listen(this, ::sucessUser)
        userViewModel.errorUser.listen(this, ::errorUser)
        user_list_progress_bar?.visibility = View.VISIBLE
    }

    private fun sucessUser(user: List<User>) {
        userAdapter.users = user
        user_list_progress_bar?.visibility = View.GONE
    }

    private fun errorUser(error: String) {
        user_list_progress_bar?.visibility = View.GONE
        Toast.makeText(this@MainActivity, error, Toast.LENGTH_SHORT).show()
    }

    private fun setupRecyclerView() {
        user_list_recycler.adapter = userAdapter
        user_list_recycler.layoutManager =
            LinearLayoutManager(this, RecyclerView.VERTICAL, false)
    }
}
