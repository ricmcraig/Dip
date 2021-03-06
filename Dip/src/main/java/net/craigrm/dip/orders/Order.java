package net.craigrm.dip.orders;

import net.craigrm.dip.map.DipMap;
import net.craigrm.dip.map.ProvinceIdentifier;
import net.craigrm.dip.orders.properties.OrderType;
import net.craigrm.dip.state.Unit;

public class Order {
	public static final boolean WELL_FORMED = true;
	public static final boolean NOT_WELL_FORMED = false;

	private final String orderText;
	private final boolean wellFormed;
	private final Unit unit;
	private final OrderType orderType;
	private final ProvinceIdentifier target;

	public Order(String orderText, boolean wellFormed, Unit unit, OrderType orderType, ProvinceIdentifier target) {
		this.orderText = orderText;
		this.wellFormed = wellFormed;
		this.unit = unit;
		this.orderType = orderType;
		this.target = target;
	}

	public boolean isDuplicate(Order other) {
		if (this.unit == null || other.unit == null) {
			return false;
		}
		return this.unit == other.unit;
	}

	public String getOrderText() {
		return orderText;
	}

	public boolean isWellFormed() {
		return wellFormed;
	}

	public Unit getUnit() {
		return unit;
	}
	
	public OrderType getOrderType() {
		return orderType;
	}
	
	public ProvinceIdentifier getUnitPosition() {
		return unit.getCurrentPosition();
	}
	
	public ProvinceIdentifier getTarget() {
		return target;
	}
	
	public boolean hasValidStartingPosition() {
		return DipMap.getMap().isValidProvinceIdentifier(unit.getCurrentPosition());
	}

	public boolean hasValidTarget() {
		return DipMap.getMap().isValidProvinceIdentifier(target);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((orderText == null) ? 0 : orderText.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Order other = (Order) obj;
		if (orderText == null) {
			if (other.orderText != null) {
				return false;
			}
		} else if (!orderText.equals(other.orderText)) {
			return false;
		}
		return true;
	}

}
