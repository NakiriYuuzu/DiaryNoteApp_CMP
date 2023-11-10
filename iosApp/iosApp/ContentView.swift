import SwiftUI
import shared

struct ComposeView: UIViewControllerRepresentable {
    func updateUIViewController(_ uiViewController: UIViewControllerType, context: Context) {}

    func makeUIViewController(context: Context) -> some UIViewController {
        Main_iosKt.MainViewController()
    }
}

struct ContentView: View {
    var body: some View {
		ComposeView().ignoresSafeArea(.all, edges: .bottom)
	}
}