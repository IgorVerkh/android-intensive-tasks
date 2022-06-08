package com.example.contactsappdemo.data.datasource

import com.example.contactsappdemo.model.Contact
import kotlin.random.Random

object FakeContactsDataSource {
    private val firstNames = listOf("Jessika", "Clyde", "Clifton", "Helmuth", "John", "Dale", "Jeffery", "Lena", "Houston", "Mike")
    private val lastNames = listOf("Rosamund", "Dieudonné", "Shayla", "Mandi", "Goodwin", "Butler", "Carlson", "Randolph", "Cain", "McConnell")
    private val phoneNumbers = listOf("+850 192 057 7726", "+850 191 983 9476", "+850 192 724 5844", "+376 690 113 491", "+376 501 000",
        "+376 354 386", "+376 690 462 007", "+376 690 005 904", "+246 388 2299", "+246 388 3609", "+246 380 1728", "+1 767-317-8380",
        "+1 767-317-6347", "+298 500918", "+298 726200", "+298 916573", "+298 530701", "+298 913055", "+354 829 0309", "+354 771 5701")

    fun generateData(): List<Contact> {
        val mutableList = mutableListOf<Contact>()
        for (i in 1..100) {
            mutableList.add(Contact(
                i,
                firstNames[Random.nextInt(firstNames.size)],
                lastNames[Random.nextInt(lastNames.size)],
                phoneNumbers[Random.nextInt(phoneNumbers.size)],
                "https://picsum.photos/seed/${i+Random.nextInt(100)}/500/500"))
        }
        return mutableList.toList()
    }
}