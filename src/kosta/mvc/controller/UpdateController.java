package kosta.mvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kosta.mvc.model.domain.Electronics;
import kosta.mvc.model.service.ElectronicsService;

public class UpdateController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String modelNum = request.getParameter("modelNum");
		String modelName = request.getParameter("model_name");
		String price = request.getParameter("price");
		String description = request.getParameter("description");
		String password = request.getParameter("password");

		if(modelNum==null || modelNum.equals("") ||
				modelName==null || modelName.equals("") ||
				price==null || price.equals("") ||
				description==null || description.equals("") ||
				password==null || password.equals("")) {
			throw new RuntimeException("입력값이 충분하지 않습니다.");
		}
				
		Electronics elec = new Electronics(modelNum, modelName, Integer.parseInt(price), description, password);
		ElectronicsService.update(elec);
		
		ModelAndView mv = new ModelAndView();
		mv.setRedirect(true);
		mv.setViewName("elec?command=read&state=1&modelNum="+modelNum);	//read.jsp
		return mv;
	}

}
