package net.craigrm.dip.orders;

import net.craigrm.dip.orders.properties.AdjustmentType;
import net.craigrm.dip.state.Unit;

public class Adjustment {

	public static final boolean WELL_FORMED = true;
	public static final boolean NOT_WELL_FORMED = false;

	private final String adjustmentText;
	private final boolean wellFormed;
	private final AdjustmentType adjustmentType;
	private final Unit unit;
	
	public Adjustment(String adjustmentText, boolean wellFormed, AdjustmentType adjustmentType, Unit unit) {
		this.adjustmentText = adjustmentText;
		this.wellFormed = wellFormed;
		this.adjustmentType = adjustmentType;
		this.unit = unit;
	}

	public String getAdjustmentText() {
		return adjustmentText;
	}

	public boolean isWellFormed() {
		return wellFormed;
	}

	public AdjustmentType getAdjustmentType() {
		return adjustmentType;
	}

	public Unit getUnit() {
		return unit;
	}
	
}
