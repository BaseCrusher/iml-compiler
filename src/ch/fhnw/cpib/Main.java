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
                    "// October 2020\n" +
                    "\n" +
                    "program intDiv(in  a:int32, in  b:int32,\n" +
                    "               out q:int32, out r:int32)\n" +
                    "global\n" +
                    "  proc divide(in copy const a:int32, in copy const b:int32,\n" +
                    "              out ref var   q:int32, out ref var   r:int32)\n" +
                    "    //requires a >= 0 /\\? b > 0\n" +
                    "    //ensures a = b * q + r /\\? 0 <= r < b\n" +
                    "  do\n" +
                    "    q init := 0;\n" +
                    "    r init := a;\n" +
                    "    while r >= b\n" +
                    "      //invar a = b * q + r /\\? 0 <= r\n" +
                    "    do\n" +
                    "      q := q + 1;\n" +
                    "      r := r - b\n" +
                    "    endwhile\n" +
                    "  endproc\n" +
                    "do\n" +
                    "  call divide(a, b, q init, r init)\n" +
                    "endprogram\n");

            System.out.println(bla);
        }
        catch (LexicalError ex){
            System.out.println(ex);
        }
    }
}
