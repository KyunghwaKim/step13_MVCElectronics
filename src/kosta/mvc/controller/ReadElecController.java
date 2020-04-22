package kosta.mvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kosta.mvc.model.domain.Electronics;
import kosta.mvc.model.service.ElectronicsService;

public class ReadElecController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//modelNum�ޱ�
		String modelNum = request.getParameter("modelNum");
		String state = request.getParameter("state");
		boolean flag = state==null ? true : false;
		if(modelNum==null || modelNum.equals("")) {
			throw new RuntimeException("�𵨹�ȣ���� ������� �ʽ��ϴ�.");
		}
		
		Electronics elec = ElectronicsService.selectByModelnum(modelNum, flag);
		request.setAttribute("elec", elec);	//${elec}
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("elecView/read.jsp");
		return mv;
	}

}
