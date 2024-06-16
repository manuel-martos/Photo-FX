import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.CanvasBasedWindow
import com.mmartosdev.photofx.PhotoFxApplication

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    CanvasBasedWindow("Photo FX") {
        PhotoFxApplication(onCloseClicked = null)
    }
}
