package com.deliveryhub.uberwatcher.network

import com.deliveryhub.uberwatcher.models.PackageName
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object PackageNameSerializer : KSerializer<PackageName> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("PackageName", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: PackageName) {
        encoder.encodeString(value.packageName)
    }

    override fun deserialize(decoder: Decoder): PackageName {
        val packageName = decoder.decodeString()
        return PackageName.entries.firstOrNull { it.packageName == packageName }
            ?: throw IllegalArgumentException("Unknown package name: $packageName")
    }
}
