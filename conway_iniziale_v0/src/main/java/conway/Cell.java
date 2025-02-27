package conway;

import java.util.Objects;

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

	@Override
	public int hashCode() {
		return Objects.hash(value);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cell other = (Cell) obj;
		return value == other.value;
	}
	
	
	
	
}
