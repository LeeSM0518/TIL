Python Regular expression and XML
===
## *파이썬 정규 표현식과 XML*

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
        for word in line.split(" "):   # line 값을 빈공간을 기준으로 나눈다
            if len(word) == 14 and word[:6].isdigit() and word[7:].isdigit():    # word의 길이가 14개이고 0부터 5까지 숫자이고 7부터 마지막까지 숫자이여야 한다.
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
  <br/>
  <br/>

  - **정규 표현식으로 구현한 코드**
    ```python
    import re    # 정규 표현식을 사용하기 위한 re 모듈

    data = """
    park 800905-1049118
    kim  700905-1059119
    """

    pat = re.compile("(\d{6})[-]\d{7}")
    print(pat.sub("\g<1>-*******",data))
    ```

  - 실행결과
    ```
    park 800905-*******
    kim  700905-*******
    ```
    > 이처럼 정규식을 사용하면 간편하고 직관적인 코드를 작성할 수 있다.

<br/>

---

<br/>

<br/>

# 07-2 ) 정규 표현식 시작하기
## 정규 표현식의 기초, 메타 문자
- 정규 표현식에서 사용하는 **메타 문자(meta characters)** 에는 다음과 같은 것들이 있다.
  ```
  . ^ $ * + ? { } [ ] \ | ( )
  ```
 <br/>
 
- **문자 클래스 [  ]**<br/>
  만약 [abc]라면 이 표현식의 의미는 'a,b,c 중 한 개의 문자와 매치'를 뜻한다. 
  
  
  - **ex )** <br/>
  
    |**정규식**|**문자열**|**매치여부**|**설명**|
    |:---:|:---:|:---:|:---:|
    |[abc]|a|Yes|"a"는 정규식과 일치하는 문자인 "a"가 있으므로 매치|
    |[abc]|before|Yes|"before"는 정규식과 일치하는 문자인 "b"가 있으므로 매치|
    |[abc]|dude|No|"dude"는 정규식과 일치하는 문자인 a,b,c 중 어느 하나도 포함하고 있지 않으므로 매치되지 않음|
  > [] 안의 두 문자 사이에 **하이픈(-)** 을 사용하게 되면 두 문자 사이의 범위를 의미한다. 예를들어 [a-c] 는 [abc]와 동일하고 [0-5]는 [012345]와 동일하다. 그리고 **^(캐럿,caret) 메타 문자** 는 **반대**라는 의미를 갖는다.
  
  <br/>
  
  - **자주 사용하는 문자 클래스**<br/>
  
    |정규 표현식|설명|
    |:---:|:---:|
    |\d|숫자와 매치, [0-9]와 동일한 표현식이다.|
    |\D|위와 반대 역할|
    |\s|whitespace(이스케이프 문자) 문자와 매치, [\t\n\r\f\v]와 동일한 표현식이다. 맨앞의 빈칸은 공백 문자를 의미한다.
    |\S|위와 반대 역할
    |\w|문자+숫자 와 매치, [a-zA-Z0-9]와 동일한 표현식
    |\W|위와 반대 역할

    <br>

  ---
  <br>

- **Dot(.)**
  <br/> Dot(.) 메타 문자는 줄바꿈 문자인 \n를 제외한 모든 문자와 매치됨을 의미.

  - ```python
    a.b  # a와 b 사이에 줄바꿈 문자를 제외한 어떤 문자가 들어가도 모두 매치
    ```
    > " a + 모든문자 + b "

  <br/>

  - **ex )** <br/>
  
    |**정규식**|**문자열**|**매치여부**|**설명**|
    |:---:|:---:|:---:|:---:|
    |a.b|aab|Yes|가운데 문자 "a"가 모든 문자를 의미하는 . 과 일치하므로 정규식과 매치|
    |a.b|a0b|Yes|가운데 문자 "0"가 모든 문자를 의미하는 . 과 일치하므로 정규식과 매치|
    |a.b|abc|No|a와 b 사이에 어떤 문자도 없으므로 매치되지 않음|
    <br/>

    > **a[.]b** : a와 b 사이에 Dot(.) 문자가 있으면 매치

    <br/>

  ---
    
  <br/>

- **반복(*)**
  ```python
  ca*t    # * 문자 바로 앞에 있는 a가 0번 이상 반복되면 매치
  ```
  <br/>

  - **ex )** <br/>
  
    |**정규식**|**문자열**|**매치여부**|**설명**|
    |:---:|:---:|:---:|:---:|
    |ca*t|ct|Yes|"a"가 0번 반복되어 매치|
    |ca*t|cat|Yes|"a"가 1번 반복되어 매치|
    |ca*t|caaat|Yes|"a"가 3번 반복되어 매치|

  <br/>

   ---
  <br/>

