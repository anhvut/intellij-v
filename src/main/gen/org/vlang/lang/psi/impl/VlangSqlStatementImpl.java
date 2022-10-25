// This is a generated file. Not intended for manual editing.
package org.vlang.lang.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;
import org.vlang.lang.psi.VlangPsiTreeUtil;
import org.vlang.lang.psi.VlangSqlExpression;
import org.vlang.lang.psi.VlangSqlStatement;
import org.vlang.lang.psi.VlangVisitor;

public class VlangSqlStatementImpl extends VlangSqlBlockStatementImpl implements VlangSqlStatement {

  public VlangSqlStatementImpl(@NotNull ASTNode node) {
    super(node);
  }

  @Override
  public void accept(@NotNull VlangVisitor visitor) {
    visitor.visitSqlStatement(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof VlangVisitor) accept((VlangVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public VlangSqlExpression getSqlExpression() {
    return notNullChild(VlangPsiTreeUtil.getChildOfType(this, VlangSqlExpression.class));
  }

}
