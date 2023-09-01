package com.example.superproyectocomicon


import android.content.Context
import androidx.annotation.VisibleForTesting
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.superproyectocomicon.data.local.HeroDB
import com.example.superproyectocomicon.data.local.HeroDao
import com.example.superproyectocomicon.data.local.HeroEntity
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

class RoomDataBaseTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var heroDao: HeroDao
    private lateinit var db: HeroDB

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, HeroDB::class.java).build()
        heroDao = db.getDaoHero()
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun insertHero_empty() = runBlocking {
        // Given
        val heroList = listOf<HeroEntity>()

        // When
        heroDao.insertsListHeroEntity(heroList)

        // Then A
        val it = heroDao.showListHeroEntity().getOrAwaitValue()
        Assert.assertNotEquals(it.size, null)//assertThat(it).isNotNull
        Assert.assertEquals(it.size, 0)//assertThat(it).isEmpty()

        // Then B
        heroDao.showListHeroEntity().observeForever {
            assertThat(it).isNotNull()
            assertThat(it).isEmpty()
        }
    }

    @Test
    fun insertHero_happyCase_1element() = runBlocking {
        // Given
        val heroList =
            listOf(
                HeroEntity(
                    1,
                    "chapulin colorado",
                    "mexico",
                    "Http//asdasd.jpg",
                    "volar",
                    "1992"
                )
            )

        // When
        heroDao.insertsListHeroEntity(heroList)

        // Then
        heroDao.showListHeroEntity().observeForever {
            assertThat(it).isNotNull()
            assertThat(it).isNotEmpty()
            assertThat(it).hasSize(1)
        }
    }

    @Test
    fun insertHero_happyCase_3elements() = runBlocking {
        // Given
        val heroList =
            listOf(
                HeroEntity(
                    1,
                    "chapulin colorado",
                    "mexico",
                    "Http//asdasd.jpg",
                    "volar",
                    "1992"
                ),
                        HeroEntity(
                        2,
                "chapulin colorado1",
                "mexico",
                "Http//asdasd.jpg",
                "volar",
                "1992"
            ),
        HeroEntity(
            3,
            "chapulin colorado2",
            "mexico",
            "Http//asdasd.jpg",
            "volar",
            "1992"
        )
            )

        // When
        heroDao.insertsListHeroEntity(heroList)

        // Then
        heroDao.showListHeroEntity().observeForever {
            assertThat(it).isNotNull()
            assertThat(it).isNotEmpty()
            assertThat(it).hasSize(3)
        }
    }
}


@VisibleForTesting(otherwise = VisibleForTesting.NONE)
fun <T> LiveData<T>.getOrAwaitValue(
    time: Long = 2, timeUnit: TimeUnit = TimeUnit.SECONDS, afterObserve: () -> Unit = {}
): T {
    var data: T? = null
    val latch = CountDownLatch(1)
    val observer = object : Observer<T> {
        override fun onChanged(value: T) {
            data = value
            latch.countDown()
            this@getOrAwaitValue.removeObserver(this)
        }
    }
    this.observeForever(observer)

    try {
        afterObserve.invoke()

        // Don't wait indefinitely if the LiveData is not set.
        if (!latch.await(time, timeUnit)) {
            throw TimeoutException("LiveData value was never set.")
        }

    } finally {
        this.removeObserver(observer)
    }

    @Suppress("UNCHECKED_CAST") return data as T
}