package com.picpay.desafio.android.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.picpay.desafio.android.R
import com.picpay.desafio.android.helper.hideView
import com.picpay.desafio.android.helper.showView
import com.picpay.desafio.android.network.RetrofitConfig
import com.picpay.desafio.android.network.users.models.User
import com.picpay.desafio.android.ui.adapter.UserAdapter
import com.picpay.desafio.android.utis.Utils
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
        RetrofitConfig.setContext(this@MainActivity)
        if (!Utils.isInternetAvailable(this@MainActivity)) {
            snackBar(getString(R.string.message_internet), false)
        }
        userViewModel.getUsers()
        initObservers()
        setupRecyclerView()
    }

    private fun initObservers() {
        userViewModel._listUser.listen(this, ::sucessUser)
        userViewModel._errorUser.listen(this, ::errorUser)
        user_list_progress_bar.showView()
    }

    private fun sucessUser(user: List<User>) {
        userAdapter.users = user
        user_list_progress_bar.hideView()
    }

    private fun errorUser(error: String) {
        snackBar(getString(R.string.message_erro), true)
    }

    private fun snackBar(message: String, action: Boolean) {
        when {
            action -> {
                Snackbar.make(user_list_recycler, message, Snackbar.LENGTH_INDEFINITE)
                    .setAction(getString(R.string.try_again)) {
                        userViewModel.getUsers()
                    }
            }
            else -> {
                Snackbar.make(user_list_recycler, message, Snackbar.LENGTH_LONG)
            }
        }.show()

        user_list_progress_bar.hideView()
    }

    private fun setupRecyclerView() {
        user_list_recycler.adapter = userAdapter
        user_list_recycler.layoutManager =
            LinearLayoutManager(this, RecyclerView.VERTICAL, false)
    }
}
