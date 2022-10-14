//package com.yedebkid.newsapp.rest
//
//import com.yedebkid.newsapp.util.UIState
//import com.google.common.truth.Truth.assertThat
//import com.yedebkid.newsapp.model.domain.NewsItemDomain
//import io.mockk.clearAllMocks
//import io.mockk.coEvery
//import io.mockk.every
//import io.mockk.mockk
//import kotlinx.coroutines.ExperimentalCoroutinesApi
//import kotlinx.coroutines.test.runTest
//
//import org.junit.After
//import org.junit.Before
//import org.junit.Test
//
//@OptIn(ExperimentalCoroutinesApi::class)
//class NewsRepositoryImplementationTest {
//
//    private val mokkNewsApi = mockk<NewsApi>(relaxed = true)
//    private lateinit var newsRepo: NewsRepository
//
//    @Before
//    fun setUp(){
//        newsRepo = NewsRepositoryImplementation(mokkNewsApi)
//    }
//
//    @After
//    fun tearDown(){
//        clearAllMocks()
//    }
//
//    @Test
//    fun getTopStoriesNewsWhenTheNetworkConnectionIsSuccessfulAndReturnsSuccess() = runTest {
//    //Arrange
//        coEvery { mokkNewsApi.getTopStoriesNews()} returns mockk{
//            every { isSuccessful } returns true
//            every { body() } returns listOf(
//                mockk(relaxed = true),
//                mockk(relaxed = true),
//                mockk(relaxed = true)
//            )
//        }
//        val states = mutableListOf<UIState>()
//        //Act
//        newsRepo.getTopStoriesNewsHere().collect() {
//            states.add(it)
//        }
//        //Assert
//        assertThat(states).hasSize(2)
//        assertThat(states[0]).isInstanceOf(UIState.LOADING::class.java)
//        assertThat(states[1]).isInstanceOf(UIState.SUCCESS::class.java)
//        assertThat((states[1] as UIState.SUCCESS<List<NewsItemDomain>>).data).hasSize(3)
//
//    }
//
//    @Test
//    fun getAllNewsWhenTheNetworkConnectionIsSuccessfulAndReturnsSuccess() = runTest {
//        //Arrange
//        coEvery { mokkNewsApi.getAllNews()} returns mockk{
//            every { isSuccessful } returns true
//            every { body() } returns listOf(
//                mockk(relaxed = true),
//                mockk(relaxed = true),
//                mockk(relaxed = true)
//            )
//        }
//        val states = mutableListOf<UIState>()
//        //Act
//        newsRepo.getAllLNewsHere().collect() {
//            states.add(it)
//        }
//        //Assert
//        assertThat(states).hasSize(2)
//        assertThat(states[0]).isInstanceOf(UIState.LOADING::class.java)
//        assertThat(states[1]).isInstanceOf(UIState.SUCCESS::class.java)
//        assertThat((states[1] as UIState.SUCCESS<List<NewsItemDomain>>).data).hasSize(3)
//
//    }
//
//
//}