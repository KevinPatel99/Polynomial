package poly;

public class Distinct {
	
	
	public static void distinct(int x, int y, int z)
	{
		
		int distinctNumbers = 0;
        if (x > y || x < y)
        {
            distinctNumbers = distinctNumbers + 1;
        }
        
        if ( x < z || x > z )
        {
            distinctNumbers = distinctNumbers + 1 ;
        }
        
        if (y < z || y > z)
        {
            distinctNumbers =  distinctNumbers + 1;
        }
        
        System.out.println(distinctNumbers);
		
	}
	
	public static void main(String[] args)
	{
		distinct(2,3,2);
	}

}
