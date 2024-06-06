package com.example.recipeapppaparaproject.di

import com.example.recipeapppaparaproject.data.api.RecipeApi
import com.example.recipeapppaparaproject.data.repo.AuthRepository
import com.example.recipeapppaparaproject.data.repo.RecipeRepository
import com.example.recipeapppaparaproject.data.repo.RecipeRepositoryImpl
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    @Provides
    @Singleton
    fun provideAuthRepository(auth: FirebaseAuth): AuthRepository {
        return AuthRepository(auth)
    }

    @Provides
    @Singleton
    fun provideRecipeRepository(api: RecipeApi): RecipeRepository {
        return RecipeRepositoryImpl(api=api)
    }







}