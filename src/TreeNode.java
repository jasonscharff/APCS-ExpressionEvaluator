/**********************************************************************************************
Class:		TreeNode
Created by:	APCS

Description:
	The standard TreeNode class assumed for the APCS exam.
	This can be used as a separate file or incorporated into you program file.  If you put it
	into another file then you must remove the "public" in front of class TreeNode.

*********************************************************************************************/
public class TreeNode
{
	/** The value stored in this node. */
	private Object value;
	
	/** This node's left child (may be null) */
	private TreeNode left;
	
	/** This node's right child (may be null) */
	private TreeNode right;
	
	/**
	 * Construct a TreeNode with the given value and children
	 * 
	 * @param v			Comparable value
	 * @param l			left child (or null)
	 * @param r			right child (or null)
	 */
	public TreeNode(Object v, TreeNode l, TreeNode r)
	{
		value = v;
		left = l;
		right = r;
	}
	
	/**
	 * Get the node's value
	 * 
	 * @return		node's value
	 */
	public Object getValue()
	{
		return value;
	}
	
	/**
	 * Get the node's left child
	 * @return		node's left child (or null)
	 */
	public TreeNode getLeft()
	{
		return left;
	}
	
	/**
	 * Get the node's right child
	 * @return		node's right child (or null)
	 */
	public TreeNode getRight()
	{
		return right;
	}
	
	/**
	 * Set the node's value
	 * 
	 * @param v		node's new value
	 */
	public void setValue(Object v)
	{
		value = v;
	}
	
	/**
	 * Set the node's left child
	 * @param l		node's new left child (may be null)
	 */
	public void setLeft(TreeNode l)
	{
		left = l;
	}
	
	/**
	 * Set the node's right child
	 * @param l		node's new right child (may be null)
	 */
	public void setRight(TreeNode r)
	{
		right = r;
	}
}