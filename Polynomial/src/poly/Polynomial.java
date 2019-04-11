package poly;


import java.io.IOException;
import java.util.Scanner;


/**
 * This class implements evaluate, add and multiply for polynomials.
 * 
 * @author runb-cs112
 *
 */
//CO-AUTHOR KEVIN M PATEL
public class Polynomial {
	
	/**
	 * Reads a polynomial from an input stream (file or keyboard). The storage format
	 * of the polynomial is:
	 * <pre>
	 *     <coeff> <degree>
	 *     <coeff> <degree>
	 *     ...
	 *     <coeff> <degree>
	 * </pre>
	 * with the guarantee that degrees will be in descending order. For example:
	 * <pre>
	 *      4 5
	 *     -2 3
	 *      2 1
	 *      3 0
	 * </pre>
	 * which represents the polynomial:
	 * <pre>
	 *      4*x^5 - 2*x^3 + 2*x + 3 
	 * </pre>
	 * 
	 * @param sc Scanner from which a polynomial is to be read
	 * @throws IOException If there is any input error in reading the polynomial
	 * @return The polynomial linked list (front node) constructed from coefficients and
	 *         degrees read from scanner
	 */
	public static Node read(Scanner sc) 
	throws IOException {
		Node poly = null;
		while (sc.hasNextLine()) {
			Scanner scLine = new Scanner(sc.nextLine());
			poly = new Node(scLine.nextFloat(), scLine.nextInt(), poly);
			scLine.close();
		}
		return poly;
	}
	
	/**
	 * Returns the sum of two polynomials - DOES NOT change either of the input polynomials.
	 * The returned polynomial MUST have all new nodes. In other words, none of the nodes
	 * of the input polynomials can be in the result.
	 * 
	 * @param poly1 First input polynomial (front of polynomial linked list)
	 * @param poly2 Second input polynomial (front of polynomial linked list
	 * @return A new polynomial which is the sum of the input polynomials - the returned node
	 *         is the front of the result polynomial
	 */
	public static Node add(Node poly1, Node poly2) {
		/** COMPLETE THIS METHOD **/
		// FOLLOWING LINE IS A PLACEHOLDER TO MAKE THIS METHOD COMPILE
		// CHANGE IT AS NEEDED FOR YOUR IMPLEMENTATION
		
		//initializes a front node for 'result' for resulting sum LL
		
		Node result = null;
		Node zeroPoly = new Node(0,0,null);
		//checks if either polynomial is null
		//return polynomial which is not null
		if (poly1 == null && poly2 == null)
		{
			
			return zeroPoly;
		}
		
		
		if (poly2 == null)
		{
			return poly1;
		}
		else if ( poly1 == null) 
		{
			return poly2;
		}
		
		
		
		
		while ( poly1 != null ) 
		{
		
		//checks if coeff is zero
		// if null then it will go to next term in poly1
		if (poly2 == null)
		{
			result = new Node(poly1.term.coeff,poly1.term.degree, result);
			poly1 = poly1.next;
			continue;
		}
		
	
		//creates new node for terms with same degrees then iterates to next terms in both
		// polynomials
		else if (poly1.term.degree == poly2.term.degree) 
				{
				
					result = new Node(poly1.term.coeff + poly2.term.coeff, poly1.term.degree, result);
					poly1 = poly1.next;
					poly2 = poly2.next;
				}
		// if poly2 doesn't have the same degree then following
		// else statement will run
			else if (poly1.term.degree < poly2.term.degree)
				{
					result = new Node(poly1.term.coeff,poly1.term.degree,result);
					poly1 = poly1.next;
				}
			
		// checks if poly1 has ran through entire poly2 meaning
		// a term of the same degree is not present in poly2
		// if so then poly2 resets back to its front
		// poly1 term is recorded then iterated
			else if (poly1.term.degree > poly2.term.degree)
				{
				result = new Node(poly2.term.coeff, poly2.term.degree, result);
				poly2 = poly2.next;
				
				}
			
		
		}
		
	
		
		//checks if poly2 has any remaining terms which
		//don't have any matching exponents in poly1
		while ( poly2 != null) {
			
			if (poly1 == null)
			{
				result = new Node(poly2.term.coeff,poly2.term.degree,result);
				poly2 = poly2.next;
				continue;
			}
			
			else if (poly2.term.degree == poly1.term.degree) {
				result = new Node(poly2.term.coeff + poly1.term.coeff ,poly1.term.degree,result);
			}
			else if (poly1.term.degree > poly2.term.degree){
				result = new Node(poly2.term.coeff,poly2.term.degree,result);
				poly2 = poly2.next;
			}
			
			else if (poly1.term.degree < poly2.term.degree)
			{
				result = new Node(poly1.term.coeff, poly1.term.degree, result);
				poly1 = poly1.next;
				
			}
			
	     } 
		//returns sum of poly1 and poly2
		Node temp = result;
		Node reverse = null;
		
		
		
		
		// reverses polynomial to descending order
			while ( temp != null)
			{
				
			if (temp.term.coeff == 0 && reverse != null)
				{
				reverse = reverse.next;
				continue;
				}
				
			else 
				{
				reverse = new Node(temp.term.coeff,temp.term.degree,reverse);
				temp = temp.next;
				}
			 }
			
			
			if (reverse == null)
			{
				return null;
			}
			
			
			return reverse;
			
		}
		
		
		
		
	
