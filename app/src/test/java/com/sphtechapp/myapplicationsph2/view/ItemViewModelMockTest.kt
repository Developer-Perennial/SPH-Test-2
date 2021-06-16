package com.sphtechapp.myapplicationsph2.view

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.sphtechapp.myapplicationsph2.CoroutineTestRule
import com.sphtechapp.myapplicationsph2.activities.main.data.DataUsageResponse
import com.sphtechapp.myapplicationsph2.activities.main.data.RecordsData
import com.sphtechapp.myapplicationsph2.activities.main.viewModel.ItemViewModel
import com.sphtechapp.myapplicationsph2.backend.ServiceUtil
import com.sphtechapp.myapplicationsph2.util.ItemDataState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.*
import org.powermock.modules.junit4.PowerMockRunner
import java.lang.IllegalStateException
@ExperimentalCoroutinesApi
@RunWith(PowerMockRunner::class)
class ItemViewModelMockTest {

    private val resourceId = "a807b7ab-6cad-4aa6-87d0-e283a7353a0f"

    private val serviceUtil: ServiceUtil = mock()

    private lateinit var itemViewModel: ItemViewModel

    @Mock
    private lateinit var response: DataUsageResponse

    @Rule
    @JvmField
    var instantExecutorRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    val coRoutineTestRule = CoroutineTestRule()

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val mockObserverForStates = mock<Observer<ItemDataState>>()

    @Before
    fun before() {
        itemViewModel = ItemViewModel(serviceUtil).apply {
            uiState.observeForever(mockObserverForStates)
        }
    }

    @Test
    fun testFetchListFromServerSuccess() {

        runBlockingTest {
            `when`(serviceUtil.getList(resourceId)).thenReturn(
                response
            )

            itemViewModel.retrieveItems()

            verify(mockObserverForStates).onChanged(ItemDataState.ShowProgress)
            verify(mockObserverForStates, times(2)).onChanged(
                ItemDataState.Success(ArgumentMatchers.any())
            )
            verifyNoMoreInteractions(mockObserverForStates)
        }
    }

    @Test
    fun testThrowErrorOnListFetchFail() {

        runBlocking {
            val error = IllegalStateException()

            `when`(serviceUtil.getList(resourceId)).thenThrow(
                error
            )

            itemViewModel.retrieveItems()

            verify(mockObserverForStates).onChanged(ItemDataState.ShowProgress)
            verify(
                mockObserverForStates,
                times(2)
            ).onChanged(ItemDataState.Error(ArgumentMatchers.any()))
            verifyNoMoreInteractions(mockObserverForStates)
        }
    }

    @Test
    fun testFilterData() {

        runBlockingTest {
            `when`(serviceUtil.getList(resourceId)).thenReturn(
                response
            )

            itemViewModel.retrieveItems()

            itemViewModel.filterData(arrayListOf(RecordsData(1, "0.0177", "2001-Q1")))

            verify(mockObserverForStates).onChanged(ItemDataState.ShowProgress)
            verify(mockObserverForStates, times(2)).onChanged(
                ItemDataState.Success(ArgumentMatchers.any())
            )
            verifyNoMoreInteractions(mockObserverForStates)
        }
    }

    private inline fun <reified T> mock(): T = mock(T::class.java)

}