package spam.blocker.ui.setting.quick

import android.Manifest
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import spam.blocker.R
import spam.blocker.ui.setting.LabeledRow
import spam.blocker.ui.theme.LocalPalette
import spam.blocker.ui.theme.Salmon
import spam.blocker.ui.widgets.Str
import spam.blocker.ui.widgets.StrokeButton
import spam.blocker.ui.widgets.SwitchBox
import spam.blocker.util.Permission
import spam.blocker.util.PermissionChain
import spam.blocker.util.Permissions.Companion.isContactsPermissionGranted
import spam.blocker.util.SharedPref.Contact

@Composable
fun Contacts() {
    val ctx = LocalContext.current
    val C = LocalPalette.current

    val spf = Contact(ctx)

    fun checkContactState(): Boolean {
        return spf.isEnabled() && isContactsPermissionGranted(ctx)
    }

    var isTurnedOn by remember { mutableStateOf(checkContactState()) }

    var isExclusive by remember { mutableStateOf(spf.isExclusive()) }

    val permChain = remember {
        PermissionChain(
            ctx,
            listOf(Permission(Manifest.permission.READ_CONTACTS))
        )
    }
    permChain.Compose()

    LabeledRow(
        R.string.allow_contact,
        helpTooltipId = R.string.help_contacts,
        content = {
            if (isTurnedOn) {
                StrokeButton(
                    label = Str(
                        strId = if (isExclusive) R.string.exclusive else R.string.inclusive
                    ),
                    color = if (isExclusive) Salmon else C.textGrey,
                ) {
                    spf.toggleExclusive()
                    isExclusive = !isExclusive
                }
            }
            SwitchBox(isTurnedOn) { isTurningOn ->
                if (isTurningOn) {
                    permChain.ask { granted ->
                        if (granted) {
                            spf.setEnabled(true)
                            isTurnedOn = true
                        }
                    }
                } else {
                    spf.setEnabled(false)
                    isTurnedOn = false
                }
            }
        }
    )
}