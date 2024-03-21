package org.fossify.calendar.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.fossify.calendar.R
import org.fossify.calendar.extensions.launchNewEventIntent
import org.fossify.calendar.extensions.launchNewTaskIntent
import org.fossify.calendar.helpers.DAY_CODE
import org.fossify.calendar.helpers.Formatter
import org.fossify.commons.dialogs.RadioGroupDialog
import org.fossify.commons.models.RadioItem

class EventTypePickerActivity : AppCompatActivity() {
    private val TYPE_EVENT = 0
    private val TYPE_TASK = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val items = arrayListOf(
            RadioItem(TYPE_EVENT, getString(R.string.event)),
            RadioItem(TYPE_TASK, getString(R.string.task))
        )
        RadioGroupDialog(this, items = items, cancelCallback = { dialogCancelled() }) {
            val dayCode = intent.getStringExtra(DAY_CODE) ?: Formatter.getTodayCode()

            val checkedId = it as Int
            if (checkedId == TYPE_EVENT) {
                launchNewEventIntent(dayCode)
            } else if (checkedId == TYPE_TASK) {
                launchNewTaskIntent(dayCode)
            }
            finish()
        }
    }

    private fun dialogCancelled() {
        finish()
    }
}
