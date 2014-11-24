/******************************************************************************
 * 
 * Name:		Jason Scharff
 * Block:		C
 * Date:		February 28, 2014
 * 
 *  Program #9:	Expression Evaluator
 *  Description:
 *     This creates a Token. A token consists of three fields: a number, an operator
 *     and a type. The type field says if it is a unary operator, binary operator, or number.
 *     The operator and type fields are always set to final integers that are used internally
 *     to determine what is in the type and operator fields. If the type is a number the operator
 *     field is the EMPTY final. Numbers are stored as doubles, and if the type is an operator
 *     it is stored as EMPTY. Program extras I added are the ability to set a number Token equal
 *     to e or pi and finals to match extra operators I added (sin, cos, tan, and mod).
 * 
 ******************************************************************************/
public class Token
{
	public static final int EMPTY = 0;
	//Types
	public static final int NUM_TYPE = 1;
	public static final int UNARY_TYPE = 2;
	public static final int BINARY_TYPE = 3;
	//Unary Ops
	public static final int PLUS = 5;
	public static final int MINUS = 6;
	//Binary Ops
	public static final int ADD = 7;
	public static final int SUBTRACT = 8;
	public static final int MULTIPLY = 9;
	public static final int DIVIDE = 10;
	//Characters for Ops
	public static final char MULT_CHAR = '*';
	public static final char DIV_CHAR = '/';
	public static final char ADD_CHAR = '+';
	public static final char SUBTRACT_CHAR = '-';
	public static final char PLUS_CHAR = '+';
	public static final char MINUS_CHAR = '-';
	//Characters for Types
	public static final char BINARY_CHAR = 'b';
	public static final char UNARY_CHAR = 'u';
	public static final char NUMBER_CHAR = '#';
	public static final char END = 'e';
	//Program Extras
	//Extra Unary Ops
	public static final int SIN = 11;
	public static final int COS = 12;
	public static final int TAN = 13;
	//Extra Binary Op
	public static final int MOD = 14;
	//Extra Operation Characters
	public static final char SIN_CHAR = 's';
	public static final char COS_CHAR = 'c';
	public static final char TAN_CHAR = 't';
	public static final char MOD_CHAR = 'm';
	public static final char EXPONENT_CHAR = 'p';
	public static final char HELP_CHAR = 'h';
	//Constant Numbers
	public static final char PI = 'p';
	public static final char EULERS = 'e';		

	//Private Class Scope Variables
	private int type;
	private double num;
	private int operator;

	/**
	 * This constructor method is called in the case that the Token is a number, and not 
	 * e or pi using by built in e and pi constants. This method makes the type
	 * equal to a number, the number equal to the passed in number, and the operator empty
	 * @param number	The number that the Token contains–Entered by the user
	 */
	public Token(double number)
	{
		type = NUM_TYPE;
		num = number;
		operator = EMPTY;
	}
	
	/**
	 * This constructor is called in the event that the token is a number, but
	 * the number is equal to e or pi (which can be entered using the characters
	 * PI and EULERS). The method checks if the character is e or pi (and if something else
	 * , which should never happen because that error is dealt with in the Expression Evaluator
	 * lass). It then sets the number equal to the constant from the Math class. If it is another character
	 * num is simply set to 0 and an error message is printed. Operator is sent to empty and type is set
	 * to NUM_TYPE
	 * @param number	A character, either e or p which is represents e or pi
	 */
	public Token(char number)
	{
		if(number == PI)
		{
			num = Math.PI;
		}
		else if (number == EULERS)
		{
			num = Math.E;
		}
		else
		{
			num = 0;
			System.out.println("Invalid Character Input");
		}
		operator = EMPTY;
		type = NUM_TYPE;
	}

