Python Regular expression and XML
===
### 파이썬 정규 표현식과 XML

<br/>

# 07-1 ) 정규 표현식 살펴보기
**정규 표현식** : 복잡한 문자열을 처리할 때 사용하는 기법이다.

<br/>

## 정규 표현식은 왜 필요한가?
- 다음과 같은 문제가 주어졌다고 가정해보자.
  ```
  주민등록번호를 포함하고 있는 텍스트가 있다. 이 텍스트에 포함된 모든 주민번호의 뒷자리를 * 문자로 변경하시오.
  ```
  <br/>

  - 정규 표현식으로 구현하지 않은 코드
    ```python
    data = """
    park 800905-1049118
    kim  700905-1059119
    """

    result = []
    for line in data.split("\n"):  # 줄바꿈 개수만큼 for문 실행 ( line 에 data를 줄바꿈하기 전까지의 값을 넣어줌)
        word_result = []
        for word in line.split(" "):   # 빈공간을 
            if len(word) == 14 and word[:6].isdigit() and word[7:].isdigit():
                word = word[:6] + "-" + "*******"
            word_result.append(word)
        result.append(" ".join(word_result))

    print("\n".join(result))

    ```
  - 실행결과
    ```
    park 800905-*******
    kim  700905-*******
    ```  
