package kosta.mvc.model.domain;

public class Electronics {

	private String modelNum;
	private String modelName ;
	private int price ;
	private String description; 
	private String password ;
	private String writeday; 
	private int readnum ;
	private String  fname; 
	private int  fsize;
	
	public Electronics(){}
	
	//삭제할때..
	public Electronics(String modelNum, String password) {
		this.modelNum = modelNum;
		this.password = password;
	}
	
	//업로드 안될경우 생성자
	public Electronics(String modelNum, String modelName, int price,
			String description, String password) {
		this.modelNum = modelNum;
		this.modelName = modelName;
		this.price = price;
		this.description = description;
		this.password = password;
	}

	//업로드가 될때
	public Electronics(String modelNum, String modelName, int price,
			String description, String password, String writeday, int readnum,
			String fname, int fsize) {
		
		this.modelNum = modelNum;
		this.modelName = modelName;
		this.price = price;
		this.description = description;
		this.password = password;
		this.writeday = writeday;
		this.readnum = readnum;
		this.fname = fname;
		this.fsize = fsize;
	}


	public String getModelNum() {
		return modelNum;
	}

	public void setModelNum(String modelNum) {
		this.modelNum = modelNum;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getWriteday() {
		return writeday;
	}

	public void setWriteday(String writeday) {
		this.writeday = writeday;
	}

	public int getReadnum() {
		return readnum;
	}

	public void setReadnum(int readnum) {
		this.readnum = readnum;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public int getFsize() {
		return fsize;
	}

	public void setFsize(int fsize) {
		this.fsize = fsize;
	}

	
	

}
