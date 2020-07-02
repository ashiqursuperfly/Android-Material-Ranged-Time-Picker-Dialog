#### material-ranged-time-picker-dialog
<img src="ss/androidlib.jpg" height="80" width="80">

[![License: MIT](https://img.shields.io/badge/License-MIT-green.svg)](https://opensource.org/licenses/MIT)
[![](https://jitpack.io/v/ashiqursuperfly/android-ranged-time-picker-dialog.svg)](https://jitpack.io/#ashiqursuperfly/android-ranged-time-picker-dialog)

#### Setup
- Just Make sure you are using material components in your default AppTheme i.e your AppTheme inherits from a material theme
- In your app-level build.gradle file: (make sure you use the latest release version, this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).)
```groovy
implementation 'com.github.ashiqursuperfly:android-ranged-time-picker-dialog:x.x.x'
```
#### Usage
```kotlin
val dialog = TimeRangePickerDialog(
    startLabel = "START",
    endLabel = "END",
    is24HourView = false,
    onPickedTimeTime = object : TimeRangePickerDialog.OnPickedTimeRange {
        override fun onPickedTime(
            startHour: Int,
            startMinute: Int,
            endHour: Int,
            endMinute: Int
        ) {
            val str = "${startHour}:${startMinute} ${endHour}:${endMinute}"
            text_view.text = str
        }

    }
)
// now you can apply slight modifications to the dialog like you can do for any dialog e.g:
d.isCancelable = false
// finally just show the dialog when you want with
d.show(supportFragmentManager, "my-dialog-tag-string")
```

#### Contributing
- Create an issue with appropriate description (explain which behaviour you want to change)
- Add either one of the following labels: bug, enhancement, documentation, question
- Prefix the branch name with the issue label and issue number like so: label/number-branchName e.g: bug/3-Hour24ViewNotVisible 
- send a PR