	/**
	 * Returns the product of two polynomials - DOES NOT change either of the input polynomials.
	 * The returned polynomial MUST have all new nodes. In other words, none of the nodes
	 * of the input polynomials can be in the result.
	 * 
	 * @param poly1 First input polynomial (front of polynomial linked list)
	 * @param poly2 Second input polynomial (front of polynomial linked list)
	 * @return A new polynomial which is the product of the input polynomials - the returned node
	 *         is the front of the result polynomial
	 */
	public static Node multiply(Node poly1, Node poly2) {
		/** COMPLETE THIS METHOD **/
		// FOLLOWING LINE IS A PLACEHOLDER TO MAKE THIS METHOD COMPILE
		// CHANGE IT AS NEEDED FOR YOUR IMPLEMENTATION
	
		
		if ( poly1 == null || poly2 == null) 
		{
			Node zero = new Node( 0,0,null);
			return zero;
		}
		
		
		Node sortedResult = null;
		Node poly2tmp = poly2;
		Node product = null;
		Node productTemp = null;
		
		
		while ( poly1 != null )
		{
			
			while ( poly2 != null)
				
			{
			productTemp = new Node(poly1.term.coeff * poly2.term.coeff, poly1.term.degree + poly2.term.degree,productTemp);
			poly2 = poly2.next;
			}
			
			
			//following code reverses the polynomial
			
			Node reverse = productTemp;
			Node productReversed = null;
			
			
			while ( reverse != null)
			{
				if(reverse.term.coeff == 0)
				{
					reverse = reverse.next;
					continue;
				}
				
				else 
				{
					productReversed = new Node(reverse.term.coeff,reverse.term.degree,productReversed);
					reverse = reverse.next;
				}
			}
			
			
			sortedResult = add(sortedResult, productReversed);
			poly1 = poly1.next;
			poly2 = poly2tmp;
			productTemp = null;
			
			
		}
		
		
		return sortedResult;
		
		
		
		
		
	
	}
	
	
	
		
	/**
	 * Evaluates a polynomial at a given value.
	 * 
	 * @param poly Polynomial (front of linked list) to be evaluated
	 * @param x Value at which evaluation is to be done
	 * @return Value of polynomial p at x
	 */
	public static float evaluate(Node poly, float x) {
		/** COMPLETE THIS METHOD **/
		// FOLLOWING LINE IS A PLACEHOLDER TO MAKE THIS METHOD COMPILE
		// CHANGE IT AS NEEDED FOR YOUR IMPLEMENTATION
		
		float value = 0;
		while( poly != null) 
		{
			value = (float) (value + (poly.term.coeff *  ( Math.pow( x, poly.term.degree) ) ));
			poly = poly.next;
		}
		return value;
		}
		
	
	public static void main(String[] arg)
	{
		
	 Node x = new Node(0,3, new Node(3,2, new Node(2,1,null)));
	 Node y = new Node(2,4, new Node(3,6, new Node(2,1,null)));
	 
	 while (y != null) {
		 Node z = add(x,y);
		 System.out.println(z.term.toString());
		 y = y.next;
		 
	 }
	
	}
	
	/**
	 * Returns string representation of a polynomial
	 * 
	 * @param poly Polynomial (front of linked list)
	 * @return String representation, in descending order of degrees
	 */
	public static String toString(Node poly) {
		if (poly == null) {
			return "0";
		} 
		
		String retval = poly.term.toString();
		for (Node current = poly.next ; current != null ;
		current = current.next) {
			retval = current.term.toString() + " + " + retval;
		}
		return retval;
	}	
}
