package com.franzandel.dicodingjetpackprosubmission.ui.tvshows

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

object TvShowsViewModelTest : Spek({
    Feature("TvShows") {
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

        val viewModel by memoized { TvShowsViewModel() }

        Scenario("getting tv shows data") {
            When("get tv shows") {
                viewModel.getTvShows()
            }

            Then("it should have a size of 20") {
                assertEquals(20, viewModel.tvShows.value?.size)
            }

            Then("it should not be null") {
                assertNotNull(viewModel.tvShows.value?.size)
            }
        }
    }
})