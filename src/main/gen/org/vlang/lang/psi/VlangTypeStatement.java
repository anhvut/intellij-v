// This is a generated file. Not intended for manual editing.
package org.vlang.lang.psi;

import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface VlangTypeStatement extends VlangStatement {

  @NotNull
  VlangSymbolVisibility getSymbolVisibility();

  @Nullable
  VlangTypeUnionList getTypeUnionList();

  @Nullable
  PsiElement getAssign();

  @NotNull
  PsiElement getType_();

  @Nullable
  PsiElement getIdentifier();

}
