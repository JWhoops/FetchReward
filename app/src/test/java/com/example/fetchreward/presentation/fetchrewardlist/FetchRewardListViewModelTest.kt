package com.example.fetchreward.presentation.fetchrewardlist

import com.example.fetchreward.data.model.FetchRewardItem
import com.example.fetchreward.domain.GetFetchRewardItemsUseCase
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class FetchRewardListViewModelTest {

    private lateinit var testSubject: FetchRewardListViewModel

    @RelaxedMockK
    lateinit var getFetchRewardListUseCase: GetFetchRewardItemsUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        testSubject = FetchRewardListViewModel(getFetchRewardListUseCase)
    }

    @Test
    fun `test if list item with null name is filtered`() {
        val listItem = listOf(FetchRewardItem(1, 1, null))
        val filteredList = testSubject.filterFetchRewardListByName(listItem)
        assertTrue(filteredList.isEmpty())
    }

    @Test
    fun `test if list item with empty string name is filtered`() {
        val listItem = listOf(FetchRewardItem(1, 1, ""))
        val filteredList = testSubject.filterFetchRewardListByName(listItem)
        assertTrue(filteredList.isEmpty())
    }

    @Test
    fun `test if list item with empty string or null name is filtered`() {
        val listItem = listOf(
            FetchRewardItem(1, 1, ""),
            FetchRewardItem(1, 1, null),
            FetchRewardItem(1, 1, "Hello")
        )
        val filteredList = testSubject.filterFetchRewardListByName(listItem)
        assertEquals(filteredList.size, 1)
    }

    @Test
    fun `test if sortFetchRewardListByListIdThenName make list sorted by listId then by name`() {
        val item1 = FetchRewardItem(1, 1, "1")
        val item2 = FetchRewardItem(2, 2, "2")
        val item3 = FetchRewardItem(3, 1, "3")
        val list = listOf(item1, item2, item3).shuffled()
        val sortedList = testSubject.sortFetchRewardListByListIdThenName(list)
        assertEquals(sortedList[0], item1)
        assertEquals(sortedList[1], item3)
        assertEquals(sortedList[2], item2)
    }

}