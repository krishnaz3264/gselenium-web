package com.g.selenium.pojo;

public class Step {
	private String selector;
	private String name;
	private String action;
	private String value;
	private int delay;
	private int order;

	public String getAction() {
		return action;
	}

	public int getDelay() {
		return delay;
	}

	public String getName() {
		return name;
	}

	public int getOrder() {
		return order;
	}

	public String getSelector() {
		return selector;
	}

	public String getValue() {
		return value;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public void setDelay(int delay) {
		this.delay = delay;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public void setSelector(String selector) {
		this.selector = selector;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
