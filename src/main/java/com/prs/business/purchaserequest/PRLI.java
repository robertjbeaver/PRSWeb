package com.prs.business.purchaserequest;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.prs.business.product.Product;


//PurchaseRequestLineItem
@Entity
@Table(name="PurchaseRequestLineItem")
public class PRLI {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JoinColumn(name="purchaseRequestID")
	private PurchaseRequest purchaseRequest;
	@ManyToOne
	@JoinColumn(name="productID")
	private Product product;
	private int quantity;
	
	public PRLI() {
		super();
	}

	public PRLI(int id, PurchaseRequest purchaseRequest, Product product, int quantity) {
		super();
		this.id = id;
		this.purchaseRequest = purchaseRequest;
		this.product = product;
		this.quantity = quantity;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public PurchaseRequest getPurchaseRequestID() {
		return purchaseRequest;
	}

	public void setPurchaseRequestID(PurchaseRequest purchaseRequest) {
		this.purchaseRequest = purchaseRequest;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "PRLI [id=" + id + ", purchaseRequestID=" + purchaseRequest + ", product=" + product
				+ ", quantity=" + quantity + "]";
	}
	
	
}
