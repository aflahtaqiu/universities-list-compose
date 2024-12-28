package id.aflah.universitieslist.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import id.aflah.universitieslist.data.dao.UniversityDao
import id.aflah.universitieslist.data.localdatabase.UniversityDatabase
import javax.inject.Singleton
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory

@Module
@InstallIn(SingletonComponent::class)
object LocalDatabaseModule {
    @Provides
    @Singleton
    fun providePortalDatabase(@ApplicationContext context: Context): UniversityDatabase {
        val passphrase = SQLiteDatabase.getBytes(UniversityDatabase.DB_PASS_PHRASE.toCharArray())
        val supportFactory = SupportFactory(passphrase)
        return Room.databaseBuilder(context, UniversityDatabase::class.java, UniversityDatabase.DB_NAME)
            .openHelperFactory(supportFactory)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideUniversityDao(database: UniversityDatabase): UniversityDao =
        database.universityDao()
}