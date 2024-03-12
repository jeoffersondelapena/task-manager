import android.widget.CalendarView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.viewinterop.AndroidView
import java.time.LocalDate
import java.time.ZoneId
import java.util.Date

@Composable
fun AppDatePicker(initialDate: Date, onSelectDate: (Date) -> Unit) {
    AndroidView(
        factory = { context ->
            CalendarView(context)
        },
        update = { views ->
            views.date = initialDate.time
            views.setOnDateChangeListener { _, year, month, dayOfMonth ->
                onSelectDate(
                    Date.from(
                        LocalDate
                            .of(year, month + 1, dayOfMonth)
                            .atStartOfDay(ZoneId.systemDefault())
                            .toInstant()
                    )
                )
            }
        },
        modifier = Modifier.fillMaxWidth().background(Color.White)
    )
}
