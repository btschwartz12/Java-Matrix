//APMatrix.java
//Ben Schwartz
//yes

public class APMatrix
{
	KeyboardReader reader = new KeyboardReader();
	
	//private variables
	private int[][] piper;
	
	//contructors
	public APMatrix(int R, int C, boolean enter)
	{
		if(enter)
		{
			System.out.println("You have created an Matrix with "+R+" rows and "+C+" columns.\n\n");
			piper = new int[R][C];
			int input;
			
			for(int i = 0; i < piper.length; i++)
			{
				for(int j = 0; j < piper[0].length; j++)
				{
					input = reader.readInt("Enter value for row ("+(i+1)+") column ("+(j+1)+") = ");
					piper[i][j] = input;
					System.out.println();	
				}
			}
		}
		else
		{
			piper = new int[R][C];
		}
	}
	public APMatrix(int[][] copyMe)
	{
		piper = new int[copyMe.length][copyMe[0].length];
		
		for(int i = 0; i < copyMe.length; i++)
		{
			for(int j = 0; j < copyMe[0].length; j++)
				piper[i][j] = copyMe[i][j];
		}
	}
	
	//accessor methods
	public int getRows()
	{
		return piper.length;
	}
	public int getColumns()
	{
		return piper[0].length;
	}
	
	//print method
	public String toString()
	{
		String aiko = "";
		aiko+= "*****Here is your Matrix*****\n\n";
		for(int i = 0; i < piper.length; i++)
		{
			for(int j = 0; j < piper[0].length; j++)
			{
				aiko += (piper[i][j]+"\t");
			}
			aiko += ("\n");
		}
		return aiko+"\n";
	}
	
	//other methods
	public APMatrix matrixMultiply(APMatrix m1)
	{
		if(this.getColumns() != m1.getRows())
		{
			System.out.println("ERROR. CANNOT MULTIPLY MATRICIES.");
			return null;
		}	
		else
		{
			APMatrix aiko = new APMatrix(this.getRows(), m1.getColumns(), false);
			
			for(int i = 0; i < aiko.getRows(); i++)
			{
				for(int j = 0; j < aiko.getColumns(); j++)
				{
					for(int k = 0; k < m1.getRows(); k++)
					{
						aiko.piper[i][j] += this.piper[i][k] * m1.piper[k][j];
					}
				}
			}
			return aiko;
		}
		
	}
	
	public int sumCross(int r, int c)
	{
		int row = r-1;
		int col = c-1;
		int sum = 0;
		if(r > piper.length || c > piper[0].length)
		{
			System.out.println("ERROR: OUT OF BOUNDS...");
			sum = -1;
		}
		else
		{
			for(int i = 0; i < piper.length; i++)
			{
				sum+= piper[i][col];
			}
			for(int j = 0; j < piper[0].length; j++)
			{
				sum+= piper[row][j];
			}
			sum -= piper[row][col];	
		}
		return sum;
	}
	
	public APMatrix removeCross(int r, int c)
	{
		int row = r-1, col = c-1;
		if(r > this.piper.length || c > this.piper[0].length)
		{
			System.out.println("ERROR. OUT OF BOUNDS");
			return this;
		}
		else if(r <= 0 || c <= 0)
		{
			System.out.println("ERROR. WRONG INDEX.");
			return this;
		}
		else
		{
			APMatrix result = new APMatrix(this.getRows()-1, this.getColumns()-1, false);
			
				for(int i = 0; i < row; i++)
				{//this will change the left side of column and 
				///right side of column removed, above the row removed
					for(int j = 0; j < col; j++)
						result.piper[i][j] = this.piper[i][j];
					for(int jj = col; jj < result.piper[0].length; jj++)
						result.piper[i][jj] = this.piper[i][jj+1];
				}
				for(int i = row; i < result.piper.length; i++)
				{//this will change the left side of column and 
				///right side of column removed, below the row removed
					for(int j = 0; j < col; j++)
						result.piper[i][j] = this.piper[i+1][j];
					for(int jj = col; jj < result.piper[0].length; jj++)
						result.piper[i][jj] = this.piper[i+1][jj+1];
				}	

			return result;
		}
		
	}
}
