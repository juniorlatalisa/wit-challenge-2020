package br.dev.juniorlatalisa.wit.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@SuppressWarnings("serial")
public class OperationRequest implements Serializable {

	public OperationRequest(BigDecimal valueA, BigDecimal valueB, Operation operation) {
		this.valueA = valueA;
		this.valueB = valueB;
		this.operation = operation;
	}

	private final BigDecimal valueA;
	private final BigDecimal valueB;
	private final Operation operation;

	public BigDecimal getValueA() {
		return valueA;
	}

	public BigDecimal getValueB() {
		return valueB;
	}

	public Operation getOperation() {
		return operation;
	}

	@Override
	public int hashCode() {
		return Objects.hash(operation, valueA, valueB);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OperationRequest other = (OperationRequest) obj;
		return operation == other.operation && Objects.equals(valueA, other.valueA)
				&& Objects.equals(valueB, other.valueB);
	}

	@Override
	public String toString() {
		return "OperationRequest [" + (valueA != null ? "valueA=" + valueA + ", " : "")
				+ (valueB != null ? "valueB=" + valueB + ", " : "")
				+ (operation != null ? "operation=" + operation : "") + "]";
	}
}
