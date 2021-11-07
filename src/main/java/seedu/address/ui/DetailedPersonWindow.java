package seedu.address.ui;
import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import seedu.address.model.person.Person;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class DetailedPersonWindow extends UiPart<Stage> {

    private static final String FXML = "DetailedPersonWindow.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on ContactBook level 4</a>
     */

    public final Person person;
    private Stage secondaryStage;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label phone;
    @FXML
    private Label email;
    @FXML
    private Label linkedin;
    @FXML
    private Label github;
    @FXML
    private Label detail;
    @FXML
    private FlowPane tags;


    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public DetailedPersonWindow(Person person, Stage secondaryStage) {
        super(FXML, secondaryStage);
        this.person = person;
        name.setText(person.getName().fullName);
        phone.setText("Phone: " + person.getPhone().value);
        email.setText("Email: " + person.getEmail().value);
        linkedin.setText("LinkedIn: " + person.getLinkedin().value);
        github.setText("Github: " + person.getGithub().githubUsername);
        detail.setText("Details: " + person.getDetail().value);
        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag + " ")));
        this.secondaryStage = secondaryStage;
    }
    void show() {
        secondaryStage.show();
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DetailedPersonWindow)) {
            return false;
        }

        // state check
        DetailedPersonWindow card = (DetailedPersonWindow) other;
        return person.equals(card.person);
    }

    /**
     * Hides the alias window
     */
    public void hide() {
        getRoot().hide();
    }
}

