package org.sample.aws.serverless.books;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

/**
 * @author arungupta
 */
@DynamoDBTable(tableName = "Books")
public class Book {

	private String id;
	private String name;
	private String isbn;
	private String cost;

	public Book() {
	}
	
	public Book(String id, String name, String isbn, String cost) {
		this.id = id;
		this.name = name;
		this.isbn = isbn;
		this.cost = cost;
	}

	@DynamoDBHashKey
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@DynamoDBAttribute
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@DynamoDBAttribute
	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	@DynamoDBAttribute
	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}

	@Override
	public String toString() {
		return "id: " + getId() 
		+ ", name: " + getName() 
		+ ", isbn: " + getIsbn() 
		+ ", cost: " + getCost();
	}

}
