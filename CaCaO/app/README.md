# Android App의 ActionBar Customizing
* Android App의 기본 Activity는 모두가 자동으로 구현되는 ActionBar를 가지고 있다. 하지만 자동으로 생성되는 ActionBar 는 Cusomizing하기가 까다롭다
* 그렇기 때문에 기존 ActionBar를 숨기고 새로운 ActionBar 를 만들어 Customizing을 수행한다

## 작업 순서
1. MainActivity에 NoActionBar Theme를 적용하여 자동 ActionBar를 감춘다.
2. ToolBar View Component를 *.xml 파일에 추가하여 둔다
3. ToolBar에 설정된 기본 Theme를 적용하기
4. MainActivity의 onCreate() method에서 설정한 ToolBar가 보이도록 설정

# Preference 만들기
* 어플에서 사용하는 '환경설정'
* 어플이 실행되는데 필요한 여러가지 값들을 세팅하고, 그 세팅된 값을 유지하기 위한 정책
* settings Activity를 추가 한뒤 root_preference.xml 파일에 필요한 항목들을 설정한뒤 Actiity를 열어서 값을 셋팅하면 App 전체에서 해당값이 적용된다
* setting 된 값이 필요한 곳에서 SharePreference interface를 사용하여 객체를 선언하면
PreferenceManager.getdefaultSharedPreferences() method 를 실행하여 값을 읽어들인다.
* 객체로부터 getString() 등의 method를 사용하여 각각 세팅된 개별 변수값을 추출하여 적용할 수 있다.