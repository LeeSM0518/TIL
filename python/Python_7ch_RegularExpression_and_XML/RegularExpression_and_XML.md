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
  
<br>

---

<br>

## 그룹핑된 문자열에 이름 붙이기
*그룹을 인덱스가 아닌 이름으로 참조할 수 있도록 한다.*

* 그룹명 ex )
  ```
  (?P<name>\w+)\s+((\d+)[-]\d+[-]\d+)
  ```
  > 기존과 달라진 부분 : (\w+) >> (?P<name>\w+)<br>
  확장 구문법 : **(?P<그룹명>...)**

  <br>

* 그룹명으로 참조 예시 )
  ```python
  >>> p = re.compile(r'(?P<name>\w+)\s+((\d+)[-]\d+[-]\d+)')
  >>> m = p.search('park 010-1234-1234')
  >>> print(m.group('name'))
  park
  ```

  <br>

* 그룹명을 이용하여 정규식 내에서 재참조 예시 )
  ```python
  >>> p = re.compile(r'(?P<word>\b\w+)\s+(?P=word)')
  >>> m = p.search('Paris in the the spring').group()
  the the
  ```

<br>

---

<br>

## 전방 탐색
* 예시 )
  ```python
  >>> p = re.compile('.+:')
  >>> m = p.search('http://google.com')
  >>> print(m.group())
  http:
  ```
  > http: 에서 :을 제외하고 출력하려면?

  <br>

  * 이럴때 필요한 것이 전방 탐색이다. <br>
  
    |**정규식**|**종류**|**설명**|
    |:---:|:---:|:---:|
    |*(?=...)*|**긍정형** 전방 탐색| ...에 해당되는 정규식과 **매치되어야 하며** 조건이 통과되어도 문자열이 소모되지 않는다.|
    |*(?!...)*|**부정형** 전방 탐색| ...에 해당되는 정규식과 **매치되지 않아야 하며** 조건이 통과 되어도 문자열이 소모되지 않는다.

    <br>

  * 긍정형 전방 탐색 예시 )
    ```python
    >>> p = re.compile('.+(?=:)')
    >>> m = p.search('http://google.com')
    >>> print(m.group())
    http
    ```
     > 매치조건은 http : 이지만 : 은 제거되서 리턴이 된다.

    <br>

      * **' 파일명 + . + 확장자 '** 정규식
        ```python
        .*[.].*$   # foo.bar, autoexec.bat, sendmail.cf 와 같은 형식의 파일과 매치된다.
        ```
        > 이 정규식에서 'bat인 파일은 제외해야 한다'는 조건을 추가해보자.
      
        <br>
      
      * **조건 추가 )**
        ```python 
        .*[.][^b].*$   # [^b] 가 b를 매치시키지 않는다.
        ```
        > 하지만 이 정규식은 **foo.bar** 이라는 파일마저 걸러낸다. 그러므로 정규식을 다음과 같이 수정해 보자.

      <br>
      
      * **조건 추가 )**
        ```python
        .*[.]([^b]..|.[^a].|..[^t])$   # b.. 이거나 .a. 이거나 ..t 인 파일을 매치시키지 않는다.
        ```
        > 하지만 이 정규식은 **sendmail.cf** 처럼 확장자의 문자 개수가 2개인 케이스를 포함하지 못 하게 된다.
      
      <br>
      
      * **조건 추가 )**
        ```python
        .*[.]([^b].?.?|.[^a]?.?|..?[^t]?)$
        ```
        > 확장자의 문자 개수가 2개여도 통과가 된다.
    
    <br>

    ---
    
    <br>

  * **부정형 전방 탐색** <br>
  *위 케이스는 부정형 전방 탐색을 사용하면 간단하게 처리된다.*
    * 정규식 )
      ```python
      .*[.](?!bat$).*$   # 확장자가 bat이 아닌 경우에만 통과된다.

      .*[.](?!bat$ | exe$).*$   # exe 역시 제외하라는 조건 추가.
      ```

<br>

---

<br>

## 문자열 바꾸기
*sub 메서드를 이용하면 정규식과 매치되는 부분을 다른 문자로 쉽게 바꿀 수 있다.*
* 예시 )
  ```python
  >>> p = re.compile('(blue|white|red)')
  >>> print(p.sub('colour', 'blue socks and red shoes'))
  colour socks and colour shoes
  print(p.sub('colour','blue socks and red shoes',count=1))
  colour socks and red shoes
  ```
  > **sub( ' 바꿀 문자열 ', ' 대상 문자열 ', ' count = 횟수 ' )**

  <br>

* **sub 메서드와 유사한 subn 메서드**
  ```python
  >>> print(p.subn('colour', 'blue socks and red shoes'))
  ('colour socks and colour shoes', 2)
  ```
  > sub와 동일한 기능을 하고 리턴되는 결과와 횟수를 튜플로 리턴한다.

  <br>

  ---

  <br>