- **반복(+)**
  ```python
  ca+t    # + 문자 바로 앞에 있는 a가 1번 이상 반복되면 매치
  ```
  <br/>

  - **ex )** <br/>
  
    |**정규식**|**문자열**|**매치여부**|**설명**|
    |:---:|:---:|:---:|:---:|
    |ca+t|ct|No|"a"가 0번 반복되어 매치 안됨|
    |ca+t|cat|Yes|"a"가 1번 반복되어 매치|
    |ca+t|caaat|Yes|"a"가 3번 반복되어 매치|

  <br>

  ---

- **반복({m,n},?)**
  - 1. **{m}**  : 앞에 있는 문자가 m번 반복되면 매치
    ```python
    ca{2}t    # a가 2번 반복되면 매치 
    ```
    > " c + a (반드시 2번 반복) + t "

    * **ex )** <br/>
  
      |**정규식**|**문자열**|**매치여부**|**설명**|
      |:---:|:---:|:---:|:---:|
      |ca{2}t|cat|No|"a"가 1번 반복되어 매치 안됨|
      |ca{2}t|caat|Yes|"a"가 2번 반복되어 매치|
  
  <br>
  <br>

  - 2. **{m, n}** : 앞에 있는 문자가 m ~ n 번 반복되면 매치
    ```python
    ca{2,5}t    # a가 2~5번 반복되면 매치
    ```
    > " c + a ( 2 ~ 5 번 반복 ) + t "

    * **ex )** <br/>
  
      |**정규식**|**문자열**|**매치여부**|**설명**|
      |:---:|:---:|:---:|:---:|
      |ca{2,5}t|cat|No|"a"가 1번 반복되어 매치 안됨|
      |ca{2,5}t|caat|Yes|"a"가 2번 반복되어 매치|
      |ca{2,5}t|caaaaat|Yes|"a"가 5번 반복되어 매치|
  
  <br>
  <br>
  
  - 3.  **?**  : 앞에 있는 문자가 있거나 없거나 매치
    ```python
    ab?c    # b가 0~1번 사용되면 매치
    ```
    > " a + b ( 있어도 되고 없어도 된다 ) + c "

    * **ex )** <br/>
  
      |**정규식**|**문자열**|**매치여부**|**설명**|
      |:---:|:---:|:---:|:---:|
      |ab?c|abc|Yes|"b"가 1번 사용되어 매치|
      |ab?c|ac|Yes|"b"가 0번 반복되어 매치|

<br>

---

<br>

## 파이썬에서 정규 표현식을 지원하는 re모듈<br>
```python
사용방법
>>> import re
>>> p = re.compile('ab*')   # re.compile을 이용하여 ab*(b가 0번 이상 반복되면 매치) 을 컴파일한다.
```

<br>
<br>

## 정규식을 이용한 문자열 검색
|**메서드**|**목적**|
|:---:|:---:|
|*match()*|문자열의 **처음부터** 정규식과 매치되는지 조사한다.
|*search()*|문자열 **전체**를 검색하여 정규식과 매치되는지 조사한다.
|*findall()*|정규식과 매치되는 모든 문자열을 **리스트**로 리턴한다.
|*finditer()*|정규식과 매치되는 모든 문자열을 반복 가능한 **객체**로 리턴한다.

<br>

우선 다음과 같은 패턴을 만들어 보자.
```python
>>> import re  # 모듈 선언
>>> p = re.compile('[a-z]+')  # 소문자 알파벳이 아무거나 1번 이상 나오면 매치
```

<br>

