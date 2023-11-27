package com.example.moviesApp.appSections.moviesListScreen.di

import com.example.moviesApp.appSections.moviesListScreen.data.dataSources.MovieDataSource
import com.example.moviesApp.appSections.moviesListScreen.data.dataSources.MovieDataSourceInterface
import com.example.moviesApp.appSections.moviesListScreen.data.repository.MovieRepositoryImpl
import com.example.moviesApp.appSections.moviesListScreen.domain.repository.MovieRepositoryInterface
import com.example.moviesApp.appSections.moviesListScreen.domain.useCases.GetMovieUseCase
import com.example.moviesApp.appSections.moviesListScreen.domain.useCases.GetMovieUseCaseInterface
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class AppModule {

    @Binds
    @ViewModelScoped
    abstract fun bindMovieRepositoryInterface(movieRepository: MovieRepositoryImpl): MovieRepositoryInterface


    @Binds
    @ViewModelScoped
    abstract fun bindMovieUseCaseInterface(movieUseCase: GetMovieUseCase): GetMovieUseCaseInterface

    @Binds
    @ViewModelScoped
    abstract fun bindMovieDataSourceInterface(movieDataSource: MovieDataSource): MovieDataSourceInterface

}