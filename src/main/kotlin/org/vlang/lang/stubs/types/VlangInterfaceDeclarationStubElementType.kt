package org.vlang.lang.stubs.types

import com.intellij.psi.stubs.StubElement
import com.intellij.psi.stubs.StubInputStream
import com.intellij.psi.stubs.StubOutputStream
import com.intellij.util.ArrayFactory
import org.vlang.lang.psi.VlangInterfaceDeclaration
import org.vlang.lang.psi.impl.VlangInterfaceDeclarationImpl
import org.vlang.lang.stubs.VlangInterfaceDeclarationStub

class VlangInterfaceDeclarationStubElementType(name: String) :
    VlangNamedStubElementType<VlangInterfaceDeclarationStub, VlangInterfaceDeclaration>(name) {

    override fun createPsi(stub: VlangInterfaceDeclarationStub) = VlangInterfaceDeclarationImpl(stub, this)

    override fun createStub(psi: VlangInterfaceDeclaration, parentStub: StubElement<*>?) =
        VlangInterfaceDeclarationStub(parentStub, this, psi.name, psi.isPublic(), psi.isGlobal())

    override fun serialize(stub: VlangInterfaceDeclarationStub, dataStream: StubOutputStream) {
        dataStream.writeName(stub.name)
        dataStream.writeBoolean(stub.isPublic)
        dataStream.writeBoolean(stub.isGlobal)
    }

    override fun deserialize(dataStream: StubInputStream, parentStub: StubElement<*>?): VlangInterfaceDeclarationStub {
        return VlangInterfaceDeclarationStub(
            parentStub,
            this,
            dataStream.readName(),
            dataStream.readBoolean(),
            dataStream.readBoolean()
        )
    }

//    override fun getExtraIndexKeys() = EXTRA_KEYS

    companion object {
//        private val EXTRA_KEYS: ArrayList<StubIndexKey<String, out VlangNamedElement>> =
//            ContainerUtil.newArrayList(VlangInterfaceIndex.KEY)

        private val EMPTY_ARRAY: Array<VlangInterfaceDeclaration?> = arrayOfNulls(0)
        val ARRAY_FACTORY = ArrayFactory<VlangInterfaceDeclaration> { count: Int ->
            if (count == 0) EMPTY_ARRAY else arrayOfNulls<VlangInterfaceDeclaration>(count)
        }
    }
}