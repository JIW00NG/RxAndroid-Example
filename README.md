# Rx

![Rx_Logo](https://reactivex.io/assets/Rx_Logo_S.png)

---

RxJava, RxAndroid 등 최근에 비동기 프로그래밍을 할 때 좋다는데 뭐가 그리 좋아서 다들 사용하는지 알아보고싶어서 공부를 하기 시작했다.

우선 Rx를 검색하면 자주 나오는 토픽으로는 선언형 프로그래밍, 반응형 프로그래밍과 같은 패러다임이 나오고 Observable, stream에 대한 설명들이 나온다.

공식홈페이지를 보더라도 Rx는
>An API for asynchronous programming with observable streams<br>
>관찰 가능한 스트림을 사용하는 비동기 프로그래밍 을 위한 API

라고 명시되어 있는데 여러번 읽어보고 검색을 하면서 공부를 하였지만 아직도 확실하지 않아 내 기준으로 정리를 시작하려 한다.

Rx에 대한 정확한 이해를 위해서 나오게된 배경, 검색했을 때 함께 나오는 토픽인 선언형 프로그래밍, 반응형 프로그래밍을 알아보고 Rx에서 사용하는 Observable, stream, observer, subscribe에 대해 알아보도록 하자. (해당 내용들은 간단하게 설명하고 넘어가도록 하겠다.)

## Rx가 나오게된 배경:

>Rx는 MS의 조직에서 다양한 기술을 하나의 플랫폼 안에서 실행할 수 있는 환경을 제공함으로써 기술 습득 비용을 줄이는 것을 목표로한 Volta라는 프로젝트에서 시작되었다. Volta에서 떨어져 나온 Reactive Framework가 2009년에 Reactive Extensions로 이름을 바꾸고 .net에서 사용하기 위해 출시되었다. 처음에는 오픈소스가 아니였다고 한다. 그 후 JS에서 사용하기 위해 RxJS가 2012년 11월에 오픈소스로 공개되기 시작되었다. 그 후 넷플릭스에서  RxJava를 오픈소스 형태로 공개하였다.


## 선언형 프로그래밍:
>우선 선언형 프로그래밍은 '무엇을 어떻게'라는 초점으로 접근하는 명령형 프로그래밍과 반대로 '무엇'에만 초점을 둔 프로그래밍을 의미한다.

## 반응형 프로그래밍:
>인터넷 환경이 발달하고 멀티코어 프로세서의 대안을 찾기 시작하면서 동시성 프로그래미이 중요해졌다고 한다. 요구사항 또한 점점 까다로워지기 때문에 해당 요구사항을 만족하기 위해 UI는 더 빠른 반응속도가 필요해지고 안정성은 더 중요한 문제가 되었다. 이러한 문제들을 해결하기 위해 데이터를 흐름으로 보고 비동기적으로 처리하여 반응형으로 만든 것이 Reactive이다.

>기존에 개발자가 작성한 코드 순서대로 실행되는 방식에서 데이터의 변화에 따라 이벤트를 받게되면 동작하도록 하는 방식을 의미한다.

외부에서 들어오는 요청에 계속해서 응답하는 것으로 계속 응답을 한다는 것은 반응한다는 뜻이고 이때 반응은 2가지로 나눌 수 있다.

    1. 자극이 밖에서 안으로 흐른다.
    2. 자극이 있어야만 반응하는 수동성을 갖는다.

>여기서 프로그램이 외부와 상호작용하는 방식을 거꾸로 뒤집어서 수동적으로 반응성을 획득하는 일 그것이 reactive의 목적이다.

>우리에게 익숙한 외부 환경과 커뮤니케이션하는 방법인 __pull scenario__ 는프로그램이 직접 제어의 흐름을 통제하지만 이를 뒤집은 __push scenario__ 는 환경이 프로그램 안으로 요청을 넣고 외부 환경에 명령을 하면 응답이 올때까지 기다리며 외부에서 응답이 오면 그때 반응하는 것이다. __push scenario__ 의 장점은 제어의 흐름을 통제할 권한을 외부 환경으로 넘김으로써 응답의 대기 시간을 줄일 수 있다. 이때문에 비동기 처리에 유리하게 된다. 응답을 요청하고 요청을 기다리는 동안 자원의 점유가 일어나지 않아 추가적인 다른 작업을 수행할 수 있다.

## Rx 요소:
### Observable
> Observable은 프로그램이 연산을 수행하는 관점을 뒤집음으로써 비동기 처리에 유리한 구조를 만들 수 있는 토대를 제공한다. Rx에서 사용하는 data source와 data stream을 생성하는 Observable은 observer의 subscribe 대상자를 의미하는데, stream은 next, error, complete 같은 이벤트를 발생시켰을 때 subscribe하고 있던 observer가 미리 지정해 놓은 작업을 수행하도록 해준다.

### Operator
>Observable stream의 데이터 변환, Observable을 생성과 같은 Observable에서 할 수 있는 많은 기능을 제공한다.

### stream
>데이터의 흐름으로 onNext, onError, onComplete 등의 이벤트를 발생시킨다.

### observer
>Observable stream을 subscribe하고 stream에서 특정 이벤트가 온다면 stream의 데이터를 이용하여 정해진 작업을 수행한다.

### subscribe
>observer가 observable stream의 이벤트를 받을 수 있도록 연결해주는 Operator
>Observer가 Observable stream을 구독한다.

## Rx 동작 방식:
앞서 설명한 Rx 요소들로 인해 유추할 수 있듯이 Rx는 Observable stream을 observer가 subscribe하여 특정 이벤트가 들어오면 미리 정의해둔 작업을 수행하고 반을을 하는 것이다.

![](https://user-images.githubusercontent.com/56468120/145357654-74a062b0-de0d-45f6-8a1f-d8147de2834a.png)

    1. Observable이 데이터와 데이터 흐름인 stream을 생성하고,
    2. observer가 Observable stream을 subscribe하면서 특정 이벤트가 들어오기를 기다리면서
    3. 특정 이벤트가 들어오면 정해진 작업을 수행하는 것으로 반응한다.

## Rx 특징:
__공식 홈페이지에 따르면 Rx의 특징은 다음과 같다.__

- Create: 이벤트 스트림과 데이터 스트림의 생성이 쉽다.
- Combine: query와 같은 operator를 사용한 stream 구성 및 변환이 자유롭다.
- Listen: 반응형이라는 특징에 맞게 observable stream을 subscribe하여 side effect 수행이 가능하다.

__또한 추가적인 특징은 다음과 같다.__
- Observable stream을 통해 복잡한 상태 저장 프로그램을 피할 수 있다.
- 복잡한 코드를 몇줄의 코드로 표현 가능하다(단, 때에 따라 Rx의 코드가 더 복잡해지기도 함)
- 비동기 처리에서 오류처리를 위한 적절한 메커니즘을 제공한다.
- 저수준 스레딩, 동기화 및 동시성 문제 추상화가 가능하다.(RxAndroid에 경우 스레딩 관리하기 쉽도록 Scheduler가 존재)

>지금까지 Rx가 무엇이고 Rx와 관련된 패러다임에 대한 정의, 그리고 Rx에서 자주 쓰이는 Observable, Observer, stream, subscribe과 해당 요소들을 이용한 Rx의 동작 방식을 알아보았다. 그렇다면 Rx를 사용하는데에서 오는 이점은 무엇인지 알아보자.

## Rx의 장점:
- Rx는 비동기적으로 데이터를 가져올 때 stream을 사용하기 때문에 callback을 처리할 필요 없어 편리하다.
- shceduler가 있어 thread 관리가 쉬워지고 동시성 문제를 해결할 수 있다.
- 여러 언어를 제공하고 동작방식이 비슷하기 때문에 한번 익숙해진다면 다른언어에서도 편하게 사용할 수 있다.
- operator로 인해 여러 확장에 많은 편리함을 준다.
- OpenSource이다.
- stream을 이용하여 처리하기 때문에 callback을 따로 처리할 필요가 없어진다.
- 위 장점들로 인해 코드가 줄어들고 가독성이 높아진다.
- onError같은 이벤트로 비동기 오류처리에 대한 오류 처리를 제공한다.

## Rx의 단점:
- 우선 가장 큰 단점으로 Rx는 학습하기가 쉽지 않았다. 공식홈페이지를 보면서 개념을 정리하였지만 코드에서 어떻게 사용하는지 추가로 학습이 필요하고 처음에 개념을 이해하는데도 오랜 시간이 걸렸다.
- 또한 많은 수의 operator가 있지만 몇몇개의 operator는 언제 사용하면 좋다 라는 내용이 있지만 그렇지 않은 operator에 경우 해당 operator에 대한 설명이 부족해 이해가 쉽지 않았다.
- 코드의 가독성이 증가하고 코드가 짧아지기는 하지만 operator를 모르는사람이 코드를 봤을 때 이게 무슨 코드인지 이해하기가 힘들다.
디버그가 힘들다.

>위에서 Rx에 대해 공부해보았다. 그렇다면 Rx는 어떻게 사용해야할까?
우선 marbles를 해석하는 방법을 배우고 RxAndroid/RxJava를 예를 들어 사용법을 알아보도록 하자.

>Rx를 사용하기 위해서 위의 내용도 중요하지만 직접 사용하기 위해 Operator에 대해서 알아야 한다. Operator란 Observable data stream을 생성하는 것 부터, stream에 있는 data를 변환하는 작업, observer가 observable을 구독하는 작업 등 수많은 Operator가 존재한다.

## operator:
>Rx에서 빼놓을 수 없는 것이 Operator인데 공통적으로 제공되는 연산자가 있으며 언어별로 조금씩 다른 연산자들을 제공한다. 우리는 이러한 Operator를 이용하여 앞에서 설명한 Observable을 생성하고 Observer가 Observable을 subscribe하거나 Thread를 변경하거나 Observable의 데이터들을 변환하는 등 여러가지 작업을 수행할 수 있다.

>거의 대부분의 연산자는 Observable 상에서 동작하고 dispose 같은 Operator를 제외한다면 대부분 Observable을 리턴한다. 그렇기 때문에 Rx는 연산자 체인을 제공한다. 사용 예제는 뒤에서 설명하도록 하겠다.

>공식 홈페이지에서 Operator 설명을 가면 각각의 Operator는 marble 이라는 그림으로 Operator를 설명하고 있다.(없는것도 존재) Operator의 수는 정말 많기 때문에 많이 쓰이는 operator를 설명하고 operator의 marble을 해석하는 방법을 설명하여 추후 그 방법으로 원하는 operator를 찾을 수 있도록 하겠다.

>marble은 간단하게 시간을 의미하는 세로 화살표 선 그리고 Observable의 종료를 의미하는 세로선, 데이터를 의미하는 marble이 있다. 또한 에러상황을 의미하는 X표시가 있으며 이정도를 알게 되면 대부분의 operator의 marble을 해석할 수 있을 것이다. 시간선에 marble이 존재하고 중간에 operator를 통해 특정 작업을 수행하면 아래 리턴으로 결과인 Observable이 나오거나, 데이터소스만을 이용해 특정 operator를 이용하여 Observable을 생성하는 등의 작업이 가능하다.

![](https://user-images.githubusercontent.com/56468120/145358212-5992268d-5ae1-4e1c-851a-69a0fb0661a4.png)

__간단한 예제로 filter operator의 marble을 보자.__
data stream에 1, 60, 5, 22, 30, 2가 순서대로 들어온다.
이때 filter(x -> x > 10) operator를 사용한다면
data stream에 10보다 큰 데이터들인 60, 22, 30 만이 들어있는 Observable stream을 리턴하게 된다.


![](https://user-images.githubusercontent.com/56468120/145358151-3a7e89c6-8d04-42fb-9c4b-988e0f752fef.png)

__다른 operator를 보자.__

__merge를 예를 들자면__
data stream에 100, 80, 50, 40, 20이 있는 observable이 있고, 1, 1이 있는 Observable이 있다고 하자 observable의 이름을 각각 observableA, observableB라고 한다면 merge(observableA, observableB)를 통해 data stream을 합친 1, 100, 80, 1, 60, 40, 20 데이터를 갖는 Observable을 얻을 수 있다.

>이렇게 marble 해석 방법을 이용하여 원하는 operator를 찾아서 적절한 시기에 사용할 수 있기를 바란다.


## Rx 사용예시 (RxAndroid/RxJava):


---
## 출처:
- __Rx 공식 홈페이지 : https://reactivex.io/__
- __Rx Marbles 설명 페이지 : https://rxmarbles.com/__
- __Rx의 기원에 대해 잘 정리되어 있음 : https://huns.me/development/2051__
- __https://choheeis.github.io/newblog//articles/2020-02/RxJavaObservable__
- __Rx를 사용하는 이유 : https://hs5555.tistory.com/96__

- __선언형 프로그래밍 wikipedia : https://ko.wikipedia.org/wiki/선언형_프로그래밍__
- __ReactiveX wikipedia : https://en.wikipedia.org/wiki/ReactiveX__
