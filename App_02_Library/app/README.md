#DataBinding Activity
* 기존의 Activity에서 *.xml 에 정의된 Component(view)를 사용하기 위해서는 각 View를 객체로 선언하고 findByViewId() 함수를 사용하여
객체와 Component를 연결하는 작업이 필요했다

* Rollipop(5.0)버전부터 findByViewID()를 사용하지 않고 Component를 사용할 수 있게 되었다 이때 도입된 개념이 DataBinding이다

* 원래 DataBinding 개념은 View Component와 데이터를 연동하기 쉽도록 하기위해 도입되었다.
* 이 개념이 확장되면서 findByViewId() 를 사용하지 않고 Component를 쓸수있도록 만들어졌다.
* 초기에 도입된 DataBinding과 현재(2021년) 사용하는 방법에는 차이가 있지만 만드는 방법, 프로젝트 활용방법은 같다

## app build.gradle에 속성 추가
* app build.gradle의 android 항복에 다음 속성을 추가한다
  buildFeatures {
          iewBinding true
  }

* old Android에서는 직접 xml에 추가해주었어야 한다

# Fragment Activity`
* 초기 Andorid에서는 모든 화면을 Activity 단위로 만들고 사용했다
* Activity는 많은 Resource(메인 메모리)를 사용하게 된다.
* App에 화면이 많으면 실행이 느려지거나 오류가 발생하는 경우가 생기게 된다
* 이런 문제를 해결하지 위해 Framnet라는 개념을 도입하게 된다.
* Fragment를 도입하여 작은 화면을 구현하고, 필요할 경우 바꿔가면서 보여주는 개념이 만들어졌다
* Fragment는 동적인 작은 Activity 라고 할수 있다.

# Retrofit 을 사용한 Open API 핸들링
* OpenAPI를 사용하여 다른 서버에서 제공하는 데이터를 가져와 사용하는 경우 데이터를 수신한 후 parsing하는과정에 많이 사용하는 툴
* 수신되는 데이터 구조에 맞는 VO(DTO)를 만들고, 데이터를 수신한 후 자동으로 Mapping 할수 있다.
* 데이터를 수신한 후 GSON 라ㅣㅇ브러리를 사용하여 내부에서 자동 parsing이 이루어 진다
* Retrofit을 사용하기 위해 retrofit과 converter-gson  Dependency를 설정한다

* API 요청과 Mapping을 하기위한 interface를 선언해주어야 한다.
*