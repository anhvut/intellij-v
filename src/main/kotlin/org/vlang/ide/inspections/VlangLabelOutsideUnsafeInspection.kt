package org.vlang.ide.inspections

import com.intellij.codeInspection.*
import com.intellij.psi.PsiElementVisitor
import org.vlang.lang.psi.*
import org.vlang.lang.utils.UnsafeUtil

class VlangLabelOutsideUnsafeInspection : VlangBaseInspection() {
    override fun buildVisitor(holder: ProblemsHolder, isOnTheFly: Boolean): PsiElementVisitor {
        return object : VlangVisitor() {
            override fun visitGotoStatement(goto: VlangGotoStatement) {
                if (UnsafeUtil.insideUnsafe(goto)) return

                holder.registerProblem(
                    goto, "Go to statement should be inside unsafe block",
                    ProblemHighlightType.GENERIC_ERROR_OR_WARNING
                )
            }
        }
    }
}
