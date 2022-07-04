// This is a generated file. Not intended for manual editing.
package org.vlang.lang.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.vlang.lang.psi.VlangSymbolMutability;
import org.vlang.lang.psi.VlangVisitor;

import static org.vlang.lang.VlangTypes.MUT;

public class VlangSymbolMutabilityImpl extends VlangCompositeElementImpl implements VlangSymbolMutability {

  public VlangSymbolMutabilityImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull VlangVisitor visitor) {
    visitor.visitSymbolMutability(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof VlangVisitor) accept((VlangVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public PsiElement getMut() {
    return findChildByType(MUT);
  }

}