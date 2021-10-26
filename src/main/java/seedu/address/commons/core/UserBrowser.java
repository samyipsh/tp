package seedu.address.commons.core;

import java.awt.Desktop;
import java.awt.GraphicsEnvironment;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Logger;

public class UserBrowser {
    private static Logger logger = LogsCenter.getLogger(UserBrowser.class);

    /**
     * Open URL inside User's Desktop Browser.
     * Assume running platform supports desktop. Use {@link #isDisplayAndBrowseCompatible()} to check.
     * No validity checks of valid url.
     *
     * @param url
     */
    public static void openUrl(String url) {
        if (!isDisplayAndBrowseCompatible()) {
            return;
        }

        Desktop desktop = Desktop.getDesktop();
        logger.info("Opening URL in user browser: " + url + "...");

        try {
            desktop.browse(new URI(url));
        } catch (URISyntaxException | IOException e) {
            logger.severe("Unable to open URL in user browser" + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Checks if platform running code supports Desktop and Desktop.Action.BROWSE.
     * Helper function used to verify platform compatibility before calls to {@link #openUrl(String)}
     */
    public static boolean isDisplayAndBrowseCompatible() {
        if (GraphicsEnvironment.isHeadless()) {
            logger.warning("Platform does not support display.\n GraphicsEnviornment.isHeadless()");
            return false;
        }

        Desktop d = Desktop.getDesktop();
        if (!d.isSupported(Desktop.Action.BROWSE)) {
            logger.warning("Platform does not support browsing in desktop");
            return false;
        }

        return true;
    }
}
