package kosta.mvc.model.dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import kosta.mvc.model.domain.Electronics;
import kosta.mvc.util.DbUtil;

public class ElectronicsDAOImpl implements ElectronicsDAO {
	Properties pro = new Properties();	//���ҽ������� ���� �ƴ�, ������Ƽ���� ����
										//������ �ѹ� �о���� ���� �ƴ϶� �ʿ� �־���� ��� ã�ƿ;���
	public ElectronicsDAOImpl() {
		//sqlQuery.properties������ �ε��ϱ�
		InputStream input = getClass().getClassLoader().getResourceAsStream("kosta/mvc/model/dao/sqlQuery.properties");
		try {
			pro.load(input);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public List<Electronics> selectAll() throws SQLException {
		//��ü�˻�
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Electronics> list = new ArrayList<Electronics>();
		String sql = pro.getProperty("list");
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			//?�� ������ŭ setXxx() �ʿ��ϴ�.
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Electronics electronics = new Electronics(rs.getString("model_num")
						, rs.getString("model_name"), rs.getInt("price"), rs.getString("description")
						, rs.getString("password"), rs.getString("writeday"), rs.getInt("readnum")
						, rs.getString("fname"), rs.getInt("fsize"));
				list.add(electronics);
			}
		}finally {
			DbUtil.dbClose(con, ps, rs);
		}
		return list;
	}

	@Override
	public Electronics selectByModelNum(String modelNum) throws SQLException {
		//�󼼺���
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Electronics electronics = null;
		String sql = pro.getProperty("detail");
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, modelNum);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				electronics = new Electronics(rs.getString(1), rs.getString(2), rs.getInt(3),
						rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getString(8), rs.getInt(9));
			}
		}finally {
			DbUtil.dbClose(con, ps, rs);
		}
		return electronics;
	}

	@Override
	public int increamentByReadnum(String modelNum) throws SQLException {
		//��ȸ������
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		String sql = pro.getProperty("readnumUpdate");
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, modelNum);
			result = ps.executeUpdate();
		}finally {
			DbUtil.dbClose(con, ps);
		}
		return result;
	}

	@Override
	public int insert(Electronics electronics) throws SQLException {
		//���
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		String sql = pro.getProperty("insert");
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, electronics.getModelNum());
			ps.setString(2, electronics.getModelName());
			ps.setInt(3, electronics.getPrice());
			ps.setString(4, electronics.getDescription());
			ps.setString(5, electronics.getPassword());
			ps.setString(6, electronics.getFname());
			ps.setInt(7, electronics.getFsize());
			result = ps.executeUpdate();
		}finally {
			DbUtil.dbClose(con, ps);
		}
		return result;
	}

	@Override
	public int delete(String modelNum, String password) throws SQLException {
		//����
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		String sql = pro.getProperty("delete");
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, modelNum);
			ps.setString(2, password);
			result = ps.executeUpdate();
		}finally {
			DbUtil.dbClose(con, ps);
		}
		return result;
	}

	@Override
	public int update(Electronics electronics) throws SQLException {
		//����
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		String sql = pro.getProperty("update");
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, electronics.getModelName());
			ps.setInt(2, electronics.getPrice());
			ps.setString(3, electronics.getDescription());
			ps.setString(4, electronics.getModelNum());
			ps.setString(5, electronics.getPassword());
			result = ps.executeUpdate();
		}finally {
			DbUtil.dbClose(con, ps);
		}
		return result;
	}

}
