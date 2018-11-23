public class Mathematics
{
	public double distanciaEuclidiana(double x1,double y1,double x2,double y2)
	{
		double baseX = y2 - y1;
		double baseY = x2 - x1;
		baseX = Math.pow(baseX, 2);
		baseY = Math.pow(baseY, 2);
		baseX = (double) Math.pow(baseX+baseY, 0.5);
		return baseX;
	}
}