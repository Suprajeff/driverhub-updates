package com.deliveryhub.uberwatcher.models.types.uber

enum class UberNotificationType(
    val matcher: (String) -> Boolean
) {
    ONLINE({ it.equals("You are currently online.", ignoreCase = true) }),
    REQUEST({ it.equals("Uber request", ignoreCase = true) }),
    ERROR({ it.equals("Something went wrong", ignoreCase = true) }),
    UNKNOWN({ it.isBlank() || it.equals("(no title)", ignoreCase = true) });

    companion object {
        fun fromTitle(title: String): UberNotificationType {
            return entries.firstOrNull { it.matcher(title) } ?: UNKNOWN
        }
    }
}
