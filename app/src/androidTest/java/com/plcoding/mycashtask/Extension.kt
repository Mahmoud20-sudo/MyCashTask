package com.plcoding.mycashtask

import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.plcoding.mycashtask.yjahz.presentation.MainActivity
import java.util.Timer
import kotlin.concurrent.schedule

object Extension {
    fun AndroidComposeTestRule<ActivityScenarioRule<MainActivity>, MainActivity>.clearAndSetContent(
        content: @Composable () -> Unit
    ) {
        (this.activity.findViewById<ViewGroup>(android.R.id.content)
            ?.getChildAt(0) as? ComposeView)?.setContent(content)
            ?: this.setContent(content)
    }
}
fun ComposeContentTestRule.waitUntilTimeout(
    timeoutMillis: Long
) {
    AsyncTimer.start(timeoutMillis)
    this.waitUntil(
        condition = { AsyncTimer.expired },
        timeoutMillis = timeoutMillis + 1000
    )
}
object AsyncTimer {
    var expired = false
    fun start(delay: Long = 1000){
        expired = false
        Timer().schedule(delay) {
            expired = true
        }
    }
}