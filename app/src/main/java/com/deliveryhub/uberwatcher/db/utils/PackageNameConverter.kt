package com.deliveryhub.uberwatcher.db.utils

import androidx.room.TypeConverter
import com.deliveryhub.uberwatcher.models.PackageName

class PackageNameConverter {
    @TypeConverter
    fun fromDeliveryApp(app: PackageName): String {
        return app.packageName
    }

    @TypeConverter
    fun toDeliveryApp(value: String): PackageName {
        return PackageName.entries.firstOrNull { it.packageName == value }
            ?: throw IllegalArgumentException("Unknown package name: $value")
    }
}
