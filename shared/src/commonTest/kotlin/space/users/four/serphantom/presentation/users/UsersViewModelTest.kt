package space.users.four.serphantom.presentation.users

import app.cash.turbine.test
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import space.users.four.serphantom.core.result.ExecutionResult
import space.users.four.serphantom.domain.model.User
import space.users.four.serphantom.domain.repository.UserRepository
import space.users.four.serphantom.domain.usecase.GetUsersUseCase
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNull
import kotlin.test.assertTrue

/**
 * Тесты [UsersViewModel]: проверяют, что состояние экрана корректно отражает
 * успех и ошибку загрузки, полученные из [GetUsersUseCase].
 *
 * `viewModelScope` завязан на `Dispatchers.Main`, поэтому Main подменяется на
 * [UnconfinedTestDispatcher] — стартовый `init { Refresh }` отрабатывает синхронно,
 * и к моменту подписки через Turbine состояние уже терминальное.
 */
@OptIn(ExperimentalCoroutinesApi::class)
class UsersViewModelTest {

    private val testDispatcher = UnconfinedTestDispatcher()

    @BeforeTest
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun refreshPopulatesUsersOnSuccess() = runTest {
        val users =
            listOf(
                User(id = "1", name = "Ann", email = "ann@four.users", avatarUrl = ""),
                User(id = "2", name = "Bob", email = "bob@four.users", avatarUrl = ""),
            )
        val viewModel = viewModelWith(ExecutionResult.Success(users))

        viewModel.state.test {
            val state = awaitItem()
            assertFalse(state.isLoading)
            assertEquals(users, state.users)
            assertNull(state.error)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun refreshExposesMessageOnError() = runTest {
        val message = "Сервис недоступен"
        val viewModel = viewModelWith(ExecutionResult.Error(code = 503, message = message))

        viewModel.state.test {
            val state = awaitItem()
            assertFalse(state.isLoading)
            assertTrue(state.users.isEmpty())
            assertEquals(message, state.error)
            cancelAndIgnoreRemainingEvents()
        }
    }

    /**
     * Собирает [UsersViewModel] поверх [FakeUserRepository] с заранее заданным [result].
     *
     * @param [result] Результат, который вернёт репозиторий на `getUsers()`.
     * @return ViewModel, у которой стартовый `Refresh` уже отработал.
     */
    private fun viewModelWith(result: ExecutionResult<List<User>>): UsersViewModel =
        UsersViewModel(GetUsersUseCase(FakeUserRepository(result)))
}

/**
 * Тестовый дублёр [UserRepository]: всегда возвращает переданный [result].
 *
 * @param [result] Фиксированный результат для всех вызовов `getUsers()`.
 */
private class FakeUserRepository(
    private val result: ExecutionResult<List<User>>,
) : UserRepository {

    override suspend fun getUsers(): ExecutionResult<List<User>> = result
}
