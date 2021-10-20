package seedu.address.commons.core;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Logger;

public class UserBrowser {
    private static Logger logger = LogsCenter.getLogger(UserBrowser.class);

    /**
     * Open URL inside User's Desktop Browser.
     * No validity checks
     * @param URL
     */
    public static void openURL(String URL) {
        logger.info("Opening URL in user browser: " + URL + "...");
        try {
            Desktop desktop = Desktop.getDesktop();
            desktop.browse(new URI(URL));
        } catch (URISyntaxException | IOException e) {
            logger.severe("Unable to open URL in user browser" + e.getMessage());
            e.printStackTrace();
        }
    }
}
