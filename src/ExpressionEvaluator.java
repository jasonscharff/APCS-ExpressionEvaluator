/******************************************************************************
 * 
 * Name:		Jason Scharff
 * Block:		C
 * Date:		February 28, 2014
 * 
 *  Program #9:	Expression Evaluator
 *  Description:
 *     This class is the main. All methods associated with getting tokens, and
 *     creating Expression Trees are in this class. The main calls generateExpressionTree
 *     which gets a group of Tokens and converts them into Expression Trees, then calls the
 *     method evaluate on the Expression Tree which yields a double for an answer. The program extras 
 *     I added for this class include a help menu that explain how to enter in Tokens and error catching
 *     that does not assume the user entered a number when they said they would. 
 * 
 ******************************************************************************/
import java.util.Scanner;
public class ExpressionEvaluator
{
	//Variables For the Help Menu
	private final static char unaryOP = 'u';
	private final static char binaryOP = 'b';
	private final static char numbers = 'n';
	private final static char exit = 'e';

	private final static Scanner console = new Scanner(System.in);

	public static void main(String[] Args)
	{
		System.out.println("For Help Type in h when prompted to Enter a Token");
		ExpressionTree equation = generateExpressionTree();
		if(equation == null)
		{
			System.out.println("You Tried To Evaluate Before Entering Anything In");
		}
		else
		{	
			System.out.println(equation.evaluate());
		}
	}

	/**
	 * A program extra I added explains the commands. This menu is prompted by
	 * entering in h when prompted for a token.
	 * This method is the first stage of the help menu. It simply prints
	 * out a list of commands that can be entered from the help menu.
	 */
	public static void generalHelpMenu()
	{
		System.out.println("Please Enter One of the Following");
		System.out.println("Help With Unary Operators - u");
		System.out.println("Help With Binary Operators - b");
		System.out.println("Help Entering Numbers - n");
		System.out.println("Exit - e");
		getHelpInput();
	}

	/**
	 * This method gets a character from the console then
	 * determines what help method to call based off the character entered.
	 * If something invalid is entered it will prompt the user to enter something else
	 */
	public static void getHelpInput()
	{
		char input = getChar();
		if(input == unaryOP)
		{
			unaryHelp();
		}
		else if (input == binaryOP)
		{
			binaryHelp();
		}
		else if (input == numbers)
		{
			numbersHelp();
		}
		else if (input == exit)
		{
			return;
		}
		else
		{
			System.out.println("Invalid Input \n Try Again");
			getHelpInput();
		}
	}

	/**
	 * This method prints out the unary operators available. I have added
	 * three program extras (sine, cosine, and tangent). It also
	 * lets the user know that the trigonometric functions are in degrees, not radians
	 */
	public static void unaryHelp()
	{
		System.out.println("There are Five Available Unary Operators");
		System.out.println("Sine = " + Token.SIN_CHAR);
		System.out.println("Cosine = " + Token.COS_CHAR);
		System.out.println("Tangent = " + Token.TAN_CHAR);
		System.out.println("Plus =  " + Token.PLUS_CHAR);
		System.out.println("Minus =  " + Token.MINUS_CHAR);
		System.out.println("Please Note That The Trigonometric Functions Are in Degrees.");
	}

	/**
	 * This method prints out the binary operators available.
	 *  I have added the program extra of modulo
	 */
	public static void binaryHelp()
	{
		System.out.println("There are Five Available Binary Operators");
		System.out.println("Add = " + Token.ADD_CHAR);
		System.out.println("Subtract = " + Token.SUBTRACT_CHAR);
		System.out.println("Multiply = " + Token.MULT_CHAR);
		System.out.println("Divide = " + Token.DIV_CHAR);
		System.out.println("Modulo = " + Token.MOD_CHAR);
	}

