import java.util.Stack;
import java.io.IOException;
import java.util.Arrays;

public class InfixToPostfix
{ 
    // A utility function to return precedence of a given operator 
    // Higher returned value means higher precedence 
    static int Prec(String operator)
    {
        switch (operator)
        { 
            case "SUM":
                return 1;
            case "MIN":
            case "MAX":
                return 2;
            case "+":
            case "-":
                return 3;
            case "*":
                return 4;
        }
        return -1;
    }

    public static boolean isNumeric(String str)
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

    // The main method that converts given infix expression 
    // to postfix expression.  
    static String infixToPostfixFunction(String exp)
    {
        // initializing empty String for result
        String result = new String("");
        String current = new String("");
        String newCharacter= new String("");
        // initializing empty stack
        Stack<String> stack = new Stack<>();
        Stack<String> stackPRE = new Stack<>();
        String operatorPRE = new String("");
        boolean anteriorEraOperando=false;
        exp+=" ";

        for (int i = 0; i<exp.length()-1; ++i)
        {
            newCharacter = String.valueOf(exp.charAt(i));

            // If the scanned character is an operand, add it to output. 
            if (isNumeric(newCharacter))
            {
                current += newCharacter;
                while(isNumeric(String.valueOf(exp.charAt(i+1))))
                {
                    current += String.valueOf(exp.charAt(i+1));
                    i+=1;
                }
                result += current+" ";
                current="";
                anteriorEraOperando=true;
            }
            else if (newCharacter.equals("-"))
            {
                if (current.equals("") && (!(anteriorEraOperando)))
                {
                    current="-";
                    exp = exp.substring(0, i+1) + "1*" + exp.substring(i+1, exp.length());
//                    System.out.println(exp);
                }
                else
                {
                    while (!stack.isEmpty() && Prec(newCharacter) <= Prec(stack.peek()))
                    {
                        result += stack.pop()+ " ";
                    }
                    stack.push(newCharacter);
                }

            }
            // If the scanned character is an '(', push it to the stack. 
            else if (newCharacter.equals("("))
            {
                stack.push("(");
                anteriorEraOperando=false;
            }

            //  If the scanned character is an ')', pop and output from the stack  
            // until an '(' is encountered. 
            else if (newCharacter.equals(")"))
            {
//                anteriorEraOperando=false;
                while (!stack.isEmpty() && stack.peek() != "(")
                    result += stack.pop()+ " ";
                if (!stack.isEmpty() && stack.peek() != "(")
                    return "Invalid Expression"; // invalid expression                 
                else
                    stack.pop();
            }
            else if (newCharacter.equals(","))
            {
                anteriorEraOperando=false;
                operatorPRE = stackPRE.pop();
                while (!stack.isEmpty() && Prec(operatorPRE) <= Prec(stack.peek()))
                {
                    result += stack.pop()+ " ";
                }
                stack.push(operatorPRE);
            }
            else // an operator is encountered
            {
                anteriorEraOperando=false;
                if (newCharacter.equals("M"))
                {
                    i++;
                    newCharacter+=String.valueOf(exp.charAt(i));
                    i++;
                    newCharacter+=String.valueOf(exp.charAt(i));
                    stackPRE.push(String.valueOf(newCharacter));
//                    System.out.println(newCharacter);
                }
                else if(newCharacter.equals("S"))
                {
                    i++;
                    newCharacter+=String.valueOf(exp.charAt(i));
                    i++;
                    newCharacter+=String.valueOf(exp.charAt(i));
                    stackPRE.push(String.valueOf(newCharacter));
                    exp = exp.substring(0, i+2) + "0," + exp.substring(i+2, exp.length());
//                    System.out.println(exp);
                }
                else
                {
                    while (!stack.isEmpty() && Prec(newCharacter) <= Prec(stack.peek()))
                    {
                        result += stack.pop()+ " ";
                    }
                    stack.push(newCharacter);
                }
            }
//            System.out.println(stackPRE.size());
//            System.out.println(result);
        }
        // pop all the operators from the stack 
        while (!stack.isEmpty()) 
            result += stack.pop() + " ";
        return result;
    }
////////////////////////////////////////////////////////////////////////////////
    // Driver method
    public static String driver(String args)
    { 
        String input = args;
        // output expected -2 23 * 6 + 1 -
        // original output 223*6+1-
        // actual output 0 23 -2*1 +6-
        String exp = infixToPostfixFunction(input);
//        System.out.println(exp);
        String[] expresion = exp.split(" ");
        if (expresion.length<3)
        {
            System.out.println(Arrays.toString(expresion)+"Is Not A Expression");
            return null;
        }
//        System.out.println(Arrays.toString(expresion));
        return solve(tree_constructor(expresion)).getExpr();
    }
////////////////////////////////////////////////////////////////////////////////

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
        if ((check_operator(node.left.expr) && check_operator(node.right.expr)))
        {
            result = new ExpressionTree(node.expr, solve(node.left), solve(node.right));
        }
        else if ((check_operator(node.left.expr) && check_operand(node.right.expr)))
        {
            result = new ExpressionTree(node.expr, solve(node.left), node.right);
        }
        else if ((check_operand(node.left.expr) && check_operator(node.right.expr)))
        {
            result = new ExpressionTree(node.expr, node.left, solve(node.right));
        }
        else if ((check_operand(node.left.expr) && check_operand(node.right.expr)))
        {
            result = new ExpressionTree(node.expr, node.left, node.right);
        }
//        System.out.println(result.getExpr());
//        System.out.println(result.right.getExpr());
//        System.out.println(result.left.getExpr());
        return result.evaluate();
    }

    public static ExpressionTree tree_constructor(String[] expresion)
    {
        int n = expresion.length;
        Stack<ExpressionTree> stack_tree = new Stack<>();
        for (int i = 0; i<n; ++i)
        {
            if (check_operand(expresion[i]))
            {
                ExpressionTree operand = new ExpressionTree(expresion[i], null, null);
                stack_tree.push(operand);
            }
            if (check_operator(expresion[i]))
            {
                if (stack_tree.size() > 1)
                {
                    ExpressionTree operand2 = stack_tree.pop();
                    ExpressionTree operand1 = stack_tree.pop();
                    ExpressionTree operator = new ExpressionTree(expresion[i],operand1,operand2);
                    stack_tree.push(operator);
                }
            }
        }
        return stack_tree.pop();
    }
}