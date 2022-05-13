package com.picpay.desafio.android

import com.picpay.desafio.android.network.users.IRepositoryUser
import com.picpay.desafio.android.network.users.models.User
import com.picpay.desafio.android.ui.UserViewModel
import com.picpay.desafio.android.utis.CustomResult
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
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
    private val testCoroutineDispatcher = TestCoroutineDispatcher()

    @Before
    fun init() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        Dispatchers.setMain(testCoroutineDispatcher)
        viewModel = UserViewModel(repository)
    }

    private fun addItemList() = listOf(
        User("ABC", "ABC", 1, "ABC"),
        User("DEF", "DEF", 2, "DEF"),
        User("GHI", "GHI", 3, "GHI")
    )

    @Test
    fun `validates call to getUsers when successful()`() {
        val response = addItemList()
        testCoroutineDispatcher.runBlockingTest {
            coEvery { repository.getUsers() } returns CustomResult.CustomResultSuccess(
                response
            )
        }
        assertEquals(response, addItemList())
    }

    @Test
    fun `validates call to getUsers when error()`() {
        testCoroutineDispatcher.runBlockingTest {
            coEvery { repository.getUsers() } returns CustomResult.CustomResultError(
                "Houve um Erro !"
            )
        }
        val error = mutableListOf<CustomResult.CustomResultError>()
        assertEquals(error, error)
    }
}