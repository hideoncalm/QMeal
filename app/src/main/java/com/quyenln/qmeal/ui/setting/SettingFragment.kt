package com.quyenln.qmeal.ui.setting

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.quyenln.qmeal.R
import com.quyenln.qmeal.broadcast.SuggestMealBroadCast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.custom_toolbar.*
import kotlinx.android.synthetic.main.fragment_setting.*
import javax.inject.Inject

@AndroidEntryPoint
class SettingFragment : Fragment(R.layout.fragment_setting) {

    @Inject
    lateinit var preferences: SharedPreferences

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonSearch.setOnClickListener {
            val action = SettingFragmentDirections.actionSettingFragmentToSearchFragment()
            findNavController().navigate(action)
        }

        switchNotification.isChecked = preferences.getBoolean(SWITCH_STATE, false)
        switchNotification.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                setUpAlarmNotification()
                preferences.edit().apply {
                    putBoolean(SWITCH_STATE, true)
                    apply()
                }
            } else {
                cancelAlarmNotification()
                preferences.edit().apply {
                    putBoolean(SWITCH_STATE, false)
                    apply()
                }
            }
        }
    }

    private fun cancelAlarmNotification() {
        val intent = Intent(activity, SuggestMealBroadCast::class.java)
        val pendingIntent = PendingIntent.getBroadcast(activity, 0, intent, 0)
        val alarmManager =
            activity?.getSystemService(AppCompatActivity.ALARM_SERVICE) as AlarmManager
        alarmManager.cancel(pendingIntent)
    }

    private fun setUpAlarmNotification() {
        val intent = Intent(activity, SuggestMealBroadCast::class.java)
        val pendingIntent = PendingIntent.getBroadcast(activity, 0, intent, 0)
        val alarmManager =
            activity?.getSystemService(AppCompatActivity.ALARM_SERVICE) as AlarmManager
        val currentTime = System.currentTimeMillis()
        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP, currentTime,
            AlarmManager.INTERVAL_DAY, pendingIntent
        )
    }

    companion object {
        private const val SWITCH_STATE = "switch_state"
    }
}
