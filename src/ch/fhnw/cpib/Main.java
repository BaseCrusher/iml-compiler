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
                    "program Euclid\n" +
                    "  (in a:int1024, in b:int1024,\n" +
                    "   out g:int1024, out numIt:int32)\n" +
                    "global\n" +
                    "  proc euclidDivNat\n" +
                    "    (in copy const a:int1024, in copy const b:int1024,\n" +
                    "     out copy var g:int1024, out copy var numIt:int32)\n" +
                    "    //requires a >= 0 /\\? b >= 0\n" +
                    "    //ensures g = gcd(a, b) >= 0\n" +
                    "  local\n" +
                    "    var g':int1024\n" +
                    "  do\n" +
                    "    g init := a ; g' init := b ;\n" +
                    "    numIt init := 0 ;\n" +
                    "    while g' > 0\n" +
                    "      //invariant g >= 0 /\\? g' >= 0\n" +
                    "      //invariant gcd(g, g') = gcd(a, b)\n" +
                    "      //decreases g'\n" +
                    "    do\n" +
                    "      g := g modE g' ;\n" +
                    "      call swap(g, g') ;\n" +
                    "      numIt := numIt + 1\n" +
                    "    endwhile\n" +
                    "  endproc ;\n" +
                    "\n" +
                    "  proc swap(inout ref var x:int1024, inout ref var y:int1024)\n" +
                    "  local const h:int1024\n" +
                    "  do h init := x ; x := y ; y := h endproc ;\n" +
                    "\n" +
                    "  const a1:int1024 ;\n" +
                    "  const b1:int1024\n" +
                    "do\n" +
                    "  a1 init := // 300 Stellen\n" +
                    "    1234567890'1234567890'1234567890'1234567890'1234567890'1234567890'\n" +
                    "    1234567890'1234567890'1234567890'1234567890'1234567890'1234567890'\n" +
                    "    1234567890'1234567890'1234567890'1234567890'1234567890'1234567890'\n" +
                    "    1234567890'1234567890'1234567890'1234567890'1234567890'1234567890'\n" +
                    "    1234567890'1234567890'1234567890'1234567890'1234567890'1234567890 ;\n" +
                    "\n" +
                    "  b1 init := // 300 Stellen\n" +
                    "    1234567890'0987654321'0987654321'0987654321'0987654321'0987654321'\n" +
                    "    1234567890'0987654321'0987654321'0987654321'0987654321'0987654321'\n" +
                    "    1234567890'0987654321'0987654321'0987654321'0987654321'0987654321'\n" +
                    "    1234567890'0987654321'0987654321'0987654321'0987654321'0987654321'\n" +
                    "    1234567890'0987654321'0987654321'0987654321'0987654321'0987654321 ;\n" +
                    "\n" +
                    "  if true then\n" +
                    "    call euclidDivNat(a, b, g init, numIt init)\n" +
                    "  else\n" +
                    "    call euclidDivNat(a1, b1, g init, numIt init)\n" +
                    "  endif\n" +
                    "endprogram\n");

            System.out.println(bla);
        }
        catch (LexicalError ex){
            System.out.println(ex);
        }
    }
}
