package com.example.rentacarapp.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton


private val Context.dataStore : DataStore<Preferences> by preferencesDataStore(name ="user_prefs")

@Singleton
class UserPreferences @Inject constructor(
 @ApplicationContext   private val context: Context
) {
    companion object{
        val EMAIL_KEY = stringPreferencesKey(name = "user_email")
        val PASS_KEY = stringPreferencesKey(name = "user_pass")
    }


    suspend fun saveUserPreferences(email:String,pass: String){
        context.dataStore.edit { preferences ->
            preferences[EMAIL_KEY] = email
            preferences[PASS_KEY] = pass
        }
    }

    fun readUserPreferences():Flow<UserCredentials?>{
        return context.dataStore.data.map {
            preferences ->
            val email = preferences[EMAIL_KEY]?:""
            val pass = preferences[PASS_KEY]?:""

            if(email.isNotEmpty() && pass.isNotEmpty()){
                UserCredentials(email,pass)
            }else{
                null
            }
        }
    }

    suspend fun clear(){
        context.dataStore.edit { it.clear() }
    }
}

data class UserCredentials(
    val email: String,
    val pass: String
)