package kosta.mvc.model.service;

import java.io.File;
import java.sql.SQLException;
import java.util.List;

import kosta.mvc.model.dao.ElectronicsDAO;
import kosta.mvc.model.dao.ElectronicsDAOImpl;
import kosta.mvc.model.domain.Electronics;

public class ElectronicsService {

	private static ElectronicsDAO elecDAO = new ElectronicsDAOImpl();

	/**
	 * ElectronicsDAOImpl�� ��緹�ڵ� �˻��ϴ� �޼ҵ� ȣ��
	 */
	public static List<Electronics> selectAll() throws SQLException {
		List<Electronics> list = elecDAO.selectAll();
		return list;
	}

	/**
	 * ElectronicsDAOImpl�� ���ڵ� �����ϴ� �޼ҵ� ȣ��
	 */
	public static void insert(Electronics electronics) throws SQLException {
		int result = elecDAO.insert(electronics);
		if(result==0) throw new SQLException("��ϵ��� �ʾҽ��ϴ�.");
	}

	/**
	 * ElectronicsDAOImpl�� �𵨹�ȣ�� �ش��ϴ� ���ڵ� �˻��ϴ� �޼ҵ� ȣ��
	 * 
	 * @param : boolean flag - ��ȸ�� ���� ���θ� �Ǻ��ϴ� �Ű�������(true�̸� ��ȸ������ / false�̸� ��ȸ�� ����
	 *          ����)
	 */
	public static Electronics selectByModelnum(String modelNum, boolean flag) throws SQLException {
		// �۹�ȣ�� �ش��ϴ� �Խù� �˻�
		if(flag) {
			if(elecDAO.increamentByReadnum(modelNum) == 0) {
				throw new SQLException("��ȸ�� ������ ������ �߻��߽��ϴ�.");
			}
		}
		Electronics dbElec = elecDAO.selectByModelNum(modelNum);
		if(dbElec == null) throw new SQLException("�𵨹�ȣ�� �ش��ϴ� ������ �˻��� �� �����ϴ�.");
		return dbElec;
	}

	/**
	 * ElectronicsDAOImpl�� �𵨹�ȣ�� �ش��ϴ� ���ڵ� ���� �޼ҵ� ȣ��
	 * @param path 
	 */
	public static void delete(String modelNum, String password, String path) throws SQLException {
		Electronics dbElec = elecDAO.selectByModelNum(modelNum);
		
		//�����ϱ� ���� ��й�ȣ ���� üũ�Ѵ�.
		if(!dbElec.getPassword().equals(password)) {
			throw new SQLException("��й�ȣ�� �ٽ� Ȯ�����ּ���.");
		}
		
		int result = elecDAO.delete(modelNum, password);
		if(result==0) throw new SQLException("�������� �ʾҽ��ϴ�.");

		//���� �Ϸ��Ŀ� ���Ͽ� ���ε��ߴ� ���� �����ϰ�ʹ�.
		if(dbElec.getFname() != null) {
			new File(path, dbElec.getFname()).delete();
		}
	}
	
	/**
	 * ElectronicsDAOImpl�� �𵨹�ȣ�� �ش��ϴ� ���ڵ� ���� �޼ҵ� ȣ��
	 */
	public static void update(Electronics electronics) throws SQLException {
		Electronics dbElec = elecDAO.selectByModelNum(electronics.getModelNum());
		
		//�����ϱ� ���� ��й�ȣ ���� üũ�Ѵ�.
		if(!dbElec.getPassword().equals(electronics.getPassword())) {
			throw new SQLException("��й�ȣ�� �ٽ� Ȯ�����ּ���.");
		}
		
		int result = elecDAO.update(electronics);
		if(result==0) throw new SQLException("��ϵ��� �ʾҽ��ϴ�.");
		
	}
}
