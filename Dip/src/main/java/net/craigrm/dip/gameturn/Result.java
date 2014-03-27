package net.craigrm.dip.gameturn;

class Result {
	
	enum OrderOrigin {
		PLAYER,
		SYSTEM;
	}
	
	private String orderText;
	private OrderOrigin origin;
	private OrderStatus status;
	private String detail;
	private String retreat;
	
	Result(String orderText, OrderOrigin origin) {
		new Result(orderText, origin, OrderStatus.UNKNOWN, "");
	}
	
	Result(String orderText, OrderOrigin origin, OrderStatus status, String detail) {
		if (orderText == null || orderText.isEmpty()) {
			throw new IllegalArgumentException("Must supply order text.");
		}
		
		if (detail == null) {
			throw new IllegalArgumentException("Detail cannot be null.");
		}
		
		this.orderText = orderText;
		this.origin = origin;
		this.status = status;
		this.detail = detail;
		this.retreat = "";
	}

	public String getRetreat() {
		return retreat;
	}

	public boolean hasRetreats() {
		return !retreat.isEmpty();
	}
	
	public void setRetreat(String retreat) {
		if (!this.retreat.isEmpty()) {
			throw new IllegalStateException("Retreat has already been set as: " + retreat + ".");
		}
		this.retreat = retreat;
	}

	public String getOrderText() {
		return orderText;
	}

	public OrderOrigin getOrigin() {
		return origin;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public String getDetail() {
		return detail;
	}
	
	public boolean isResolved() {
		return status != OrderStatus.UNKNOWN;
	}
	
	public void Resolve (OrderStatus status, String detail) {
		if (this.status != OrderStatus.UNKNOWN) {
			throw new IllegalStateException("This result is already resolved as: " + this.status + ".");
		}
		
		if (status == OrderStatus.UNKNOWN) {
			throw new IllegalArgumentException("Cannot resolve to " + OrderStatus.UNKNOWN + ".");
		}
		
		if (detail == null) {
			throw new IllegalArgumentException("Detail cannot be null.");
		}
		
		this.status = status;
		this.detail = detail;
		
	}
	
	
	
}
