package net.craigrm.dip.orders.properties;

public enum OrderType {

	MOVE("to"),
	HOLD("hold"),
	SUPPORT("sup"),
	CONVOY("con");
	
	private static String expectedMessage = "Expected value of \"to\", \"hold\", \"sup\" or \"con\".";
	
	private String type;
	
	OrderType(String type){
		this.type = type; 
	}
	
	public static OrderType getOrderType (String orderType){
		String trimmedOrderType = orderType.trim();
		
		for (OrderType o: OrderType.values()){
			if (o.getType().equalsIgnoreCase(trimmedOrderType)){
				return o;
			}
		}
		throw new OrderTypeFormatException(OrderType.expectedMessage + "Got: " + trimmedOrderType);
	}

	public String getType(){
		return this.type;
	}
	
}
