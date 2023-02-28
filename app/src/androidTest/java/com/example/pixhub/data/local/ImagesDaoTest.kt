package com.example.pixhub.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.pixhub.getOrAwaitValue
import com.example.pixhub.utils.ApiType
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
@OptIn(ExperimentalCoroutinesApi::class)
class ImagesDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: ImageItemDB
    private lateinit var dao: ImagesDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            ImageItemDB::class.java
        ).allowMainThreadQueries().build()
        dao = database.imagesDao()
    }

    @After
    fun teardown() {
        database.close()
    }


    @Test
    fun insertImageItem() = runTest {
        val imageItem = ImageItem(ApiType.UNSPLASH,"x",1)
        dao.insertImageItem(imageItem)

        val allImageItems = dao.observeAllSavedImages().getOrAwaitValue()
        assertThat(allImageItems).contains(imageItem)
    }

    @Test
    fun deleteImageItem() = runTest {
        val imageItem = ImageItem(ApiType.UNSPLASH,"x",1)
        dao.insertImageItem(imageItem)
        dao.deleteImageItem(imageItem)

        val allImageItems = dao.observeAllSavedImages().getOrAwaitValue()
        assertThat(allImageItems).doesNotContain(imageItem)
    }

}
