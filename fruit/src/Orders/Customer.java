package Orders;;

public class Customer {
	String account;
	String name;
	String name2;
	String remarks;
	String driverNum;
	
	Customer(String account_ , String name_,String name2_,String remarks_ ,String driverNum_)
	{
		account = account_;
		name = name_;
		name2 = name2_;
		if (remarks_ == null){
			remarks = "";
		}else{
			remarks = remarks_;
		}		
		
		driverNum = driverNum_;
	}

	
	/**
	 * @return the account
	 */
	public String getAccount() {
		return account;
	}

	/**
	 * @param account the account to set
	 */
	public void setAccount(String account) {
		this.account = account;
	}
	
	/**
	 * @return the driverNum
	 */
	public String getDriverNum() {
		return driverNum;
	}

	/**
	 * @param driverNum the driverNum to set
	 */
	public void setDriverNum(String driverNum) {
		this.driverNum = driverNum;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the name2
	 */
	public String getName2() {
		return name2;
	}

	/**
	 * @param name2 the name2 to set
	 */
	public void setName2(String name2) {
		this.name2 = name2;
	}

	/**
	 * @return the remarks
	 */
	public String getRemarks() {
		return remarks;
	}

	/**
	 * @param remarks the remarks to set
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	
}
