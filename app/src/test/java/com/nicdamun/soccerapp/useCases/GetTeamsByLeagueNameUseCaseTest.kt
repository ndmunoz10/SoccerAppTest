package com.nicdamun.soccerapp.useCases

import com.nicdamun.core.models.TeamModel
import com.nicdamun.repository.teams.TeamsRepository
import com.nicdamun.soccerapp.useCases.teams.GetTeamsByLeagueNameUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Test

import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyNoMoreInteractions
import org.mockito.Mockito.`when`

@ExperimentalCoroutinesApi
class GetTeamsByLeagueNameUseCaseTest {

    private val mockTeamsRepository = mock(TeamsRepository::class.java)
    private val getTeamsByLeagueNameUseCase = GetTeamsByLeagueNameUseCase(mockTeamsRepository)

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
        verifyNoMoreInteractions(mockTeamsRepository)
    }

    @Test
    fun `test that teams are retrieved`() {
        runBlockingTest {
            `when`(mockTeamsRepository.getTeamsByLeagueName("la_liga")).thenReturn(teamModels)

            val result = getTeamsByLeagueNameUseCase.invoke("la_liga")
            verify(mockTeamsRepository).getTeamsByLeagueName("la_liga")

            assertEquals(teamModels, result)
        }
    }
}