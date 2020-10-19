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
                    "// January 2020\n" +
                    "// October 2020\n" +
                    "\n" +
                    "program Factorial\n" +
                    "  (in n:int32, out factRec1024:int1024, out allEqual:bool)\n" +
                    "global\n" +
                    "  // 170! ok, 171! overflow\n" +
                    "  fun factRec1024(n:int32) returns const fact:int1024\n" +
                    "  do\n" +
                    "    if n = 0 then\n" +
                    "      fact init := 1\n" +
                    "    else\n" +
                    "      fact init := n * factRec1024(n-1)\n" +
                    "    endif\n" +
                    "  endfun;\n" +
                    "\n" +
                    "  // 170! ok, 171! overflow\n" +
                    "  fun fact1024(n:int32) returns var fact:int1024\n" +
                    "  local\n" +
                    "    var i:int32\n" +
                    "  do\n" +
                    "    fact init := 1;\n" +
                    "    i    init := 2;\n" +
                    "    while i <= n do\n" +
                    "      fact := fact * i;\n" +
                    "      i    := i + 1\n" +
                    "    endwhile\n" +
                    "  endfun;\n" +
                    "\n" +
                    "  // 20! ok, 21! overflow\n" +
                    "  fun fact64(n:int32) returns var fact:int64\n" +
                    "  local\n" +
                    "    var i:int32\n" +
                    "  do\n" +
                    "    fact init := 1;\n" +
                    "    i    init := 2;\n" +
                    "    while i <= n do\n" +
                    "      fact := fact * i;\n" +
                    "      i    := i + 1\n" +
                    "    endwhile\n" +
                    "  endfun;\n" +
                    "\n" +
                    "  // 12! ok, 13! overflow\n" +
                    "  fun fact32(n:int32) returns var fact:int32\n" +
                    "  local\n" +
                    "    var i:int32\n" +
                    "  do\n" +
                    "    fact init := 1;\n" +
                    "    i    init := 2;\n" +
                    "    while i <= n do\n" +
                    "      fact := fact * i;\n" +
                    "      i    := i + 1\n" +
                    "    endwhile\n" +
                    "  endfun;\n" +
                    "\n" +
                    "  fact1024:int1024;\n" +
                    "  fact64:int64;\n" +
                    "  fact32:int32\n" +
                    "do\n" +
                    "  factRec1024 init := factRec1024(n);\n" +
                    "  debugout factRec1024;\n" +
                    "  fact1024 init := fact1024(n);\n" +
                    "  debugout fact1024;\n" +
                    "  fact64 init := fact64(n);\n" +
                    "  debugout fact64;\n" +
                    "  fact32 init := fact32(n);\n" +
                    "  debugout fact32;\n" +
                    "\n" +
                    "  allEqual init :=\n" +
                    "    factRec1024 = fact1024 /\\? fact1024 = fact64 /\\? fact64 = fact32\n" +
                    "endprogram\n");

            System.out.println(bla);
        }
        catch (LexicalError ex){
            System.out.println(ex);
        }
    }
}