- **match 메서드** : 문자열의 처음부터 정규식을 비교한다.
  ```python
  >>> m = p.match("python")
  >>> print('p.match("python") = ', m)
  p.match("python") =  <re.Match object; span=(0, 6), match='python'>  #  모두 소문자 알파벳으로 이루어져 있으므로 매치되며 match 객체가 리턴됨.
  ```
  > "python" 이라는 문자열은 [a-z]+ 정규식에 부합되므로 match 객체가 리턴된다.

  ```python
  >>> m = p.match("pYthon")
  >>> print('p.match("pYthon") = ', m)
  p.match("pYthon") =  <re.Match object; span=(0, 1), match='p'>  # p라는 소문자 알파벳이 잇으므로 매치되며 match 객체가 리턴.
  ```

  <br>

  ```python
  >>> m = p.match("3 python")
  >>> print(m)
  None
  ```
  > " 3 python " 이라는 문자열은 처음에 나오는 3이라는 문자가 정규식에 부합되지 않으므로 None이 리턴된다.

  <br>

  - 파이썬 정규식 프로그램
    ```python
    import re
    p = re.compile(정규 표현식)
    m = p.match("조사할 문자열")
    if m:
      print('Match fount : ', m.group())
    else:
      print('No match')
    ```
    > 즉 , match의 결과값이 있을 때만 그 다음 작업을 수행하겠다는 것이다.

    <br>

    - ex)
      ```python
      print('\nmatch 프로그램')
      def match_str(st1, st2):
        p = re.compile(st1)
        m = p.match(st2)
        if m:
          print('Match fount : ', m.group())
        else:
          print('No match')

      print('정규 표현식 : \'a.c\' 조사할 문자열 : \"abc\"  ')
      match_str('a.c','abc')
      print('정규 표현식 : \'a.c\' 조사할 문자열 : \"abc\"  ')
      match_str('a.c','ddd')
      ```
      - 실행결과
      ```
      match 프로그램
      정규 표현식 : 'a.c' 조사할 문자열 : "abc"  
      Match fount :  abc
      정규 표현식 : 'a.c' 조사할 문자열 : "ddd"
      No match
      ```

  <br>

  ---

  <br>

- **search** : 모든 문자열과 정규식과 비교한다.
  ```python
  >>> p = re.compile('[a-z]+')
  >>> m = p.search("python")
  >>> print(m)
  <re.Match object; span=(0, 6), match='python'>
  ```
  > match 메서드와 동일하게 수행

  <br>

  ```python
  >>> p = re.compile('[a-z]+')
  >>> m = p.search("3 python")
  >>> print(m)
  <re.Match object; span=(2, 8), match='python'>
  ```
  > 문자열 전체를 검색하기 때문에 정규식과 매치된다.
  
  `match 메서드와 search 메서드는 문자열의 처음부터 검색할지의 여부에 따라 다르게 사용해야한다`

  <br>

  ---

  <br>

- **findall** : 문자열의 각 단어들이 각각 정규식과 매치되어 리스트로 리턴된다.
  ```python
  >>> p = re.compile('[a-z]+')
  >>> result = p.findall('life is too short')
  >>> print(result)
  ['life', 'is', 'too', 'short']
  ```

  <br>

  ---

  <br>

- **finditer** : findall과 동일하지만 그 결과로 반복 가능한 객체를 리턴한다.
  ```python
  >>> p = re.compile('[a-z]+')
  >>> result = p.finditer("life is too short")
  >>> print(result)
  <callable_iterator object at 0x03885B70>
  >>> for r in result : print(r)
  <re.Match object; span=(0, 4), match='life'>
  <re.Match object; span=(5, 7), match='is'>
  <re.Match object; span=(8, 11), match='too'>
  <re.Match object; span=(12, 17), match='short'>
  ```

<br>

---

<br>

## **match 객체의 메서드** 
: 어떤 문자열이 매치되었는지 그리고 매치된 문자열의 인덱스는 어디부터 어디까지 인지를 해결
  
  <br>

- 표 )
  |**메서드**|목적|
  |:---:|:---:|
  |group()|매치된 문자열을 리턴한다.|
  |start()|매치된 문자열의 시작 위치를 리턴|
  |end()|매치된 문자열의 끝 위치를 리턴|
  |span()|매치된 문자열의 (시작, 끝)에 해당되는 튜플을 리턴|

  
  <br>

    - **match** 객체의 메서드 예시
      ```python
      >>> import re
      >>> p = re.compile('[a-z]+')
      >>> m = p.match("python")
      >>> m.group()   # 매치된 python 리턴
      'python'
      >>> m.start()   # 매치된 문자열 처음 0 리턴
      0
      >>> m.end()   # 매치된 문자열 마지막 6 리턴
      6
      >>> m.span()   # 매치된 문자열 시작과 끝 (0,6) 리턴
      (0,6)
      ```
      > match 메서드는 start()의 결과값은 항상 0일 수 밖에 없다.

      <br>
      
    - **search** 객체의 메서드 예시 

      ```python
      >>> m = p.search('3 python')
      >>> m.group()
      'python'
      >>> m.start()  # '3 '은 매치가 되지 않으므로 2부터 시작
      2
      >>> m.end()  
      8
      >>> m.span()
      (2,8)
      ```
    <br>

    - 모듈 단위로 수행하기
      ```python
      >>> p = re.compile('[a-z]+')
      >>> m = p.match("python")
      ```
      > 위와 같이 번거롭게 하지않고 밑에 예시 처럼 보다 간단하게 작업을 수행할 수있다.
      
      ```python
      >>> m = re.match( '[a-z]+', "python")
      ```

