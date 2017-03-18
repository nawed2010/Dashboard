package org.cts.dashboard.serviceImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;







import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.cts.dashboard.dao.DefectDao;
import org.cts.dashboard.dao.DefectDaoImpl;
import org.cts.dashboard.dao.ModuleDaoImpl;
import org.cts.dashboard.dao.RiskDao;
import org.cts.dashboard.model.Defect;
import org.cts.dashboard.model.Risk;
import org.cts.dashboard.risk.service.RiskService;
import org.cts.dashboard.utility.DashboardSystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class RiskServiceImpl implements RiskService {
	@Autowired
	private DefectDao defectDao;
	private RiskDao riskDao;
	private static transient final Logger LOGGER =LoggerFactory.getLogger(RiskServiceImpl.class);
	public List<Risk> saveRisk(MultipartFile fileObj, String name) throws IOException, DashboardSystemException {
		System.out.println("Service Entry...");
		List returnList= new ArrayList(); 
		File file = convert(fileObj);
		List<Risk> riskLst = new ArrayList<Risk>();
		List<Defect> defectLst = new ArrayList<Defect>();
		
		if (name.contains("risk")){
			riskLst = readFile(file);
		returnList=riskDao.updateRisk(riskLst);
		}
		else{
			defectLst = readFileDefect(file);
			returnList=defectDao.updateDefect(defectLst);
		}
		return returnList;
	}



	private File convert(MultipartFile file) throws IOException {
		File convFile = new File(file.getOriginalFilename());
		convFile.createNewFile();
		FileOutputStream fos = new FileOutputStream(convFile);
		fos.write(file.getBytes());
		fos.close();
		System.out.println("Service Entry1...");
		return convFile;
	}

	private List<Risk> readFile(File file0) {
		List<Risk> riskLst = new ArrayList<Risk>();
		try {
			InputStream file = new FileInputStream(file0);
			// Get the workbook instance for XLS file
			XSSFWorkbook  wb = new XSSFWorkbook(file);
			// Get first sheet from the workbook
			XSSFSheet sheet = wb.getSheetAt(0);
			// Iterate through each rows from first sheet
			Iterator rowIterator = sheet.iterator();
			while (rowIterator.hasNext()) {
				XSSFRow row = (XSSFRow) rowIterator.next();
				if(row.getRowNum()==0){
				   continue; //just skip the rows if row number is 0
				 }
				Risk risk = new Risk();
				String[] data =new String[row.getLastCellNum()];
				LOGGER.info("The data available:id"+row.getCell(0)+"project id"+row.getCell(1)+"description"+row.getCell(2)+"priority raised date "+row.getCell(3)+"Raised_date"+row.getCell(4)+"Resolution_date"+row.getCell(5)+"remarks"+row.getCell(4));	
				for (int i=0; i<row.getLastCellNum();i++){
					String value=row.getCell(i).toString();
				String parts[]=value.split("\\.");
				data[i]=parts[0];
				
				}
				risk.setRisk_actual_id(data[0]);
				risk.setRelease_id(data[1]);
				risk.setDescription(data[2]);
				risk.setPriority(data[3]);
				risk.setRaised_date(data[4]);
				risk.setResolution_date(data[5]);
				risk.setRemarks(data[6]);
				
				
				
		/*		risk.setRisk_actual_id(row.getCell(0).toString());
				risk.setRelease_id(row.getCell(1).toString());
				risk.setDescription(row.getCell(2).toString());
				risk.setPriority(row.getCell(3).toString());
				risk.setRaised_date(row.getCell(4).toString());
				risk.setResolution_date(row.getCell(5).toString());*/
				
				riskLst.add(risk);
			}
			file.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Service Entry2...");
		return riskLst;
	}
	
	private List<Defect> readFileDefect(File file0) {
		List<Defect> riskLst = new ArrayList<Defect>();
		try {
			InputStream file = new FileInputStream(file0);
			// Get the workbook instance for XLS file
			XSSFWorkbook  wb = new XSSFWorkbook(file);
			// Get first sheet from the workbook
			XSSFSheet sheet = wb.getSheetAt(0);
			// Iterate through each rows from first sheet
			Iterator rowIterator = sheet.iterator();
			while (rowIterator.hasNext()) {
				XSSFRow row = (XSSFRow) rowIterator.next();
				if(row.getRowNum()==0){
				   continue; //just skip the rows if row number is 0
				 }
				Defect defect = new Defect();
				String[] data =new String[row.getLastCellNum()];
				LOGGER.info("The data available:id"+row.getCell(0)+"project id"+row.getCell(1)+"description"+row.getCell(2)+"priority raised date "+row.getCell(3)+"Raised_date"+row.getCell(4)+"Resolution_date"+row.getCell(5)+"remarks"+row.getCell(4));	
				for (int i=0; i<row.getLastCellNum();i++){
					String value=row.getCell(i).toString();
				String parts[]=value.split("\\.");
				data[i]=parts[0];
				
				}
				defect.setDefect_actual_id(data[0]);
				defect.setRelease_id(data[1]);
				defect.setDescription(data[2]);
				defect.setPriority(data[3]);
				defect.setRaised_date(data[4]);
				defect.setResolution_date(data[5]);
				defect.setRemarks(data[6]);
				
				
				
		/*		risk.setRisk_actual_id(row.getCell(0).toString());
				risk.setRelease_id(row.getCell(1).toString());
				risk.setDescription(row.getCell(2).toString());
				risk.setPriority(row.getCell(3).toString());
				risk.setRaised_date(row.getCell(4).toString());
				risk.setResolution_date(row.getCell(5).toString());*/
				
				riskLst.add(defect);
			}
			file.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Service Entry2...");
		return riskLst;
	}
}
