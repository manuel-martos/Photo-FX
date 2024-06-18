import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.mmartosdev.photofx.PhotoFxApplication
import java.awt.Dimension

fun main() = application {
    Window(
        title = "Photo FX",
        state = rememberWindowState(width = 800.dp, height = 600.dp),
        onCloseRequest = ::exitApplication,
    ) {
        window.minimumSize = Dimension(350, 600)
        PhotoFxApplication(
            onCloseClicked = ::exitApplication,
        )
    }
}