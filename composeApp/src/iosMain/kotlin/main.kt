import androidx.compose.ui.window.ComposeUIViewController
import com.mmartosdev.photofx.App
import platform.UIKit.UIViewController
import kotlin.system.exitProcess

fun MainViewController(): UIViewController =
    ComposeUIViewController {
        App(onCloseClicked = { exitProcess(0) })
    }
