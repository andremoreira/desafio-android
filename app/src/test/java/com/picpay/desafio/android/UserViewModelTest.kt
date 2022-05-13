package com.picpay.desafio.android

import com.picpay.desafio.android.network.users.IRepositoryUser
import com.picpay.desafio.android.network.users.models.User
import com.picpay.desafio.android.ui.UserViewModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class UserViewModelTest {

    @MockK
    lateinit var repository: IRepositoryUser

    @MockK
    private lateinit var viewModel: UserViewModel
    private val mainThreadSurrogate = TestCoroutineDispatcher()

    @Before
    fun init() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        Dispatchers.setMain(mainThreadSurrogate)
        viewModel = UserViewModel(repository)
    }

    private fun addItemList() = listOf(
        User("ABC", "ABC", 1, "ABC"),
        User("DEF", "DEF", 2, "DEF"),
        User("GHI", "GHI", 3, "GHI")
    )

    @Test
    fun `Given list users WHEN fun getUsers is called THEN return list`() {
        val responseList = addItemList()
        coEvery {
            repository.getUsers()
        }
        assertEquals(addItemList(), responseList)
    }
}