# Git 이란?

Git은 컴퓨터 파일의 변경사항을 추적하고 여러 명의 사용자들 간에 해당 파일들의 작업을 조율하기 위한 분산 버전 관리 시스템이다.

<br>

## 버전 관리란?

버전 관리 시스템은 파일 변화를 시간에 따라 기록했다가 나중에 특정 시점의 버전을 다시 꺼내올 수 있는 시스템이다.

이러한 시스템을 이용하면 파일을 이전 상태로 되돌릴 수 있고, 프로젝트를 통째로 이전 상태로 되돌릴 수 있고, 시간에 따라 수정 내용을 비교해 볼 수 있고, 누가 문제를 일으켰는지도 추적할 수 있고, 누가 언제 만들어낸 이슈인지도 알 수 있다. 그리고 파일을 잃어버리거나 잘못 고쳤을 때도 쉽게 복구할 수 있다.

<br>

# Git 기초

## 스냅샷

Git은 데이터를 파일 시스템 스냅샷의 연속으로 취급하고 크기가 아주 작다. Git은 프로젝트의 상태를 저장할 때마다 파일이 존재하는 그 순간을 중요하게 여긴다.

<img src="https://git-scm.com/book/en/v2/images/snapshots.png">

> 시간순으로 프로젝트의 스냅샷을 저장.

<br>

## Git의 세 가지 상태

Git은 파일을 세 가지 상태로 관리한다.

* **Modified** : 수정한 파일을 아직 로컬 데이터베이스에 커밋하지 않은 것을 의미
* **Staged** : 현재 수정한 파일을 곧 커밋할 것이라고 표시한 상태를 의미
* **Commited** : 데이터가 로컬 데이터베이스에 안전하게 저장됐다는 것을 의미

<br>

이 세 가지 상태는 Git 프로젝트의 세 가지 단계와 연결돼 있다.

<img src="https://git-scm.com/book/en/v2/images/areas.png">

* **Git의 처리 과정**
  1. .git이 존재하는 디렉토리에서 파일을 수정하게 되면 working directory로 상태가 변하게 된다.
  2. working directory 상태에서 파일을 Stage 해서 커밋할 스냅샷을 만든다. 모든 파일을 Stage 할 수도 있고, 선택하여 Stage 할 수도 있다.
  3. Staging Area에 있는 파일들을 커밋해서 Git 디렉토리에 영구적인 스냅샷으로 저장한다.

<br>

# Git 최초 설정

## 사용자 정보

사용자 이름과 이메일 주소를 설정하는 것이다. Git은 커밋할 때마다 이 정보를 사용한다. 한 번 커밋한 후에는 정보를 변경할 수 없다.

```shell
$ git config --global user.name "Sangming Lee"
$ git config --global user.email "nalsm0518@gmail.com"
```

<br>

## 설정 확인

```shell
$ git config --list
credential.helper=osxkeychain
user.email=nalsm98@naver.com
user.name=sangminLee
...
```

<br>

# Git 저장소 만들기

주로 다음 두 가지 중 한 가지 방법으로 Git 저장소를 쓴다.

1. 아직 버전관리를 하지 않는 로컬 디렉토리 하나를 선택해서 Git 저장소를 적용하는 방법

   (즉, Git을 사용하지 않았던 폴더에 Git을 적용하는 방법)

2. 다른 어딘가에서 Git 저장소를 Clone 하는 방법

<br>

## 기존 디렉토리를 Git 저장소로 만들기

1. 자신이 Git 저장소로 만들고 싶은 위치로 이동한다.

   >  Git 저장소로 만들고 싶은 디렉토리가 없다면 새로 하나 만들어주고, 파일을 하나 만들어준다.
   >
   > **$ mkdir git-tutorial** 
   >
   > **$ touch README.md**

   ```shell
   $ cd git-tutorial
   ```

2. git init 명령 실행

   ```sh
   $ git init
   ```

3. Git의 상태를 확인 후 저장소에 파일을 추가하고 커밋한다.

   ```sh
   $ git status
   $ git add README.md
   $ git status
   $ git commit -m "README file upload"
   $ git status
   ```

<br>

## 기존 저장소를 Clone 하기

