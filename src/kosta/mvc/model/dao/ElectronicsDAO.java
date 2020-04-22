package kosta.mvc.model.dao;

import java.sql.SQLException;
import java.util.List;

import kosta.mvc.model.domain.Electronics;

public interface ElectronicsDAO {
	 /**
	  * 레코드 전체 검색
	  * */
	  List<Electronics> selectAll() throws SQLException;
	  
	  /**
	   * 모델번호에 해당하는 레코드 검색(상세보기)
	   * @param 
	   * @return 
	   * */
	  Electronics selectByModelNum(String modelNum) throws SQLException;
	  
	  /**
	   * 조회수를 증가하는 기능
	   * update Electronics set readnum = readnum + 1 where model_num=?
	   * */
	  int increamentByReadnum(String modelNum) throws SQLException;
	  
	  
	/**
	 * 레코드 삽입
	 * @return : 1-삽입성공 , 0 - 삽입실패
	 * */
	  int insert(Electronics electronics) throws SQLException;
	  
	  /**
	   * 모델번호에 해당하는 레코드 삭제
	   * @return : 1-삭제성공 , 0 - 삭제실패
	   * */
	  int delete(String modelNum, String password) throws SQLException;
	  
	   /**
	    * 모델번호에 해당하는 레코드 수정(모델이름, 가격, 내용)
	    * @return : 1-수정성공 , 0 - 수정실패
	    * */
	  int update(Electronics electronics) throws SQLException;
}





