package node.types;

import compiler.ByteCode;
import compiler.Compiler;
import env.Environment;
import errors.compiler.CompilerException;
import errors.eval.EvaluationException;
import errors.eval.UnexpectedTypeException;
import node.ASTNode;
import value.IValue;
import value.VInt;

public class ASTNum implements ASTNode {
  private IValue val;

  public ASTNum(IValue val) throws EvaluationException {
    if (!(val instanceof VInt))
      throw new UnexpectedTypeException(val.type(), "int");

    this.val = val;
  }

  @Override
  public IValue eval(Environment env) {
    return val;
  }

  @Override
  public void compile(Compiler compiler) throws CompilerException {
    compiler.emit(ByteCode.PUSH, String.valueOf(val));
  }
}
