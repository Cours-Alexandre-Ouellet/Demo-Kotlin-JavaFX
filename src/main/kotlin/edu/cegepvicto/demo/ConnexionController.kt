package edu.cegepvicto.demo

import javafx.collections.FXCollections.observableArrayList
import javafx.fxml.FXML
import javafx.scene.control.*
import javafx.scene.text.Text

/**
 * Contrôleur de l'interface de connexion
 */
class ConnexionController {

    /**
     * Liste à choix du type de comptes
     */
    @FXML
    private lateinit var typeCompte: ComboBox<String>

    /**
     * Champ pour saisir le courriel
     */
    @FXML
    private lateinit var courriel: TextField

    /**
     * Champ pour saisir le mot de passe
     */
    @FXML
    private lateinit var motDePasse: PasswordField

    /**
     * Champ pour indiquer que l'on reste connecté
     */
    @FXML
    private lateinit var resterConnecter: CheckBox

    /**
     * Zone pour afficher le texte d'erreur
     */
    @FXML
    private lateinit var messageErreurTypeCompte: Text

    /**
     * Constante du message d'erreur pour les types de compte
     */
    private val erreurTypeCompte = "Aucun type de compte n'a été sélectionné."

    @FXML
    fun initialize() {
        // On peuple le ComboBox
        // ArrayList = mutableList (kotlin) = List (C#)
        typeCompte.items = observableArrayList(
            listOf("Sélectionnez une valeur", "Administrateur", "Invité")
        )

        // SelectionModel pointe sur les éléments qui sont sélectionnés
        typeCompte.selectionModel.selectFirst()

        // Ajoute un listener pour réagir à l'événement de changement d'item sélectionné
        typeCompte.selectionModel.selectedItemProperty().addListener {objetObservable, vielleValeur, nouvelleValeur -> validerTypeCompte()}

        // Si on voulait le faire avec la perte de focus
        // _ pour les paramètres de lambda non utilisés
        // typeCompte.focusedProperty().addListener({_, _, _ -> validerTypeCompte()})
    }

    /**
     * Valide que le type de compte est différent de "Sélectionnez une valeur".
     */
    private fun validerTypeCompte(): Boolean {
        val indexTypeCompteSelectionne = typeCompte.selectionModel.selectedIndex

        // simplication du code
        val typeCompteInvalide = indexTypeCompteSelectionne < 1;
        messageErreurTypeCompte.text = if (typeCompteInvalide) erreurTypeCompte else ""
        return !typeCompteInvalide
    }

    @FXML
    private fun connexion() {
        if (!validerTypeCompte()) {
            return
        }
        val typeCompteSelectionne = typeCompte.selectionModel.selectedItem

        // Regex pour vérifier le courriel
        val courrielEntre = courriel.text
        if(!courrielEntre.matches(Regex("[\\w\\d]+@\\w+\\.\\w+"))) {
            // Erreur de courriel
            println("Erreur de courriel")
        }

        val motDePasseSaisie = motDePasse.text
        val doitResterConnecter = resterConnecter.isSelected

        println("Type $typeCompteSelectionne | Courriel : $courrielEntre | Mot de passe : $motDePasseSaisie | Rester connecter : $doitResterConnecter")

        // Appel DAO
        val utilisateurs = listOf(Utilisateur("Administrateur", "Bob@bob.bob", "12345"))

        // Vérification de la connexion
        if(utilisateurs.any { u -> u.courriel == courrielEntre && u.motDePasse == motDePasseSaisie && u.typeCompte == typeCompteSelectionne }) {
            println("Connecté avec succès")
        } else {
            println("Accès refusé")
        }

    }
}