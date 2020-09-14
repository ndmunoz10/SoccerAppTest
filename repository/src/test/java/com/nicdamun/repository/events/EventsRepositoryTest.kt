package com.nicdamun.repository.events

import com.nicdamun.core.dto.EventDTO
import com.nicdamun.core.models.EventModel
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
class EventsRepositoryTest {

    private val mockEventsRemoteDataSource = mock(EventRemoteDataSource::class.java)
    private val eventsRepository = EventRepository(mockEventsRemoteDataSource)
    private val eventDTOs = listOf(
        EventDTO(
            leagueName = "La Liga",
            name = "Real Madrid vs Granada",
            dateEvent = "2020-08-10"
        ),
        EventDTO(
            leagueName = "La Liga",
            name = "Real Madrid vs Barcelona",
            dateEvent = "2020-08-15"
        ),
        EventDTO(
            leagueName = "La Liga",
            name = "Real Madrid vs Atlético Madrid",
            dateEvent = "2020-08-20"
        )
    )

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
        verifyNoMoreInteractions(mockEventsRemoteDataSource)
    }

    @Test
    fun `test events are retrieved successfully`() {
        runBlockingTest {
            `when`(mockEventsRemoteDataSource.getEventsByTeamId("123")).thenReturn(Result.Success(eventDTOs))

            val result = eventsRepository.getEventsByTeamId("123")
            verify(mockEventsRemoteDataSource).getEventsByTeamId("123")

            assertEquals(eventModels, result)
        }
    }

    @Test
    fun `test events are retrieved with error`() {
        runBlockingTest {
            `when`(mockEventsRemoteDataSource.getEventsByTeamId("123")).thenReturn(Result.Failure(Exception()))

            val result = eventsRepository.getEventsByTeamId("123")
            verify(mockEventsRemoteDataSource).getEventsByTeamId("123")

            assertEquals(emptyList<EventModel>(), result)
        }
    }
}