package ch.fhnw.cpib;

import ch.fhnw.cpib.compiler.Scanner;
import ch.fhnw.cpib.compiler.error.LexicalError;
import ch.fhnw.cpib.compiler.tokens.ITokenList;

public class Main {

    public static void main(String[] args){
        Scanner s = new Scanner();
        try {
            ITokenList bla = s.scan("// BasicIML V01\n" +
                    "// Edgar F.A. Lederer, FHNW\n" +
                    "// October 2018\n" +
                    "// October 2020\n" +
                    "\n" +
                    "program GcdDef\n" +
                    "  (in a:int64, in b:int64,\n" +
                    "   out g:int64, out numIt:int32)\n" +
                    "global\n" +
                    "  proc gcdDefNatPlus\n" +
                    "    (in copy var a:int64, in copy var b:int64,\n" +
                    "     out copy var g:int64, out copy var numIt:int32)\n" +
                    "    // requires a > 0 /\\? b > 0\n" +
                    "    // ensures g = gcd(a, b)\n" +
                    "  local\n" +
                    "    const h:int64 ; var small:int64 ; var large:int64 ; var run:bool\n" +
                    "  do\n" +
                    "    h init := a ; if a > b then a := b ; b := h endif ;\n" +
                    "    // assert a <= b\n" +
                    "    g init := 1 ;\n" +
                    "    small init := 1 ;\n" +
                    "    large init := a ;\n" +
                    "    run init := true ;\n" +
                    "    numIt init := 0 ;\n" +
                    "    while run /\\? small * small <= a do\n" +
                    "      if a modE small = 0 then\n" +
                    "        large := a divE small ;\n" +
                    "        // assert a = small * large /\\? small <= large\n" +
                    "        if b modE large = 0 then\n" +
                    "          g := large ;\n" +
                    "          run := false\n" +
                    "        else\n" +
                    "          if b modE small = 0 then\n" +
                    "            g := small\n" +
                    "          endif ;\n" +
                    "          small := small + 1 ;\n" +
                    "          numIt := numIt + 1\n" +
                    "        endif\n" +
                    "      else\n" +
                    "        small := small + 1 ;\n" +
                    "        numIt := numIt + 1\n" +
                    "      endif\n" +
                    "    endwhile\n" +
                    "  endproc\n" +
                    "do\n" +
                    "  call gcdDefNatPlus(a, b, g init, numIt init)\n" +
                    "endprogram\n");

            System.out.println(bla);
        }
        catch (LexicalError ex){
            System.out.println(ex);
        }
    }
}
