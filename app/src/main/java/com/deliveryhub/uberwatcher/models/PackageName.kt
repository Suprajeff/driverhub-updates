package com.deliveryhub.uberwatcher.models

enum class PackageName(val packageName: String) {
    DELIVEROO("com.deliveroo.driverapp"),
    UBER("com.ubercab.driver");

    companion object {
        fun from(value: String): PackageName? =
            entries.find { it.packageName == value }
    }

}