package node.op;

import compiler.ByteCode;
import compiler.Compiler;
import env.Environment;
import errors.compiler.CompilerException;
import errors.env.EnvironmentException;
import errors.eval.DivideByZeroException;
import errors.eval.EvaluationException;
import node.ASTNode;

public class ASTDiv extends ASTOp {
  public ASTDiv(ASTNode left, ASTNode right) {
    super(left, right);
  }

  @Override
  public int eval(Environment env) throws EvaluationException, EnvironmentException {
    int v1 = getLeft().eval(env);
    int v2 = getRight().eval(env);

    if (v2 == 0)
      throw new DivideByZeroException();

    return v1 / v2;
  }

  @Override
  public void compile(Compiler compiler) throws CompilerException {
    getLeft().compile(compiler);
    getRight().compile(compiler);

    compiler.emit(ByteCode.DIV);
  }
}
