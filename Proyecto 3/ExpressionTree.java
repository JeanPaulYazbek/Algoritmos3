// idea basada en la implementacion en python
// para la resolucion de expresiones en notacion
// polaca inversa usando arboles binarios del
// usuario S1V1R0 en github
// https://github.com/S1V1R0/ProyectoAlgoritmos

/*
* Integrantes: José Ramón Barrera Melchor / 15-10123
*              Jean Paul Yazbek Farah     / 15-11550
* Referencia: https://github.com/S1V1R0/ProyectoAlgoritmos
*/

public class ExpressionTree
{
    private String expr; //expresion cuya evalucion representa el valor
    private ExpressionTree left; // hijo izquierdo
    private ExpressionTree right; // hijo derecho

    /**
     * contructor para crear un ExpressionTree con todos sus atributos
     * @param expr expresion del nodo a crear
     * @param left ExpressionTree hijo izquierdo
     * @param right ExpressionTree hijo derecho
     */
    ExpressionTree(String expr,ExpressionTree left,ExpressionTree right)
    {
        this.expr = expr;
        this.left = left;
        this.right = right;
    }
    /**
     * metodo para obterner la expr de un nodo
     * @return expr del nodo
     */
    public String getExpr()
    {
        return expr;
    }

    /**
     * metodo para obterner el hijo izquierdo de un nodo
     * @return el hijo izquierdo del nodo
     */
    public ExpressionTree getLeft()
    {
        return left;
    }

    /**
     * metodo para obterner el hijo derecho de un nodo
     * @return el hijo derecho del nodo
     */
    public ExpressionTree getRight()
    {
        return right;
    }

    /**
     * metodo para obterner el hijo derecho de un nodo
     * @return un ExpressionTree con hijos null y expr igual
     * al resultado de la operacion
     */
    public ExpressionTree evaluate()
    {
        int result=0;
        int izq = Integer.valueOf(left.getExpr());
        int der = Integer.valueOf(right.getExpr());

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

        ExpressionTree solution = new ExpressionTree(String.valueOf(result),null,null);
        return solution;
    }
}