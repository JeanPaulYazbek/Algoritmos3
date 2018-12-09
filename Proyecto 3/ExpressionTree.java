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

        if (expr.equals("SUM"))
        {
            if (der<0)
            {
                der*=-1;
                for (int i = 1; i<=der; ++i)
                {
                    result+=(i);
                }
                result*=-1;
            }
            else
            {
                for (int i = 1; i<=der; ++i)
                {
                    result+=(i);
                }
            }
        }
        else if (expr.equals("MIN"))
        {
            if (der>izq)
            {
                result=0+(izq);
            }
            else
            {
                result=0+(der);
            }
        }
        else if (expr.equals("MAX"))
        {
            if (der>izq)
            {
                result=0+(der);
            }
            else
            {
                result=0+(izq);
            }
        }
        else if (expr.equals("+"))
        {
            result=0+(izq+(der));
        }
        else if (expr.equals("-"))
        {
            result=0+(izq-(der));
        }
        else if (expr.equals("*"))
        {
            result=0+(izq*(der));
        }
//        System.out.println(result);
        ExpressionTree solution = new ExpressionTree(String.valueOf(result),null,null);
        return solution;
    }
}