<br>

---

<br>

## 컴파일 옵션
- 정규식을 컴파일할 때 다음과 같은 옵션을 사용할 수 있다.<br>
  
  |옵션명|약어|설명|
  |:---:|:---:|:---:|
  |DOTALL|S|**줄바꿈 문자를 포함**하여 모든 문자와 매치할 수 있도록 한다.|
  |IGNORECASE|I|**대,소문자에 관계 없이** 매치할 수 있도록 한다.
  |MULTILINE|M|**여러 줄과 매치**할 수 있도록 한다. ( ^ , $ 메타 문자의 사용과 관계가 있는 옵션이다)
  |VERBOSE|X|verbose 모드를 사용할 수 있도록 한다.(정규식을 보기 편하게 만들 수도 있고 **주석** 등을 사용할 수도 있다.)
  
  > re.DOTALL == re.S (약어 사용법)

  <br>

  - **DOTALL, S**
    ```python
    >>> p = re.compile('a.b', re.DOTALL)
    >>> m = p.match('a\nb')
    >>> print(m)
    <_sre.SRE_Match object at 0x01FCF3D8>
    ```
    > ( . ) 메타 문자가 '\n'을 읽을 수 있도록 한다. <br>보통 여러 줄로 이루어진 문자열에서 \n에 상관없이 검색하고자 사용.

  <br>
  <br>

  - **IGNORECASE, I** : 대,소문자 구분 없이 매치 수행
    ```python
    >>> p = re.compile('[a-z]',re.I)
    >>> p.match('python')
    <객체 리턴>
    >>> p.match('PYTHON')
    <객체 리턴>
    ```



  <br>
  <br>

  - **MULTILINE, M**<br>
  *^ 메타 문자* : 문자열의 처음 <br>
  *$ 메타 문자* : 문자열의 끝 <br>
    
    <br>

    - 옵션을 사용하지 않은 예 )
      ```python
      import re
      p = re.compile("^python\s\w+")

      data = """python none
      life is too short
      python two
      you need python
      python three"""

      print(p.findall(data))
      ```
      실행결과
      ```
      ['python one']
      ```
      > ^ 메타 문자에 의해 python이라는 문자열이 사용된 첫 번째 라인만 매치가 됨.
    
    <br>

    - 옵션을 사용한 예 )
      ```python
      import re
      p = re.compile("^python\s\w+", re.MULTIFLINE)

      data = """python one
      life is too shore
      python two
      you need python
      python three"""

      print(p.findall(data))
      ```
      실행결과
      ```
      ['python one', 'python two', 'python three']
      ```
      > 즉, re.MULTILINE 옵션은 ^ , $ 메타 문자를 문자열의 각 라인마다 적용해 주는 것이다.

  <br>
  <br>

  - **VERBOSE, X** : 어려운 정규식을 주석 또는 라인 단위로 구분하게 해준다.
    - 예시)
      ```python
      charref = re.compile(r"""
      &[#]    # 숫자의 엔티티 참조 시작
      (
        0[0-7]+   # 8진법
        | [0-9]+   # 10진법
        | x[0-9a-fA-F]+   # 16진법
      )
      ;
      """, re.VERBOSE)  # VERBOSE 옵션으로 인해 #기호를 주석으로 처리해준다.
      ```

<br>

---

<br>

## 백슬래시 문제
- ex )
  ```
  \section   # 이 정규식은 [\t\n\r\f\v]ection 과 같이 해석된다. ( \s 문자가 이스케이프 코드로 해석되기 때문.)
  ```
  > 의도한 대로 매치하고 싶다면 `\\section`과 같이 변경해야 한다.
  
  <br>

  - 만약 백슬래시 표현이 계속 반복된다면 )
    ```python
    >>> p = re.compile(r'\\section')  # 이처럼 정규식 앞에 r을 붙이면 Raw String임을 알려주어 백슬래시를 2개 대신 1개만 써도 된다.
    ```

<br>

---

<br>

# 07-3 ) 강력한 정규 표현식의 세계로
*이제 몇몇 메타 문자들과 그룹을 만드는법, 전방 탐색등에 살펴보자.*

<br>

## 메타 문자
*문자열 소모가 없는 메타 문자들*
* **| (Pipe)** : A|B, A또는 B라는 의미
  ```python
  >>> p = re.compile('Crow|Servo')
  >>> m = p.match('CrowHello')
  >>> print(m)
  <re.Match object; span=(0, 4), match='Crow'>
  ```

