//package com.yedebkid.newsapp.viewModel
//
//import androidx.arch.core.executor.testing.InstantTaskExecutorRule
//import com.yedebkid.newsapp.rest.NewsRepository
//import com.yedebkid.newsapp.util.UIState
//import com.google.common.truth.Truth.assertThat
//import io.mockk.MockKAnnotations
//import io.mockk.clearAllMocks
//import io.mockk.every
//import io.mockk.impl.annotations.MockK
//import io.mockk.mockk
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.ExperimentalCoroutinesApi
//import kotlinx.coroutines.flow.flowOf
//import kotlinx.coroutines.test.UnconfinedTestDispatcher
//import kotlinx.coroutines.test.resetMain
//import kotlinx.coroutines.test.setMain
//
//import org.junit.After
//import org.junit.Assert.assertEquals
//import org.junit.Before
//import org.junit.Rule
//import org.junit.Test
//
//@OptIn(ExperimentalCoroutinesApi::class)
//class NewsViewModelTest {
//    @get:Rule val taskRule = InstantTaskExecutorRule()
//    private val testDispatcher = UnconfinedTestDispatcher()
//
//    private lateinit var  testObject: NewsViewModel
//    private val mockNewsRepo = mockk<NewsRepository>(relaxed = true)
//
//    @Before
//    fun setUp() {
//        testObject = NewsViewModel(mockNewsRepo, testDispatcher)
//        Dispatchers.setMain(testDispatcher)
//    }
//
//    @After
//    fun tearDown() {
//        clearAllMocks()
//        Dispatchers.resetMain()
//    }
//
//    @Test
//    fun getLiveNewsONlyWhenTheNetworkCallReturnsSucess() {
//        //Arrange
//        val states = mutableListOf<UIState>()
//        every { mockNewsRepo.getAllLNewsHere() } returns flowOf(
//            UIState.SUCCESS(listOf(mockk(), mockk(), mockk()))
//        )
//        testObject.allNews.observeForever{
//            states.add(it)
//        }
//        //Act
//        mockNewsRepo.getAllLNewsHere()
//
//        //Assert
//        assertEquals(2,states.size)
//        assertThat(states[0]).isInstanceOf(UIState.LOADING::class.java)
//        assertThat(states[1]).isInstanceOf(UIState.SUCCESS::class.java)
//        assertEquals(3, (states[1] as UIState.SUCCESS<*>).data.size)
//
//    }
//
//    @Test
//    fun getLiveNewsONlyReturnsErrorFollowingTheNetworkCall() {
//        // Arange
//
//        val states = mutableListOf<UIState>()
//        every { mockNewsRepo.getAllLNewsHere() } returns flowOf(
//            UIState.ERROR(Exception("Error"))
//        )
//
//        testObject.allNews.observeForever {
//            states.add(it)
//        }
//
//        // ACTION - here you perform the action to be tested
//        testObject.allNews
//
//        // ASSERTION - here you assert values fro the testing
//        assertEquals(2, states.size)
//        assertThat(states[0]).isInstanceOf(UIState.LOADING::class.java)
//        assertThat(states[1]).isInstanceOf(UIState.ERROR::class.java)
//        assertThat((states[1] as UIState.ERROR).error.localizedMessage).isEqualTo("Error")
//    }
//}