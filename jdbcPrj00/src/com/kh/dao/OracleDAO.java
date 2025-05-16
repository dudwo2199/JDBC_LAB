package com.kh.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kh.dto.Book;
import com.kh.exception.RecordNotFoundException;

public class OracleDAO {
	public List<Book> select(Connection con) throws SQLException {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<Book> list = new ArrayList<Book>();
		
		try {
			
			String sql = "SELECT * FROM BOOK";
			
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				int bid = rs.getInt("BID");
				String title = rs.getString("TITLE");
				String author = rs.getString("AUTHOR");
				int price = rs.getInt("PRICE");
				Date releaseDate = rs.getDate("RELEASE_DATE");

				list.add(new Book(bid, title, author, price, releaseDate));
			}
			
		} finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
		}
		return list;
	}

	public void insert(Connection con, Book book) {
		
		PreparedStatement pstmt = null;
		
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO BOOk(TITLE, AUTHOR, PRICE, RELEASE_DATE)");
			sql.append("VALUES(?, ?, ?, ?)");

			pstmt = con.prepareStatement(sql.toString());

			pstmt.setString(1, book.getTitle());
			pstmt.setString(2, book.getAuthor());
			pstmt.setInt(3, book.getPrice());
			pstmt.setDate(4, book.getReleaseDate());

			int result = pstmt.executeUpdate();
			System.out.println(result + " 개의 레코드가 변경되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void update(Connection con, Book book) throws RecordNotFoundException {
		
		PreparedStatement pstmt = null;
		
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE BOOK ");
			sql.append("SET ");
			sql.append("TITLE=?");
			sql.append(", AUTHOR=?");
			sql.append(", PRICE=?");
			sql.append(", RELEASE_DATE=?");
			sql.append(" WHERE BID=?");

			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, book.getTitle());
			pstmt.setString(2, book.getAuthor());
			pstmt.setInt(3, book.getPrice());
			pstmt.setDate(4, book.getReleaseDate());
			pstmt.setInt(5, book.getbID());
			
			int result = pstmt.executeUpdate();
			System.out.println(result+" 개의 레코드가 변경되었습니다.");
			if (result == 0) {
				throw new RecordNotFoundException(book.getbID()+" 레코드가 없습니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt!=null) pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}