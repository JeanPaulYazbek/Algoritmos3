//Obtener el resultado de una formula que esta contenida en una variable de tipo string: eval.
package formula;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Formula {    
    public static void main(String[] args) {        
        ScriptEngineManager mgr = new ScriptEngineManager();    
        ScriptEngine engine = mgr.getEngineByName("JavaScript");        
        try {
            String formula = "5-3+6*(10/2)";
            System.out.println(formula + " = " + engine.eval(formula));
        } catch (ScriptException ex) {}
    }    
}