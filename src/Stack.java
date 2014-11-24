/******************************************************************************
 * 
 * Name:		Jason Scharff
 * Block:		C
 * Date:		February 28, 2014
 * 
 *  Program #9:	Expression Evaluator
 *  Description:
 *     This method creates a new Stack. A stack is a First In, Last Out data structure.
 *     Each element of the stack is a ListNode with the first ListNode of the LinkedList being
 *     the top. If the user pushes then something is added to the top of the linked list, if the user
 *     pops the head is removed from the linked list. There is also an isEmpty method in the Stack.
 *     This code was taken from Moodle, then modified as to remove any mention of the size of the stack,
 *     the isFull(), and the ToString() method.
 * 
 ******************************************************************************/
public class Stack
{
	private ListNode stackTop;
	
	/**
	 * The constructor simply initializes ListNode StackTop by making it null
	 */
	public Stack()
	{
		stackTop = null;
	}
	
	/**
	 * This method returns true if the Stack is empty, false if not
	 * @return	A boolean saying if the stack is Empty
	 */
	public boolean isEmpty()
	{
		return stackTop == null;
	}
	
	/**
	 * This method pushes something onto the Stack by making
	 * the stackTop equal to the new Object and 
	 * @param toPush	The Object to push onto the stack
	 */
	public void push(Object toPush)
	{
		stackTop = new ListNode(toPush, stackTop);
	}
	
	/**
	 * This method pops something from the stack and returns the
	 * Object. If it is empty simply returns null
	 * @return	Null if the stack is empty, the object that is popped if it is not
	 */
	public Object pop()
	{
		if (!isEmpty())
		{
			Object temp = stackTop.getValue();
			stackTop = stackTop.getNext();
			return temp;
		}
		else
		{
			return null;
		}
	}
}
