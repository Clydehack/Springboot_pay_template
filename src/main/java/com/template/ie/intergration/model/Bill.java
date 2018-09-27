package com.template.ie.intergration.model;

/**
 * @function:
 * 		通用账单对象
 * @author Clyde 2018-09-27
 */
public class Bill {
	
	String id;				// 账单编号
	String name;			// 商品名称
	String quantity;		// 商品数量
	String unit;			// 商品单位
	String desc;			// 商品描述
	String payStatus;		// 是否付款（0：否；1：是）
	String payMoney;		// 实际交易金额
	String discountMoney;	// 优惠金额
	String discountType;	// 优惠类型
	String discountDesc;	// 优惠描述
	String money;			// 总金额
	String createDate;		// 账单创建日期
	String createTime;		// 账单创建时间
	String upDateDate;		// 账单更新日期
	String upDateTime;		// 账单更新时间
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getPayStatus() {
		return payStatus;
	}
	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}
	public String getPayMoney() {
		return payMoney;
	}
	public void setPayMoney(String payMoney) {
		this.payMoney = payMoney;
	}
	public String getDiscountMoney() {
		return discountMoney;
	}
	public void setDiscountMoney(String discountMoney) {
		this.discountMoney = discountMoney;
	}
	public String getDiscountType() {
		return discountType;
	}
	public void setDiscountType(String discountType) {
		this.discountType = discountType;
	}
	public String getDiscountDesc() {
		return discountDesc;
	}
	public void setDiscountDesc(String discountDesc) {
		this.discountDesc = discountDesc;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getUpDateDate() {
		return upDateDate;
	}
	public void setUpDateDate(String upDateDate) {
		this.upDateDate = upDateDate;
	}
}