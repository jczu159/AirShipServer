package com.airship.server.pojo;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bet_both_number")
public class BetOrderPojo implements Serializable {

	private static final long serialVersionUID = -1798070786983154676L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private Integer id; // ID 自增

	@Column(name = "PERIOD")
	private Integer period; // 期數

	@Column(name = "RANKING")
	private Integer ranking; // 下單名次

	@Column(name = "ORDER_RESULT")
	private String order_result; // 建議下單結果

	@Column(name = "OVERWEIGHT")
	private Integer overweight; // 加碼次數

	@Column(name = "CREATE_DATE")
	private Date create_date; // 建立時間

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPeriod() {
		return period;
	}

	public void setPeriod(Integer period) {
		this.period = period;
	}

	public Integer getRanking() {
		return ranking;
	}

	public void setRanking(Integer ranking) {
		this.ranking = ranking;
	}

	public String getOrder_result() {
		return order_result;
	}

	public void setOrder_result(String order_result) {
		this.order_result = order_result;
	}

	public Integer getOverweight() {
		return overweight;
	}

	public void setOverweight(Integer overweight) {
		this.overweight = overweight;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

}
