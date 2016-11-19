package de.htwg.se.moerakikemu.view.viewimpl;

import de.htwg.se.moerakikemu.controller.IController;

/**
 * Created by Eti on 16.11.2016.
 */
public class WebInterface {

    private IController controller;

    private static final String OPENING = "[";
    private static final String CLOSING = "]";
    private static final String oOpening = "{";
    private static final String oCLOSING = "}";
    private static final String NEWLINE = "\n";
    private static final String JSONARRAYDELIMITER = ", ";
    private static final String EMPTYSTRING = "";
    private static final String DQUOTES = "\"";
    private static final String LINES = "lines";
    private static final String CELLS = "cells";

    public WebInterface(IController controller) {
        this.controller = controller;
    }
    
    public String occupyAndGetBoard(final String coordinates) {
		int []ij = splitXY(coordinates);
    	controller.occupy(ij[0], ij[1]);
    	return getBoardAsJSON();
    }

	/**
	 * Takes a parameter (from AJAX call) and extracts the x and y coordinate.
	 *
	 * @param param String that must match the pattern [0-9]+/[0-9]*
	 * @return An array of int with the length of 2.
	 */
	public static final int[] splitXY(final String param) {
	    final int idx = param.indexOf("-");
        final int i = Integer.parseInt(param.substring(0, idx));
	    final int j = Integer.parseInt(param.substring(idx + 1));
	    return new int[]{i, j};
	}
	
    public String getBoardAsJSON() {
        final int boardLength = controller.getEdgeLength();

        StringBuilder json = new StringBuilder(oOpening);
        json.append(JsonEscapeValue(LINES)).append(":");
        json.append(OPENING).append(NEWLINE);

        for (int i = 0; i < boardLength; i++) {
            json.append(getJSONLine(i));
            json.append(delimiter(boardLength, i));
        }
        json.append(CLOSING);

        return json.append(oCLOSING).toString();
    }

    private String getJSONLine(final int lineNumber) {
    	final int boardLength = controller.getEdgeLength();
    	
    	StringBuilder line = new StringBuilder(oOpening);
    	line.append(JsonEscapeValue(CELLS)).append(":");
    	
    	line.append(OPENING);
    	for (int j = 0; j < boardLength; j++) {
    		line.append(JsonEscapeValue(controller.getIsOccupiedByPlayer(lineNumber, j)));
    		line.append(delimiter(boardLength, j));
    	}
    	line.append(CLOSING).append(NEWLINE);
    	
    	return line.append(oCLOSING).toString();
    }
    
    private String delimiter(final int edgeLength, final int pos) {
        return pos < edgeLength - 1 ? JSONARRAYDELIMITER : EMPTYSTRING;
    }

    private static String JsonEscapeValue(final String value) {
        return DQUOTES + value + DQUOTES;
    }

}
