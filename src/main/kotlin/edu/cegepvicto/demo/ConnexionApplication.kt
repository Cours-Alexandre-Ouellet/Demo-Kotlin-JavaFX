package edu.cegepvicto.demo

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.stage.Stage

/**
 * Démarre l'application de connexion
 */
class ConnexionApplication : Application() {
    override fun start(stage: Stage) {
        val fxmlLoader = FXMLLoader(ConnexionApplication::class.java.getResource("connexion.fxml"))
        val scene = Scene(fxmlLoader.load(), 320.0, 240.0)
        stage.title = "Connectez-vous !"
        stage.scene = scene
        stage.show()
    }
}

fun main() {
    Application.launch(ConnexionApplication::class.java)
}