* **sub 메서드 사용 시 참조 구문 사용하기** <br><br> **예시 )**
  
  ```python
  >>> p = re.compile(r"(?P<name>\w+)\s+(?P<phone>(\d+)[-]\d+[-]\d+)")
  >>> print(p.sub("\g<phone> \g<name>", "park 010-1234-1234"))
  010-1234-1234 park
  # '이름 + 전화번호' >> '전화번호 + 이름'   
  ```
  > sub의 바꿀 문자열 부분에 `'\g<그룹명>'` 을 이용

  <br>
  
  **참조번호를 이용한 예시 )**

  ```python
  >>> p = re.compile(r"(?P<name>\w+)\s+(?P<phone>(\d+)[-]\d+[-]\d+)")
  >>> print(p.sub("\g<2> \g<1>", "park 010-1234-1234"))
  010-1234-1234 park
  # \g<phone> \g<name>   >>   \g<2> \g<1>  
  ```

  <br>
  <br>

* **sub 메서드의 입력 인수로 함수 넣기** <br>
  *sub 메서드의 첫 번째 입력 인수로 함수를 넣을 수도 있다.*

  * **예시 )**
    ```python
    def hexrepl(match):
      "Return the hex string for a decimal number"
      value = int(match.group())
      return hex(value)  
    # match 객체를 입력으로 받아 16진수로 변환하는 함수

    p = re.compile(r'\d+')
    print(p.sub(hexrepl, 'Call 65490 for printing, 49152 for user code.'))
    ```
    실행결과
    ```
    Call 0xffd2 for printing, 0xc000 for user code.
    ```
    > sub의 첫 번째 입력 인수로 함수를 사용할 경우 해당 함수의 인수에는 **정규식과 매치된 match 객체**가 입력된다. 그리고 매치되는 문자열은 함수의 리턴 값으로 바뀌게 된다.

<br>

---

<br>

## Greedy vs Non-Greedy
* 예시 )
  ```python
  >>> s = '<html><head><title>Title</title>'
  >>> print(len(s))
  32
  >>> print(re.match('<.*>',s).span())
  (0, 32)
  >>> print(re.match('<.*>',s).group())
  <html><head><title>Title</title>
  >>> print(re.match('<.*?>',s).group())
  <html>
  ```
  > (*) 메타 문자 때문에 s의 모든 문자열을 리턴 시킨다. 그러므로 **non-greed 문자인 ?** 을 같이 사용하여 Greedy를 제한시킬수 있다.

<br>

---

<br>

# 07-4 ) 파이썬으로 XML 처리하기
*XML 처리를 위한 파이썬 라이브러리는 [http://wiki.python.org/moin/PythonXml](http://wiki.python.org/moin/PythonXml) 에서 확인할 수 있다.*

<br>

## XML 문서 생성하기
*ElementTree를 이용하여 다음과 같은 구조의 XML 문서를 생성*

* 예시 )
  ```python
  from xml.etree.ElementTree import Element, dump   # XML 라이브러리 추가

  note = Element('note')  # 태그 추가
  to = Element('to')  # 태그 추가
  to.text = 'tove'  # to 태그의 텍스트 추가

  note.append(to)   # note 태그에 to 태그 삽입
  dump(note)    # note 출력
  ```
  실행결과
  ```
  <note><to>Tove</to></note>
  ```
  > **엘리먼트(Element)** 를 이용하면 태그를 만들 수 있고, 만들어진 태그에 텍스트 값을 추가할 수 있음.

<br>

* **SubElement**<br>
**서브엘리먼트(SubElement)** 를 이용하면 조금 더 편리하게 태그를 추가할 수 있다.
  
  <br>

  * 예시 )
    ```python
    from xml.etree.ElementTree import Element, SubElement, dump

    note = Element('note')
    to = Element('to')
    to.text = 'Tove'

    note.append(to)
    SubElement(note, 'from').text = 'Jani'    # note 태그에 from 이라는 태그를 'Jani'라는 내용으로 추가

    dump(note)
    ```
    실행결과
    ```
    <note><to>Tove</to><from>Jani</from></note>
    ```
    > 서브엘리먼트는 **태그명과 태그의 텍스트 값**을 한 번에 설정할 수 있다.

    <br>

  * 태그를 추가하거나 삭제 )
    ```python
    dummy = Element('dummy')
    note.insert(1, dummy)   # note에 1번째 위치에 dummy라는 태그 삽입
    dump(note)
    note.remove(dummy)    # dummy 태그 삭제
    ```
    실행결과
    ```
    <note><to>Tove</to><dummy /><from>Jani</from></note>
    ```
    > dummy 라는 태그를 삽입하고 삭제하는 경우.

  <br>

  ---

  <br>

