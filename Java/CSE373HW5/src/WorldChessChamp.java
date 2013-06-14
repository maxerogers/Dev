
public class WorldChessChamp 
{
	private static final double WW = 0.7;
	static double WL = 0.5;
	static double WD = 0.3;
	static double BW = 0.6;
	static double BL = 0.4;
	static double BD = 0.1;
	public static void main(String[] args)
	{
		double[][] white=  new double[24][24];
		double[][] black = new double[24][24];
		for(int i=0;i<24;i++)
		{
			for(int j=0;j<24;j++)
			{
				white[i][j] = PW(i,j);
				black[i][j] = PB(i,j);
			}
		}
	}
	
	public static double PW(double g, double h)
	{
		if(g == 0)
			return 0;
		return WW*PW(g-1,h-1) + WD*PW(g-1,h-0.5)+WL*PW(g-1,h);
	}
	public static double PB(double g, double h)
	{
		return BW*PB(g-1,h-1) + BD*PB(g-1,h-0.5)+BL*PB(g-1,h);
	}
}
