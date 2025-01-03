package id.aflah.universitieslist.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.aflah.universitieslist.data.api.UniversityApiService
import id.aflah.universitieslist.data.dao.UniversityDao
import id.aflah.universitieslist.data.mapper.UniversityDbEntityMapper
import id.aflah.universitieslist.data.mapper.UniversityMapper
import id.aflah.universitieslist.data.mapper.UniversityToDbEntityMapper
import id.aflah.universitieslist.data.repositoryimpl.UniversityRepositoryImpl
import id.aflah.universitieslist.domain.repo.UniversityRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideUniversityRepository(
        apiService: UniversityApiService,
        universityDao: UniversityDao,
    ): UniversityRepository = UniversityRepositoryImpl(
        apiService,
        universityDao,
        UniversityMapper(),
        UniversityDbEntityMapper(),
        UniversityToDbEntityMapper(),
    )
}