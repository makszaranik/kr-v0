package lab23.service;

public class CalculationService {
	
	public static double getResult(String formula, String aParam, String bParam, String cParam, String dParam)
	{
		double a = Double.parseDouble(aParam);
		double b = Double.parseDouble(bParam);
		double c = Double.parseDouble(cParam);
		double d = Double.parseDouble(dParam);
		
		if(formula.equals("form1")) {
			return formula1(a, b, c, d);
		}else if (formula.equals("form2")){
			return formula2(a, b, c, d);
		}else{
			return formula3(a, b, c, d);
		}
	}
	
    private static double formula1(double a, double b, double c, double d){
        double absoluteValue = Math.abs(b / a);
        double logAbsoluteValue = Math.log( absoluteValue );
        double innerExpression = Math.sin(c) + Math.exp(d);
        double sqrtInnerExpression = Math.sqrt( innerExpression );
        double result = 3 * (logAbsoluteValue + sqrtInnerExpression);
        return result;
    }

    private static double formula2(double a, double b, double c, double d){
        double tanA = Math.tan(a);
        double absoluteArccosB = Math.abs(Math.acos(b));
        double sqrtResult = Math.sqrt(tanA / absoluteArccosB);
        double expDifference = Math.exp(c - a);
        double sinhD = Math.sinh(d);
        double powerResult = Math.pow(expDifference * sinhD, 1.0 / 3.0);
        double result = 2 * sqrtResult - 3 * powerResult;
        return result;
    }

    private static double formula3(double a, double b, double c, double d){
        double cosB = Math.cos(b);
        double sqrtA = Math.sqrt(a);
        double sinSqrtA = Math.sin(sqrtA);
        double logC = Math.log(c);
        double expD = Math.exp(d);
        double den = 2 * logC + expD;
        double result = (cosB + sinSqrtA) / den;
        return result;
    }
}