	/**
	 * This constructor is called in the case that the Token is an operator, binary or unary.
	 * The method simply determines whether or not is is binary, unary, or other (which should
	 * not ever happen because it is tested against in the Expression Evaluator Class). 
	 * Once it knows the type of operator it calls a method that handles all of the if's for
	 * the particular operator type
	 * @param type	A character representing whether or not it is binary or unary
	 * @param op	A character holding the symbol for an operation
	 */
	public Token(char type, char op)
	{
		num = EMPTY;
		if(type == UNARY_CHAR)
		{
			UnaryConstructor(op);
		}
		else if (type == BINARY_CHAR)
		{
			binaryConstructor(op);
		}
		else
		{
			System.out.println("Invalid Entry");
		}
	}
	
	/**
	 * This method handles all of the cases for if the Token type is a binary operator.
	 * First the type is set to Binary, then it checks the to see what type of binary operator
	 * the parameter op is. It then sets operator equal to the proper value. Though there
	 * is an else at the end in case it is none of the recognized symbols it should never be called
	 * because correct input is checked in the Expression Evaluator Class.
	 * @param op	A character containing the symbol for the binary operator
	 */
	public void binaryConstructor(char op)
	{
		this.type = BINARY_TYPE;
		if(op == ADD_CHAR)
		{
			operator = ADD;
		}
		else if (op == SUBTRACT_CHAR)
		{
			operator = SUBTRACT;
		}
		else if (op == MULT_CHAR)
		{
			operator = MULTIPLY;
		}
		else if (op == DIV_CHAR)
		{
			operator = DIVIDE;
		}
		else if (op == MOD_CHAR)
		{
			operator = MOD;
		}
		else 
		{
			System.out.println("Error");
		}
	}
	
	/**
	 * This method handles all of the cases for the Unary operator. It 
	 * first sets the type to unary then goes through if statements to find
	 * out what type of unary operator it is. It then sets operator equal 
	 * to the proper value. Though there is an else if the character is 
	 * not recognizes it should never be called because input is checked in 
	 * the Expression Evaluator Class.
	 * @param op	A character containing the symbol for the unary op
	 */
	public void UnaryConstructor(char op)
	{
		this.type = UNARY_TYPE;
		if(op == MINUS_CHAR)
		{
			operator = MINUS;
		}
		else if (op == PLUS_CHAR)
		{
			operator = PLUS;
		}
		else if (op == SIN_CHAR)
		{
			operator = SIN;
		}
		else if (op == COS_CHAR)
		{
			operator = COS;
		}
		else if (op == TAN_CHAR)
		{
			operator = TAN;
		}
		else
		{
			System.out.print("Error");
		}
	}
	
	/**
	 * This method just returns the number inside the Number Field. If empty it returns 0 
	 * (the final for empty is set to 0).
	 * @return	The number inside the Number Field.
	 */
	public double getNum()
	{
		return num;
	}
	
	/**
	 * This method returns the operator type as the number it is internally stored as
	 * @return	The number the operator is internally stored as (one of the finals)
	 */
	public int getOp()
	{
		return operator;
	}
	
	/**
	 * This method returns the type as the number it is internally stored as
	 * @return	The corresponding internal number for number, unary operator, or binary operator.
	 */
	public int getType()
	{
		return type;
	}
	
	/**
	 * This method converts a Token to a String. It prints out the number it is internally stored
	 * as in addition to the text of its type. It prints out the number because this is only used
	 * for testing purposes and in testing purposes it is useful to know the internal storage.
	 * @return	A Token in String format
	 */
	public String toString()
	{
		String theToken = "";
		if(type == UNARY_TYPE)
		{
			theToken = theToken + "Unary Operator";
			theToken = theToken + "\n";
			theToken = theToken + operator;
		}
		else if (type == BINARY_TYPE)
		{
			theToken = theToken + "Binary Operator";
			theToken = theToken + "\n";
			theToken = theToken + operator;
		}
		else if (type == NUM_TYPE) 
		{
			theToken = theToken + "Number";
			theToken = theToken + "\n";
			theToken = theToken + num;
		}
		return theToken;
		
		
	}

}
