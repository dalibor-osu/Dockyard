package io.github.dockyardmc.protocol

import io.netty.buffer.ByteBuf

fun <T> ByteBuf.writeOptional(item: T?, kFunction2: (ByteBuf, T) -> ByteBuf) {
    val isPresent = item != null
    this.writeBoolean(isPresent)
    if (isPresent) {
        kFunction2.invoke(this, item!!)
    }
}

fun <T> ByteBuf.readOptional(unit: (ByteBuf) -> T): T? {
    val present = this.readBoolean()
    return if(!present) null else unit.invoke(this)
}