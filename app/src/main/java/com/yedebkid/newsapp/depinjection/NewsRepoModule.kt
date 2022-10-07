package com.yedebkid.newsapp.depinjection

import com.yedebkid.newsapp.rest.NewsRepository
import com.yedebkid.newsapp.rest.NewsRepositoryImplementation
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class NewsRepoModule {
    @Binds
    abstract fun providesRepository(
        newsRepositoryImplementation: NewsRepositoryImplementation
    ): NewsRepository
}