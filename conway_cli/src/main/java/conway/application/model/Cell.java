package conway.application.model;

public class Cell {
	private CellValue value;
	
	public Cell(CellValue value) {
		this.value = value;
	}

	public CellValue getValue() {
		return value;
	}

	public void setValue(CellValue value) {
		this.value = value;
	}
	
	
	
}
