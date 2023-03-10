package com.shahtott.alexon_task.di

import android.content.Context
import com.shahtott.alexon_task.data.local.DataStoreImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideDataStore(
        @ApplicationContext appContext: Context
    ): DataStoreImpl {
        return DataStoreImpl(appContext)
    }
}