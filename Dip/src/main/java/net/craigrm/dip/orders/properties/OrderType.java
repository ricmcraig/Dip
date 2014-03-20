package net.craigrm.dip.orders.properties;

public enum OrderType {

	MOVE("to"),
	HOLD("hold"),
	SUPPORT("sup"),
	CONVOY("con");
	
	private static String expectedMessage = "\"to\", \"hold\", \"sup\" or \"con\"";
	
	private String type;
	
	OrderType(String type) {
		this.type = type; 
	}
	
	/**
	 * 
	 * @param orderType textual representation of the type of order (e.g. "Hold", "to", etc)
	 * @return verified order type
	 * @throws OrderTypeFormatException if the textual representation cannot be matched to a verified order type.   
	 */ 

	public static OrderType getOrderType (String orderType) {
		String trimmedOrderType = orderType.trim();
		
		for (OrderType o: OrderType.values()) {
			if (o.getType().equalsIgnoreCase(trimmedOrderType)) {
				return o;
			}
		}
		throw new OrderTypeFormatException(trimmedOrderType, OrderType.expectedMessage);
	}

	public String getType() {
		return this.type;
	}
	
}
