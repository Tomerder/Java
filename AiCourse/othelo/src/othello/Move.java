package othello;

public class Move {
	public int i ;
	public int j ;
	
	
	public Move(){}
	
	
	public Move(int i , int j)
	{
		this.i=i;
		this.j=j;
	}
	
	public boolean equals(Object other)
	{
		Move o=(Move)other;
		if(o.i==i   &&    o.j==j)
		{
			return true;
		}
		return false;
	}
	
}
