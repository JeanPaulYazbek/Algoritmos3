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
        return expr;
    }

    public ExpressionTree evaluate()
    {
        int result = 0;
        String operator = expr;
        switch (operator)
        {
        case "SUM":
            int n = Integer.parseInt(this.right.expr);
            for (int i = 1; i<=n; ++i)
            {
                result+=i;
            }
        case "MIN":
            if (Integer.parseInt(this.right.expr)>Integer.parseInt(this.left.expr))
            {
                result=(Integer.parseInt(this.left.expr));
            }
            else
            {
                result=(Integer.parseInt(this.right.expr));
            }
        case "MAX":
            if (Integer.parseInt(this.right.expr)>Integer.parseInt(this.left.expr))
            {
                result=(Integer.parseInt(this.right.expr));
            }
            else
            {
                result=(Integer.parseInt(this.left.expr));
            }
        case "+":
            result=Integer.parseInt(this.right.expr)+Integer.parseInt(this.left.expr);
        case "-":
            result=Integer.parseInt(this.right.expr)-Integer.parseInt(this.left.expr);
        case "*":
            result=Integer.parseInt(this.right.expr)*Integer.parseInt(this.left.expr);
        }
        ExpressionTree solution = new ExpressionTree(""+result, null, null);
        return solution;
    }
}