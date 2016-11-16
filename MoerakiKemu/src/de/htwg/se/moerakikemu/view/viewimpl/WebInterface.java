package de.htwg.se.moerakikemu.view.viewimpl;

import de.htwg.se.moerakikemu.controller.IController;

/**
 * Created by Eti on 16.11.2016.
 */
public class WebInterface {

    private IController controller;

    private static final String OPENING = "[";
    private static final String CLOSING = "]";
    private static final String NEWLINE = "\n";
    private static final String JSONARRAYDELIMITER = ", ";
    private static final String EMPTYSTRING = "";
    private static final String DQUOTES = "\"";

    public WebInterface(IController controller) {
        this.controller = controller;
    }

    public String getBoardAsJSON() {
        final int boardLength = controller.getEdgeLength();

        StringBuilder json = new StringBuilder("{");
        json.append(OPENING).append(NEWLINE);

        for (int i = 0; i < boardLength; i++) {
            json.append(OPENING);
            for (int j = 0; j < boardLength; j++) {
                json.append(JsonEscapeValue(controller.getIsOccupiedByPlayer(i, j)));
                json.append(delimiter(boardLength, j));
            }
            json.append(CLOSING).append(NEWLINE);
            json.append(delimiter(boardLength, i));
        }
        json.append(CLOSING);

        return json.append("}").toString();
    }

    private String delimiter(final int edgeLength, final int pos) {
        return pos < edgeLength - 1 ? JSONARRAYDELIMITER : EMPTYSTRING;
    }

    private static String JsonEscapeValue(final String value) {
        return DQUOTES + value + DQUOTES;
    }

}
