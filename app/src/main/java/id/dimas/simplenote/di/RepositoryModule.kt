package id.dimas.simplenote.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.dimas.simplenote.data.repository.NoteRepository
import id.dimas.simplenote.data.repositoryImpl.NoteRepositoryImpl
import id.dimas.simplenote.data.room.AppDatabase
import id.dimas.simplenote.data.room.dao.NoteDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideNoteRepository(noteDao: NoteDao): NoteRepository {
        return NoteRepositoryImpl(noteDao)
    }


}