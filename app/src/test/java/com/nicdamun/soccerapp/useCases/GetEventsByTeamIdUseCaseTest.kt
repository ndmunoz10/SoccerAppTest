package com.nicdamun.soccerapp.useCases

import com.nicdamun.core.models.EventModel
import com.nicdamun.repository.events.EventRepository
import com.nicdamun.soccerapp.useCases.events.GetEventsByTeamIdUseCase
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
class GetEventsByTeamIdUseCaseTest {

    private val mockEventRepository = mock(EventRepository::class.java)
    private val getEventsByTeamIdUseCase = GetEventsByTeamIdUseCase(mockEventRepository)

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
        verifyNoMoreInteractions(mockEventRepository)
    }

    @Test
    fun `test events are retrieved`() {
        runBlockingTest {
            `when`(mockEventRepository.getEventsByTeamId("1")).thenReturn(eventModels)

            val result = getEventsByTeamIdUseCase.invoke("1")
            verify(mockEventRepository).getEventsByTeamId("1")

            assertEquals(eventModels, result)
        }
    }
}