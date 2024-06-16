import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.CanvasBasedWindow
import com.mmartosdev.photofx.PhotoFxApplication
import org.jetbrains.skiko.wasm.onWasmReady

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    onWasmReady {
        CanvasBasedWindow("Photo FX") {
            PhotoFxApplication(onCloseClicked = null)
        }
    }
}
