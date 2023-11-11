package net.yuuzu.diarynoteapp

import com.moriatsushi.insetsx.WindowInsetsUIViewController
import net.yuuzu.diarynoteapp.di.appModule
import net.yuuzu.diarynoteapp.di.initKoin
import platform.UIKit.UIScreen
import platform.UIKit.UIUserInterfaceStyle
import platform.UIKit.UIViewController

@Suppress("FunctionName", "unused")
fun MainViewController(): UIViewController {
    val isDarkMode = UIScreen.mainScreen.traitCollection.userInterfaceStyle ==
            UIUserInterfaceStyle.UIUserInterfaceStyleDark

    return WindowInsetsUIViewController {
        initKoin { modules(appModule) }
        App(
            darkTheme = isDarkMode,
            dynamicColor = false
        )
    }
}