package br.dev.juniorlatalisa.wit.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@SuppressWarnings("serial")
public class OperationResponse implements Serializable {

	public OperationResponse(BigDecimal result) {
		this.result = result;
	}

	private final BigDecimal result;

	public BigDecimal getResult() {
		return result;
	}

	@Override
	public int hashCode() {
		return Objects.hash(result);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OperationResponse other = (OperationResponse) obj;
		return Objects.equals(result, other.result);
	}

	@Override
	public String toString() {
		return "OperationResponse [" + (result != null ? "result=" + result : "") + "]";
	}
}
