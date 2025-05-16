package com.kh.dto;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class Book {
	
	public Book() {
		
	}
	
	public Book(String title, String author, int price, Date releaseDate) {
		this.title = title;
		this.author = author;
		this.price = price;
		this.releaseDate = releaseDate;
	}
	
	public Book(int bid, String title, String author, int price, Date releaseDate) {
		this.bID = bid;
		this.title = title;
		this.author = author;
		this.price = price;
		this.releaseDate = releaseDate;
	}
	
	public int getbID() { return bID; }
	public String getTitle() { return title; }
	public String getAuthor() { return author; }
	public int getPrice() { return price; }
	public Date getReleaseDate() { return releaseDate; }
	
	@Override
	public String toString() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy년 MM월");
		return String.format("%03d, 제목=%s, 저자=%s, 가격=%,d, 출시일=%s",
				bID
				, title
				, author
				, price
				, dateFormat.format(releaseDate));
	}
	
	public static Book Modify(int bid, String newTitle, String newAuthor, int newPrice, Date newReleaseDate) {
		return new Book(bid, newTitle, newAuthor, newPrice, newReleaseDate);
	}

	private int bID;
	private String title;
	private String author;
	private int price;
	private Date releaseDate;
}
