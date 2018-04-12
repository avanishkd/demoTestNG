package utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.List;



public class CSVUtility {
	String csv_FILE_Header;
	String COMMA_DELIMITER;
	String NEW_LINE_SEPARATOR;
	FileWriter fileWriter;
	String fileName;
	File f;
	String filePath;
	public  CSVUtility(String header){
		
		this.COMMA_DELIMITER=";";
		this.NEW_LINE_SEPARATOR="\n";
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Into CSV Generator");
		
		csv_FILE_Header = header;
		
		Date d=new Date();
		this.fileName=d.getDate()+"_"+d.getMonth()+"_"+d.getHours()+"_"+d.getMinutes()+"_"+d.getSeconds();
		try {
			this.f =new File(System.getProperty("user.dir")+"\\"+"generatedCVSs"+"\\"+this.fileName+".csv");
			fileWriter = new FileWriter(this.f);
			fileWriter.append(csv_FILE_Header);
			System.out.println("Into CSV Generator++++++++++++++");
			
			this.filePath=f.getAbsolutePath();
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void writeToCSV(String content){
		try {
			this.fileWriter.append(content);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void closeCSVUtility(){
		try{
			this.fileWriter.flush();
			this.fileWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
