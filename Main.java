import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws Exception{
	Scanner in = new Scanner(System.in);
	System.out.print("Программа выполняет арифметические операции\n"
			+ "между двумя числами от 1 до 10 (арабскими/римскими).\n"
			+ "Формат: число знак число.\n\n");
	System.out.print("Введите выражение: ");
	String s = in.nextLine();
	System.out.print(s + " = " + calc(s));
	}
	public static String calc(String input) throws Exception{
		String[] p = input.split(" ");

		//Алфавиты 
		String[] a1 = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
		String[] a2 = {"-", "+", "*", "/"};
		String[] a3 = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
		String[][] alphabet = {a1, a2, a3};
		
		//Проверка строки
		String check="";
		int arab=0, rome=0, digit=0;
		for(String e1 : p)
		{
			int i=0;
			for(String[] e2 : alphabet)
			{
				i++;
				for(String e3 : e2)
					if (e1.equals(e3)) 
						{
							check+=e1;
							switch(i){
							    case 1:
							        arab++;
							        break;
						        case 2:	        
							        digit++;
							        break;
                                case 3:							        
							        rome++;
							        break;
					            default:
					                break;
							}
						}
			}	
		}
		
		if (!check.equals(String.join("", p))) 
			throw new Exception("Введёная строка имеет недопустимые значения");		
		
		if (digit != 1) 
			throw new Exception("Отсутсвует математический знак");		
		
		if (p.length > 3) 
				throw new Exception("Введено недопустимое количество значений");
		
		if (arab !=2 && rome !=2)
			throw new Exception("В введёной строке используются различные системы счисления");
		
		//Вычисление выражения
		if (rome==2)
			for (int i = 0; i < a3.length; i++)
			{
				if (a3[i].equals(p[0])) p[0] = a1[i];
				if (a3[i].equals(p[2])) p[2] = a1[i];
			}

		int num1 = Integer.parseInt(p[0]);
		int num2 = Integer.parseInt(p[2]);
		int result = 0;
		
		switch (p[1])
		{
		case "+":
			result = num1+num2;	
			break;
		case "-":
			result = num1-num2;
			break;
		case "*":
			result = num1*num2;	
			break;
		case "/":
			result = num1/num2;
			break;
		default:
			break;
		}
		
		if(rome==2 && result < 1)
			throw new Exception("Результат вычисления выражения, заданного римскими цифрами, не может быть меньше единицы");
		
		String results="";
		if (arab==2) results = Integer.toString(result);
		if (rome==2) results = arabicToRoman(result);
		return results;
	}
	public static String arabicToRoman(int arabicNumber) {
	    String[] romanSymbols = {"C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
	    int[] arabicValues = {100, 90, 50, 40, 10, 9, 5, 4, 1};
	    int i = 0; String romanNumber="";
	    
	    while (arabicNumber > 0) {
	        int divisor = arabicValues[i];
	        if (arabicNumber >= divisor) {
	            arabicNumber -= divisor;
	            romanNumber+=romanSymbols[i];
	        } else i++;
	    }
	    return romanNumber;
	}
}