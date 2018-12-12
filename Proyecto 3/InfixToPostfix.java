import java.util.Stack;
import java.io.IOException;
import java.util.Arrays;

/*
* Integrantes: José Ramón Barrera Melchor / 15-10123
*              Jean Paul Yazbek Farah     / 15-11550
* Referencias: (1) https://www.geeksforgeeks.org/stack-set-2-infix-to-postfix/
*              (2) https://github.com/S1V1R0/ProyectoAlgoritmos
*/

public class InfixToPostfix
{ 
////////////////////////////////////////////////////////////////////////////////
    /**
    * funcion que dado un string verifica si es un integer
    * @param str String que se desea verificar
    * @return true si es un int, false si no lo es
    */
    private boolean isNumeric(String str)
    {
        try
        {
            int d = Integer.parseInt(str);
        }
        catch(NumberFormatException nfe)
        {
            return false;
        }
        return true;
    }
////////////////////////////////////////////////////////////////////////////////
// idea basada en el contenido de la Referencia (1)
////////////////////////////////////////////////////////////////////////////////
    /**
    * funcion que dado una expresion en formato infijo devuelve una expresion
    * equivalente en notacion postfija
    * @param exp expresion en notacion infija que se desea pasar a postfija
    * @return String que representa la expresion en notacion postfija
    */
    private String infixToPostfixFunction(String exp)
    {
        String result = new String("");
        String current = new String("");
        String newCharacter= new String("");
        Stack<String> stack = new Stack<>();
        Stack<String> stackPRE = new Stack<>();
        String operatorPRE = new String("");
        boolean anteriorEraOperando=false;
        exp+=" ";

        for (int i = 0; i<exp.length()-1; ++i)
        {
            newCharacter = String.valueOf(exp.charAt(i));

            // Si leemos un numero entero iteramos hasta encontrar algo que
            // no sea un numero entero y lo agregamos al resultado
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

            // Si leemos un "-"" distinguimos si es un operando o un signo
            else if (newCharacter.equals("-"))
            {
                if (current.equals("") && (!(anteriorEraOperando)))
                {
                    current="-";
                    exp = exp.substring(0, i+1)+"1*"+exp.substring(i+1,exp.length());
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

            // Si leemos un "(", lo empilamos
            else if (newCharacter.equals("("))
            {
                stack.push("(");
                anteriorEraOperando=false;
            }

            // Si leemos un ")", desempilamos y agregamos al resultado hasta
            // encontrar un "("
            else if (newCharacter.equals(")"))
            {
                while (!stack.isEmpty() && stack.peek() != "(")
                {
                    result += stack.pop()+ " ";
                }

                if (!stack.isEmpty() && stack.peek() != "(")
                {
                    return "Invalid Expression";
                }

                else
                {
                    stack.pop();
                }
            }

            // Encontramos un ","
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

            // Encontramos un operador
            else
            {
                anteriorEraOperando=false;
                // MIN y MAX se comportan de forma similar
                if (newCharacter.equals("M"))
                {
                    i++;
                    newCharacter+=String.valueOf(exp.charAt(i));
                    i++;
                    newCharacter+=String.valueOf(exp.charAt(i));
                    stackPRE.push(String.valueOf(newCharacter));
                }
                // En el caso de SUM agregamos un "0," para volverlo un operador
                // binario
                else if(newCharacter.equals("S"))
                {
                    i++;
                    newCharacter+=String.valueOf(exp.charAt(i));
                    i++;
                    newCharacter+=String.valueOf(exp.charAt(i));
                    stackPRE.push(String.valueOf(newCharacter));
                    exp = exp.substring(0, i+2)+"0,"+exp.substring(i+2,exp.length());
                }
                // Otro operador
                else
                {
                    while (!stack.isEmpty()&&Prec(newCharacter)<=Prec(stack.peek()))
                    {
                        result += stack.pop()+ " ";
                    }
                    stack.push(newCharacter);
                }
            }
        }
        // Desempilamos todos los operadores de la pila
        while (!stack.isEmpty())
        {
            result += stack.pop() + " ";
        }
        return result;
    }
////////////////////////////////////////////////////////////////////////////////
// idea basada en el contenido de la Referencia (1)
////////////////////////////////////////////////////////////////////////////////
    /**
    * funcion que dado una expresion en formato infijo devuelve el resultado
    * de su evaluacion como string
    * @param args expresion en notacion infija que se desea evaluar
    * @return String que representa el valor de la expresion
    */
    public String driver(String args)
    { 
        String exp = infixToPostfixFunction(args.replace(" ",""));
        String[] expresion = exp.split(" ");
        if ((expresion.length==0)||(expresion.length==2))
        {
            System.out.println(Arrays.toString(expresion)+"Is Not A Expression");
            return null;
        }
        if (expresion.length==1)
        {
            return args;
        }
        
        return solve(tree_constructor(expresion)).getExpr();
    }
////////////////////////////////////////////////////////////////////////////////
// idea basada en el contenido de la Referencia (1)
////////////////////////////////////////////////////////////////////////////////
    /**
    * funcion que dado un string, si es un operador algun de
    * los operadores SUM,MIN,MAX,+,-,*, devuelve un entero que
    * representa su precedencia, a mayor el entero mayor la precedencia
    * @param operator String que se desea verificar si es un operador
    * @return -1 si no es un operador registrado, [1,4] dependiendo del operador
    */
    private int Prec(String operator)
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
////////////////////////////////////////////////////////////////////////////////
// idea basada en la implementacion en python de la Referencia (2)
////////////////////////////////////////////////////////////////////////////////
    /**
    * funcion que dado un string, si es un operador algun de
    * los operadores SUM,MIN,MAX,+,-,*, devuelve true o false
    * @param operator String que se desea verificar si es un operador
    * @return false si no es un operador registrado, true si lo es
    */
    private boolean check_operator(String operator)
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
////////////////////////////////////////////////////////////////////////////////
// idea basada en la implementacion en python de la Referencia (2)
////////////////////////////////////////////////////////////////////////////////
    /**
    * funcion que dado un ExpressionTree, recorre recursivamente sus hijos
    * y evalua
    * @param node raiz del arbol que se desea evaluar
    * @return ExpressionTree cuya expr es el valor de evaluar el arbol de raiz node
    */
    private ExpressionTree solve(ExpressionTree node)
    {
        ExpressionTree result = new ExpressionTree("",null,null);

        if ((check_operator(node.getLeft().getExpr()) 
            && check_operator(node.getRight().getExpr())))
        {
            result = new ExpressionTree(node.getExpr(),
                solve(node.getLeft()),solve(node.getRight()));
        }
        else if ((check_operator(node.getLeft().getExpr()) 
            && isNumeric(node.getRight().getExpr())))
        {
            result = new ExpressionTree(node.getExpr(),
                solve(node.getLeft()),node.getRight());
        }
        else if ((isNumeric(node.getLeft().getExpr()) 
            && check_operator(node.getRight().getExpr())))
        {
            result = new ExpressionTree(node.getExpr(),
                node.getLeft(),solve(node.getRight()));
        }
        else if ((isNumeric(node.getLeft().getExpr()) 
            && isNumeric(node.getRight().getExpr())))
        {
            result = new ExpressionTree(node.getExpr(),
                node.getLeft(),node.getRight());
        }
        return result.evaluate();
    }
////////////////////////////////////////////////////////////////////////////////
// idea basada en la implementacion en python de la Referencia (2)
////////////////////////////////////////////////////////////////////////////////
    /**
    * funcion que dado un expresion, representada como arreglo de strings
    * construye un arbol binario ExpressionTree nodo por nodo que representa
    * dicha expresion y retorna la raiz de dicho arbol
    * @param expresion que se desea representar como arbol
    * @return ExpressionTree raiz del arbol construido
    *
    */
    private ExpressionTree tree_constructor(String[] expresion)
    {
        int n = expresion.length;
        Stack<ExpressionTree> stack_tree = new Stack<>();
        for (int i = 0; i<n; ++i)
        {
            if (isNumeric(expresion[i]))
            {
                ExpressionTree operand = new ExpressionTree(expresion[i],null,null);
                stack_tree.push(operand);
            }
            if (check_operator(expresion[i]))
            {
                if (stack_tree.size() > 1)
                {
                    ExpressionTree op2 = stack_tree.pop();
                    ExpressionTree op1 = stack_tree.pop();
                    ExpressionTree operator = new ExpressionTree(expresion[i],op1,op2);
                    stack_tree.push(operator);
                }
            }
        }
        return stack_tree.pop();
    }
////////////////////////////////////////////////////////////////////////////////
}