	/**
	 * This method prints out information regarding how to enter numbers.
	 * A program extra I added allows the user to enter p for pi and e for e which 
	 * is explained in this method
	 */
	public static void numbersHelp()
	{
		System.out.println("In Addition To Entering Regular Numbers Aproximations of Two Numbers are Available");
		System.out.println("Pi = " + Token.PI);
		System.out.println("Eulers Number (e) = " + Token.EULERS);
	}
	/**
	 * This method is a wrapper function for auxGenerateExpressionTree.
	 * It creates a new stack then calls auxGenerateExpressionTree with the 
	 * new Stack
	 * @return	An ExpressionTree to be evaluated
	 */
	public static ExpressionTree generateExpressionTree()
	{
		Stack expressionStack = new Stack();
		return auxGenerateExpressionTree(expressionStack);
	}

	/**
	 * This method creates an expression Trees from tokens. It gets a Token
	 * then goes through if statements to determine what to do. If the user enters
	 * 'e' it evaluates the Expression Tree. For operators and numbers it calls another method
	 * that deals with the cases for the specific type. While there is an else if the type is 
	 * "non of the above" then it returns null and displays an error message, though this should not be called
	 * because it is checked against earlier
	 * @param expressionStack
	 * @return
	 */
	public static ExpressionTree auxGenerateExpressionTree(Stack expressionStack)
	{
		Token item = getToken();
		if (item == null)
		{
			return evaluateCall(expressionStack);
		}
		else if(item.getType() == Token.UNARY_TYPE)
		{
			return unaryCall(expressionStack, item);
		}
		else if (item.getType() == Token.BINARY_TYPE)
		{
			return binaryCall(expressionStack, item);
		}
		else if (item.getType() == Token.NUM_TYPE)
		{
			return numberCall(expressionStack, item);
		}

		else
		{
			System.out.println("Invalid Type Creating an Expression Tree");
			return null;
		}
	}

	/**
	 * This method creates a new Expression Tree in the case that the Token 
	 * contains a number. A new ExpressionTree is created with the Token at the root
	 * and no children (null). It is then pushed onto the stack. Another Expression Tree
	 * is returned by calling auxGenerateExpression Tree. This just keeps the loop of creating expression
	 * trees going until the user enters 'e'
	 * @param expressionStack	A Stack that the ExpressionTree is pushed onto.
	 * @param item	A Token that the ExpressionTree has at its root
	 * @return	A brand new Expression Tree that is created by the method
	 * auxGenerateExpressionTree. This is to keep the "loop" of creating expression trees going
	 * until the user wishes to evaluate.
	 */
	public static ExpressionTree numberCall(Stack expressionStack, Token item)
	{
		ExpressionTree Express = new ExpressionTree(item, null, null);
		expressionStack.push(Express);
		return auxGenerateExpressionTree(expressionStack);
	}

	/**
	 * This method prepares for the Evaluate method to be called. It is given
	 * a stack which should contain the final expressionTree. A pop operation
	 * is called. If the stack is not empty at this point an error message appears
	 * and the user is prompted to re-enter their entire equation. If the stack is empty
	 * the final equation is returned
	 * @param expressionStack	The stack containing the final Expression Tree
	 * @return	The final expressionTree to be evaluated.
	 */
	public static ExpressionTree evaluateCall(Stack expressionStack)
	{
		ExpressionTree finalEquation = (ExpressionTree) expressionStack.pop();
		if(expressionStack.isEmpty() == false)
		{
			System.out.println("Operators Were Not Entered Correctly–Stack is Not Empty \n"
					+ "Please Re-Enter Your Entire Equation");
			return auxGenerateExpressionTree(new Stack());
		}
		return finalEquation;
	}