다른 프로젝트에 참여하려거나 Git 저장소를 복사하고 싶을 때 `git clone` 명령을 사용한다.

`git clone <url>` 명령으로 저장소를 Clone 한다.

1. 자신이 원하는 git url로 들어간다. https://github.com/LeeSM0518/MVC

<img src="capture/스크린샷 2019-11-24 오후 11.33.17.png">

2. clone을 할 url을 복사한다.

   <img src="capture/스크린샷 2019-11-24 오후 11.35.29.png">

3. 터미널에서 clone을 실행한다.

   <img src="capture/스크린샷 2019-11-24 오후 11.39.13.png">

<br>

# 수정하고 저장소에 저장하기

워킹 디렉토리의 모든 파일은 크게 Tracked(관리대상)와 Untracked(관리대상이 아님)로 나눈다. Tracked 파일은 이미 스냅샷에 포함돼 있던 파일이다.

* **Tracked**
  * Unmodified(수정하지 않음)
  * Modified(수정함)
  * Staged(커밋으로 저장소에 기록함)

> Git이 알고 있는 파일들

처음 저장소를 Clone하면 모든 파일은 Tracked이면서 Unmodified 상태이다.

<br>

* **파일의 라이프사이클**

  <img src="https://git-scm.com/book/en/v2/images/lifecycle.png">

  * 파일을 수정하게 되면 **Modified** 상태로 인식
  * 실제로 커밋을 하기 위해서는 이 수정한 파일을 **Staged** 상태로 만든다.
  * **Staged** 상태의 파일을 커밋하여 **Unmodiied** 상태로 돌아온다.

<br>

## 파일의 상태 확인하기

파일의 상태를 확인하려면 `git status` 명령을 사용한다.

```sh
$ git status
On branch master
Your branch is up-to-date with 'origin/master'.
nothing to commit, working directory clean
```

<br>

project에 README 파일을 만들어보자.

```sh
$ touch README
```

그 후 다시 `git status` 를 실행하면 **'Untracked files'** 에 들어 있다.

```sh
$ git status
On branch master
Your branch is up-to-date with 'origin/master'.
Untracked files:
  (use "git add <file>..." to include in what will be committed)

    README

nothing added to commit but untracked files present (use "git add" to track)
```

<br>

## 파일을 새로 추적하기

`git add` 명령으로 파일을 새로 추적한다.

```sh
$ git add README
```

`git status` 명령을 다시 실행하면 `README` 파일이 Tracked 상태이면서 커밋에 추가될 Staged 상태로 바뀐다.

```sh
$ git status
On branch master
Your branch is up-to-date with 'origin/master'.
Changes to be committed:
  (use "git reset HEAD <file>..." to unstage)

    new file:   README
```

<br>

## 파일 무시하기

어떤 파일은 Git이 관리할 필요가 없다. 보통 로그 파일이나 빌드 시스템이 자동으로 생성한 파일이 그렇다. 그런 파일을 무시하려면 `.gitignore` 파일을 만들고 그 안에 무시할 파일 패턴을 적는다.

* **.gitignore 파일 예시**

  ```sh
  $ cat .gitignore
  *.[oa]
  ```

  * ***.[oa]** : ".o" 나 ".a" 인 파일을 Git이 무시하라는 것이다.
  * 주로 https://www.gitignore.io 를 이용한다.

<br>

## 변경사항 커밋하기

Git은 생성하거나 수정하고 나서 `git add` 명령으로 추가하지 않은 파일은 커밋하지 않는다. 그 파일은 여전히 Modified 상태로 남아 있다. 커밋하기 전에 `git status` 명령으로 모든 것이 Staged 상태인지 확인할 수 있다. 그 후에 `git commit` 을 실행하여 커밋한다.

```sh
$ git commit -m "file upload"
```

<br>

# 커밋 히스토리 조회하기

가끔 저장소의 히스토리를 보고 싶을 때가 있다. 이때 `git log` 명령어를 실행하면 된다.

