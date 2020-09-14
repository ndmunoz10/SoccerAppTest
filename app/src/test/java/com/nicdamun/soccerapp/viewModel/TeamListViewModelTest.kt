package com.nicdamun.soccerapp.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nicdamun.core.models.TeamModel
import com.nicdamun.soccerapp.CoroutineTestRule
import com.nicdamun.soccerapp.LiveDataTestUtil
import com.nicdamun.soccerapp.teamList.TeamListViewModel
import com.nicdamun.soccerapp.useCases.teams.GetTeamsByLeagueNameUseCase
import kotlinx.coroutines.Dispatchers
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
class TeamListViewModelTest {

    @get:Rule
    val instantExecutor = InstantTaskExecutorRule()

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    private val mockGetTeamsByLeagueNameUseCase = mock(GetTeamsByLeagueNameUseCase::class.java)
    private val teamListViewModel = TeamListViewModel(mockGetTeamsByLeagueNameUseCase)
    private val teamModels = listOf(
        TeamModel(
            name = "Real Madrid",
            twitter = "twitter.com",
            facebook = "facebook.com",
            instagram = "instagram.com",
            id = "1",
            description = "description",
            badge = "badge.png",
            banner = "banner.png",
            formedYear = 1923,
            jersey = "jersey.png",
            stadiumName = "Bernabeu",
            website = "realmadrid.com"
        ),
        TeamModel(
            name = "Barcelona",
            twitter = "twitter.com",
            facebook = "facebook.com",
            instagram = "instagram.com",
            id = "2",
            description = "description",
            badge = "badge.png",
            banner = "banner.png",
            formedYear = 1924,
            jersey = "jersey.png",
            stadiumName = "Camp Nou",
            website = "barcelona.com"
        ),
        TeamModel(
            name = "Atletico Madrid",
            twitter = "twitter.com",
            facebook = "facebook.com",
            instagram = "instagram.com",
            id = "3",
            description = "description",
            badge = "badge.png",
            banner = "banner.png",
            formedYear = 1926,
            jersey = "jersey.png",
            stadiumName = "Wanda Metropolitano",
            website = "atletico.com"
        ),
    )

    @After
    fun tearDown() {
        verifyNoMoreInteractions(mockGetTeamsByLeagueNameUseCase)
    }

    @Test
    fun `test that teams are retrieved correctly`() {
        runBlockingTest {
            `when`(mockGetTeamsByLeagueNameUseCase.invoke("la_liga")).thenReturn(teamModels)
            teamListViewModel.getTeamsByLeagueName("la_liga")
            val result = LiveDataTestUtil.getValue(teamListViewModel.getTeamObs())
            verify(mockGetTeamsByLeagueNameUseCase).invoke("la_liga")
            assertEquals(teamModels, result)
        }
    }
}