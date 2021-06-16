package com.sphtechapp.myapplicationsph2.view

import com.sphtechapp.myapplicationsph2.activities.main.data.RecordsData
import junit.framework.TestCase.assertEquals
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Spy
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MockVsSpyTest {

    @Mock
    private lateinit var mockItemList: ArrayList<RecordsData>

    @Spy
    private val spyItemList = ArrayList<RecordsData>()

    @Test
    fun testMockItemList() {
        //by default, calling the methods of mock object will do nothing
        val recordsData = RecordsData(1, "0.555", "2018-Q1")
        mockItemList.add(recordsData)
        Assert.assertNull(mockItemList[0])
        val recordsDataCopy = recordsData.copy(_id = 2)
        assertEquals(2, recordsDataCopy._id)
    }

    @Test
    fun testSpyItemList() {
        //spy object will call the real method when not stub
        spyItemList.add(RecordsData(1, "0.555", "2018-Q1"))
        Assert.assertEquals(1, spyItemList[0]._id)
        Assert.assertEquals("0.555", spyItemList[0].volume_of_mobile_data)
        Assert.assertEquals("2018-Q1", spyItemList[0].quarter)
    }

    @Test
    fun testMockItemWithStub() {
        //try stubbing a method
        val expected = RecordsData(1, "0.555", "2018-Q1")
        Mockito.`when`(mockItemList[100]).thenReturn(expected)
        Assert.assertEquals(expected, mockItemList[100])
    }

    @Test
    fun testSpyItemWithStub() {
        //stubbing a spy method will result the same as the mock object
        val expected = RecordsData(1, "0.555", "2018-Q1")
        //take note of using doReturn instead of when
        Mockito.doReturn(expected).`when`(spyItemList)[100]
        Assert.assertEquals(expected, spyItemList[100])
    }
}