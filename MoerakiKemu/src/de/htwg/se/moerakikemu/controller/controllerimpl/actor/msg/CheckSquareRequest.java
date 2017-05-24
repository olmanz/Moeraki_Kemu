package de.htwg.se.moerakikemu.controller.controllerimpl.actor.msg;

import java.awt.Point;

import de.htwg.se.moerakikemu.modellayer.IField;

public class CheckSquareRequest {
	private Point point;
	private int fieldLength;
	private IField field;

	public CheckSquareRequest(Point point, IField field, int fieldLength) {
		this.point = point;
		this.field = field;
		this.fieldLength = fieldLength;
	}

	public Point getPoint() {
		return this.point;
	}

	public int getFieldLength() {
		return fieldLength;
	}

	public IField getField() {
		return field;
	}
}
