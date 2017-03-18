package org.cts.dashboard.risk.endpoint;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;



import org.cts.dashboard.model.Risk;
import org.cts.dashboard.risk.service.RiskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/upload")
public class FileUpload {
	
	@Autowired
	public RiskService riskService;

	@RequestMapping(value="/risk", method = RequestMethod.POST)
	@ResponseBody
	public List<Risk> saveRiskData(@RequestParam("file") MultipartFile file) throws IOException {
		System.out.println("Entry1...Start");
		String name= file.getName();
		name = name.contains("risks")?"risk":"defect";
		List<Risk> riskLst = new ArrayList<Risk>();
		try {
			riskLst = riskService.saveRisk(file, name);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Entry1...End");
		return riskLst;
 
	}
	
	@RequestMapping(value="/test", method = RequestMethod.GET)
	public @ResponseBody String getRiskData(HttpServletRequest request, ModelMap model) throws IOException {
		System.out.println("Entry...");
		
		return "Hello";
 
	}
}
