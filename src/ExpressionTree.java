/******************************************************************************
 * 
 * Name:		Jason Scharff
 * Block:		C
 * Date:		February 28, 2014
 * 
 *  Program #9:	Expression Evaluator
 *  Description:
 *     This class creates a new Expression Tree. It manages one class scope variables, the root.
 *     An expression tree has a constructor which handles cases for every type of token, an evaluate and
 *     a toString (though it is never used). the Expression Evaluator Class creates new Expression Trees 
 *    then evalutes it using the method in this class.
 * 
 ******************************************************************************/
public class ExpressionTree
{
	private TreeNode root = null;

	/**
	 * This constructor creates a new Expression Tree. It makes the root equal to the 
	 * the Token inputed by the user. It checks to make sure on of the sides (or both) is not null,
	 * then calls the method get root on the parameter leftExpr and rightExpr and sets that equal
	 * to left and right. By doing so the method creates a new Expression Tree with previously created
	 * Expression Trees as roots
	 * @param rootToken	The Token that an expression Tree is being created from
	 * @param leftExpr	This parameter becomes the left portion of the tree node
	 * @param rightExpr	This parameter becomes the right portion of the tree node
	 */
	public ExpressionTree(Token rootToken, ExpressionTree leftExpr, ExpressionTree rightExpr)
	{
		if(leftExpr == null && rightExpr == null)
		{
			root = new TreeNode(rootToken, null, null);
		}
		else if (rightExpr == null)
		{
			root = new TreeNode (rootToken, leftExpr.getRoot(), null);
		}
		else
		{	
			root = new TreeNode (rootToken, leftExpr.getRoot(), rightExpr.getRoot());
		}
	}

	/**
	 * This method simply returns the root of an ExpressionTree
	 * It is used to connect new Expression Trees to previously 
	 * created ones in the constructor
	 * @return	The root of a tree
	 */
	private TreeNode getRoot()
	{
		return root;
	}

	/**
	 * This method serves as the wrapper method for auxEvaluate
	 * @return	The double value of the evaluated Expression Tree
	 */
	public double evaluate()
	{
		return auxEvaluate(root);
	}

	/**
	 * This method recursively evaluates the expression Tree.
	 * It uses a post order traversal to create algebraic expressions
	 * it can then evaluate. The base case is when the parameter t 
	 * is null. It then checks to see what type of Token it is.
	 * If a number, it just returns the number
	 * If a Unary Op it gets the number from the left child (recursively)
	 * If a Binary Op it gets the numbers from the left and right children (recursively)
	 * Then it calls another method that sees what operator to call on the children.
	 * There is an else, but it should never be called because correction input is checked in the
	 * Expression Evaluator Class
	 * @param t	A TreeNode containing a Token. Methods are called to yield operators and numbers from it.
	 * @return	The double yielded by evaluation the Expression Tree
	 */
	public double auxEvaluate(TreeNode t)
	{
		if(t == null)
		{
			return 0;
		}
		else if (((Token) t.getValue()).getType() == Token.NUM_TYPE)
		{
			return ((Token) t.getValue()).getNum();
		}

		else if (((Token) t.getValue()).getType() == Token.UNARY_TYPE)
		{
			double x = auxEvaluate(t.getLeft());
			return unaryOPEvauluate(x, t);
		}
		else if (((Token) t.getValue()).getType() == Token.BINARY_TYPE)
		{
			double x = auxEvaluate(t.getLeft());
			double y = auxEvaluate(t.getRight());
			return binaryOPEvaluate(x, y, t);

		}
		else
		{
			System.err.println("An Error in Evaluation Has Occured");
			return 0;
		}
	}

	/**
	 * This method performs a unary operator on a given double. It checks 
	 * to see what type of unary operator it is, then calls the proper function on the double.
	 * For trig functions it converts from radians to degrees
	 * @param x	A double that the operator is performed on
	 * @param t	TreeNode containing the unary operator
	 * @return	The double that is yielded by performing the operation on double x
	 */
	public double unaryOPEvauluate(double x, TreeNode t)
	{
		if(((Token) t.getValue()).getOp() == Token.PLUS)
		{
			return x;
		}
		else if (((Token) t.getValue()).getOp() == Token.MINUS)
		{
			return x * -1;
		}
		else if (((Token) t.getValue()).getOp() == Token.SIN)
		{
			return Math.sin(Math.toRadians(x));
		}
		else if (((Token) t.getValue()).getOp() == Token.COS)
		{
			return Math.cos(Math.toRadians(x));
		}
		else if (((Token) t.getValue()).getOp() == Token.TAN)
		{
			return Math.tan(Math.toRadians(x));
		}
		else
		{
			System.err.println("Invalid Op in UnaryOP");
			return 0;
		}
	}

	/**
	 * This method evaluates a Binary Operator on two doubles. 
	 * It determines what type of Binary Operator it is through a series
	 * of if statements, then performs the specified operator in the Token.
	 * If you attempt to divide by 0 it prints an error message and returns 0
	 * @param x	The first number that the binary operator uses
	 * @param y	The second number that the binary operator uses
	 * @param t	A TreeNode containing a Token which says which Binary Op to perform
	 * @return	The double yielded from evaluating the binary operator and x and y
	 * 
	 */
	public double binaryOPEvaluate(double x, double y, TreeNode t)
	{
		if(((Token) t.getValue()).getOp() == Token.ADD)
		{
			return x + y;
		}
		else if (((Token) t.getValue()).getOp() == Token.SUBTRACT)
		{
			return x - y;
		}
		else if (((Token) t.getValue()).getOp() == Token.MULTIPLY)
		{
			return x * y;
		}
		else if (((Token) t.getValue()).getOp() == Token.DIVIDE)
		{
			if(y == 0)
			{
				System.err.println("Dividing by 0 Error");
				return 0;
			}
			return x / y;
		}
		else if ((((Token) t.getValue()).getOp() == Token.MOD))
		{
			if(y == 0)
			{
				System.err.println("Dividing by 0 Error");
				return 2^63 -1;
			}
			return x % y;
		}
		else
		{
			return 0;
		}
	}


	/**
	 * Post order traversal of the tree. 
	 * A wrapper method for auxToStringPostOrder
	 * @return	The Tree as a String
	 */
	public String toString()
	{
		return auxToStringPostOrder(root);
	}

	/**
	 * This method traverses the tree in post order (recursively)
	 * and appends the contents of each tree node to a String that is returned
	 * @param	A TreeNode which contains a Token
	 * @return	An expression tree as a String in post order traversal
	 */
	public String auxToStringPostOrder(TreeNode t)
	{
		if (t == null)
		{
			return "";
		}
		else
		{
			return 	auxToStringPostOrder(t.getLeft()) +  
					auxToStringPostOrder(t.getRight()) + 
					t.getValue().toString() + '\n';
		}
	}

}
