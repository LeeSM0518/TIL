Git Instruction
=================

## Git 사용법

- **Initialization ( 초기화 )**
```
- 전역 사용자명/ 이메일 구성하기
$ git config --global user.name "NAME"
$ git config --global user.email "EMAIL"

- 전역 설정 정보 조회
$ git config --global --list

- 저장소별 설정 정보 조회
$ git config --list

- Git의 출력결과 색상 활성화하기
$ git config --global color.ui "auto"

- 저장소 복제하기
$ git git clone <저장소 url>
```
---
<br/>


- **Often used Instruction ( 자주 사용되는 명령어 )**
```
$ git init      // 깃 명령어를 사용할 수 있는 디렉토리를 만든다.

$ git status    // 현재 상태를 확인한다.

$ git add .     // 변경된 상태를 추가한다.

$ git commit -m "~설명~"      // 커밋을 한다.

$ git remote add origin [주소]    // 로컬과 원격 저장소를 연결한다.

$ git remote -v     // 연결상태를 확인한다.

$ git push origin master    //  깃허브로 푸시한다.

$ git pull origin master    //  원격 저장소의 변경 사항을 가져온다.

$ git branch -a     // 지역과 원격을 포함한 모든 브랜치 목록 보기

$ git branch [새로운 브랜치]     // 현재 브랜치에서 새로운 브랜치 생성하기

$ git checkout -b [새로운 브랜치]       // 다른 브랜치 체크아웃 하기

$ git merge [브랜치]   // 다른 브랜치를 현재 브랜치로 합치기

$ git branch -d [삭제할 브랜치]   // 삭제할 브랜치가 현재 브랜치에 합져졌을 경우에만
$ git branch -D [삭제할 브랜치]   // 삭제할 브랜치가 현재 브랜치에 합쳐지지 않았어도

$ git log   // 모든 이력 보기

$ git clone <저장소>   // 저장소 복제하기

$ git fetch     // 원격 저장소에서 합치지 않고 지역 브랜치로 변경 사항 가져오기
```
---
<br/>

- **Branch ( 브랜치 )**

```
$ git branch    // 지역 브랜치 목록 보기

$ git branch -r     // 원격 브랜치 목록 보기

$ git branch -a     // 지역과 원격을 포함한 모든 브랜치 목록 보기

$ git branch [새로운 브랜치]     // 현재 브랜치에서 새로운 브랜치 생성하기

$ git checkout -b [새로운 브랜치]       // 다른 브랜치 체크아웃 하기

$ git branch [새로운 브랜치] [브랜치를 생성할 위치]    // 다른 시작 지점에서 브랜치 생성하기

$ git branch -f [기존 브랜치] [브랜치를 생성할 위치]  // 기존의 브랜치를 새로운 브랜치로 덮어쓰기

$ git checkout -m [기존 브랜치] [새로운 브랜치]    // 브랜치를 옮기거나 브랜치명 변경하기 (새로운 브랜치가 존재하지 않을 경우)

$ git checkout -M [기존 브랜치] [새로운 브랜치]    // 새로운 브랜치 가 존재하지 않을 경우 (무조건 덮어쓰기)

$ git merge [브랜치]   // 다른 브랜치를 현재 브랜치로 합치기

$ git merge --no-commit [브랜치]   // 커밋하지 않고 합치기

$ git cherry-pick-n [커밋명]   // 선택하여 합치기

$ git merge --squash[브랜치]   // 브랜치의 이력을 다른 브랜치에 합치기

$ git branch -d [삭제할 브랜치]   // 삭제할 브랜치가 현재 브랜치에 합져졌을 경우에만

$ git branch -D [삭제할 브랜치]   // 삭제할 브랜치가 현재 브랜치에 합쳐지지 않았어도

```

---

<br/>

- **Git 이력**

```
$ git log   // 모든 이력 보기

$ git log -p    // 변경 사항을 보여주는 패치와 함깨 로그 표시하기

$ git log -1    // 1개의 항목만 보이도록 로그 개수 제한하기

$ git log -20 -p    // 20개의 항목과 패치만 보이도록 로그 제한하기

$ git log --since = "6 hours"   // 6개월 동안의 커밋 로그 보기

$ git log --before = "2 days"   // 이틀 전까지의 커밋 로그 보기
```

---

- **원격 저장소**
```
$ git clone <저장소>   // 저장소 복제하기

$ git remote add <원격 저장소> <저장소 url>     // 새로운 원격 저장소 추가

$ git fetch     // 원격 저장소에서 합치지 않고 지역 브랜치로 변경 사항 가져오기

$ git pull <원격저장소>  // 원경 저장소에서 변경 사항을 가져와 현재 브랜치에 합치기

$ git push <원격 저장소> <지역 브랜치>    // 지역 브랜치를 동일한 이름의 원격 브랜치에 푸싱
```