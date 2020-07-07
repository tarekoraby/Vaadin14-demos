package org.vaadin.tarek.advanced;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.InitialPageSettings;
import com.vaadin.flow.server.PageConfigurator;

/**
 * The main view contains a button and a click listener.
 */
@Route("PageConfiguratorView")
public class PageConfiguratorView extends VerticalLayout implements PageConfigurator {

    public PageConfiguratorView() {
        add("PageConfiguratorView");
    }

    @Override
    public void configurePage(InitialPageSettings settings) {
        /*
         * settings.addInlineFromFile(InitialPageSettings.Position.PREPEND, "inline.js",
         * InitialPageSettings.WrapMode.JAVASCRIPT);
         */

        settings.addMetaTag("og:title", "The Rock");
        settings.addMetaTag("og:type", "video.movie");
        settings.addMetaTag("og:url", "http://www.imdb.com/title/tt0117500/");
        settings.addMetaTag("og:image", "http://ia.media-imdb.com/images/rock.jpg");

        settings.addLink("shortcut icon", "icons/favicon.ico");
        settings.addFavIcon("icon", "icons/VaadinLogomark_RGB_500x500.png",
                "192x192");
    }
}
