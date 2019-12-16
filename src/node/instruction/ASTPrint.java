package node.instruction;

import compiler.Compiler;
import compiler.errors.CompileError;
import interpreter.Interpreter;
import interpreter.errors.InterpretationError;
import node.ASTNode;
import typechecker.TypeChecker;
import typechecker.errors.TypeCheckError;
import types.*;
import values.*;

public class ASTPrint implements ASTNode {
  private ASTNode node;

  public ASTPrint(ASTNode node) {
    this.node = node;
  }

  @Override
  public IValue eval(Interpreter interpreter) throws InterpretationError {
    IValue value = node.eval(interpreter);

    if (value instanceof VString || value instanceof VInt || value instanceof VBool)
      System.out.println(value.asString());

    return null;
  }

  @Override
  public void compile(Compiler compiler) throws CompileError {

  }

  @Override
  public IType typeCheck(TypeChecker typeChecker) throws TypeCheckError {
    IType type = node.typeCheck(typeChecker);

    if (type instanceof TString || type instanceof TInt || type instanceof TBool)
      return TVoid.SINGLETON;

    throw new TypeCheckError("Invalid type", "print", type);
  }
}