* **애트리뷰트 추가하기** <br>
*note 태그에 **애트리뷰트(attribute)** 를 추가*
  * 추가 )
    ```python
    from xml.etree.ElementTree import Element, SubElement, dump

    note = Element('note')
    to = Element('to')
    to.text = 'Tove'

    note.append(to)
    SubElement(note, 'from').text = 'Jani'
    note.attrib['date'] = '20120104'    # 애트리뷰트 값 추가

    dump(note)
    ```
    실행결과
    ```
    <note date="20120104"><to>Tove</to><from>Jani</from></note>
    ```

  <br>

  ---

  <br>

* **XML 태그와 애트리뷰트를 추가하여 완성된 소스**
  ```python
  from xml.etree.ElementTree import Element, SubElement, dump

  note = Element('note')
  note.attrib['note'] = '20140104'
  
  to = Element('to')
  to.text = 'Tove'
  note.append(to)

  SubElement(note, 'from').text = 'Jani'
  SubElement(note, 'heading').text = 'Reminder'
  SubElement(note, 'body').text = "Don't forget me this weekend!"
  dump(note)
  ```
  실행결과
  ```
  <note note="20140104"><to>Jani</to><from>Jani</from><heading>Reminder</heading><body>Don't forget me this weekend!</body></note>
  ```

  <br>
  
  ---

  <br>

* **indent 함수** <br>
*정렬된 형태의 XML 값을 보기 위함*
  ```python
  from xml.etree.ElementTree import Elment, SubElement, dump

  note = Element('note')
  note.attrib['date'] = '20180924'
  to = Element('to')
  to.text = 'Jani'
  note.append(to)
  SubElement(note, 'from').text = 'Jani'
  SubElement(note, 'heading').text = 'Reminder'
  SubElement(note, 'body').text = "Don't forget me this weekend!"

  def indent(elem, level=0):
    i = '\n' + level*" "
    if len(elem):
      if not elem.text or not elem.text.strip():
        elem.text = i + " "
      if not elem.tail or not elem.tail.strip():
        elem.tail = i
      for elem in elem:
        indent(elem, level + 1)
      if not elem.tail or not elem.tail.strip():
        elem.tail = i
    else:
      if level and (not elem.tail or not elem.tail.strip()):
        elem.tail = i

  indent(note)
  dump(note)
  ```
  실행결과
  ```
  <note date="20180924">
   <to>Tove</to>
   <from>Jani</from>
   <heading>Reminder</heading>
   <body>Don't forget me this weekend!</body>
  </note>
  ```

  <br>

  ---

  <br>

* **파일에 쓰기(write) 수행하기** <br>
*엘리먼트의 write 메서드를 이용하여 파일에 쓰기*
  ```py
  from xml.etree.ElementTree import ElementTree
  ElementTree(note).write('note.xml')
  ```
  > **note.xml** 이 생성되는 것을 확인할 수 있다.

<br>

---

<br>

## XML 문서 파싱하기
*XML 문서를 파싱(parsing)하고 검색하는 방법*
```py
from xml.etree.ElementTree import parse
tree = parse('note.xml')
note = tree.getroot()
```

<br>

* **애트리뷰트 값 읽기**
  ```py
  >>> print(note.get('date'))
  20180924
  
  >>> print(note.get('foo','default'))    # 두 번째 인수로 디폴트 값을 주면 첫 번째 인자에 해당되는 애트리뷰트 값이 없을 경우 두 번째 값을 리턴.
  default
  
  >>> print(note.keys())    # 애트리뷰트의 키 값
  ['date']

  >>> print(note.items())   # 애트리뷰트의 key와 value 값 리턴
  [('date','20180924')]
  ```

  <br>

* **XML 태그 접근하기**
  ```py
  >>> from_tag = note.find('from')
  >>> from_tags = note.findall('from')
  >>> from_text = note.findtext('from')
  
  >>> print(from_tag)
  <Element 'from' at 0x0353A540>  # from 객체 리턴

  >>> print(from_tags)
  [<Element 'from' at 0x0353A540>]  # from 객체를 리스트로 리턴

  >>> print(from_text)  # from 의 text를 리턴
  Jani
  ```

<br>

---

<br>
<br>
<br>

# 연 습 문 제

