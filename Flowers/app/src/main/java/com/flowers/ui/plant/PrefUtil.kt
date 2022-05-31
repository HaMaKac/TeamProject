package com.flowers.ui.plant

import android.content.Context
import android.preference.PreferenceManager
import android.preference.PreferenceManager.*

class PrefUtil {
    companion object{



        private const val TIMER_LENGTH_ID = "com.flower.timer.timer_length"
        fun getTimerLength(context: Context): Int{
            val preferences = PreferenceManager.getDefaultSharedPreferences(context)
            return preferences.getInt(TIMER_LENGTH_ID, 10)
        }

        fun setTimerLength(seconds: Int, context: Context){
            val editor = PreferenceManager.getDefaultSharedPreferences(context).edit()
            editor.putInt(TIMER_LENGTH_ID, seconds)
            editor.apply()
        }



        private const val PREVIOUS_TIMER_LENGTH_SECONDS_ID ="com.flowers.timer.previous_timer_lenght_seconds_id"

        fun getPreviousTimerLengthSeconds(context: Context): Long{
            val preferences = getDefaultSharedPreferences(context);
            return preferences.getLong(PREVIOUS_TIMER_LENGTH_SECONDS_ID, 0);
        }

        fun setPreviousTimerLengthSeconds(seconds: Long, context: Context){
            val editor = PreferenceManager.getDefaultSharedPreferences(context).edit()
            editor.putLong(PREVIOUS_TIMER_LENGTH_SECONDS_ID, seconds)
            editor.apply()
        }

        private const val TIMER_STATE_ID = "com.flowers.timer.timer_state"

        fun getTimerState(context: Context): PlantFragment.TimerState{
            val preferences = getDefaultSharedPreferences(context);
            val ordinal = preferences.getInt(TIMER_STATE_ID, 0)
            return PlantFragment.TimerState.values()[ordinal];
        }

        fun setTimerState(state: PlantFragment.TimerState, context: Context){
            val editor = PreferenceManager.getDefaultSharedPreferences(context).edit()
            val ordinal = state.ordinal
            editor.putInt(TIMER_STATE_ID, ordinal)
            editor.apply()
        }

        private const val SECONDS_REMAINING_ID ="com.flowers.timer.seconds_remaining"

        fun getSecondsRemaining(context: Context): Long{
            val preferences = getDefaultSharedPreferences(context);
            return preferences.getLong(SECONDS_REMAINING_ID, 0);
        }

        fun setSecondsRemaining(seconds: Long, context: Context){
            val editor = PreferenceManager.getDefaultSharedPreferences(context).edit()
            editor.putLong(SECONDS_REMAINING_ID, seconds)
            editor.apply()
        }

    }
}