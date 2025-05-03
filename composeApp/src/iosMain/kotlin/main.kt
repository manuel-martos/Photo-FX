import androidx.compose.ui.window.ComposeUIViewController
import com.mmartosdev.photofx.PhotoFxApplication
import platform.UIKit.UIViewController
import kotlin.system.exitProcess

fun MainViewController(): UIViewController =
    ComposeUIViewController(
        { enforceStrictPlistSanityCheck = false }
    ) {
        PhotoFxApplication(onCloseClicked = { exitProcess(0) })
    }
