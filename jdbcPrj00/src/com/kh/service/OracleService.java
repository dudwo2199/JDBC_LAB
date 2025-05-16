package com.kh.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import org.json.simple.JSONObject;

import com.kh.dao.OracleDAO;
import com.kh.dto.Book;
import com.kh.exception.RecordNotFoundException;

import util.JSONCtrl;

public class OracleService {
	private static final String CONFIG = "config";

	private static final String KEY_CLASS_NAME = "CLASSNAME";
	private static final String KEY_URL = "URL";
	private static final String KEY_USER = "USER";
	private static final String KEY_PASSWORD = "PASSWORD";

	private String className;
	private String url;
	private String user;
	private String passWord;

	OracleDAO dao;

	public OracleService() {
		JSONObject obj = JSONCtrl.load(CONFIG);
		className = JSONCtrl.get(obj, KEY_CLASS_NAME);
		url = JSONCtrl.get(obj, KEY_URL);
		user = JSONCtrl.get(obj, KEY_USER);
		passWord = JSONCtrl.get(obj, KEY_PASSWORD);

		dao = new OracleDAO();

		try {
			Class.forName(className);
			System.out.println("드라이버 로딩 성공");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public List<Book> select() throws SQLException {
		Connection con = null;
		List<Book> list = null;
		try {
			con = DriverManager.getConnection(url, user, passWord);
			list = dao.select(con);
		} finally {
			if (con != null)
				con.close();
		}
		return list;
	}
	
	public void insert(Book book) {
		Connection con = null;
		try {
			con = DriverManager.getConnection(url, user, passWord);
			dao.insert(con, book);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(con!=null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void update(Book book) throws RecordNotFoundException {
		Connection con = null;
		try {
			con = DriverManager.getConnection(url, user, passWord);
			dao.update(con, book);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(con!=null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
