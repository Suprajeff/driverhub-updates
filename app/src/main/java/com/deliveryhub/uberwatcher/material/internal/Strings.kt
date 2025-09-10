package com.deliveryhub.uberwatcher.material.internal

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.platform.LocalContext
import com.deliveryhub.uberwatcher.R
import kotlin.jvm.JvmInline

@Immutable
@JvmInline
internal value class Strings constructor(val value: Int) { // value is now an R.string.id
    companion object {
        // Map each string identifier to its corresponding R.string.id
        val NavigationMenu: Strings = Strings(R.string.core_material_navigation_menu)
        val CloseDrawer: Strings = Strings(R.string.core_material_close_drawer)
        val CloseRail: Strings = Strings(R.string.core_material_close_rail)
        val CloseSheet: Strings = Strings(R.string.core_material_close_sheet)
        val DefaultErrorMessage: Strings = Strings(R.string.core_material_default_error_message)
        val ExposedDropdownMenu: Strings = Strings(R.string.core_material_exposed_dropdown_menu)
        val SliderRangeStart: Strings = Strings(R.string.core_material_slider_range_start)
        val SliderRangeEnd: Strings = Strings(R.string.core_material_slider_range_end)
        val Dialog: Strings = Strings(R.string.core_material_dialog)
        val MenuExpanded: Strings = Strings(R.string.core_material_menu_expanded)
        val MenuCollapsed: Strings = Strings(R.string.core_material_menu_collapsed)
        val ToggleDropdownMenu: Strings = Strings(R.string.core_material_toggle_dropdown_menu)
        val SnackbarDismiss: Strings = Strings(R.string.core_material_snackbar_dismiss)
        val SnackbarPaneTitle: Strings = Strings(R.string.core_material_snackbar_pane_title)
        val SearchBarSearch: Strings = Strings(R.string.core_material_search_bar_search)
        val SuggestionsAvailable: Strings = Strings(R.string.core_material_suggestions_available)
        val DatePickerTitle: Strings = Strings(R.string.core_material_date_picker_title)
        val DatePickerHeadline: Strings = Strings(R.string.core_material_date_picker_headline)
        val DatePickerYearPickerPaneTitle: Strings = Strings(R.string.core_material_date_picker_year_picker_pane_title)
        val DatePickerSwitchToYearSelection: Strings = Strings(R.string.core_material_date_picker_switch_to_year_selection)
        val DatePickerSwitchToDaySelection: Strings = Strings(R.string.core_material_date_picker_switch_to_day_selection)
        val DatePickerSwitchToNextMonth: Strings = Strings(R.string.core_material_date_picker_switch_to_next_month)
        val DatePickerSwitchToPreviousMonth: Strings = Strings(R.string.core_material_date_picker_switch_to_previous_month)
        val DatePickerNavigateToYearDescription: Strings = Strings(R.string.core_material_date_picker_navigate_to_year_description)
        val DatePickerHeadlineDescription: Strings = Strings(R.string.core_material_date_picker_headline_description)
        val DatePickerNoSelectionDescription: Strings = Strings(R.string.core_material_date_picker_no_selection_description)
        val DatePickerTodayDescription: Strings = Strings(R.string.core_material_date_picker_today_description)
        val DatePickerScrollToShowLaterYears: Strings = Strings(R.string.core_material_date_picker_scroll_to_show_later_years)
        val DatePickerScrollToShowEarlierYears: Strings = Strings(R.string.core_material_date_picker_scroll_to_show_earlier_years)
        val DateInputTitle: Strings = Strings(R.string.core_material_date_input_title)
        val DateInputHeadline: Strings = Strings(R.string.core_material_date_input_headline)
        val DateInputLabel: Strings = Strings(R.string.core_material_date_input_label)
        val DateInputHeadlineDescription: Strings = Strings(R.string.core_material_date_input_headline_description)
        val DateInputNoInputDescription: Strings = Strings(R.string.core_material_date_input_no_input_description)
        val DateInputInvalidNotAllowed: Strings = Strings(R.string.core_material_date_input_invalid_not_allowed)
        val DateInputInvalidForPattern: Strings = Strings(R.string.core_material_date_input_invalid_for_pattern)
        val DateInputInvalidYearRange: Strings = Strings(R.string.core_material_date_input_invalid_year_range)
        val DatePickerSwitchToCalendarMode: Strings = Strings(R.string.core_material_date_picker_switch_to_calendar_mode)
        val DatePickerSwitchToInputMode: Strings = Strings(R.string.core_material_date_picker_switch_to_input_mode)
        val DateRangePickerTitle: Strings = Strings(R.string.core_material_date_range_picker_title)
        val DateRangePickerStartHeadline: Strings = Strings(R.string.core_material_date_range_picker_start_headline)
        val DateRangePickerEndHeadline: Strings = Strings(R.string.core_material_date_range_picker_end_headline)
        val DateRangePickerScrollToShowNextMonth: Strings = Strings(R.string.core_material_date_range_picker_scroll_to_show_next_month)
        val DateRangePickerScrollToShowPreviousMonth: Strings = Strings(R.string.core_material_date_range_picker_scroll_to_show_previous_month)
        val DateRangePickerDayInRange: Strings = Strings(R.string.core_material_date_range_picker_day_in_range)
        val DateRangeInputTitle: Strings = Strings(R.string.core_material_date_range_input_title)
        val DateRangeInputInvalidRangeInput: Strings = Strings(R.string.core_material_date_range_input_invalid_range_input)
        val FloatingToolbarCollapse: Strings = Strings(R.string.core_material_floating_toolbar_collapse)
        val FloatingToolbarExpand: Strings = Strings(R.string.core_material_floating_toolbar_expand)
        val BottomSheetPaneTitle: Strings = Strings(R.string.core_material_bottom_sheet_pane_title)
        val BottomSheetDragHandleDescription: Strings = Strings(R.string.core_material_bottom_sheet_drag_handle_description)
        val BottomSheetPartialExpandDescription: Strings = Strings(R.string.core_material_bottom_sheet_partial_expand_description)
        val BottomSheetDismissDescription: Strings = Strings(R.string.core_material_bottom_sheet_dismiss_description)
        val BottomSheetExpandDescription: Strings = Strings(R.string.core_material_bottom_sheet_expand_description)
        val TooltipLongPressLabel: Strings = Strings(R.string.core_material_tooltip_long_press_label)
        val TimePickerAM: Strings = Strings(R.string.core_material_time_picker_am)
        val TimePickerPM: Strings = Strings(R.string.core_material_time_picker_pm)
        val TimePickerPeriodToggle: Strings = Strings(R.string.core_material_time_picker_period_toggle)
        val TimePickerHourSelection: Strings = Strings(R.string.core_material_time_picker_hour_selection)
        val TimePickerMinuteSelection: Strings = Strings(R.string.core_material_time_picker_minute_selection)
        val TimePickerHourSuffix: Strings = Strings(R.string.core_material_time_picker_hour_suffix)
        val TimePicker24HourSuffix: Strings = Strings(R.string.core_material_time_picker_24_hour_suffix)
        val TimePickerMinuteSuffix: Strings = Strings(R.string.core_material_time_picker_minute_suffix)
        val TimePickerHour: Strings = Strings(R.string.core_material_time_picker_hour)
        val TimePickerMinute: Strings = Strings(R.string.core_material_time_picker_minute)
        val TimePickerHourTextField: Strings = Strings(R.string.core_material_time_picker_hour_text_field)
        val TimePickerMinuteTextField: Strings = Strings(R.string.core_material_time_picker_minute_text_field)
        val TimePickerDialogTitle: Strings = Strings(R.string.core_material_time_picker_dialog_title)
        val TimeInputDialogTitle: Strings = Strings(R.string.core_material_time_input_dialog_title)
        val TimePickerToggleKeyboard: Strings = Strings(R.string.core_material_time_picker_toggle_keyboard)
        val TimePickerToggleTouch: Strings = Strings(R.string.core_material_time_picker_toggle_touch)
        val TooltipPaneDescription: Strings = Strings(R.string.core_material_tooltip_pane_description)
        val WideNavigationRailPaneTitle: Strings = Strings(R.string.core_material_wide_navigation_rail_pane_title)
    }
}

// Implement getString(Strings)
@Composable
@ReadOnlyComposable
internal fun getString(string: Strings): String {
    return LocalContext.current.getString(string.value)
}

// Implement getString(Strings, vararg formatArgs: Any)
@Composable
@ReadOnlyComposable
internal fun getString(string: Strings, vararg formatArgs: Any): String {
    return LocalContext.current.getString(string.value, *formatArgs)
}

// Implement formatString (doesn't need @Composable as it takes a String directly)
internal fun formatString(string: String, vararg formatArgs: Any?): String {
    // This assumes the 'string' parameter is already a resolved localized string
    // potentially containing format specifiers like %s, %d etc.
    return String.format(string, *formatArgs)
}