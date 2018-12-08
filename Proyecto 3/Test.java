import java.util.Stack;
import java.io.IOException;

class Test 
{ 
    // A utility function to return precedence of a given operator 
    // Higher returned value means higher precedence 
    static int Prec(String operator)
    {
        switch (operator)
        { 
        case "SUM":
            return 2;

        case "MIN":
        case "MAX":
            return 1;

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
    static String infixToPostfix(String exp)
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
        int esUnario=0;
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
                if (esUnario>0)
                {
                    operatorPRE = stackPRE.pop();
                    while (!stack.isEmpty() && Prec(operatorPRE) <= Prec(stack.peek()))
                    {
                        result += stack.pop()+ " ";
                    }
                    stack.push(operatorPRE);
                }
            }

            //  If the scanned character is an ')', pop and output from the stack  
            // until an '(' is encountered. 
            else if (newCharacter.equals(")"))
            {
                anteriorEraOperando=false;
                while (!stack.isEmpty() && stack.peek() != "(")
                    result += stack.pop()+ " ";
                if (!stack.isEmpty() && stack.peek() != "(")
                    return "Invalid Expression"; // invalid expression                 
                else
                    if (esUnario<1)
                    {
                        stack.pop();
                        esUnario-=1;
                    }
            }
            else if (newCharacter.equals(","))
            {
                operatorPRE = stackPRE.pop();
                while (!stack.isEmpty() && Prec(operatorPRE) <= Prec(stack.peek()))
                {
                    result += stack.pop()+ " ";
                }
                stack.push(operatorPRE);
            }
            else // an operator is encountered 
            {
                if (newCharacter.equals("M"))
                {
                    i++;
                    newCharacter+=String.valueOf(exp.charAt(i));
                    i++;
                    newCharacter+=String.valueOf(exp.charAt(i));
                    stackPRE.push(String.valueOf(newCharacter));
                    System.out.println(newCharacter);
                }
                else if(newCharacter.equals("S"))
                {
                    i++;
                    newCharacter+=String.valueOf(exp.charAt(i));
                    i++;
                    newCharacter+=String.valueOf(exp.charAt(i));
                    stackPRE.push(String.valueOf(newCharacter));
                    esUnario+=1;
                    System.out.println(newCharacter);
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
            System.out.println(stackPRE.size());
            System.out.println(result);
        }
        // pop all the operators from the stack 
        while (!stack.isEmpty()) 
            result += stack.pop() + " ";
        return result;
    }

    // Driver method  
    public static void main(String[] args)  
    { 
        String exp = "-1*MAX(SUM(5),SUM(MIN(5,4)))";
        // output expected -2 23 * 6 + 1 -
        // original output 223*6+1-
        // actual output 0 23 -2*1 +6-
        System.out.println(infixToPostfix(exp)); 
    }
}