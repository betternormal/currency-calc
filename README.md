# 스프링을 이용한 환율계산 웹기능

<kbd>
 <img src="https://github.com/wirebarley/apply/blob/master/images/coding_test1.png">
</kbd>

- 송금국가는 미국으로 고정입니다. 통화는 미국달러(USD)입니다. 
- 수취국가는 한국, 일본, 필리핀 세 군데 중 하나를 select box로 선택합니다. 각각 통화는 KRW, JPY, PHP 입니다.
- 수취국가를 선택하면 아래 환율이 바뀌어나타나야 합니다. 환율은 1 USD 기준으로 각각 KRW, JPY, PHP의 대응 금액입니다.
- 송금액을 USD로 입력하고 Submit을 누르면 아래 다음과 같이 수취금액이 KRW, JPY, PHP 중 하나로 계산되어서 나옵니다.
- 환율과 수취금액은 소숫점 2째자리까지, 3자리 이상 되면 콤마를 가운데 찍어 보여줍니다. 예를 들어 1234라면 1,234.00으로 나타냅니다.

<kbd>
 <img src="https://github.com/wirebarley/apply/blob/master/images/coding_test3.png">
</kbd>

- 환율정보는 https://currencylayer.com/ 의 무료 서비스를 이용해서 실시간으로 가져옵니다
- Submit을 누르면 선택된 수취국가와 그 환율, 그리고 송금액을 가지고 수취금액을 계산해서 하단에 보여줍니다.
- 수취금액을 입력하지 않거나, 0보다 작은 금액이거나 10,000 USD보다 큰 금액, 혹은 바른 숫자가 아니라면 “송금액이 바르지 않습니다"라는 에러 메시지를 보여줍니다.
