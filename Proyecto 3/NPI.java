import java.util.Stack;

public class NPI
{
	public static void main(String expresion)
	{
		String[] exp = expresion.split(" ");
		System.out.println(solve(tree_constructor(exp)));
	}
	public static boolean check_operator(String operator)
	{
		switch (operator)
		{ 
			case "SUM":
			case "MIN":
			case "MAX":
			case "+":
			case "-":
			case "*":
			return true;
		}
		return false;
	}
    public static boolean check_operand(String str)
    {
        try
        {
            double d = Double.parseDouble(str);
        }
        catch(NumberFormatException nfe)
        {
            return false;
        }
        return true;
    }
    public static ExpressionTree solve(ExpressionTree node)
    {
		ExpressionTree result = new ExpressionTree("",null,null);
		if (check_operator(node.left.expr) && check_operator(node.right.expr))
		{
			result = new ExpressionTree(node.expr, solve(node.left), solve(node.right));
		}
		else if (check_operator(node.left.expr) && check_operand(node.right.expr))
		{
			result = new ExpressionTree(node.expr, solve(node.left), node.right);
		}
		else if (check_operator(node.left.expr) && check_operand(node.right.expr))
		{
			result = new ExpressionTree(node.expr, solve(node.left), node.right);
		}
		else if (check_operator(node.right.expr) && check_operand(node.left.expr))
		{
			result = new ExpressionTree(node.expr, node.left, solve(node.right));
		}
		else if (check_operand(node.left.expr) &&  check_operand(node.right.expr))
		{
			result = new ExpressionTree(node.expr, node.left, node.right);
		}
		return result.evaluate();
    }
    public static ExpressionTree tree_constructor(String[] expresion)
	{
		Stack<ExpressionTree> stack_tree = new Stack<>();
		ExpressionTree operand;
        for (int i = 0; i<expresion.length; ++i)
        {
			if (check_operand(expresion[i]))
			{
				operand = new ExpressionTree(expresion[i], null, null);
				stack_tree.push(operand);
			}
			else if (check_operator(expresion[i]))
			{
				operand = new ExpressionTree(expresion[i], null, null);
				stack_tree.push(operand);
				if (stack_tree.size() > 1)
				{
					ExpressionTree operand2 = stack_tree.pop();
					ExpressionTree operand1 = stack_tree.pop();
					ExpressionTree operator = new  ExpressionTree(expresion[i], operand1, operand2);
					stack_tree.push(operator);
				}
			}
        }
		return stack_tree.pop();
	}
}