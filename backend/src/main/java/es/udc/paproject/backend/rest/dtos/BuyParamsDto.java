package es.udc.paproject.backend.rest.dtos;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Size;
import javax.validation.constraints.Positive;

public class BuyParamsDto {

	private Long sesionId;
	private int quantity;
	private String creditCard;
		
	@NotNull
	public Long getSesionId() {
		return sesionId;
	}

	public void setSesionId(Long sesionId) {
	this.sesionId = sesionId;
	}
	
	@NotNull
	@Positive
	@Max(value = 10)
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	//@Size(min=16, max=16) 
	public String getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(String creditCard) {
		this.creditCard = creditCard;
	}
}
