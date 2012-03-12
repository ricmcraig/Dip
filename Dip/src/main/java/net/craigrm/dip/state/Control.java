package net.craigrm.dip.state;

import net.craigrm.dip.map.ProvinceIdentifier;
import net.craigrm.dip.map.properties.Powers;

public class Control {
	private final ProvinceIdentifier provinceId;
	private final Powers controller;

	public Control(ProvinceIdentifier provinceId, Powers controller) {
		this.provinceId = provinceId;
		this.controller = controller;
	}

	public ProvinceIdentifier getProvinceId() {
		return provinceId;
	}

	public Powers getController() {
		return controller;
	}

}
