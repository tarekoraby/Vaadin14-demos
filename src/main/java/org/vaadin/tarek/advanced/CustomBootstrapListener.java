package org.vaadin.tarek.advanced;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.vaadin.flow.server.BootstrapListener;
import com.vaadin.flow.server.BootstrapPageResponse;

public class CustomBootstrapListener implements BootstrapListener {

    @Override
    public void modifyBootstrapPage(BootstrapPageResponse response) {
        Document document = response.getDocument();

        Element head = document.head();

        head.appendChild(createMeta(document, "og:title", "The Rock"));
        head.appendChild(createMeta(document, "og:type", "video.movie"));
        head.appendChild(createMeta(document, "og:url",
                "http://www.imdb.com/title/tt0117500/"));
        head.appendChild(createMeta(document, "og:image",
                "http://ia.media-imdb.com/images/rock.jpg"));
    }

    private Element createMeta(Document document, String property,
            String content) {
        Element meta = document.createElement("meta");
        meta.attr("property", property);
        meta.attr("content", content);
        return meta;
    }
}