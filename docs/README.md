# 기능 요구사항

## 1. 로또 구입 금액 입력
- [x] `1.1` 구입 금액 입력 안내문을 출력한다.
- [x] `1.2` 구입 금액을 입력 받는다.
  - [x] `1.2.1` **[ERROR]** 입력 금액이 정수가 아닌 경우 예외를 발생시킨다. 
  - [x] `1.2.2` **[ERROR]** 1,000원으로 나누어 떨어지지 않는 경우 예외를 발생시킨다.
  - [x] `1.2.3` 예외를 처리하여 다시 입력 받는다.

## 2. 로또 구입 내역 출력
- [x] `2.1` 구입 내역 안내문을 출력한다.
- [x] `2.2` 입력된 금액만큼 로또를 생성한다.
  - [x] `2.2.1` **[ERROR]** 로또 생성 시 로또의 크기가 유효한 크기/형식이 아니면 예외를 발생시킨다.
  - [x] `2.2.2` **[ERROR]** 로또 생성 시 하나의 숫자라도 중복된 숫자가 있을 시 예외를 발생시킨다.
  - [x] `2.2.3` **[ERROR]** 로또 생성 시 하나의 숫자라도 유효한 범위 내가 아니면 예외를 발생시킨다.
- [x] `2.3` 구입 개수 안내문을 출력한다. 
- [x] `2.4` 생성된 로또를 지정된 포맷으로 출력한다.

## 3. 당첨 번호 입력
- [x] `3.1` 당첨 번호 입력 안내문을 출력한다.
- [x] `3.2` 쉼표(`,`)로 구분하여 당첨 번호를 입력 받는다.
  - [x] `3.2.1` **[ERROR]** 당첨 로또 생성 시 로또의 크기가 유효한 크기/형식이 아니면 예외를 발생시킨다. 
  - [x] `3.2.2` **[ERROR]** 당첨 로또 생성 시 하나의 숫자라도 중복된 숫자가 있을 시 예외를 발생시킨다.
  - [x] `3.2.3` **[ERROR]** 당첨 로또 생성 시 하나의 숫자라도 유효한 범위 내가 아니면 예외를 발생시킨다.
  - [x] `3.2.4` 예외를 처리하여 다시 입력 받는다.
- [x] `3.3` 보너스 번호 입력 안내문을 출력한다.
- [x] `3.4` 보너스 번호를 입력 받는다.
  - [x] `3.4.1` **[ERROR]** 보너스 번호 생성 시 정수가 아닌 경우 예외를 발생시킨다.
  - [x] `3.4.2` **[ERROR]** 보너스 번호 생성 시 유효한 범위 내가 아니면 예외를 발생시킨다.
  - [x] `3.4.3` **[ERROR]** 보너스 번호 생성 시 하나의 숫자라도 로또 숫자와 중복 시 예외를 발생시킨다.
  - [x] `3.4.4` 예외를 처리하여 다시 입력 받는다.

## 4. 당첨 결과 출력
- [x] `4.1` 당첨 통계 안내문을 출력한다.
- [x] `4.2` 일치하는 수만큼 당첨 통계를 출력한다.
  - [x] `4.2.1` 보너스 번호에 따른 2, 3등을 구분할 수 있다. 
- [x] `4.3` 수익률을 출력한다.

---
# 클래스 구조

`lotto`
- `controller` : **MVC**의 **Controller**
  - `BuyerController`
  - `LottoController`
  - `MainController` : `LottoController`, `BuyerController`를 호출하며 run()을 통한 메인 로직 구현 
- `domain` : **MVC**의 **Model**의 **Domain**
  - `constants` : 상수 패키지
    - `LottoConfig` : 로또 게임과 관련된 설정을 저장하는 enum
    - `LottoStatiscticsConstants` : 로또 당첨 통계량 생성 시 쓰이는 상수를 저장하는 enum
    - `LottoStatisticsContent` : 로또 당첨 통계량 형식을 상수화한 enum
  - `Bonus` : 보너스 번호 객체
  - `Buyer` : 구매금액과 구매 개수를 갖고 있는 구매자
  - `Lotto` : 구매자의 정보로 생성되는 로또
  - `Lottos` : 로또 일급컬렉션
  - `LottoStatistics` : 로또 당첨 통계량 객체
  - `NumberChecker` : 로또, 보너스, 구매자의 정보를 통해 로또 번호를 체킹하는 객체
- `exception`
  - `constants` 
    - `ErrorMessage` : 로또 예외 관련 메시지 상수 enum
  - `LottoException` : 커스텀 예외
- `service` **MVC**의 **Model**의 **Service**
  - `BuyerService` : `Buyer`과 관련된 비즈니스 로직을 구현한 서비스
  - `LottoService` : `Lotto`와 관련된 비즈니스 로직을 구현한 서비스 
  - `NumberCheckerService` : `NumberChecker`와 관련된 비즈니스 로직을 구현한 서비스
- `validator`
  - `BuyerPurchaseAmountValidator` : `Buyer`가 구매 금액을 입력받을 때 검증하는 검증자
  - `Validator` : 검증자 인터페이스
- `view` : **MVC**의 **View**
  - `constants`
    - `ViewMessage` : 입출력 관련 상수 enum
  - `InputView` : 입력 UI
  - `OutputView` : 출력 UI