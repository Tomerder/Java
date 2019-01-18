package Orders;



public class Item implements Comparable {
	//from csv
	int cat;
	String name;	
	String uom;
	String name2;
	String name3;
	String name4;
	
	//from input
	float  inputQtyNumber;
	String inputQty;
	String inputRemark;
	String inputUom;
	String inputName;
	float    inputSpecialPrice;

	//item from DB (csv file)
	Item(int cat_ , String name_,String uom_,String name2_, String name3_, String name4_)
	{
		cat = cat_;
		name = name_;
		uom = uom_;
		name2 = name2_;		
		name3 = name3_;		
		name4 = name4_;		
	}
	
	//item from order (input name on input file)
	Item(int cat_ , String name_,String uom_,String name2_, String inputName_)
	{
		cat = cat_;
		name = name_;
		uom = uom_;
		name2 = name2_;
		//inputQty = inputQty_;
		//inputRemark = inputRemark_;
		//inputUom = inputUom_;
		inputName = inputName_;
	}
	
	/**
	 * @return the inputSpecialPrice
	 */
	public float getInputSpecialPrice() 
	{
		return inputSpecialPrice;
	}

	/**
	 * @param inputSpecialPrice the inputSpecialPrice to set
	 */
	public void setInputSpecialPrice(float inputSpecialPrice) {
		this.inputSpecialPrice = inputSpecialPrice;
	}
	
	/**
	 * @return the name3
	 */
	public String getName3() {
		return name3;
	}

	/**
	 * @param name3 the name3 to set
	 */
	public void setName3(String name3) {
		this.name3 = name3;
	}

	/**
	 * @return the name4
	 */
	public String getName4() {
		return name4;
	}

	/**
	 * @param name4 the name4 to set
	 */
	public void setName4(String name4) {
		this.name4 = name4;
	}

	
	/**
	 * @return the cat
	 */
	public int getCat() {
		return cat;
	}

	/**
	 * @param cat the cat to set
	 */
	public void setCat(int cat) {
		this.cat = cat;
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
	 * @return the uom
	 */
	public String getUom() {
		return uom;
	}

	/**
	 * @param uom the uom to set
	 */
	public void setUom(String uom) {
		this.uom = uom;
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
	 * @return the inputQty
	 */
	public String getInputQty() {
		return inputQty;
	}

	/**
	 * @param inputQty the inputQty to set
	 */
	public void setInputQty(String inputQty) {
		this.inputQty = inputQty;
	}

	/**
	 * @return the inputRemark
	 */
	public String getInputRemark() {
		return inputRemark;
	}

	/**
	 * @param inputRemark the inputRemark to set
	 */
	public void setInputRemark(String inputRemark) {
		this.inputRemark = inputRemark;
	}



	/**
	 * @return the inputUom
	 */
	public String getInputUom() {
		return inputUom;
	}

	/**
	 * @param inputUom the inputUom to set
	 */
	public void setInputUom(String inputUom) {
		this.inputUom = inputUom;
	}

	/**
	 * @return the inputName
	 */
	public String getInputName() {
		return inputName;
	}

	/**
	 * @param inputName the inputName to set
	 */
	public void setInputName(String inputName) {
		this.inputName = inputName;
	}

	/**
	 * @return the inputQtyNumber
	 */
	public float getInputQtyNumber() {
		return inputQtyNumber;
	}

	/**
	 * @param inputQtyNumber the inputQtyNumber to set
	 */
	public void setInputQtyNumber(float inputQtyNumber) {
		this.inputQtyNumber = inputQtyNumber;
	}
	
	//************************************************************************
	//for sorting items by cat
	@Override
	public int compareTo(Object otherItem) {
		boolean is1Big = (this.getCat() > ((Item) otherItem).getCat());
		if (is1Big) {
			return 1;
		}
		return -1;
	}
	
}
