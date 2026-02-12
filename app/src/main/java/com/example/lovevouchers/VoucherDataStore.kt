package com.example.lovevouchers

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "voucher_prefs")

class VoucherDataStore(private val context: Context) {

    suspend fun setVoucherRedeemed(voucherId: String) {
        val key = booleanPreferencesKey(voucherId)
        context.dataStore.edit { preferences ->
            preferences[key] = true
        }
    }

    fun isVoucherRedeemed(voucherId: String): Flow<Boolean> {
        val key = booleanPreferencesKey(voucherId)
        return context.dataStore.data.map { preferences ->
            preferences[key] ?: false
        }
    }
}