```sh
$ git log
commit ca82a6dff817ec66f44342007202690a93763949
Author: Scott Chacon <schacon@gee-mail.com>
Date:   Mon Mar 17 21:52:11 2008 -0700

    changed the version number

commit 085bb3bcb608e1e8451d4b2432f8ecbe6306e7e7
Author: Scott Chacon <schacon@gee-mail.com>
Date:   Sat Mar 15 16:40:33 2008 -0700

    removed unnecessary test

commit a11bef06a3f659402fe7563abf99ad00de2209e6
Author: Scott Chacon <schacon@gee-mail.com>
Date:   Sat Mar 15 10:31:28 2008 -0700

    first commit
```

<br>

# 되돌리기

일을 하다보면 모든 단계에서 어떤 것은 되돌리고 싶을 때가 있다. Git을 사용하면 우리가 저지른 실수는 복구 될 수 있지만 되돌린 것은 복수할 수 없다.

<br>

## 파일 상태를 Unstage로 변경하기

파일을 두 개 수정하고서 따로따로 커밋하려고 했는데, 실수로 `git add *` 라고 실행하게 되었을 때 사용하는 명령어가 있다.

우선 `git status` 로 명령을 확인한다.

```sh
$ git add *
$ git status
On branch master
Changes to be committed:
  (use "git reset HEAD <file>..." to unstage)

    renamed:    README.md -> README
    modified:   CONTRIBUTING.md
```

`Changes to be commited` 밑에 `git reset HEAD <file>...` 메시지가 보인다. 이 명령으로 Unstaged 상태로 변경할 수 있다.

```sh
$ git reset HEAD CONTRIBUTING.md
Unstaged changes after reset:
M	CONTRIBUTING.md
$ git status
On branch master
Changes to be committed:
  (use "git reset HEAD <file>..." to unstage)

    renamed:    README.md -> README

Changes not staged for commit:
  (use "git add <file>..." to update what will be committed)
  (use "git checkout -- <file>..." to discard changes in working directory)

    modified:   CONTRIBUTING.md
```

<br>

## Modified 파일 되돌리기

`git checkout -- <filename>` 명령어를 통해 파일을 수정한 것을 다시 되돌릴 수 있도록 할 수 있다.

```sh
$ git status
Changes not staged for commit:
  (use "git add <file>..." to update what will be committed)
  (use "git checkout -- <file>..." to discard changes in working directory)

    modified:   CONTRIBUTING.md
$ git checkout -- CONTRIBUTING.md
$ git status
On branch master
Changes to be committed:
  (use "git reset HEAD <file>..." to unstage)

    renamed:    README.md -> README
```

<br>

# 리모트 저장소

리모트 저장소는 인터넷이나 네트워크 어딘가에 있는 저장소를 말한다.

간단히 말해서 다른 사람들과 함께 일한다는 것은 리모트 저장소를 관리하면서 데이터를 거기에 Push 하고 Pull 하는 것이다.

<br>

## 리모트 저장소 확인하기

`git remote -v` 명령으로 현재 프로젝트에 등록된 리모트 저장소를 단축이름과 URL을 함께 확인할 수 있다.

```sh
$ git remote -v
origin	https://github.com/schacon/ticgit (fetch)
origin	https://github.com/schacon/ticgit (push)
```

<br>

## 리모트 저장소 추가하기

기존 워킹 디렉토리에 새 리모트 저장소를 쉽게 추가할 수 있다. `git remote add <단축이름> <url>` 명령을 사용한다.

```sh
$ git remote add pb https://github.com/paulboone/ticgit
$ git remote -v
origin	https://github.com/schacon/ticgit (fetch)
origin	https://github.com/schacon/ticgit (push)
pb	https://github.com/paulboone/ticgit (fetch)
pb	https://github.com/paulboone/ticgit (push)
```

<br>

## 리모트 저장소를 Pull 하기

`git pull` 명령으로 리모트 저장소 브랜치에서 데이터를 가져올 뿐만 아니라 자동으로 로컬 브랜치와 Merge 시킬 수 있다. 또한 Clone 한 서버에서 데이터를 가져오고 그 데이터를 자동으로 현재 작업하는 코드와 Merge 시킨다.

```sh
$ git pull origin master
```

<br>

## 리모트 저장소에 Push 하기

프로젝트를 공유하고 싶을 때 Upstream 저장소에 Push 할 수 있다. `git push <리모트 저장소 이름> <브랜치 이름>`

```sh
$ git push origin master
```

