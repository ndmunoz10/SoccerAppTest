package com.nicdamun.soccerapp.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nicdamun.core.models.EventModel
import com.nicdamun.soccerapp.CoroutineTestRule
import com.nicdamun.soccerapp.LiveDataTestUtil
import com.nicdamun.soccerapp.teamDetail.TeamDetailViewModel
import com.nicdamun.soccerapp.useCases.events.GetEventsByTeamIdUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyNoMoreInteractions
import org.mockito.Mockito.`when`

@ExperimentalCoroutinesApi
class TeamDetailViewModelTest {

    @get:Rule
    val instantExecutor = InstantTaskExecutorRule()

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()
    private val mockGetEventsByTeamIdUseCase = mock(GetEventsByTeamIdUseCase::class.java)
    private val teamDetailViewModel = TeamDetailViewModel(mockGetEventsByTeamIdUseCase)
    private val eventModels = listOf(
        EventModel(
            leagueName = "La Liga",
            name = "Real Madrid vs Granada",
            dateEvent = "2020-08-10"
        ),
        EventModel(
            leagueName = "La Liga",
            name = "Real Madrid vs Barcelona",
            dateEvent = "2020-08-15"
        ),
        EventModel(
            leagueName = "La Liga",
            name = "Real Madrid vs Atlético Madrid",
            dateEvent = "2020-08-20"
        )
    )

    @After
    fun tearDown() {
        verifyNoMoreInteractions(mockGetEventsByTeamIdUseCase)
    }

    @Test
    fun `test that events are received correctly`() {
        runBlockingTest {
            `when`(mockGetEventsByTeamIdUseCase.invoke("123")).thenReturn(eventModels)

            teamDetailViewModel.fetchTeamEventsById("123")
            val result = LiveDataTestUtil.getValue(teamDetailViewModel.teamEventsObs())
            verify(mockGetEventsByTeamIdUseCase).invoke("123")

            assertEquals(eventModels, result)
        }
    }
}