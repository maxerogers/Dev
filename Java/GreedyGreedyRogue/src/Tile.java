/**
 * A tile is an object that hold a reference to whatever happens to be standing over that tile.
 * Whether it is a creature, item or obstacle 
 * @author max
 *
 */
public class Tile 
{
	private Avatar contained;
	private int rowId;
	private int colId;
	public Tile()
	{}
	
	public boolean insert(Avatar inc)
	{
		return inc.contactWith(contained);
	}

	public int getRowId() {
		return rowId;
	}

	public void setRowId(int rowId) {
		this.rowId = rowId;
	}

	public int getColId() {
		return colId;
	}

	public void setColId(int colId) {
		this.colId = colId;
	}
}