	/**
	 * This method deals with the case of creating an Expression Tree
	 * if the operator is unary. It confirms the stack is not empty then pops from the stack.
	 * The popped item becomes the left child of the Expression Tree and the right child becomes null.
	 * The Expression Tree is pushed onto the stack, then calls auxGenerateExpressionTree to create another
	 * Expression Tree. If the stack is empty an error message is displayed and the user is prompted to
	 * re-enter their equation
	 * @param expressionStack	The stack that contains Expression Trees. It is used to pop
	 * a number that the operator is called on, then the new expression tree is pushed onto it
	 * @param item	A Unary Operator Token, becomes the root of the epxressionTree
	 * @return	Another expression tree creates by auxGenerateExpressionTree
	 * The return simply keeps the method cycle going until the user enters 'e'
	 */
	public static ExpressionTree unaryCall(Stack expressionStack, Token item)
	{
		if(expressionStack.isEmpty())
		{
			System.out.println("Pop Failed – An Input Error Has Occured"
					+ " \n Please Re-Enter Your Entire Equation");
			return auxGenerateExpressionTree(new Stack());
		}
		ExpressionTree Express = new ExpressionTree(item, (ExpressionTree) expressionStack.pop(), null);
		expressionStack.push(Express);
		return auxGenerateExpressionTree(expressionStack);
	}

	/**
	 * This method prompts the user to enter in a Token.
	 * If the help method is called, it calls that method, then recursively calls itself
	 * for a new Token. Every time the user creates a new Token methods that
	 * specifically deal with each type of token are called. If the user enters 'e' for evaluate
	 * null is returned
	 * @return	A new Token
	 */
	public static Token getToken()
	{
		System.out.println("Please Enter a Token");
		char type = getChar();
		if(type == Token.END)
		{
			return null;
		}
		else if (type == Token.HELP_CHAR)
		{
			generalHelpMenu();
			return getToken();
		}
		String data = getData();
		if(type == Token.BINARY_CHAR)
		{
			return confirmBinaryInput(data.charAt(0), type);
		}
		else if (type == Token.UNARY_CHAR)
		{
			return confirmUnaryInput(data.charAt(0), type);
		}
		else if (type == Token.NUMBER_CHAR)
		{
			return numberCases(data);
		}
		else
		{
			System.out.println("Invalid Input");
			return getToken(); //Recursive till it gets the correct input
		}
	}

	/**
	 * This method confirms that the user entered a character that represents
	 * a real binary operator. The method calls a bunch of else if's (and one if)
	 * instead of using or for cleanliness. If everything is a good a new Binary Operator
	 * Token is created and returned. If an issue occurs the an error message is displayed
	 * and the user is prompted to enter a new Token
	 * @param op	The character that the user entered to represent a binary op
	 * @param type	The type of Token it is always binary, simply used to pass on to constructor)
	 * @return	A new binary Token
	 */
	public static Token confirmBinaryInput(char op, char type)
	{
		if(op == Token.ADD_CHAR)
		{
			return new Token(type, op);
		}
		else if (op == Token.SUBTRACT_CHAR)
		{
			return new Token(type, op);
		}
		else if (op == Token.MULT_CHAR)
		{
			return new Token(type, op);
		}
		else if (op == Token.DIV_CHAR)
		{
			return new Token(type, op);
		}
		else if (op == Token.MOD_CHAR)
		{
			return new Token(type, op);
		}
		else
		{
			System.out.println("Error. Invalid Binary Entry");
			return getToken();
		}
	}

	/**
	 * This method simply confirms that the user entered a real unary operator.
	 * Instead of using the "or" in an if statement for cleanliness else if's are used.
	 * If all is good it creates and returns a new Token with a Unary Operator.
	 * If there is an error an error message is displayed and the user is prompted
	 * to enter a new token
	 * @param op	The character that the user entered to represent a unary op
	 * @param type	The type of Token it is always unary, simply used to pass on to constructor)
	 * @return	A new Unary Operator Token.
	 */
	public static Token confirmUnaryInput(char op, char type)
	{
		if(op == Token.MINUS_CHAR)
		{
			return new Token(type, op);
		}
		else if (op == Token.PLUS_CHAR)
		{
			return new Token(type, op);
		}
		else if (op == Token.SIN_CHAR)
		{
			return new Token(type, op);
		}
		else if (op == Token.COS_CHAR)
		{
			return new Token(type, op);
		}
		else if (op == Token.TAN_CHAR)
		{
			return new Token(type, op);
		}
		else
		{
			System.out.print("Error. Invalid Unary Type"); 
			return getToken();
		}
	}

