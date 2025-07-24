package com.cbfacademy.springbootexercise.ious;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;

/**
 * The IOU class represents an IOU (I Owe You) entity with details such as ID, borrower, lender, amount, and date/time.
 */
@Entity
@Table(name = "ious")
public class IOU {

	/**
	 * The unique identifier for the IOU.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	// NOTE: There have been a few concerns raised by students who have noticed that the id field isn't readable in the database, because it's stored in MySQL as a BINARY(16) column.
	// It's possible for the field to be stored as a readable string, by using the following annotations:
	// @GeneratedValue
	// @JdbcTypeCode(Types.VARCHAR) or @JdbcType(VarcharJdbcType.class)
	// However, this is not recommended as it is not portable and may cause issues with other databases.
	// Another alternative is to use the `BIN_TO_UUID` function in MySQL to convert the binary value to a UUID, e.g.:
	// `SELECT BIN_TO_UUID(id) AS id, amount, borrower, lender, created_at FROM ious;`
	// This is also a good opportunity to discuss the pros and cons of storing binary values in a database.
	private UUID id;

	/**
	 * The name of the borrower in the IOU transaction.
	 */
	private String borrower;

	/**
	 * The name of the lender in the IOU transaction.
	 */
	private String lender;

	/**
	 * The amount of money in the IOU transaction.
	 */
	private BigDecimal amount;

	/**
	 * The timestamp when the IOU was created.
	 */
	private Instant createdAt;

	/**
	 * Default no-params constructor
	 */
	public IOU() {
		this(null, null, BigDecimal.ZERO, Instant.now());
	}

	/**
	 * Parameterised constructor to create an IOU with specified details.
	 *
	 * @param borrower The name of the borrower.
	 * @param lender   The name of the lender.
	 * @param amount   The amount of money in the IOU.
	 * @param createdAt The timestamp when the IOU was created.
	 */
	public IOU(String borrower, String lender, BigDecimal amount, Instant createdAt) {
		this.borrower = borrower;
		this.lender = lender;
		this.amount = amount;
		this.createdAt = createdAt;
	}

	/**
	 * Get the ID of the IOU.
	 *
	 * @return The unique identifier for the IOU.
	 */
	public UUID getId() {
		return this.id;
	}

	/**
	 * Get the name of the borrower.
	 *
	 * @return The name of the borrower in the IOU transaction.
	 */
	public String getBorrower() {
		return this.borrower;
	}

	/**
	 * Set the name of the borrower.
	 *
	 * @param borrower The name of the borrower to set in the IOU transaction.
	 */
	public void setBorrower(String borrower) {
		this.borrower = borrower;
	}

	/**
	 * Get the name of the lender.
	 *
	 * @return The name of the lender in the IOU transaction.
	 */
	public String getLender() {
		return this.lender;
	}

	/**
	 * Set the name of the lender.
	 *
	 * @param lender The name of the lender to set in the IOU transaction.
	 */
	public void setLender(String lender) {
		this.lender = lender;
	}

	/**
	 * Get the amount of money in the IOU transaction.
	 *
	 * @return The amount of money in the IOU.
	 */
	public BigDecimal getAmount() {
		return this.amount;
	}

	/**
	 * Set the amount of money in the IOU transaction.
	 *
	 * @param amount The amount of money to set in the IOU.
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	/**
	 * Get the timestamp of when the IOU was created.
	 *
	 * @return The timestamp when the IOU was created.
	 */
	public Instant getCreatedAt() {
		return this.createdAt;
	}
}
