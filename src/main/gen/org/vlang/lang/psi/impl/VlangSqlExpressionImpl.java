// This is a generated file. Not intended for manual editing.
package org.vlang.lang.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.ResolveState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.vlang.lang.psi.*;

public class VlangSqlExpressionImpl extends VlangExpressionImpl implements VlangSqlExpression {

  public VlangSqlExpressionImpl(@NotNull ASTNode node) {
    super(node);
  }

  @Override
  public void accept(@NotNull VlangVisitor visitor) {
    visitor.visitSqlExpression(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof VlangVisitor) accept((VlangVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public VlangExpression getExpression() {
    return VlangPsiTreeUtil.getChildOfType(this, VlangExpression.class);
  }

  @Override
  @Nullable
  public VlangSqlBlock getSqlBlock() {
    return VlangPsiTreeUtil.getChildOfType(this, VlangSqlBlock.class);
  }

  @Override
  @Nullable
  public VlangType getType(@Nullable ResolveState context) {
    return VlangPsiImplUtil.getType(this, context);
  }

}
