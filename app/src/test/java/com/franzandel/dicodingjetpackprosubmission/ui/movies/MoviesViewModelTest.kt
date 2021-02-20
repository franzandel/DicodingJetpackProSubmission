package com.franzandel.dicodingjetpackprosubmission.ui.movies

import androidx.arch.core.executor.ArchTaskExecutor
import androidx.arch.core.executor.TaskExecutor
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature


/**
 * Created by Franz Andel on 20/02/21.
 * Android Engineer
 */

object MoviesViewModelTest : Spek({
    Feature("Movies") {
        beforeEachTest {
            ArchTaskExecutor.getInstance().setDelegate(object : TaskExecutor() {
                override fun executeOnDiskIO(runnable: Runnable) {
                    runnable.run()
                }

                override fun isMainThread(): Boolean = true

                override fun postToMainThread(runnable: Runnable) {
                    runnable.run()
                }
            })
        }

        afterEachTest { ArchTaskExecutor.getInstance().setDelegate(null) }

        val viewModel by memoized { MoviesViewModel() }

        Scenario("getting movies data") {
            When("get movies") {
                viewModel.getMovies()
            }

            Then("it should have a size of 19") {
                assertEquals(19, viewModel.movies.value?.size)
            }

            Then("it should not be null") {
                assertNotNull(viewModel.movies.value?.size)
            }
        }
    }
})