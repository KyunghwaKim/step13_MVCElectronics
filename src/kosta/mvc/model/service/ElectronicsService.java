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
	 * ElectronicsDAOImpl의 모든레코드 검색하는 메소드 호출
	 */
	public static List<Electronics> selectAll() throws SQLException {
		List<Electronics> list = elecDAO.selectAll();
		return list;
	}

	/**
	 * ElectronicsDAOImpl의 레코드 삽입하는 메소드 호출
	 */
	public static void insert(Electronics electronics) throws SQLException {
		int result = elecDAO.insert(electronics);
		if(result==0) throw new SQLException("등록되지 않았습니다.");
	}

	/**
	 * ElectronicsDAOImpl의 모델번호에 해당하는 레코드 검색하는 메소드 호출
	 * 
	 * @param : boolean flag - 조회수 증가 여부를 판별하는 매개변수임(true이면 조회수증가 / false이면 조회수 증가
	 *          안함)
	 */
	public static Electronics selectByModelnum(String modelNum, boolean flag) throws SQLException {
		// 글번호에 해당하는 게시물 검색
		if(flag) {
			if(elecDAO.increamentByReadnum(modelNum) == 0) {
				throw new SQLException("조회수 증가에 오류가 발생했습니다.");
			}
		}
		Electronics dbElec = elecDAO.selectByModelNum(modelNum);
		if(dbElec == null) throw new SQLException("모델번호에 해당하는 정보를 검색할 수 없습니다.");
		return dbElec;
	}

	/**
	 * ElectronicsDAOImpl의 모델번호에 해당하는 레코드 삭제 메소드 호출
	 * @param path 
	 */
	public static void delete(String modelNum, String password, String path) throws SQLException {
		Electronics dbElec = elecDAO.selectByModelNum(modelNum);
		
		//삭제하기 전에 비밀번호 먼저 체크한다.
		if(!dbElec.getPassword().equals(password)) {
			throw new SQLException("비밀번호를 다시 확인해주세요.");
		}
		
		int result = elecDAO.delete(modelNum, password);
		if(result==0) throw new SQLException("삭제되지 않았습니다.");

		//삭제 완료후에 파일에 업로드했던 파일 삭제하고싶다.
		if(dbElec.getFname() != null) {
			new File(path, dbElec.getFname()).delete();
		}
	}
	
	/**
	 * ElectronicsDAOImpl의 모델번호에 해당하는 레코드 수정 메소드 호출
	 */
	public static void update(Electronics electronics) throws SQLException {
		Electronics dbElec = elecDAO.selectByModelNum(electronics.getModelNum());
		
		//수정하기 전에 비밀번호 먼저 체크한다.
		if(!dbElec.getPassword().equals(electronics.getPassword())) {
			throw new SQLException("비밀번호를 다시 확인해주세요.");
		}
		
		int result = elecDAO.update(electronics);
		if(result==0) throw new SQLException("등록되지 않았습니다.");
		
	}
}
