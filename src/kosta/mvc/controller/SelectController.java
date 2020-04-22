package kosta.mvc.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kosta.mvc.model.domain.Electronics;
import kosta.mvc.model.service.ElectronicsService;

/**
 * 전체 검색하기
 */
public class SelectController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//전체 검색
		List<Electronics> list = ElectronicsService.selectAll();
		
		request.setAttribute("list", list);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("elecView/list.jsp");
		
		return mv;
	}

}
