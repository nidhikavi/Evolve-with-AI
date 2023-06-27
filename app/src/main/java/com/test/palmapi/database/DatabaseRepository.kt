package com.test.palmapi.database


import com.test.palmapi.database.accounts.Accounts
import com.test.palmapi.database.accounts.AccountsDao
import com.test.palmapi.database.chats.ChatDao
import com.test.palmapi.database.chats.ChatMessage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class DatabaseRepo(private val chatDao: ChatDao, private val accountsDao: AccountsDao) {
    val allAccounts: Flow<List<Accounts>> = accountsDao.getAccount()
    val allMessages: Flow<List<ChatMessage>> = chatDao.getAllChatMessages()
    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    fun insertMessage(chatMessage: ChatMessage) {
        coroutineScope.launch {
            chatDao.insertChatMessage(chatMessage)
        }
    }

    fun insertAccount(account: Accounts) {
        coroutineScope.launch {
            accountsDao.insertAccount(account)
        }
    }

    fun saveChatMessage(name: String, isPined: Boolean) {
        coroutineScope.launch {
            chatDao.saveChatMessage(name, isPined)
        }
    }


    fun getSavedMessage(name: String) = chatDao.getSavedMessages(name)

    fun deleteMessage(time: Long) {
        coroutineScope.launch {
            chatDao.deleteChatMessage(time)
        }
    }

}