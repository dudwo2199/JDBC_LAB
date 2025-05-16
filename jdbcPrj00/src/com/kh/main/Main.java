package com.kh.main;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kh.dto.Book;
import com.kh.exception.RecordNotFoundException;
import com.kh.service.OracleService;

public class Main {

	public static void main(String[] args) throws Exception {

		OracleService service = new OracleService();

		/*INSERT*/
//		service.insert(new Book("백범일지", "김구", 10800, Date.valueOf("2005-11-01")));
//		service.insert(new Book("조선이 만난 아인슈타인", "민태기", 16650, Date.valueOf("2023-08-01")));
//		service.insert(new Book("아리랑", "님 웨일즈, 김산", 16200, Date.valueOf("2005-08-01")));
//		service.insert(new Book("그들의 대한제국 1897~1910", "김태웅", 10800, Date.valueOf("2024-12-01")));

		
		/*SELECT*/
//		List<Book> list = new ArrayList<>();
//		try {
//			list = service.select();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//
//		list.forEach(x -> {
//			System.out.println("Book [" + x + "]");
//		});
//		
		/*UPDATE*/
//		Book targetBook = Book.Modify(3, "Kotlin in Action 2/e", "세바스티안 아이그너", 43200, Date.valueOf("2025-02-01"));
//		try {
//			service.update(targetBook);
//		} catch (RecordNotFoundException e1) {
//			System.out.println(e1.getMessage());
//		}
	}
}