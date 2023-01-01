package org.vlang.lang.completion

import com.intellij.codeInsight.completion.CompletionType
import com.intellij.codeInsight.lookup.Lookup.NORMAL_SELECT_CHAR
import com.intellij.testFramework.fixtures.BasePlatformTestCase
import org.intellij.lang.annotations.Language

abstract class CompletionTestBase : BasePlatformTestCase() {
    override fun getTestDataPath() = "src/test/resources/completion"

    protected open fun doTestCompletion(
        txt: String,
        @Language("vlang") after: String,
    ) {
        val newText = txt.replace(CARET, CARET_ORIGINAL)
        myFixture.configureByText("a.v", newText)
        val variants = myFixture.complete(CompletionType.BASIC)
        if (variants != null) {
            myFixture.finishLookup(NORMAL_SELECT_CHAR)
        }
        myFixture.checkResult(after)
    }

    enum class CheckType {
        EQUALS, INCLUDES, EXCLUDES, ORDERED_EQUALS, EMPTY, NOT_EMPTY,
    }

    fun checkEmpty(
        @Language("vlang") txt: String,
        count: Int,
        vararg variants: String,
    ) = doTestVariants(txt, CompletionType.BASIC, count, CheckType.EMPTY, *variants)

    fun checkNotEmpty(
        @Language("vlang") txt: String,
        count: Int,
        vararg variants: String,
    ) = doTestVariants(txt, CompletionType.BASIC, count, CheckType.NOT_EMPTY, *variants)

    fun checkIncludes(
        @Language("vlang") txt: String,
        count: Int,
        vararg variants: String,
    ) = doTestVariants(txt, CompletionType.BASIC, count, CheckType.INCLUDES, *variants)

    fun checkEquals(
        @Language("vlang") txt: String,
        count: Int,
        vararg variants: String,
    ) = doTestVariants(txt, CompletionType.BASIC, count, CheckType.EQUALS, *variants)

    fun checkExcludes(
        @Language("vlang") txt: String,
        count: Int,
        vararg variants: String,
    ) = doTestVariants(txt, CompletionType.BASIC, count, CheckType.EXCLUDES, *variants)

    protected fun doTestVariants(
        @Language("vlang") txt: String,
        type: CompletionType,
        count: Int,
        checkType: CheckType,
        vararg variants: String,
    ) {
        val newText = txt.replace(CARET, CARET_ORIGINAL)
        val isTest = newText.startsWith("// test")
        val fileName = if (isTest) "a_test.v" else "a.v"
        myFixture.configureByText(fileName, newText)
        doTestVariantsInner(type, count, checkType, *variants)
    }

    open fun doTestVariantsInner(type: CompletionType, count: Int, checkType: CheckType, vararg variants: String) {
        myFixture.complete(type, count)
        val stringList = myFixture.lookupElementStrings ?: emptyList()
        assertNotNull(
            """
            
            Possibly the single variant has been completed.
            File after:
            ${myFixture.file.text}
            """.trimIndent(), stringList
        )
        val varList = mutableListOf<String>()
        varList.addAll(variants)

        when (checkType) {
            CheckType.ORDERED_EQUALS -> {
                assertOrderedEquals(stringList, *variants)
            }
            CheckType.EQUALS         -> {
                assertSameElements(stringList, *variants)
            }
            CheckType.INCLUDES       -> {
                varList.removeAll(stringList.toSet())
                assertTrue("Missing variants: $varList", varList.isEmpty())
            }
            CheckType.EXCLUDES       -> {
                varList.retainAll(stringList.toSet())
                assertTrue("Unexpected variants: $varList", varList.isEmpty())
            }
            CheckType.EMPTY          -> {
                assertTrue("Expected empty completion, but got: $stringList", stringList.isEmpty())
            }
            CheckType.NOT_EMPTY      -> {
                assertTrue("Expected non-empty completion, but got: $stringList", stringList.isNotEmpty())
            }
        }
    }

    companion object {
        const val CARET = "/*caret*/"
        const val CARET_ORIGINAL = "<caret>"
    }
}