## 정규 표현식
* **Q1** 다음 중 정규식 " a [ . ] { 3 , } b  " 과 매치되는 문자열은 무엇일까? <br>

  |번호|문자열|
  |:---:|:---:|
  |A|acccb|
  |B|a....b|
  |C|aaab|
  |D|a.cccb|

   <br>
   <br>
   <br>
   <br>
   <br>
   <br>
   <br>
   <br>
   <br>
   <br>
   <br>
   <br>

   **답 : B , 왜냐하면 우선 문자열의 앞에는 a가 들어가야 한다. 그리고 [.] 은 . 과 달리 Dot(.) 자체를 의미한다. { 3, } 의 의미는 앞에 있는 문자가 3번 이상 반복되면 매치가 된다는 의미이다. 마지막으로 문자열의 마지막은 b가 있어야된다. 그러므로 a....b 가 답이 된다**

   <br>

   ---

   <br>

* **Q2** : 다음 코드의 결과값은 무엇일까?
  ```py
  >>> import re
  >>> p = re.compile('[a-z]+')
  >>> m = p.search('5 python')
  >>> m.start() + m.end()
  ```

   <br>
   <br>
   <br>
   <br>
   <br>
   <br>
   <br>
   <br>
   <br>
   <br>
   <br>
   <br>

   **답 : 10, ' [ a - z ] + ' 의 의미는 소문자 알파벳이 1번 이상 반복되는지 매치시키는 것이다. 그러므로 5 python의 매치되는 부분의 시작은 2이고 마지막은 8미만 까지이다.**

   <br>

   ---

   <br>

* **Q3** : 다음과 같은 문자열에서 핸드폰 번호 뒷자리인 숫자 4개를 ####로 바꾸는 프로그램을 정규식을 이용하여 작성해 보자.
  ```py
  """
  park 010-9999-9988
  kim 010-9909-7789
  lee 010-8789-7769
  """
  ```

  <br>
  <br>
  <br>
  <br>
  <br>
  <br>
  <br>
  <br>
  <br>
  <br>
  <br>
  <br>

  답 :
    ```py
    import re

    data ="""
    park 010-9999-9988
    kim 010-9909-7789
    lee 010-8789-7769
    """

    def change_phoneNum(data):
      p = re.compile('(\w+\s+\d+[-]\d+[-])(\d+)',re.MULTILINE)
      print(p.sub('\g<1>####',data))

    change_phoneNum(data)
    ```
    실행결과
    ```
    park 010-9999-####
    kim 010-9909-####
    lee 010-8789-####
    ```

  <br>
  
  ---

  <br>

* **Q4** : 다음은 이메일 주소를 나타내는 정규식이다. 이 정규식은 park@naver.com, kim@daum.net, lee@myhome.co.kr 등과 매치된다. 긍정형 전방 탐색 기법을 이용하여 .com, .net 이 아닌 이메일 주소는 제외시키는 정규식을 작성해 보자.
  ```
  .*[@].*[.].*$
  ```

  
  <br>
  <br>
  <br>
  <br>
  <br>
  <br>
  <br>
  <br>
  <br>
  <br>
  <br>
  <br>

  답 : 
    ```py
    def imail_check(data):
    p = re.compile('.*[@].*(?=[.]com$|[.]net$)',re.MULTILINE)
    print(p.findall(data))

    data ="""
    park@naver.com
    kim@daum.net
    lee@myhome.co.kr
    """

    imail_check(data)
    ```

    실행결과
    ```
    ['park@naver', 'kim@daum']
    ```

  <br>

  ---

  <br>

## XML 처리
* **Q1** : ElementTree를 이용하여 다음 XML 문서를 작성하고 파일에 저장해 보자.
  ```py
  <blog date="20151231">
   <subject>Why python?</subject>
   <author>Eric</author>
   <content>Life is too short, You need Python!</content>
  </blog>
  ```
  
    
  <br>
  <br>
  <br>
  <br>
  <br>
  <br>
  <br>
  <br>
  <br>
  <br>
  <br>
  <br>

  답 :
  ```py
  from xml.etree.ElementTree import ElementTree, Element, SubElement, dump, parse

  blog = Element('blog')
  blog.attrib['date'] = '20151231'

  SubElement(blog, 'subject').text = 'Why python?'
  SubElement(blog, 'author').text = 'Eric'
  SubElement(blog, 'content').text = 'You need Python!'

  def indent(elem, level=0):
      i = '\n' + level*" "
      if len(elem):
          if not elem.text or not elem.text.strip():
            elem.text = i + " "
          if not elem.tail or not elem.tail.strip():
            elem.tail = i
          for elem in elem:
            indent(elem, level + 1)
          if not elem.tail or not elem.tail.strip():
            elem.tail = i
      else:
          if level and (not elem.tail or not elem.tail.strip()):
            elem.tail = i

  indent(blog)
  dump(blog)

  ElementTree(blog).write('blog.xml')
  ```
  실행결과
  ```
  <blog date="20151231">
   <subject>Why python?</subject>
   <author>Eric</author>
   <content>You need Python!</content>
  </blog>
  ```

<br>

---

<br>

# 감 사 합 니 다.
## Peedback
* 