# Cash App

A decade of Movies app

## Installation
Clone this repository and import into **Android Studio**
```bash
git clone git@github.com:Mahmoud20-sudo/OrangeTask.git
```

## Configuration
### Keystores:
Create `app/keystore` with the following info:
```gradle
ext.key_alias='key'
ext.key_password='MyCashTask'
ext.store_password='MyCashTask'

## About

Orange task a local list of proudct,food,...etc that should be displayed in the main screen.
Then user shall signup or login if he alread has an accpuntbefore acccess main page.
Main page contains:
  ● User Name
  ● User Address
  ● Scrollable list of categogries
  ● Scrollable list of popular & trendy selleres

## Features

The android design based on :
  ● MVVM
  ● Unit tesing 
  ● Integeration tesing
  ● UI testing
  ● Needs no special permissions on Android 6.0+.


## Generating signed APK
From Android Studio:
1. ***Build*** menu
2. ***Generate Signed APK...***
3. Fill in the keystore information *(you only need to do this once manually and then let Android Studio remember it)*


## Permissions

On Android versions prior to Android 6.0, wallabag requires the following permissions:
- Full Network Access.


## Contributing

1. Fork it
2. Create your feature branch (git checkout -b my-new-feature)
3. Commit your changes (git commit -m 'Add some feature')
4. Run the linter (ruby lint.rb').
5. Push your branch (git push origin my-new-feature)
6. Create a new Pull Request
