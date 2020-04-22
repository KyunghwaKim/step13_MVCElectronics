package kosta.mvc.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kosta.mvc.model.domain.Electronics;
import kosta.mvc.model.service.ElectronicsService;

/**
 * ��ü �˻��ϱ�
 */
public class SelectController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//��ü �˻�
		List<Electronics> list = ElectronicsService.selectAll();
		
		request.setAttribute("list", list);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("elecView/list.jsp");
		
		return mv;
	}

}
