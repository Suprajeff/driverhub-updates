package com.deliveryhub.uberwatcher.models.types.deliveroo

enum class DeliverooNotificationType(
    val matcher: (String) -> Boolean,
    val extractor: (String) -> String? = { null } // default = nothing
) {
    PICK_UP_ORDER({ it.equals("Pick up order", ignoreCase = true) }),
    GO_TO_RESTAURANT(
        matcher = { it.startsWith("Go to restaurant", ignoreCase = true) },
        extractor = { title ->
            // Extract restaurant name after dash
            title.substringAfter("-", "").trim().ifBlank { null }
        }
    ),
    COLLECT_ORDER(
        matcher = { Regex("^Collect order #\\d{4}$").matches(it) },
        extractor = { title ->
            Regex("\\d{4}").find(title)?.value // extract 4-digit order number
        }
    ),
    GO_TO_CUSTOMER({ it.equals("Go to customer", ignoreCase = true) }),
    ORDER_NO_LONGER_AVAILABLE({ it.equals("Order no longer available", ignoreCase = true) }),
    EMPTY_TITLE({ it.isBlank() || it.equals("(no title)", ignoreCase = true) }),
    ACCEPT_ANOTHER_ORDER({ it.equals("Accept another order?", ignoreCase = true) }),
    RIDER({ it.equals("Rider", ignoreCase = true) }),
    NEW_TIP(
        matcher = { Regex("""^New £([\d.]+) tip from (.+)!$""").matches(it) },
        extractor = { title ->
            val regex = Regex("""^New £([\d.]+) tip from (.+)!$""")
            regex.find(title)?.let { match ->
                val amount = match.groupValues[1] // "2.39"
                val customer = match.groupValues[2] // "Hetty"
                """{"amount":"$amount","customer":"$customer"}""" // return JSON-like string
            }
        }
    ),
    UNKNOWN({ true });

    companion object {
        fun fromTitle(title: String): DeliverooNotificationType {
            return entries.firstOrNull { it.matcher(title.trim()) } ?: UNKNOWN
        }

        fun extractInfo(title: String): String? {
            val type = fromTitle(title)
            return type.extractor(title.trim())
        }
    }
}
