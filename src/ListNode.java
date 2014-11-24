/**********************************************************************************************
Class:		ListNode
Created by:	APCS

Description:
	The standard ListNode class assumed for the APCS exam.
	This can be used as a separate file or incorporated into you program file.  If you put it
	into another file then you must remove the "public" in front of class ListNode.

*********************************************************************************************/
public class ListNode 
{
	private Object value;
	private ListNode next;
	
	// Constructor
	public ListNode(Object initValue, ListNode initNext)
	{
		value = initValue;
		next = initNext;
	}
	//Accessors
	public Object getValue()
	{
		return value;
	}
	public ListNode getNext()
	{
		return next;
	}
	
	// Mutators
	public void setValue(Object theNewVal)
	{
		value = theNewVal;
	}
	public void setNext(ListNode theNewNext)
	{
		next = theNewNext;
	}
}