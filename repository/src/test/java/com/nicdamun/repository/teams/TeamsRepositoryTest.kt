package com.nicdamun.repository.teams

import com.nicdamun.core.dto.TeamDTO
import com.nicdamun.core.models.TeamModel
import com.nicdamun.repository.helpers.Result
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
class TeamsRepositoryTest {

    private val mockTeamRemoteDataSource = mock(TeamsRemoteDataSource::class.java)
    private val teamsRepository = TeamsRepository(mockTeamRemoteDataSource)

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

    private val teamDTOs = listOf(
        TeamDTO(
            team = "Real Madrid",
            twitter = "twitter.com",
            facebook = "facebook.com",
            instagram = "instagram.com",
            id = "1",
            description = "description",
            badge = "badge.png",
            banner = "banner.png",
            formedYear = 1923,
            jersey = "jersey.png",
            stadium = "Bernabeu",
            website = "realmadrid.com"
        ),
        TeamDTO(
            team = "Barcelona",
            twitter = "twitter.com",
            facebook = "facebook.com",
            instagram = "instagram.com",
            id = null,
            description = "description",
            badge = "badge.png",
            banner = "banner.png",
            formedYear = 1924,
            jersey = "jersey.png",
            stadium = "Camp Nou",
            website = "barcelona.com"
        ),
        TeamDTO(
            team = "Atletico Madrid",
            twitter = "twitter.com",
            facebook = "facebook.com",
            instagram = "instagram.com",
            id = "3",
            description = "description",
            badge = "badge.png",
            banner = "banner.png",
            formedYear = 1926,
            jersey = "jersey.png",
            stadium = "Wanda Metropolitano",
            website = "atletico.com"
        ),
    )

    @After
    fun tearDown() {
        verifyNoMoreInteractions(mockTeamRemoteDataSource)
    }

    @Test
    fun `test teams are retrieved successfully with one id equal to null`() {
        runBlockingTest {
            `when`(mockTeamRemoteDataSource.getTeamsByLeagueName("la_liga")).thenReturn(Result.Success(teamDTOs))

            val result = teamsRepository.getTeamsByLeagueName("la_liga")
            verify(mockTeamRemoteDataSource).getTeamsByLeagueName("la_liga")

            assertEquals(teamModels, result)
        }
    }

    @Test
    fun `test teams are retrieved with service failure, result should be empty`() {
        runBlockingTest {
            `when`(mockTeamRemoteDataSource.getTeamsByLeagueName("la_liga")).thenReturn(Result.Failure(Exception()))

            val result = teamsRepository.getTeamsByLeagueName("la_liga")
            verify(mockTeamRemoteDataSource).getTeamsByLeagueName("la_liga")

            assertEquals(emptyList<TeamModel>(), result)
        }
    }
}