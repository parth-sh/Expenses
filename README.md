# Naming conventions
https://stackoverflow.com/questions/12870537/android-naming-convention

<br>Android Guidelines are a good example of standard naming conventions:
<br>https://github.com/ribot/android-guidelines/blob/master/project_and_code_guidelines.md
### Naming convention for XML files:

```text
activity_<ACTIVITY NAME>.xml - for all activities
dialog_<DIALOG NAME>.xml - for all custom dialogs
row_<LIST_NAME>.xml - for custom row for listview
fragment_<FRAGMENT_NAME>.xml - for all fragments
```
All components for X activity must start with the activity name all component should have prefix or short name like btn for Button For example,name for login activity component should be like following.
```text
activity_login_btn_login
activity_login_et_username
activity_login_et_password
```
Short name of major components:
```text
Button - btn
EditText - et
TextView - tv
ProgressBar - pb
Checkbox - chk
RadioButton - rb
ToggleButton - tb
Spinner - spn
Menu - mnu
ListView - lv
GalleryView - gv
LinearLayout -ll
RelativeLayout - rl
```


# References
https://developer.android.com/codelabs/android-room-with-a-view?hl=en#0

# Dependencies
https://developer.android.com/jetpack/androidx/releases/room