<br>

* **^ (Caret)** : 문자열의 맨 처음
  ```python
  >>> print(re.search('^Life','Life is too short'))
  <re.Match object; span=(0, 4), match='Life'>
  >>> print(re.search('^Life','short Life'))
  None
  ```

<br>

* **$ (Dollar)** : 문자열의 끝
  ```python
  >>> print(re.search('short$','Life is too short'))
  <re.Match object; span=(12, 17), match='short'>
  >>> re.search('short$','Life is too short, you')
  None
  ```

<br>

* **\A** : 전체 문자열의 처음과 매치.
  ```python
  >>> p = re.compile('\ALife',re.MULTILINE)
  >>> data ="""life
  Life is
  """
  >>> m = p.search(data)
  >>> print(m)
  None  # Life 가 전체 문자열의 처음이 아니다.
  ```

<br>

* **\Z** : 전체 문자열의 마지막.
  ```python
  >>> p = re.compile('\Zshort',re.MULTILINE)
  >>> data = """Life
  is too short,
  you need python"""
  >>> m = p.search(data)
  >>> print(m)
  None  # short가 전체 문자열의 끝이 아니다.
  ```

<br>

* **\b** : whitespace에 구분이 된다.
  ```python
  >>> p = re.compile('\bclass\b')
  >>> print(re.search('no class at all'))
  <re.Match object; span=(3, 8), match='class'>
  >>> print(re.search('the delclassified'))  # class 앞뒤가 빈칸이 아니다.
  None
  ```
  > \b 메타 문자가 단어 구분자임을 알려주기 위해 r'\bclass\b' 처럼 **기호 r**을 반드시 붙여 주어야 한다.

<br>

* **\B** : \b 메타 문자와 반대 경우.
  ```python
  >>> p = re.compile(r'\Bclass\B')
  >>> print(p.search('no class at all'))
  None
  >>> print(p.search('the declassfied algorithm'))
  <re.Match object; span=(6, 11), match='class'>  # class의 앞뒤가 빈칸이 아니다.
  ```

<br>

---

<br>

## 그룹핑 
*계속해서 반복되는 정규식 작성을 위함*


* 예시
  ```python
  (ABC)+
  ```
  > ( ) 을 이용하여 그룹핑을 할 수 있다.

  <br>

  * ex 1 )

    ```python
    >>> p = re.compile('(ABC)+')
    >>> m = p.search('ABCABCABC OK?')
    >>> print(m)
    <re.Match object; span=(0, 9), match='ABCABCABC'>
    >>> print(m.group())
    ABCABCABC  # ABC가 3번 반복되는것을 다 받아드린다.
    ```

  <br>

  * ex 2 )
    ```python
    >>> p = re.compile(r'\w+\s+\d+[-]\d+[-]\d+')
    >>> m = re.search('park 010-1234-1234')
    >>> print(m)
    <re.Match object; span=(0, 18), match='park 010-1234-1234'>
    ```
    > \w : 문자와 숫자<br>\s : 이스케이프 <br>\d : 숫자<br>[ ] : 대괄호 안에 문자 유무

  <br>

  * ex 3 ) '이름'부분만 쓴다.
    ```python
    >>> p = re.compile(r'(\w+)\s+\d+[-]\d+[-]\d+')
    >>> m = p.search('park 010-1234-1234')
    >>> print(m.group())
    park 010-1234-1234
    >>> print(m.group(0))
    park 010-1234-1234
    >>> print(m.group(1))
    park
    ```

  <br>

  * **group 메서드 표**<br>
  
    |*group(인덱스)*|*설명*|
    |:---:|:---:|
    |group(0)|매치된 전체 문자열|
    |group(1)|첫 번째 그룹에 해당되는 문자열|
    |group(2)|두 번째 그룹에 해당되는 문자열|
    |group(n)|n 번째 그룹에 해당되는 문자열|

  <br>

  * ex 4 )
    ```python
    >>> p = re.compile(r'(\w+)\d+\s+((\d+)[-]\d+[-]\d+)')
    >>> m = p.search('park 010-1234-1234')
    >>> print(m.group(1))
    park
    >>> print(m.group(2))
    010-1234-1234
    >>> print(m.group(3))
    010

  <br>

  * ex 5 ) **그룹핑된 문자열 재참조하기**
    ```python
    >>> p = re.compile(r'(\b\w+)\s+\1')
    >>> print(p.search('Paris in the the spring').group())
    the the
    ```
    > **\1** 이 재참조 메타 문자이다. \1은 정규식의 그룹 중 첫 번째 그룹을 지칭한다.