	/**
	 * This method handles the case if the user enters a Binary Operator Token.
	 * The method confirms the stack is not empty (because it will need to pop) 
	 * then proceeds to pop two items from the stack (after another isEmpty Check)
	 * These popped items become the left and Right Child of the Expression Tree
	 * which is created, then pushed onto the stack. If the Stack at any point is empty
	 * an error message is displayed and the user is asked to start over.
	 * @param expressionStack	A stack containing ExpressionTrees. The ExpressionTree 
	 * (in a normal case) holds number tokens which are used to become the left and right child
	 * @param item	The Token containing the binary operator
	 * @return	A new ExpressionTree/
	 */
	public static ExpressionTree binaryCall (Stack expressionStack, Token item)
	{
		if(expressionStack.isEmpty())
		{
			System.out.println("Pop Failed – An Input Error Has Occured"	
					+ " \nPlease Re-Enter Your Entire Equation");
			return auxGenerateExpressionTree(new Stack());

		}
		ExpressionTree rightChild = (ExpressionTree) expressionStack.pop();
		if(expressionStack.isEmpty())
		{
			System.out.println("Pop Failed. This Means Numbers Were Not Entered Correctly Before Operators"
					+ "Please Re-Enter Your Entire Equation");
			return auxGenerateExpressionTree(new Stack());

		}
		ExpressionTree leftChild = (ExpressionTree) expressionStack.pop();
		ExpressionTree Express = new ExpressionTree (item, leftChild, rightChild);

		expressionStack.push(Express);
		return auxGenerateExpressionTree(expressionStack);
	}

	/**
	 * This method handles the various cases if the Token is a number.
	 * It confirms that the user entered a number by calling isDouble on 
	 * the String data. If it is not a double it checks to see if the user
	 * entered e or pi, if they did not an error message is displayed
	 * and the user is prompted for a new token. If the user enters correct
	 * information a new Token is created and returned.
	 * @param data	A string containing either a number or a character to
	 * represent e or pi.
	 * @return	A new token
	 */
	public static Token numberCases(String data)
	{
		if(isDouble(data))
		{	
			return new Token (Double.parseDouble(data));
		}
		else
		{
			char charData = data.charAt(0);
			if(charData == Token.PI || charData == Token.EULERS)
			{
				return new Token(charData);
			}
			else
			{
				System.out.println("Invalid Entry");
				return getToken();
			}
		}
	}

	/**
	 * This method gets a character from the console.
	 * It takes in a String (an entire line) that the user enters
	 * and returns the first character of the string (so if the user 
	 * enters more than one character, just the first character is used)
	 * If the user enters nothing the method will continue to get a String
	 * from the next line
	 * @return	The character entered by the user.
	 */
	public static char getChar()
	{	
		String line = console.nextLine();
		while(line.length() == 0) 
		{
			line = console.nextLine();
		}
		return line.charAt(0);
	}

	/**
	 * This method gets the next line from the console
	 * If the user enters nothing it continues to try to get
	 * the next line until the user finally enters something
	 * @return
	 * The String the user entered
	 */
	public static String getData()
	{
		String line = console.nextLine();
		while(line.length() == 0) 
		{
			line = console.nextLine();
		}
		return line;
	}

	/**
	 * This program extra confirms that a String contains a double
	 * It attempts to parse the Double out of the String 
	 * and if that causes and exception false is returned
	 * If an exception is not thrown true is returned.
	 * @param data	The string that is being tested to see if it contains a double
	 * @return
	 * True if the String is a double
	 * False if not.
	 */
	public static boolean isDouble(String data)
	{
		try 
		{ 
			Double.parseDouble(data); 
		}
		catch(NumberFormatException e) 
		{ 
			return false; 
		}
		return true;
	}


}
