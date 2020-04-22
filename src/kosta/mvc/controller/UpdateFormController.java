package kosta.mvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kosta.mvc.model.domain.Electronics;
import kosta.mvc.model.service.ElectronicsService;

public class UpdateFormController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String modelNum = request.getParameter("modelNum");
		
		if(modelNum==null || modelNum.equals("")) {
			throw new RuntimeException("�𵨹�ȣ �����Դϴ�.-������");
		}
		
		Electronics elec = ElectronicsService.selectByModelnum(modelNum, false);
		
		request.setAttribute("elec", elec);	//update.jsp���� ���

		ModelAndView mv = new ModelAndView();
		mv.setViewName("elecView/update.jsp");
		return mv;
	}

}