* 이 명령은 Clone 한 리모트 저장소에 쓰기 권한이 있고, Clone 하고 난 이후 아무도 저장소에 Push 하지 않았을 때만 사용할 수 있다. 즉, 먼저 다른 사람이 Push 한 이력이 있으면 먼저 다른 사람이 작업한 것을 가져와서 Merge 한 후에 Push 할 수 있다.

<br>

# 브랜치란 무엇인가

개발을 하다 보면 코드를 여러 개로 복사해야 하는 일이 자주 생긴다. 코드를 통째로 복사하고 나서 원래 코드와는 상관없이 독립적으로 개발을 진행할 수 있는데, 이렇게 독립적으로 개발하는 것이 브랜치다.

<br>

## 새 브랜치 생성하기

`git branch` 명령으로 `testing` 브랜치를 만들어보자.

```sh
$ git branch testing
```

<img src="https://git-scm.com/book/en/v2/images/head-to-master.png">

> 새로 만든 브랜치도 지금 작업하고 있던 마지막 커밋을 가리킨다.

<br>

## 브랜치 이동하기

`git checkout` 명령으로 다른 브랜치로 이동할 수 있다.

```sh
$ git checkout testing
```

<img src="https://git-scm.com/book/en/v2/images/head-to-testing.png">

그 후 커밋을 새로 한 번 해보자.

```sh
$ vi test
// 아무 글을 쓰고 !wq
$ git commit -a -m "commit"
```

<img src="https://git-scm.com/book/en/v2/images/advance-testing.png">

그리고 다시 master로 돌아가서 파일을 만들고 커밋을 해보자.

```sh
$ git checkout master
$ vi test2
$ git commit -a -m "commit"
```

<img src="https://git-scm.com/book/en/v2/images/advance-master.png">

> 브랜치가 갈리게 된다.

<br>

# 브랜치와 Merge의 기초

## 브랜치의 기초

* **현재 커밋 히스토리**

  <img src="https://git-scm.com/book/en/v2/images/basic-branching-1.png">

이슈 관리 시스템에 등록된 53번 이슈를 처리한다고 가정한다면 이 이슈에 집중할 수 있는 브랜치를 새로 하나 만든다.

```sh
$ git checkout -b iss53				// 브랜치를 만들면서 Checkout까지 한 번에 가능
Switched to a new branch "iss53"
```

<img src="https://git-scm.com/book/en/v2/images/basic-branching-2.png">

`iss53` 브랜치에서 뭔가 일을 하고 커밋하면 `iss53` 브랜치는 앞으로 나아간다.

```sh
$ vim index.html
$ git commit -a -m 'added a new footer [issue 53]'
```

<img src="https://git-scm.com/book/en/v2/images/basic-branching-3.png">

만약 여기서 기존에 구현해놨던 것에 버그가 발생했다는 것을 알게 된다면, 다시 이전 상태인 `master` 로 돌아가서 버그를 먼저 해결해야 한다.

```sh
$ git checkout master
Switched to branch 'master'
```

그러면 `iss53` 상태에서 생성했던 파일들이나 디렉토리들이 전부 지워지고 이전의 디렉토리 상태인 `master` 로 재구성된다.

그러면 다시 새로운 문제에 집중할 수 있는 환경이 만들어지게 되고, 이 상태에서 hotfix 를 해결하기 위해 `hotfix` 브랜치를 만들고 버그를 해결한다.

```sh
$ git checkout -b hotfix
Switched to a new branch 'hotfix'
$ vim index.html
$ git commit -a -m 'fixed the broken email address'
[hotfix 1fb7853] fixed the broken email address
 1 file changed, 2 insertions(+)
```

<img src="https://git-scm.com/book/en/v2/images/basic-branching-4.png">



운영 환경에 적용하려면 문제를 제대로 고쳤는지 테스트하고 최종적으로 운영환경에 배포하기 위해 `hofix` 브랜치를 `master` 브랜츠에 합쳐야한다. 이때 `git merge` 를 사용한다.

```sh
$ git checkout master							// master 상태로 다시 돌아간 뒤
$ git merge hotfix								// hofix 브랜치랑 master 브랜치랑 합친다.
Updating f42c576..3a0874c
Fast-forward
 index.html | 2 ++
 1 file changed, 2 insertions(+)
```

