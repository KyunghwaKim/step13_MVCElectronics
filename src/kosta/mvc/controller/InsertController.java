package kosta.mvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kosta.mvc.model.domain.Electronics;
import kosta.mvc.model.service.ElectronicsService;

public class InsertController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String saveDir=request.getServletContext().getRealPath("/save");
		int maxSize=1024*1024*100;	//100M
		String encoding="UTF-8";
		
		MultipartRequest m = new MultipartRequest(request, saveDir, maxSize, encoding, new DefaultFileRenamePolicy());

		//request�� ���۵Ǵ� ������ ��ȿ�� üũ
		String modelNum = m.getParameter("model_num");
		String modelName = m.getParameter("model_name");
		String price = m.getParameter("price");
		String description = m.getParameter("description");
		String password = m.getParameter("password");
		
		if(modelNum==null || modelNum.equals("") ||
				modelName==null || modelName.equals("") ||
				price==null || price.equals("") ||
				description==null || description.equals("") ||
				password==null || password.equals("")) {
			throw new RuntimeException("�Է°��� ������� �ʽ��ϴ�.");
		}
		
		Electronics elec = new Electronics(modelNum, modelName, Integer.parseInt(price), description, password);
		
		if(m.getFilesystemName("file")!=null) {
			//����÷�� �Ǿ��ٸ�
			elec.setFname(m.getFilesystemName("file"));
			elec.setFsize((int)m.getFile("file").length());
		}
		
		ElectronicsService.insert(elec);
		
		ModelAndView mv = new ModelAndView(true, "elec");
		
		return mv;
	}

}
