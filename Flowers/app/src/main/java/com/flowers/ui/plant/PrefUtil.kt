package com.flowers.ui.plant

import android.content.Context
import android.preference.Preference
import android.preference.PreferenceManager
import android.preference.PreferenceManager.*

class PrefUtil {
    companion object{



        private const val TIMER_LENGTH_ID = "com.flower.timer.timer_length"
        fun getTimerLength(context: Context): Int{
            val preferences = PreferenceManager.getDefaultSharedPreferences(context)
            return preferences.getInt(TIMER_LENGTH_ID, 10)
        }

        private const val PREVOIUS_TIMER_LENGHT_SECONDS_ID ="com.flowers.timer.previous_timer_lenght_seconds_id"

        fun getPreviousTimerLenghtSeconds(contex: Context): Long{
            val preferences = getDefaultSharedPreferences(contex);
            return preferences.getLong(PREVOIUS_TIMER_LENGHT_SECONDS_ID, 0);
        }

        fun setPreviousTimerLenghtSeconds(seconds: Long, contex: Context){
            val editor = PreferenceManager.getDefaultSharedPreferences(contex).edit()
            editor.putLong(PREVOIUS_TIMER_LENGHT_SECONDS_ID, seconds)
            editor.apply()
        }

        private const val TIMER_STATE_ID = "com.flowers.timer.timer_state"

        fun getTimerState(contex: Context): PlantFragment.TimerState{
            val preferences = getDefaultSharedPreferences(contex);
            val ordinal = preferences.getInt(TIMER_STATE_ID, 0)
            return PlantFragment.TimerState.values()[ordinal];
        }

        fun setTimerState(state: PlantFragment.TimerState, contex: Context){
            val editor = PreferenceManager.getDefaultSharedPreferences(contex).edit()
            val ordinal = state.ordinal
            editor.putInt(TIMER_STATE_ID, ordinal)
            editor.apply()
        }

        private const val SECONDS_REMAINING_ID ="com.flowers.timer.seconds_remaining"

        fun getSecondsRemaining(contex: Context): Long{
            val preferences = getDefaultSharedPreferences(contex);
            return preferences.getLong(SECONDS_REMAINING_ID, 0);
        }

        fun setSecondsRemaining(seconds: Long, contex: Context){
            val editor = PreferenceManager.getDefaultSharedPreferences(contex).edit()
            editor.putLong(SECONDS_REMAINING_ID, seconds)
            editor.apply()
        }

    }
}