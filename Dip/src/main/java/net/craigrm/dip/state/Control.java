package net.craigrm.dip.state;

import net.craigrm.dip.map.Identifier;
import net.craigrm.dip.map.properties.Powers;

public class Control {
	private final Identifier provinceId;
	private final Powers controller;

	public Control(Identifier provinceId, Powers controller){
		this.provinceId = provinceId;
		this.controller = controller;
	}

	public Identifier getProvinceId() {
		return provinceId;
	}

	public Powers getController() {
		return controller;
	}

}
