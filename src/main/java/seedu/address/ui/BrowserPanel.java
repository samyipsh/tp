package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;


/**
 * Controller for a help page
 */
public class BrowserPanel extends UiPart<Stage> {

    public static final String URL = "https://www.google.com";

    private static final Logger logger = LogsCenter.getLogger(BrowserPanel.class);
    private static final String FXML = "BrowserPanel.fxml";

    @FXML
    private Button loadButton;

    @FXML
    private TextField urlField;

    @FXML
    private WebView browser;

    private WebEngine engine;

    /**
     * Creates a new BrowserPanel.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public BrowserPanel(Stage root) {
        super(FXML, root);
        engine = browser.getEngine();
        engine.load(URL);
    }

    /**
     * Creates a new BrowserPanel.
     */
    public BrowserPanel() {
        this(new Stage());
    }

    /**
     * Shows the BrowserPanel window.
     * @throws IllegalStateException
     * <ul>
     *     <li>
     *         if this method is called on a thread other than the JavaFX Application Thread.
     *     </li>
     *     <li>
     *         if this method is called during animation or layout processing.
     *     </li>
     *     <li>
     *         if this method is called on the primary stage.
     *     </li>
     *     <li>
     *         if {@code dialogStage} is already showing.
     *     </li>
     * </ul>
     */
    public void show() {
        logger.fine("Showing Browser page about the application.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the BrowserPanel window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the BrowserPanel window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the BrowserPanel window.
     */
    public void focus() {
        getRoot().requestFocus();
    }
}