<img src="https://git-scm.com/book/en/v2/images/basic-branching-5.png">

그 후 `hofix` 브랜치는 `master` 브랜치와 같은 스냅샷이기 때문에 `hofix` 브랜치를 삭제한다.

```sh
$ git branch -d hotfix
Deleted branch hotfix (3a0874c).
```

이제 다시 이슈 53번을 처리하던 환경으로 되돌아가서 하던 일을 계속한다.

```sh
$ git checkout iss53
Switched to branch "iss53"
$ vim index.html
$ git commit -a -m 'finished the new footer [issue 53]'
[iss53 ad82d7a] finished the new footer [issue 53]
1 file changed, 1 insertion(+)
```

<img src="https://git-scm.com/book/en/v2/images/basic-branching-6.png">

<br>

## Merge 의 기초

53번 이슈를 다 구현하고 master 브랜치에서 Merge 하는 과정을 살펴보자. `iss53` 브랜치를 `master` 브랜치에 Merge 하는 것은 앞서 살펴본 `hotfix` 브랜치를 Merge 하는 것과 비슷하다. `git merge` 명령으로 합칠 브랜치에서 합쳐질 브랜치를 Merge 하면 된다.

```sh
$ git checkout master
Switched to branch 'master'
$ git merge iss53
Merge made by the 'recursive' strategy.
index.html |    1 +
1 file changed, 1 insertion(+)
```

<img src="https://git-scm.com/book/en/v2/images/basic-merging-2.png">

iss53 브랜치를 master에 Merge 하고 나면 더는 iss53 브랜치가 필요 없다. 그러므로 브랜치를 삭제하고 이슈의 상태를 처리 완료로 표시한다.

```sh
$ git branch -d iss53
```

<br>

## 충돌의 기초

Merge 하는 두 브랜치에서 같은 파일의 한 부분을 동시에 수정하고 Merge 하면 Git은 해당 부분을 Merge 하지 못한다. 예를 들어, 53번 이슈와 `hotfix` 가 같은 부분을 수정했다면 Git은 Merge 하지 못하고 아래와 같은 충돌(Conflict) 메시지를 출력한다.

```sh
$ git merge iss53
Auto-merging index.html
CONFLICT (content): Merge conflict in index.html
Automatic merge failed; fix conflicts and then commit the result.
```

Git은 자동으로 Merge 하지 못해서 새 커밋이 생기지 않는다. 변경사항의 충돌을 개발자가 해결하지 않는 한 Merge 과정을 진행할 수 없다. Merge 충돌이 일어났을 때 Git이 어떤 파일을 Merge 할 수 없었는지 살펴보려면 `git status` 명령을 이용한다.

```sh
$ git status
On branch master
You have unmerged paths.
  (fix conflicts and run "git commit")

Unmerged paths:
  (use "git add <file>..." to mark resolution)

    both modified:      index.html

no changes added to commit (use "git add" and/or "git commit -a")
```

충돌이 일어난 파일은 unmerged 상태로 표시된다. Git은 충돌이 난 부분을 표준 형식에 따라 표시해준다. 그러면 개발자는 해당 부분을 수동으로 해결한다. 충돌 난 부분은 아래와 같이 표시된다.

```sh
<<<<<<< HEAD:index.html
<div id="footer">contact : email.support@github.com</div>
=======
<div id="footer">
 please contact us at support@github.com
</div>
>>>>>>> iss53:index.html
```

`=======` 위쪽의 내용은 `HEAD` 버전(merge 명령을 실행할 때 작업하던 `master` 브랜치)의 내용이고 아래쪽은 `iss53` 브랜치의 내용이다. 충돌을 해결하려면 위쪽이나 아래쪽 내용 중에서 고르거나 새로 작성하여 Merge 한다. 아래는 아예 새로 작성하여 충돌을 해결하는 예제다.

```html
<div id="footer">
please contact us at email.support@github.com
</div>
```

충돌한 양쪽에서 조금씩 가져와서 새로 수정했다. 그리고 `<<<<<<<`, `=======`, `>>>>>>>`가 포함된 행을 삭제했다. 이렇게 충돌한 부분을 해결하고 `git add` 명령으로 다시 Git에 저장한다.

