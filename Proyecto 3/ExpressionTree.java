public class ExpressionTree
{
    public String expr;
    public ExpressionTree left;
    public ExpressionTree right;

    ExpressionTree(String expr,ExpressionTree left,ExpressionTree right)
    {
        this.expr = expr;
        this.left = left;
        this.right = right;
    }

    public String getExpr()
    {
        return String.valueOf(expr);
    }

    public ExpressionTree evaluate()
    {
        int result=0;
        int izq = Integer.valueOf(String.valueOf(this.left.getExpr()));
        int der = Integer.valueOf(String.valueOf(this.right.getExpr()));
        String operator = expr;

        switch (operator)
        {
        case "SUM":
            for (int i = 1; i<=der; ++i)
            {
                result+=i;
            }
        case "MIN":
            if (der>izq)
            {
                result=0+izq;
            }
            else
            {
                result=0+der;
            }
        case "MAX":
            if (der>izq)
            {
                result=0+der;
            }
            else
            {
                result=0+izq;
            }
        case "+":
            result=0+izq+der;
        case "-":
            result=0+izq-(der);
        case "*":
            result=0+(izq*der);
        }
        ExpressionTree solution = new ExpressionTree(""+result,null,null);
        return solution;
    }
}